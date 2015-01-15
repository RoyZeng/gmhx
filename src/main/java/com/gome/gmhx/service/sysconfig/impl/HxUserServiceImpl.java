package com.gome.gmhx.service.sysconfig.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.sysconfig.HxUserDao;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.HxUserUnload;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Service("hxUserService")
public class HxUserServiceImpl implements HxUserService {
	@Resource
	private HxUserDao hxUserDao;

	@Override
	public List<Map<String, Object>> getUserPageList(Page page) {
		return hxUserDao.getUserPageList(page);
	}

	@Override
	public void addUser(HxUser user) {
		hxUserDao.addUser(user);
	}
	
	@Override
	public HxUser getUserById(String userId) {
		return hxUserDao.getUserById(userId);
	}

	@Override
	public HxUser getShowById(String loginId){
	    return hxUserDao.getShowById(loginId);
	}

	@Override
	public void updateUser(HxUser user) {
		hxUserDao.updateUser(user);
	}

	@Override
	public List<Map<String, Object>> getRolePageListByUserId(Page page) {
		return hxUserDao.getRolePageListByUserId(page);
	}

	@Override
	public SysUser getUserByUserLoginName(String indexLoginId) {
		return hxUserDao.getUserByUserLoginName(indexLoginId);
	}

	@Override
	public List<Map<String, Object>> getPositionPageListByUserLoginName(
			Page page) {
		return hxUserDao.getPositionPageListByUserLoginName(page);
	}
	
	@Override
	public List<Map<String, Object>> getPositionPageListByUserLoginName_gome(
			Page page) {
		return hxUserDao.getPositionPageListByUserLoginName_gome(page);
	}

	@Override
	public List<Map<String, String>> getFittingPosition(SysUser sysUser) {
		return hxUserDao.getFittingPosition(sysUser);
	}

	@Override
	public List<Map<String, Object>> getPositionUserPageList(Page page) {
		return hxUserDao.getPositionUserPageList(page);
	}
	
	@Override
	public List<Map<String, Object>> getPositionUserPageList_gome(Page page) {
		return hxUserDao.getPositionUserPageList_gome(page);
	}

	@Override
	public void delUserUnload(HxUserUnload hxUserUnload) {
		hxUserDao.delUserUnload(hxUserUnload);
	}

	@Override
	public List<Map<String, Object>> queryAllUserList() {
		return hxUserDao.queryAllUserList();
	}

	@Override
	public void addUserUnload(HxUserUnload hxUserUnload) {
		hxUserDao.addUserUnload(hxUserUnload);
	}

	@Override
	public HxUser queryUserByUserLoginName(String userLoginName) {
		return hxUserDao.queryUserByUserLoginName(userLoginName);
	}

	@Override
	public List<Map<String, Object>> getAllUserList() {
		return hxUserDao.getAllUserList();
	}

	@Override
	public void updateUserUnload(HxUserUnload hxUserUnload) {
		hxUserDao.updateUserUnload(hxUserUnload);
	}

	@Override
	public List<Map<String, Object>> queryHxUserList() {
		return hxUserDao.queryHxUserList();
	}
	
	@Override
	public List<Map<String, Object>> getHxUserList() {
		return hxUserDao.getHxUserList();
	}
	
	@Override
	public void delEmpUserUnload(HxUserUnload hxUserUnload) {
		hxUserDao.delEmpUserUnload(hxUserUnload);
	}

	@Override
	public List<Map<String, Object>> queryAllEmpUserList() {
		return hxUserDao.queryAllEmpUserList();
	}

	@Override
	public void addEmpUserUnload(HxUserUnload hxUserUnload) {
		hxUserDao.addEmpUserUnload(hxUserUnload);
	}
	
	@Override
	public List<Map<String, Object>> getAllEmpUserList() {
		return hxUserDao.getAllEmpUserList();
	}

	@Override
	public void updateEmpUserUnload(HxUserUnload hxUserUnload) {
		hxUserDao.updateEmpUserUnload(hxUserUnload);
	}

	@Override
	public SysUser getPositionInfoBySysUser(SysUser sysUser) {
		return hxUserDao.getPositionInfoBySysUser(sysUser);
	}

	@Override
	public List<Map<String, Object>> getUserExport(HxUser hxUser) {
		return hxUserDao.getUserExport(hxUser);
	}

	@Override
	public Map<String, String> insertUsers(List<HxUser> users) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String exists = "{";
		String success = "{";
		String failure = "{";
		
		for(int i=0 ; i< users.size() ; i++){
			HxUser hxUser = users.get(i);
			HxUser temp = hxUserDao.queryUserByUserLoginName(hxUser.getUserLoginName());
			if(temp!=null){
				exists +=(i+2)+",";
			}else{
				String userLoginName =hxUser.getUserLoginName().trim();
				String userName = hxUser.getUserName().trim();
				String userOrgId = hxUser.getUserOrgId().trim();
				if(userLoginName==null || ("").equals(userLoginName.trim()) ||
						userName==null || ("").equals(userName.trim()) ||
								userOrgId==null || ("").equals(userOrgId.trim())
										){
					failure +=(i+2)+",";
				}else{
					try{
						hxUser.setUserActive("1");//是否有效用户
						hxUserDao.addUser(hxUser);
						success +=(i+2)+",";
					}catch(Exception e){
						failure +=(i+2)+",";
					}
				}
			}
		}
		resultMap.put("exists", exists.length()==1 ? exists+"}":exists.subSequence(0, exists.length()-1)+"}");
		resultMap.put("success", success.length()==1 ? success+"}":success.subSequence(0, success.length()-1)+"}");
		resultMap.put("failure", failure.length()==1 ? failure+"}":failure.subSequence(0, failure.length()-1)+"}");
		return resultMap;
	}

	@Override
	public void updateUserPwd(HxUser userTemp) {
		hxUserDao.updateUserPwd(userTemp);
	}
}
