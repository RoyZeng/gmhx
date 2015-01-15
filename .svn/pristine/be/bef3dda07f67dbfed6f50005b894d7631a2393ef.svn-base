package com.gome.gmhx.dao.sysconfig;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.HxUserUnload;
import com.gome.gmhx.entity.SysUser;

@Repository("hxUserDao")
public interface HxUserDao {
	
	List<Map<String, Object>> getUserPageList(Page page);
	
	void addUser(HxUser user);
	
	HxUser getUserById(String orgId);
	
	HxUser getShowById(String orgId);
	
	void updateUser(HxUser user);

	List<Map<String, Object>> getRolePageListByUserId(Page page);

	SysUser getUserByUserLoginName(String indexLoginId);

	List<Map<String, Object>> getPositionPageListByUserLoginName(Page page);
	
	List<Map<String, Object>> getPositionPageListByUserLoginName_gome(Page page);

	List<Map<String, String>> getFittingPosition(SysUser sysUser);

	List<Map<String, Object>> getPositionUserPageList(Page page);
	
	List<Map<String, Object>> getPositionUserPageList_gome(Page page);

	void delUserUnload(HxUserUnload hxUserUnload);

	List<Map<String, Object>> queryAllUserList();
	
	void addUserUnload(HxUserUnload hxUserUnload);

	HxUser queryUserByUserLoginName(String userLoginName);

	List<Map<String, Object>> getAllUserList();

	void updateUserUnload(HxUserUnload hxUserUnload);

	List<Map<String, Object>> queryHxUserList();

	List<Map<String, Object>> getHxUserList();

	void delEmpUserUnload(HxUserUnload hxUserUnload);

	List<Map<String, Object>> queryAllEmpUserList();

	void addEmpUserUnload(HxUserUnload hxUserUnload);

	List<Map<String, Object>> getAllEmpUserList();

	void updateEmpUserUnload(HxUserUnload hxUserUnload);

	SysUser getPositionInfoBySysUser(SysUser sysUser);

	List<Map<String, Object>> getUserExport(HxUser hxUser);

	void updateUserPwd(HxUser userTemp);

}
