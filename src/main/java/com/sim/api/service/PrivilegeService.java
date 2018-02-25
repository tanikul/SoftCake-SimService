package com.sim.api.service;

import java.util.List;

import com.sim.api.model.PrivilegeMst;
import com.sim.api.model.ProgramMst;
import com.sim.api.model.RoleMst;

public interface PrivilegeService {
	
	public void savePrivilegeMst(List<ProgramMst> programList , RoleMst roleMst, String userLogin) ;
	public void editPrivilegeMst(List<ProgramMst> programList , RoleMst roleMst, String userLogin);
	public List<PrivilegeMst> getPrivilegeMstListByRoleId(int roleId );

}
