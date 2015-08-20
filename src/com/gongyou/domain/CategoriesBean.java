package com.gongyou.domain;

import java.lang.reflect.Field;

import com.gongyou.base.BaseBean;

public class CategoriesBean extends BaseBean {

	public String amount;
	public String icon;
	public String name;
	public String categoryNo;

	@Override
	public String toString() {
		String s = "";
		Field[] arr = this.getClass().getFields();
		for (Field f : getClass().getFields()) {
			try {
				s += f.getName() + "=" + f.get(this) + "\n,";
			} catch (Exception e) {
			}
		}
		return getClass().getSimpleName() + "["
				+ (arr.length == 0 ? s : s.substring(0, s.length() - 1)) + "]";
	}

}
