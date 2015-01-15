package com.gome.gmhx.jbpm;

import com.gome.gmhx.entity.SysUser;

public class CurrentSysUser {
	
	
	private static ThreadLocal<SysUser> sysUsers = new ThreadLocal<SysUser>();
	
	public static void setCurrentSysUser(SysUser sysUser){
		sysUsers.set(sysUser);
	}
	
	public static SysUser getCurrentSysUser(){
		return sysUsers.get();
	}
	
	public static void removeCurrentSysUser(){
		sysUsers.remove();
	}
	

}
