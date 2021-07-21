package com.zzz.spring.demo.listener;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CurrentRoleDTO {
	
	/**
	 * 角色编码
	 */
	private String roleCode;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	
}
