package com.sim.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sim.api.common.ServiceException;
import com.sim.api.dao.ErrorMessageDao;
import com.sim.api.dao.PrivilegeDao;
import com.sim.api.dao.RoleDao;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.RoleMst;

@Service("roleService")
public class RoleServiceImpl  implements RoleService{
	private static final Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	@Qualifier("roleDao")
	RoleDao roleDao;

	@Autowired
	@Qualifier("privilegeDao")
	PrivilegeDao privilegeDao;
	

	@Autowired
	@Qualifier("errorMessageDao")
	ErrorMessageDao errorMessageDao;

	@Override
	public void saveRoleMst(RoleMst roleMst)  throws ServiceException {
		String message = "";
		try {
			int duppCount = roleDao.checkDuplicateRoleNameByRoleType(roleMst.getRoleName());
			if(duppCount > 0 ){
				message = errorMessageDao.getErrorMsgByCode("117").getErrorDescription();
				throw new ServiceException(message);
			}else{
				roleDao.insertRoleMst(roleMst);
			}
		}catch(Exception ex){
			LOGGER.error(ex);
			throw ex; 
		}
	}


	@Override
	public DataTable<RoleMst> searchRoleByDataTable(SearchDataTable<RoleMst> roleData) {

		DataTable<RoleMst> result = new DataTable<>();
		try {
			List<RoleMst> roleList = roleDao.searchRoleByDataTable(roleData);
			result.setData(roleList);
			result.setDraw(roleData.getDraw());
			result.setRecordsTotal(roleDao.countSearchRoleByDataTableTotal(roleData));
			result.setRecordsFiltered(roleDao.countSearchRoleByDataTableFilter(roleData));
		}catch(Exception ex){
			LOGGER.error(ex);
			throw ex; 
		}
		return result;
	}

	@Override
	public String validateAndDeleteRoleByRoleNameByRoleType(String roleName) throws ServiceException {
		String message = null;
		try{

			if(roleDao.checkRoleExistingInUserMaster(roleName)  > 0 ){
				message = errorMessageDao.getErrorMsgByCode("114").getErrorDescription();
				LOGGER.info("checkRoleExistingInUserMaster === > " + message);
				throw new ServiceException(message);
			}else{
				RoleMst roleMst = roleDao.getRoleMstByRoleNameByRoleType(roleName);
				/******** Delete privilege **************/
				privilegeDao.deletePrivilegeMstByRoleId(roleMst.getRoleId());
				/******** Delete privilege **************/
				roleDao.deletetRoleMstByRoleName(roleName);
				
				/**** #############################################*/
				message = errorMessageDao.getErrorMsgByCode("116").getErrorDescription();
			}

		}catch(Exception e){
			throw new ServiceException(e);
		}

		LOGGER.info("validateAndDeleteRoleByRoleNameByRoleType === > " + message);
		return message;
	}

	@Override
	public RoleMst getRoleMstByRoleNameByRoleType(String roleName) {
			return  roleDao.getRoleMstByRoleNameByRoleType(roleName);
	}
}
