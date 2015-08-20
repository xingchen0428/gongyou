package com.gongyou.net;

import java.io.File;

import com.gongyou.base.BaseNet;
import com.gongyou.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

/**
 * 工单工作流管理接口
 * @author peng
String caseCode
RequestParams params = new RequestParams();
params.addBodyParameter("", file)
 */
public class OrderStream extends BaseNet{

	public void assign(EntityCallback callback,String caseCode,String fixStaffCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("fixStaffCode", fixStaffCode);
		entity(params, NetContacts.ASSIGN, callback);
	}
	
	public void assignTeam(EntityCallback callback,String caseCode,String fixTeamId ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("fixTeamId", fixTeamId);
		entity(params, NetContacts.ASSIGNTEAM, callback);
	}
	
	public void vie(EntityCallback callback,String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		entity(params, NetContacts.VIE, callback);
	}
	
	public void forward(EntityCallback callback,String caseCode,String fixStaffCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("fixStaffCode", fixStaffCode);
		entity(params, NetContacts.FORWARD, callback);
	}
	
	/**
	 * 因为工单编号 的请求太多就写了一个基方法
	 * @param callback 空的回掉方法
	 * @param caseCode 工单编号
	 * @return	请求参数
	 */
	public RequestParams baseCodeFirst(String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		return params;
	}
	
	/**
	 * 接受工单
	 * @param callback
	 * @param caseCode
	 */
	public void accept(EntityCallback callback,String caseCode){
		entity(baseCodeFirst(caseCode), NetContacts.ACCEPT, callback);
	}
	
	public void reject(EntityCallback callback,String caseCode){
		entity(baseCodeFirst(caseCode), NetContacts.REJECT, callback);
	}
	
	public void arrive(EntityCallback callback,String caseCode){
		entity(baseCodeFirst(caseCode), NetContacts.ARRIVE, callback);
	}
	
	/**
	 *  确认工单已被修理
	 * @param callback
	 * @param caseCode
	 * @param signature 签名图片文件
	 */
	public void fix(EntityCallback callback,String caseCode,File signature ){
		RequestParams params = baseCodeFirst(caseCode);
		params.addBodyParameter("signature", signature, "image/png");
		entity(baseCodeFirst(caseCode), NetContacts.FIX, callback);
	}
	
	//客户评价工单
	public void evaluate(EntityCallback callback,String caseCode,String evaluateRate ,String evaluateContent){
		RequestParams params = baseCodeFirst(caseCode);
		params.addBodyParameter("evaluateRate", evaluateRate);
		params.addBodyParameter("evaluateContent", evaluateContent);
		entity(baseCodeFirst(caseCode), NetContacts.EVALUATE, callback);
	}
	
	public void close(EntityCallback callback,String caseCode,String description  ){
		RequestParams params = baseCodeFirst(caseCode);
		params.addBodyParameter("description", description);
		entity(baseCodeFirst(caseCode), NetContacts.CLOSE, callback);
	}
}
