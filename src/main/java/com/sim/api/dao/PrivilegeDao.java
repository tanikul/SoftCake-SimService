package com.sim.api.dao;

import java.util.List;

import com.sim.api.model.PrivilegeMst;

public interface PrivilegeDao {
		public static final String YES = "Y";
		public static final String NO = "N";
		
		
		public void insertPrivilegeMst(PrivilegeMst privilegeMst);
		public PrivilegeMst getPrivilegeMstByRoleIdByProgramId(int roleId , int programId);
		public void updatePrivilegeMst(PrivilegeMst privilegeMst);
		public void deletePrivilegeMstByRoleIdByProgramId(int roleId , int programId);
		public void deletePrivilegeMstByRoleId(int roleId) ;
		public int checkComparePrivilegeMst(PrivilegeMst privilegeMst) ;
		public int checkDupplicatePrivilegeMst(PrivilegeMst privilegeMst) ;

		public List<PrivilegeMst> getPrivilegeMstListByRoleId(int roleId );
}
