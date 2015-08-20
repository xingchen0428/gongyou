package com.gongyou.domain;

import java.lang.reflect.Field;
import java.util.List;

import com.gongyou.base.BaseBean;

public class FindByEquipmentNoBean extends BaseBean {

	public Boolean warn;
	public String location;
	public String fixStaffCode;
	public String vendorAddress;
	public String overhaulCycle;
	public String modelNo;
	public String maintenanceSop;
	public String releaseDate;
	public String description;
	public String technicalParameters;
	public String name;
	public String repairCaseCode;
	public String icon;
	public String forwardMaintenanceDate;
	public String vendorName;
	public String image;
	public String useDate;
	public String fixStaffName;
	public String vendorPhone;
	public String vendorContact;
	public String nextMaintenanceDate;
	public String serialNo;
	public String category;
	public String iomFile;
	public String equipmentNo;
	public String manufacturer;
	public String specification;
	public String serviceArea;
	public List<MaintainRecords> maintainRecords;

	public class MaintainRecords {
		public String description;
		public String fixStaffCode;
		public String fixStaffName;
		public int maintainRecordId;
		public String maintainDate;

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
