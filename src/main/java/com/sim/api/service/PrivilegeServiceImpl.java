package com.sim.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sim.api.dao.PrivilegeDao;
import com.sim.api.model.PrivilegeMst;
import com.sim.api.model.ProgramMst;
import com.sim.api.model.RoleMst;

@Service("privilegeService")
public class PrivilegeServiceImpl  implements PrivilegeService{
	private static final Logger LOGGER = Logger.getLogger(PrivilegeServiceImpl.class);

	@Autowired
	@Qualifier("privilegeDao")
	PrivilegeDao privilegeDao;


	
	@Override
	public void savePrivilegeMst(List<ProgramMst> programList , RoleMst roleMst, String userLogin) {
		//LOGGER.info("<< == SAVE privilegeMst MST == >> ");
		PrivilegeMst privilegeMst = new PrivilegeMst();
		int roleId = roleMst.getRoleId();
		try {
			for(ProgramMst pro : programList){
				privilegeMst = new PrivilegeMst();
				if("Y".equals(pro.getNone())){
					LOGGER.info("<< == SAVE privilegeMst MST none:== >> "+pro.getProgramId());
				}else{
					privilegeMst.setRoleId(roleId);
					privilegeMst.setProgramId(pro.getProgramId());
					privilegeMst.setViewer(pro.getViewer());
					privilegeMst.setChecker(pro.getChecker());
					privilegeMst.setMaker(pro.getMaker());
					privilegeMst.setCreatedBy(userLogin);
					privilegeMst.setLastUpdateBy(userLogin);

					privilegeDao.insertPrivilegeMst(privilegeMst);
				}
			}
		}catch(Exception ex){
			LOGGER.error(ex);
			throw ex; 
		}
	}

	@Override
	public void editPrivilegeMst(List<ProgramMst> programList , RoleMst roleMst, String userLogin) {
		PrivilegeMst privilegeMst = new PrivilegeMst();
		int roleId = roleMst.getRoleId();
		LOGGER.info("<< == EDIT privilegeMst MST == roleID>> "+roleId);
			for(ProgramMst pro : programList){
				privilegeMst = new PrivilegeMst();
				if("Y".equals(pro.getNone())){
					privilegeDao.deletePrivilegeMstByRoleIdByProgramId(roleId, pro.getProgramId());
				}else{
					privilegeMst.setRoleId(roleId);
					privilegeMst.setProgramId(pro.getProgramId());
					privilegeMst.setViewer(pro.getViewer());
					privilegeMst.setChecker(pro.getChecker());
					privilegeMst.setMaker(pro.getMaker());
					privilegeMst.setLastUpdateBy(userLogin);
					if(privilegeDao.checkDupplicatePrivilegeMst(privilegeMst) == 0){
						privilegeDao.insertPrivilegeMst(privilegeMst);
					}else if( privilegeDao.checkComparePrivilegeMst(privilegeMst) == 0 ){
						privilegeDao.updatePrivilegeMst(privilegeMst);
					}else{
						LOGGER.info("<< == EDIT privilegeMst not update none:== >> "+pro.getProgramId());
					}
				}
			}
	}

	public List<PrivilegeMst> getPrivilegeMstListByRoleId(int roleId ){
		return privilegeDao.getPrivilegeMstListByRoleId(roleId);
	}
	

}
