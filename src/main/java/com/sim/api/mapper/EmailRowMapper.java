package com.sim.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.sim.api.model.Email;

public class EmailRowMapper  implements RowMapper<Email> {

	@Override
	public Email mapRow(ResultSet rs, int arg1) throws SQLException {
		Email email = new Email(); 
		email.setEmailId(rs.getInt("EMAIL_ID"));
		email.setActiveStatus(rs.getString("ACTIVE_STATUS"));
		email.setCc(rs.getString("CC"));
		email.setDescription(rs.getString("DESCRIPTION"));
		email.setEmailId(rs.getInt("EMAIL_ID"));
		email.setEmailName(rs.getString("EMAIL_NAME"));
		email.setFrom(rs.getString("FROM"));
		email.setTitle(rs.getString("TITLE"));
		email.setTo(rs.getString("TO"));
		return email; 
	}

}