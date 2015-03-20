package com.meat.question.service;

import java.util.List;

import com.meat.question.bean.PageBean;
import com.meat.question.bean.RoleBean;
import com.meat.question.pojo.Role;


public interface IRoleService {
	public String queryRoleName(String userId);
	public String queryRoleId(String userId);
	public PageBean queryRole(RoleBean roleBean);
	public boolean addRole(RoleBean roleBean);
	public Role getRole(String roleId);
	public List<String> getMenuIds(String roleId);
	public boolean updateRole(RoleBean roleBean);
	public List<Role> queryRole(String userId);
}
