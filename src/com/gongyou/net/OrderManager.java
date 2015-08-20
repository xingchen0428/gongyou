package com.gongyou.net;

import java.io.File;

import com.gongyou.base.BaseNet;
import com.gongyou.domain.CasesBean;
import com.gongyou.domain.DataBean;
import com.gongyou.domain.MessageBean;
import com.gongyou.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;


public class OrderManager extends BaseNet{

	
	/**
	 * 在网页上是不需要参数但是要jessionid
	 * 这里明确了泛型的类型
	 */
	public void createdCases(BaseCallback<CasesBean> baseCallback){
	
		baseMethod( NetContacts.CREATEDCASES, baseCallback,CasesBean.class);
	}
	
	
	public void assginGroupCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.ASSGINGROUPCASES, baseCallback, CasesBean.class);
		
	}
	
	/**
	 * 查询派发给工人的工单
	 * @param baseCallback
	 */
	public void assginStaffCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.ASSGINSTAFFCASES, baseCallback, CasesBean.class);
		
	}
	
	/**
	 * 查询已接受的工单
	 * @param baseCallback
	 */
	public void acceptCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.ACCEPTCASES, baseCallback, CasesBean.class);
	}
	
	public void rejectCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.REJECTCASES, baseCallback, CasesBean.class);
	}
	
	public void fixedCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.FIXEDCASES, baseCallback, CasesBean.class);
	}
	
	public void cases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.CASES, baseCallback, CasesBean.class);
	}
	
	public void myCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.MYCASES, baseCallback, CasesBean.class);
	}
	
	public void teamCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.TEAMCASES, baseCallback, CasesBean.class);
	}
	
	//TODO:11 完成bean 
	public void data(String code,BaseCallback<DataBean> baseCallback){
		baseMethod(NetContacts.DATA, baseCallback, DataBean.class);
	}
	
	public void uploadArrivePicture(EntityCallback callback ,String caseCode ,String description,File picture ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("description", description);
		params.addBodyParameter("picFile", picture, "image/png"); 
		entity(params, NetContacts.UPLOADARRIVEPICTURE, callback);
	}
	
	//创建客户工单并派发给工人
	/**
	 * 
	 * @param baseCallback    回掉方法
	 * @param serviceRequestId 服务请求编号
	 * @param staffCode	维修组
	 * @param priority	优先级(0低、1中、2高)
	 */
	public void createCustomerAndAssignStaff(BaseCallback<MessageBean> baseCallback,String serviceRequestId ,String staffCode ,String priority ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId);
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("priority", priority);
		baseMethod(params, NetContacts.CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
	}
	
	//创建设备工单并派发给工人
	/**
	 * 创建设备工单并派发给工人
	 * @param baseCallback
	 * @param equipmentNo	设备编号
	 * @param staffCode	维修员工
	 * @param expectedFixTime	期望完成时间
	 * @param priority	 优先级(0低、1中、2高)
	 * @param description	 描述
	 */
    public  void createEquipmentAndAssignStaff(BaseCallback<MessageBean> baseCallback,String equipmentNo,String staffCode,String expectedFixTime ,String priority,String description){ 
    	RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("expectedFixTime", expectedFixTime);
		params.addBodyParameter("priority", priority);
		params.addBodyParameter("description", description);
		baseMethod(params, NetContacts.CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
    }
	
    /**
     *  
     *  参数同上
     * @param baseCallback
     * @param equipmentNo
     * @param teamId	 维修组
     * @param expectedFixTime
     * @param priority
     * @param description
     */
    public void createEquipmentAndAssignGroup(BaseCallback<MessageBean> baseCallback,String equipmentNo,String teamId,String expectedFixTime ,String priority,String description){ 
    	RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		params.addBodyParameter("teamId", teamId);
		params.addBodyParameter("expectedFixTime", expectedFixTime);
		params.addBodyParameter("priority", priority);
		params.addBodyParameter("description", description);
		baseMethod(params, NetContacts.CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
    }
   

    /**
     * 添加，删除工单伙伴
     * @param caseCode	工单编号
     * @param staffCode 工单伙伴员工编号
     */
    public void basePartner(String url,EntityCallback callback,String caseCode,String staffCode){
    	RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("staffCode", staffCode);
		entity(params, url, callback);
    }
    
    public void addPartner(EntityCallback callback,String caseCode,String staffCode){
    	basePartner(NetContacts.ADDPARTNER, callback, caseCode, staffCode);
    }
    
    public void deletePartner(EntityCallback callback,String caseCode,String staffCode){
    	basePartner(NetContacts.DELETEPARTNER, callback, caseCode, staffCode);
    }
    
}
