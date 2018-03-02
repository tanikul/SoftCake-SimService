package com.sim.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.sim.api.model.Predict;

public class PredictRowMapper implements RowMapper<Predict> {

	@Override
	public Predict mapRow(ResultSet rs, int arg1) throws SQLException {
		Predict privilege = new Predict(); 
		privilege.setPredictId(rs.getInt("PREDICT_ID"));
		privilege.setPredictContent(rs.getString("PREDICT_CONTENT"));
		privilege.setCreatedBy(rs.getString("CREATED_BY"));
        privilege.setCreatedDate(rs.getDate("CREATED_DATE"));
        privilege.setLastUpdateBy(rs.getString("LAST_UPDATED_BY"));
        privilege.setLastUpdateDate(rs.getDate("LAST_UPDATED_DATE"));
        return privilege; 
	}

}