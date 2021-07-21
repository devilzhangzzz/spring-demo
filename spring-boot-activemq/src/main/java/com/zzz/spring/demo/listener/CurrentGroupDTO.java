package com.zzz.spring.demo.listener;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CurrentGroupDTO {
	/**
	 * 组编码
	 */
	private String groupCode;
	
	/**
	 * 组名称
	 */
	private String groupName;
	
	/**
	 * 归属
	 */
	private String belong;
	
	/**
	 *  归属名称
	 */
	private String belongName;
	

}
