package com.sim.api.service;

import java.util.List;

import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.PrivilegeJson;
import com.sim.api.model.User;

public interface UserService {

	public User checkLogin(User user) throws Exception;
	public User checkValidateToken(String tokenId);
	public DataTable<User> searchUser(SearchDataTable<User> searchDataTable);
	public void insertUser(User user) throws Exception;
	public User loadUserById(String userId);
	void deleteUserById(String userId);
	int checkDuplicateUser(String userId);
	List<PrivilegeJson> getRightUserDefault();
	void updateConfirmation(User user);
	void registerUser(User user) throws Exception;
	void updateUser(User user);
	int updatePassword(String userId, String oldPassword, String newPassword);
	void updateUserAdmin(User user);
	String updateForgotPassword(String email) throws Exception;
	User selectUserByEmail(String email);
	User selectUserByEmailAndForgotPassword(User user);
	void updatePasswordForgotPassword(User user) throws Exception;
	void editUserCustomer(User user);
	User checkLoginWithEmail(User user) throws Exception;
	int checkEmailInSystem(String email);
}
