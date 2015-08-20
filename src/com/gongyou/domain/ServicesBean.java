package com.gongyou.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.gongyou.base.BaseBean;

public class ServicesBean extends BaseBean {

	public int prePage;
	public int startRecord;
	public int nextPage;
	public Boolean error;
	public int endRecord;
	public int count;
	public String voClass;
	public int pageSize;
	public int totalPage;
	public int pageIndex;
	public List<Records> records;

	public class Records implements Serializable {

		public String customerName;
		public String serviceRequestId;
		public String caseCode;
		public String requestTime;
		public String status;
		public String address;
		public String expectedFixTime;
		public String description;
		public String customerCode;
		public String statusDisplay;
		public String customerPhone;
		public List<Traces> traces;

		public class Traces implements Serializable {

			public String staffCode;
			public String actionDisplay;
			public String status;
			public String staffName;
			public String statusDisplay;
			public String action;
			public String caseTraceId;
			public String executeTime;

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
				return getClass().getSimpleName()
						+ "["
						+ (arr.length == 0 ? s : s.substring(0, s.length() - 1))
						+ "]";
			}
		}

		//这里是手动更改的	图片名称集合
//		public List<Pictures> pictures;
		public List<String> pictures;

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
