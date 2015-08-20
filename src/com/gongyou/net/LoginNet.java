package com.gongyou.net;

import java.io.File;


import org.apache.http.Header;

import android.util.Log;

import com.gongyou.base.BaseNet;
import com.gongyou.domain.LoginBean;
import com.gongyou.global.NetContacts;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 用UserManager替代
 * @author peng
 *
 */
@Deprecated
public class LoginNet extends BaseNet{


	/**
	 * 这是进行登录的方法 由网页登录可以看出为post方式
	 * 
	 * @param staffCode
	 *            用户名
	 * @param password
	 *            密码 参考：http://www.open-open.com/lib/view/open1369637365753.html
	 *            http://blog.csdn.net/shineflowers/article/details/41309667
	 */
	public void userLoginIn(final String staffCode, String password,
			final LoginCallback callback) {

		// Utils.setRegisterId(Login_Activity.this, pushId);

		// post请求参数 拼接
		RequestParams params = new RequestParams();
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("password", password);

		// 设置超时时间 进行数据请求
		httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.USER_LOGIN, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Log.i("ggggggggghttpfilemessage", arg1);

						Log.i("ggggggggghttpseeor", arg0.toString());
						callback.connectFailure();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						Log.i("gggggggggggTAG", arg0.toString());

						String successInfo = arg0.result;
						
						
						Header header = arg0.getFirstHeader("Set-Cookie");
						String cookie = header.getValue();
						NetContacts.COOKIE = cookie;
						
						System.out.println("ggggggggglogincookie"+NetContacts.COOKIE);
						

						LoginBean fromJson = gson.fromJson(successInfo,
								LoginBean.class);

						System.out.println("gggggggggggggsuccessInfo"
								+ successInfo);
						if (fromJson.staffCode == null) {
							callback.loginFailure(successInfo);
							
						}

						callback.loginSuccess(fromJson);
						

					}

				});
	}

	public interface LoginCallback {
		// 当登录成功时回调
		void loginSuccess(LoginBean loginBean);

		// 当登录失败时回调
		void loginFailure(String successInfo);

		// 当连接失败时回调
		void connectFailure();
	}
	
	/**
	 * 这是登出的接口
	 * 
	 * @param callback
	 */
	public void userLogout(final EntityCallback callback) {
		entity(NetContacts.USER_LOGOUT, callback);
	}

	/**
	 * 心跳连接
	 * 
	 * @param callback
	 */
	public void heartbeat(final EntityCallback callback) {
		entity(NetContacts.HEARTBEAT, callback);
	}

	/**
	 * 获取用户登录信息
	 * 
	 * @param code
	 * @param name
	 */
	public void staffInfo(Integer code, String name, final LoginCallback callback) {

		// Utils.setRegisterId(Login_Activity.this, pushId);

		// post请求参数 拼接
		RequestParams params = new RequestParams();
		params.addBodyParameter("code", String.valueOf(code));
		params.addBodyParameter("name", name);

		// 设置超时时间 进行数据请求
		httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.USER_LOGIN, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Log.i("ggggggggghttpfilemessage", arg1);

						Log.i("ggggggggghttpseeor", arg0.toString());
						callback.connectFailure();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						Log.i("gggggggggggTAG", arg0.toString());

						String successInfo = arg0.result;

						LoginBean fromJson = gson.fromJson(successInfo,
								LoginBean.class);

						System.out.println("gggggggggggggsuccessInfo"
								+ successInfo);
						if (fromJson.staffCode == null) {
							callback.loginFailure(successInfo);
						}

						callback.loginSuccess(fromJson);

					}

				});
	}

	
	public void staffInfo(int code, final LoginCallback callback) {
		staffInfo(code, "", callback);
	}

	public void staffInfo(String name, final LoginCallback callback) {
		staffInfo(null, name, callback);
	}
	
	//这里是封装的基本方法  与基本 接口
	//=======================================================

//	public interface BaseCallback<T> {
//		// 当登录成功时回调
//		void messageSuccess(T t);
//
//		// 当登录失败时回调
//		void messageFailure(String successInfo);
//
//		// 当连接失败时回调
//		void connectFailure();
//	}
	
	
//	public interface BaseCallback {
//		// 当登录成功时回调
//		void messageSuccess(Object object);
//
//		// 当登录失败时回调
//		void messageFailure(String successInfo);
//
//		// 当连接失败时回调
//		void connectFailure();
//	}
	
	
	
//	public  <T> void baseMethod(RequestParams params,String url,final BaseCallback baseCallback,final Object t){
//
//		// 设置超时时间 进行数据请求
//		httpUtils.configTimeout(3000);
//
//		// 进行数据请求
//		httpUtils.send(HttpMethod.POST,url, params,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						baseCallback.connectFailure();
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> arg0) {
//
//
//						String successInfo = arg0.result;
//						Object fromJson = (Object) gson.fromJson(successInfo,t.getClass());
//						
////						T t = gson.fromJson(successInfo,
////								T.class);
//
//						if (fromJson.staffCode == null) {
//							baseCallback.messageFailure(successInfo);
//						}
//
//						baseCallback.messageSuccess(fromJson);
//
//					}
//
//				});
//	}
	
	
	
	
	
	//========================================================
	
	/**
	 * 这是注册时的方法为
	 * @param callback
	 * @param staffCode
	 * @param password
	 * @param name
	 * @param sex
	 * @param phone
	 */
	public void register(EntityCallback callback,String staffCode ,String password,String name,int sex,String phone){
		// post请求参数 拼接
		RequestParams params = new RequestParams();
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("password", password);
		params.addBodyParameter("name", name);
		params.addBodyParameter("sex", String.valueOf(sex));
		params.addBodyParameter("phone", phone);
		
		entity(params, NetContacts.REGISTER, callback);
	}
	
	
	public void resetPassword(EntityCallback callback,String oldPassword,String newPassword){
		RequestParams params = new RequestParams();
		params.addBodyParameter("oldPassword", oldPassword);
		params.addBodyParameter("newPassword", newPassword);
		entity(NetContacts.RESETPASSWORD, callback);
	}
	
	
	//TODO:进行文件的上传	参数是图片文件   返回的路径用加吗？
	public void uploadPhoto(EntityCallback callback,File imageFile){
		RequestParams params = new RequestParams();
		params.addBodyParameter("picFile", imageFile, "image/png"); 
		entity(NetContacts.UPLOADPHOTO, callback);
	}

}
