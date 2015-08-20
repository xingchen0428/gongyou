package com.gongyou.base;

import java.util.ArrayList;
import java.util.HashMap;

import com.gongyou.R;
import com.gongyou.domain.WorkInfoBean;
import com.gongyou.utils.StringUtil;

public class BaseResource {

	private static HashMap<String, Integer> stateMap;
	private ArrayList<WorkInfoBean> gridList;
	private ArrayList<WorkInfoBean> menuList;

	public static HashMap<String, Integer> getResourceState() {
		if (stateMap == null) {
			stateMap = new HashMap<String, Integer>();
		}

		stateMap.put("已完成", R.drawable.state_finish);
		stateMap.put("派给工人", R.drawable.state_allot);
		stateMap.put("已关闭", R.drawable.state_delete);
		stateMap.put("已维修", R.drawable.finish_work);
		stateMap.put("派给组", R.drawable.state_group);
		stateMap.put("已到达", R.drawable.state_allot);
		stateMap.put("已拒绝", R.drawable.user_request_fix);

		return stateMap;
	}

	public ArrayList<WorkInfoBean> getResourceGrid(String type) {
		if (gridList == null) {
			gridList = new ArrayList<WorkInfoBean>();
		}
		gridList.clear();
		int t = StringUtil.str2Int(type);
		switch (t) {
		case 0:
			addInfoUser();
			break;
		case 1:
			addInfoWorker();
			break;
		case 2:
			addInfoLeader();
			break;
		case 3:
			addInfoService();
			break;
		case 4:
			addInfoManager();
			break;
		case 5:
			addInfoBoss();
			break;
		}
		return gridList;
	}

	public ArrayList<WorkInfoBean> getResourceMenu(String type) {
		if (menuList == null) {
			menuList = new ArrayList<WorkInfoBean>();
		}
		menuList.clear();
		int t = StringUtil.str2Int(type);
		switch (t) {
		case 1:
			addMenuWorker();
			break;
		case 2:
			addMenuGroup();
			break;
		case 3:
			addMenuService();
			break;
		case 4:
			addMenuLeader();
			break;
		}

		return menuList;
	}

	private void addMenuWorker() {
		menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "我的工单"));
		menuList.add(new WorkInfoBean(R.drawable.group_work, "组的工单"));
		menuList.add(new WorkInfoBean(R.drawable.accept_work, "已接受工单"));
		menuList.add(new WorkInfoBean(R.drawable.finish_work, "已维修工单"));
		menuList.add(new WorkInfoBean(R.drawable.pre_protext, "预防性维保"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_warg, "设备报警"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
	}

	private void addMenuGroup() {
		menuList.add(new WorkInfoBean(R.drawable.group_work, "派给组的工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "派给工人的工单"));
		menuList.add(new WorkInfoBean(R.drawable.accept_work, "已接受工单"));
		menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "已拒绝工单"));
		menuList.add(new WorkInfoBean(R.drawable.pre_protext, "预防性维保"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_warg, "设备报警"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
	}

	private void addMenuService() {
		menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "客户报修"));
		menuList.add(new WorkInfoBean(R.drawable.group_work, "已派发工单"));
		menuList.add(new WorkInfoBean(R.drawable.finish_work, "已维修工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_service_finish, "已评价工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
	}

	private void addMenuLeader() {
		menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "客户报修"));
		menuList.add(new WorkInfoBean(R.drawable.group_work, "已派发工单"));
		menuList.add(new WorkInfoBean(R.drawable.accept_work, "已接受工单"));
		menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "已拒绝工单"));
		menuList.add(new WorkInfoBean(R.drawable.pre_protext, "预防性维保"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_warg, "设备报警"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
		menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
	}

	private void addInfoUser() {
		gridList.add(new WorkInfoBean(R.drawable.home_user_request, "日常报修"));
		gridList.add(new WorkInfoBean(R.drawable.home_user_state, "维修状态"));
		gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "维修评价"));
		gridList.add(new WorkInfoBean(R.drawable.home_user_notify, "楼宇通知"));
	}

	private void addInfoWorker() {
		gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "我的工单"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "组的工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "已接受工单"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_service, "已维修工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防性维保"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
	}

	private void addInfoLeader() {
		gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "派给组工单"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "派给工人工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "已接受工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_group_none, "已拒绝工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防性维保"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
	}

	private void addInfoService() {
		gridList.add(new WorkInfoBean(R.drawable.user_function_request, "客户报修"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "已派发工单"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_service, "已维修工单"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_comment, "已评价工单"));
	}

	private void addInfoManager() {
		gridList.add(new WorkInfoBean(R.drawable.user_function_request, "客户报修"));
		gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "已派发工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "已接受工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_group_none, "已拒绝工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防性维保"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
	}

	private void addInfoBoss() {
		gridList.add(new WorkInfoBean(R.drawable.home_boss_workinfo, "工单"));
		gridList.add(new WorkInfoBean(R.drawable.home_boss_nengyuan, "能源管理"));
		gridList.add(new WorkInfoBean(R.drawable.home_boss_kongqi, "空气质量"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "设备维护"));
		gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "社区"));
		gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
	}
}
