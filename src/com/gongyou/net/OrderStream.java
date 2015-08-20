package com.gongyou.net;

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
	public RequestParams baseCode(EntityCallback callback,String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		return params;
	}
	
}
