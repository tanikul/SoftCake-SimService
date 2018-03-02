package com.sim.api.dao;

import java.util.List;

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.PrivilegeJson;
import com.sim.api.model.User;

public interface UserDao {

	public User checkLogin(User user);
	public User checkValidateToken(String tokenId);
	public void updateTokenLogin(User user);
	public List<User> searchUser(SearchDataTable<User> searchDataTable);
	public int countUserTotal(SearchDataTable<User> searchDataTable);
	public int countUserFilter(SearchDataTable<User> searchDataTable);
	public void insertUser(User user);
	public User loadUserById(String userId);
	void deleteUserById(String userId);
	int checkDuplicateUser(String userId);
	List<PrivilegeJson> getRightUserByRoleId(String roleId);
	List<PrivilegeJson> getRightUserDefault();
	void updateConfirmation(User user);
}
