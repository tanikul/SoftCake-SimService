package com.sim.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.sim.api.model.Sim;

public class SimRowMapper implements RowMapper<Sim> {

	@Override
	public Sim mapRow(ResultSet rs, int arg1) throws SQLException {
		Sim sim = new Sim();
		sim.setSimNumber(rs.getString("SIM_NUMBER"));
        sim.setCreditTerm(rs.getString("CREDIT_TERM"));
        sim.setRecievedDate(rs.getDate("RECIEVED_DATE"));
        sim.setPrice(rs.getBigDecimal("PRICE"));
        sim.setActiveStatus(rs.getString("ACTIVE_STATUS"));
        sim.setLastUpdateBy(rs.getString("LAST_UPDATED_BY"));
        sim.setLastUpdateDate(rs.getDate("LAST_UPDATED_DATE"));
        sim.setCreatedBy(rs.getString("CREATED_BY"));
        sim.setCreatedDate(rs.getDate("CREATED_DATE"));
        return sim; 
	}

}