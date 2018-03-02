package com.sim.api.dao;

import java.util.List;

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.RoleMst;

public interface RoleDao {

	public void insertRoleMst(RoleMst roleMst);
	public RoleMst getRoleMstByRoleNameByRoleType(String roleName);
	public int checkDuplicateRoleNameByRoleType(String roleName);
	public List<RoleMst> searchRoleByDataTable(SearchDataTable<RoleMst> roleData);
	public int countSearchRoleByDataTableTotal(SearchDataTable<RoleMst> roleData) ;
	public int countSearchRoleByDataTableFilter(SearchDataTable<RoleMst> roleData) ;
	public void deletetRoleMstByRoleName(String roleName);
	public int checkRoleExistingInUserMaster(String roleName);
}
