package com.sim.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sim.api.model.ProgramMst;

public class ProgramRowMapper implements RowMapper<ProgramMst> {
	/**
	 * 
  PROGRAM_ID INTEGER IDENTITY PRIMARY KEY,
  PROGRAM_NAME varchar(255) NOT NULL,
  PROGRAM_REF varchar(255) NOT NULL,
  PROGRAM_TYPE varchar(4) NOT NULL,
  PROGRAM_LEVEL INT  NOT NULL,
  PROGRAM_GROUP varchar(255) NOT NULL,
  GROUP_LEVEL INT  NOT NULL,
  VIEWER char(1) NOT NULL,
  MAKER char(1) NOT NULL,
  CHECKER char(1) NOT NULL,
  ACTIVE_STATUS char(1) NOT NULL,
  CREATED_DATE datetime NOT NULL,
  CREATED_BY varchar(120) NOT NULL,
  LAST_UPDATED_DATE datetime NOT NULL,
  LAST_UPDATED_BY varchar(120) NOT NULL

	 */
	@Override
	public ProgramMst mapRow(ResultSet rs, int arg1) throws SQLException {
		ProgramMst program = new ProgramMst(); 
		program.setProgramId(rs.getInt("PROGRAM_ID"));
		program.setProgramName(rs.getString("PROGRAM_NAME"));
		program.setProgramRef(rs.getString("PROGRAM_REF"));
		program.setProgramType(rs.getString("PROGRAM_TYPE"));
		program.setProgramLevel(rs.getInt("PROGRAM_LEVEL"));
		program.setProgramGroup(rs.getString("PROGRAM_GROUP"));
		program.setGroupLevel(rs.getInt("GROUP_LEVEL"));
		program.setProgramGroup(rs.getString("PROGRAM_GROUP"));
		//program.setNone(rs.getString("NONE"));
		program.setDefaultViewer(rs.getString("DEFVIEWER"));
		program.setDefaultMaker(rs.getString("DEFMAKER"));
		program.setDefaultChecker(rs.getString("DEFCHECKER"));
		program.setProgramType(rs.getString("PROGRAM_TYPE"));
        program.setCreatedBy(rs.getString("CREATED_BY"));
        program.setCreatedDate(rs.getDate("CREATED_DATE"));
        program.setLastUpdateBy(rs.getString("LAST_UPDATED_BY"));
        program.setLastUpdateDate(rs.getDate("LAST_UPDATED_DATE"));
        program.setExceptRole(rs.getString("EXCEPT_ROLE"));
        program.setDisableFlag(rs.getString("DISABLE_FLAG"));
        return program; 
	}

}