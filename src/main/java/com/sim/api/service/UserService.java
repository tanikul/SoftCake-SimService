package com.sim.api.service;

import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.User;

public interface UserService {

	public User checkLogin(User user) throws Exception;
	public User checkValidateToken(String tokenId);
	public DataTable<User> searchUser(SearchDataTable<User> searchDataTable);
	public void insertUser(User user) throws Exception;
	public User loadUserById(String userId);
	void deleteUserById(String userId);
	int checkDuplicateUser(String userId);
}
