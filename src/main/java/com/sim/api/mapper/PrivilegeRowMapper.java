package com.sim.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sim.api.dao.PrivilegeDao;
import com.sim.api.model.PrivilegeMst;

public class PrivilegeRowMapper implements RowMapper<PrivilegeMst> {

	@Override
	public PrivilegeMst mapRow(ResultSet rs, int arg1) throws SQLException {
		PrivilegeMst privilege = new PrivilegeMst(); 
		privilege.setRoleId(rs.getInt("ROLE_ID"));
		privilege.setProgramId(rs.getInt("PROGRAM_ID"));
        privilege.setMaker(PrivilegeDao.YES.equals(rs.getString("MAKER"))?PrivilegeDao.YES:PrivilegeDao.NO);
        privilege.setChecker(PrivilegeDao.YES.equals(rs.getString("CHECKER"))?PrivilegeDao.YES:PrivilegeDao.NO);
        privilege.setViewer(PrivilegeDao.YES.equals(rs.getString("VIEWER"))?PrivilegeDao.YES:PrivilegeDao.NO);
        privilege.setCreatedBy(rs.getString("CREATED_BY"));
        privilege.setCreatedDate(rs.getDate("CREATED_DATE"));
        privilege.setLastUpdateBy(rs.getString("LAST_UPDATED_BY"));
        privilege.setLastUpdateDate(rs.getDate("LAST_UPDATED_DATE"));
        return privilege; 
	}

}