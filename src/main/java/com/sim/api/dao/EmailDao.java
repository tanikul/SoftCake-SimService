package com.sim.api.dao;

import com.sim.api.model.Email;

public interface EmailDao {

	public Email getEmailById(int id);
	void updateEmail(Email email);
}
