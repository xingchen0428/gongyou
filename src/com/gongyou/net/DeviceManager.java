package com.gongyou.net;

import java.util.List;

import com.gongyou.base.BaseNet;
import com.gongyou.base.BaseNet.BaseCallback;
import com.gongyou.domain.CategoriesBean;
import com.gongyou.global.NetContacts;

/**
 * 08 设备管理
 * @author peng
 *
 */
public class DeviceManager extends BaseNet {

	public void  categories(BaseCallback<List<CategoriesBean>> baseCallback){
		baseListMethod(null, NetContacts.CATEGORIES, baseCallback, CategoriesBean.class);
	}
}
