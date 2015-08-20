package com.gongyou.net;

import java.io.File;
import java.util.List;

import com.gongyou.base.BaseNet;
import com.gongyou.domain.CategoriesBean;
import com.gongyou.domain.ServicesBean;
import com.gongyou.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

/**
 * 接口0707 服务请求
 * @author peng
 *
 */
public class ServiceRequest extends BaseNet {

	// TODO: 方法没有经过验证
	/**
	 *	
	 * 创建用户请求服务(相当于创建工单) 
	 * @param description
	 * @param expectedFixTime
	 * @param serviceFiles
	 */
	public void createService(String description,String expectedFixTime,File[] serviceFiles,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("description", description);
		params.addBodyParameter("expectedFixTime", expectedFixTime);
		params.addBodyParameter("serviceFiles", serviceFiles[0]);
		for (int i = 0; i < serviceFiles.length; i++) {
			params.addBodyParameter("serviceFiles",serviceFiles[i],"image/png");			
		}
		entity(params, NetContacts.CREATESERVICE, callback);
	}
	
	/**
	 * 查询服务请求列表
	 * @param baseCallback
	 */
	public void services(BaseCallback<ServicesBean> baseCallback){
		baseMethod( NetContacts.SERVICES, baseCallback, ServicesBean.class);
	}
	

	/**
	 * 按ID查询服务请求
	 * @param serviceRequestId  id
	 * @param baseCallback		服务对象返回这里是复用了上一个列表
	 */
	public void findById(String serviceRequestId,BaseCallback<com.gongyou.domain.ServicesBean.Records> baseCallback){
		baseMethod( NetContacts.FINDBYID, baseCallback, com.gongyou.domain.ServicesBean.Records.class);
	}
	
}
