package com.gongyou.base;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.gongyou.domain.MessageBean;
import com.gongyou.global.NetContacts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 
 * @author Administrator
 * 
 */
public class BaseNet {
	protected HttpUtils httpUtils;
	protected Gson gson;

	public BaseNet() {
		httpUtils = new HttpUtils();
		// 这里先用gson进行数据的请求 公司jsonobject map集合也不错
		// http://blog.sina.com.cn/s/blog_7ffb8dd501013q9c.html
		gson = new Gson();
	}

	// ============================================================这里是返回结果为空时回掉===================================================
	public class EntityrResult {
		public EntityrResult(EntityType entityType) {
			this.entityType = entityType;
		}

		public EntityrResult(boolean error, String message,
				EntityType entityType) {
			this.error = error;
			this.message = message;
			this.entityType = entityType;
		}

		/**
		 * 返回值的boolean 基本没用
		 */
		boolean error;

		/**
		 * 这是请求返回的信息
		 */
		String message;

		/**
		 * 枚举：空返回值的类型
		 */
		EntityType entityType;
	}

	/**
	 * 回返3种类型 那这里就用枚举吧
	 * 
	 * @author Administrator 空的返回类型 无参回调返回
	 */
	public enum EntityType {
		connectFailure, messagetrue, messagefalse
	}

	public interface EntityCallback {
		// 进行连接状态的回调
		void connectCallback(EntityrResult entityrResult);
	}

	/**
	 * TODO:基方法 扩展 也可以切面编程 无大用 再写第三个类时添加 每次返回一个新的请求参数 也可以在里边加值
	 * 
	 * @return
	 */
	public RequestParams getParams() {
		return new RequestParams();
	}

	/**
	 * 空返回值空参数的基本方法
	 * 
	 * @param url
	 * @param callback
	 */
	public void entity(String url, final EntityCallback callback) {
		entity(null, url, callback);
	}

	/**
	 * 空的返回值基本方法 只是中转为了加cookie
	 * 
	 * @param params
	 * @param url
	 * @param callback
	 */
	public void entity(RequestParams params, String url,
			final EntityCallback callback) {

		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader("Cookie", NetContacts.COOKIE);
		entityJSESSIONID(params, url, callback);
	}

	/**
	 * 这是空返回值的基本方法
	 * 
	 * @param url
	 * @param callback
	 */
	private void entityJSESSIONID(RequestParams params, String url,
			final EntityCallback callback) {

		httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.USER_LOGOUT, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						callback.connectCallback(new EntityrResult(
								EntityType.connectFailure));

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
							NetContacts.COOKIE = cookie;
						}
						EntityrResult entityBean = gson.fromJson(arg0.result,
								EntityrResult.class);

						if (entityBean.error == false) {
							entityBean.entityType = EntityType.messagetrue;
							callback.connectCallback(entityBean);
							return;
						}

						entityBean.entityType = EntityType.messagefalse;
						callback.connectCallback(entityBean);
					}

				});
	}

	// =======================这里是返回一个对象时回调====================================

	/**
	 * 基本接口
	 * 
	 * @author Administrator
	 * 
	 * @param <T>
	 */
	public interface BaseCallback<T> {
		/**
		 * 当登录成功时回调
		 * 
		 * @param bean
		 */
		void messageSuccess(T bean);

		/**
		 * 当链接成功但是返回消息失败的时候回调失败时回调
		 * 
		 * @param backError
		 *            返回错误信息状态
		 */
		void messageFailure(MessageBean backError);

		/**
		 * 当连接失败时回调
		 * 
		 * @param messageBean
		 */
		void connectFailure(MessageBean messageBean);
	}

	public <T> void baseMethod(String url, final BaseCallback<T> baseCallback,
			final Class<T> t) {
		baseMethod(null, url, baseCallback, t);
	}

	/**
	 * 这是请求的基本方法 可不可以继续优化
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	public <T> void baseMethod(RequestParams params, String url,
			final BaseCallback<T> baseCallback, final Class<T> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader("Cookie", NetContacts.COOKIE);
		baseMethodJSESSIONID(params, url, baseCallback, bean);
	}

	/**
	 * 这是请求的基本方法 可不可以继续优化
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	public <T> void baseMethodJSESSIONID(RequestParams params, String url,
			final BaseCallback<T> baseCallback, final Class<T> bean) {

		// 设置超时时间 进行数据请求
		httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						baseCallback.connectFailure(new MessageBean(arg0
								.getExceptionCode(), arg1));
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
							NetContacts.COOKIE = cookie;
						}

						String successInfo = arg0.result;

//						if (isBackError(baseCallback, successInfo)) {
//							return;
//						}
System.out.println("ggggggggggggggggggggg"+successInfo);
						T fromJson = (T) gson.fromJson(successInfo, bean);

						baseCallback.messageSuccess(fromJson);

					}

				});
	}

	// ==============================Gson转化有泛型的list04-06=====================================

	/**
	 * 判断链接信息是否错误的方法
	 * 
	 * @param <T>
	 * @param baseCallback
	 * @param successInfo
	 * @return 当不报异常时返回 MessageBean 否则返回null
	 */
	private <T> Boolean isBackError(BaseCallback<T> baseCallback,
			String successInfo) {
		/**
		 * 8月16日上午修改 为了提高效率 判断是否成功 判断信息是否为错误信息 若是那么 就进行错误的回调
		 */
		MessageBean backError = null;

		try {
			JSONObject jsonObject = new JSONObject(successInfo);
			boolean error = jsonObject.getBoolean("error");
			if (error) {
				backError = gson.fromJson(successInfo, MessageBean.class);
				baseCallback.messageFailure(backError);
				return error;
			}
		} catch (JSONException e) {
			e.printStackTrace();

		}
		return false;
	}

	public <T> void baseListMethod(String url,
			final BaseCallback<List<T>> baseCallback, final Class<T> bean) {
		baseListMethod(null, url, baseCallback, bean);
	}

	/**
	 * json转化有泛型的list集合
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	public <T> void baseListMethod(RequestParams params, String url,
			final BaseCallback<List<T>> baseCallback, final Class<T> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader("Cookie", NetContacts.COOKIE);
		baseListMethodJSESSIONID(params, url, baseCallback);
	}

	/**
	 * json转化有泛型的list集合
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	private <T> void baseListMethodJSESSIONID(RequestParams params, String url,
			final BaseCallback<List<T>> baseCallback) {

		// 设置超时时间 进行数据请求
		httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						baseCallback.connectFailure(new MessageBean(arg0
								.getExceptionCode(), arg1));
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						String successInfo = arg0.result;

						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
							NetContacts.COOKIE = cookie;
						}
						
						if (isBackError(baseCallback, successInfo)) {
							return;
						}
						
						List<T> list = gson.fromJson(successInfo,
								new TypeToken<List<T>>() {
								}.getType());

						baseCallback.messageSuccess(list);
					}
				});
	}

}
