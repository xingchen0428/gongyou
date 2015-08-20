package com.gongyou.net;

import com.gongyou.base.BaseNet;
import com.gongyou.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

public class Test extends BaseNet{


	public void push(EntityCallback callback,String tag,String caseCode,String staffCode){
		RequestParams params = new RequestParams();

		params.addBodyParameter("tag", tag);
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("staffCode", staffCode);
		
		entity(params, NetContacts.PUSH, callback);
	}
}
