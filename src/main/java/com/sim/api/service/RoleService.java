package com.sim.api.service;

import com.sim.api.common.ServiceException;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.RoleMst;

public interface RoleService {
	
	public void saveRoleMst(RoleMst roleMst)  throws ServiceException ;
	public DataTable<RoleMst> searchRoleByDataTable(SearchDataTable<RoleMst> roleData);
	public String validateAndDeleteRoleByRoleNameByRoleType(String roleType , String roleName) throws ServiceException ;
	public RoleMst getRoleMstByRoleNameByRoleType(String roleName,String roleType) ;

}
