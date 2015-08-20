package com.gongyou.net;

import java.util.List;

import com.gongyou.base.BaseNet;
import com.gongyou.base.BaseNet.BaseCallback;
import com.gongyou.domain.CategoriesBean;
import com.gongyou.domain.FindByCategoryNoBean;
import com.gongyou.domain.FindByEquipmentNoBean;
import com.gongyou.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

/**
 * 08 设备管理
 * @author peng
 *
 */
public class DeviceManager extends BaseNet {

	public void  categories(BaseCallback<List<CategoriesBean>> baseCallback){
		baseListMethod(null, NetContacts.CATEGORIES, baseCallback, CategoriesBean.class);
	}
	
	public void findByCategoryNo(BaseCallback<FindByCategoryNoBean> baseCallback,String categoryNo ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("categoryNo", categoryNo);
		baseMethod(params, NetContacts.FINDBYCATEGORYNO, baseCallback, FindByCategoryNoBean.class);
	}
	/**
	 * 通过设备编号查询设备
	 * @param baseCallback
	 * @param equipmentNo
	 */
	public void findByEquipmentNo(BaseCallback<FindByEquipmentNoBean> baseCallback ,String equipmentNo ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		baseMethod(params, NetContacts.FINDBYEQUIPMENTNO, baseCallback, FindByEquipmentNoBean.class);
	}
	
}
