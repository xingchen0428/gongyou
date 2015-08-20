package com.gongyou.domain;

import java.io.Serializable;
import java.util.List;

import com.gongyou.base.BaseBean;

public class LoginBean extends BaseBean implements Serializable{

	public String staffCode;
	public String sex;
	public String phone;
	public String sexDisplay;
	public String typeDisplay;
	public String type;
	public String contact;
	public String photo;
	public String email;
	public String address;
	public String description;
	public String name;
	public List<Teams> teams;

	public class Teams {

	}

	@Override
	public String toString() {
		return "LoginBean [staffCode=" + staffCode + ", sex=" + sex
				+ ", phone=" + phone + ", sexDisplay=" + sexDisplay
				+ ", typeDisplay=" + typeDisplay + ", type=" + type
				+ ", contact=" + contact + ", photo=" + photo + ", email="
				+ email + ", address=" + address + ", description="
				+ description + ", name=" + name + ", teams=" + teams + "]";
	}
	
	

}