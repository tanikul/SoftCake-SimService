package com.sim.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sim.api.model.RoleMst;

public class RoleRowMapper implements RowMapper<RoleMst> {

	@Override
	public RoleMst mapRow(ResultSet rs, int arg1) throws SQLException {
		RoleMst role = new RoleMst(); 
        role.setRoleId(rs.getInt("ROLE_ID"));
        role.setRoleName(rs.getString("ROLE_NAME"));
        role.setActiveStatus(rs.getString("ACTIVE_STATUS")!=null?rs.getString("ACTIVE_STATUS"):"N");
        role.setLastUpdateBy(rs.getString("LAST_UPDATED_BY"));
        role.setLastUpdateDate(rs.getDate("LAST_UPDATED_DATE"));
        role.setCreatedBy(rs.getString("CREATED_BY"));
        role.setCreatedDate(rs.getDate("CREATED_DATE"));
        return role; 
	}

}