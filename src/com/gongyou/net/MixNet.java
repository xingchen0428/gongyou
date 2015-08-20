package com.gongyou.net;

import java.util.List;

import com.gongyou.base.BaseNet;
import com.gongyou.domain.ActionsBean;
import com.gongyou.domain.StaffListBean;
import com.gongyou.domain.TeamListBean;
import com.gongyou.global.NetContacts;

/**
 * 这个类是接口文档04-06工单
 * @author peng
 *
 */
public class MixNet extends BaseNet{
	/**
	 * 这是获得工人列表的方法	04
	 * @param baseCallback
	 */
	public void staffList(BaseCallback<List<StaffListBean>> baseCallback){
		baseListMethod(null, NetContacts.STAFFLIST, baseCallback, StaffListBean.class);
	}
	
	public void teamList(BaseCallback<List<TeamListBean>> baseCallback){
		baseListMethod(null, NetContacts.TEAMLIST, baseCallback, TeamListBean.class);
	}
	
	/**
	 * 查询当前状态能执行的工作流操作
	 * @param baseCallback
	 */
	public void actions(BaseCallback<List<ActionsBean>> baseCallback){
		baseListMethod(null, NetContacts.TEAMLIST, baseCallback, ActionsBean.class);
	}
	
	
}
