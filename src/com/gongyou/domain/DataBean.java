package com.gongyou.domain;

import java.lang.reflect.Field;
import java.util.List;

import com.gongyou.base.BaseBean;

public class DataBean extends BaseBean{

	public String evaluateContent;
	public String customerName;
	public String requestTime;
	public String evaluateRateDisplay;
	public String status;
	public String typeDisplay;
	public String fixStaffCode;
	public String statusDisplay;
	public String priorityDisplay;
	public String fixStaffName;
	public String customerPhone;
	public int type;
	public String fixStaffPhone;
	public Integer signatureId;//TODO:ggg
	public String address;
	public String description;
	public String expectedFixTime;
	public int priority;
	public int evaluateRate;
	public String customerCode;
	public String repairCaseCode;
	public List<Traces> traces;

	public class Traces {

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
					+ (arr.length == 0 ? s : s.substring(0, s.length() - 1))
					+ "]";
		}
	}

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
