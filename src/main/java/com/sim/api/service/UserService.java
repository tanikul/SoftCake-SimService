package com.sim.api.service;

import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.User;

public interface UserService {

	public User checkLogin(User user);
	public User checkValidateToken(String tokenId);
	public DataTable<User> searchUser(SearchDataTable<User> searchDataTable);
	public void insertUser(User user);
}
