package com.sim.api.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sim.api.common.ServiceException;
import com.sim.api.dao.UserDao;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.PrivilegeJson;
import com.sim.api.model.User;
import com.sim.api.utils.AppUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AppUtils app;
	
	@Autowired
	private EmailService emailService;

	@Override
	public User checkLogin(User user) throws Exception {
		User result = null;
		try {
			user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
			result = userDao.checkLogin(user);
			if(result == null){
				throw new ServiceException(101);
			}else{
				List<PrivilegeJson> privileges = userDao.getRightUserByRoleId(result.getRole());
				if(privileges.isEmpty()){
					 throw new ServiceException(113);
				}
				result.setPrivilegeJsons(privileges);
				result.setTokenId(app.generateToken());
				userDao.updateTokenLogin(result);
			}
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public User checkLoginWithEmail(User user) throws Exception {
		User result = null;
		try {
			result = userDao.checkLoginWithEmail(user);
			if(result == null){
				throw new ServiceException(101);
			}else{
				List<PrivilegeJson> privileges = userDao.getRightUserByRoleId(result.getRole());
				if(privileges.isEmpty()){
					 throw new ServiceException(113);
				}
				result.setPrivilegeJsons(privileges);
				result.setTokenId(app.generateToken());
				userDao.updateTokenLogin(result);
			}
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public User checkValidateToken(String tokenId) {
		User result = null;
		try {
			result = userDao.checkValidateToken(tokenId);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public DataTable<User> searchUser(SearchDataTable<User> searchDataTable) {
		DataTable<User> result = new DataTable<>();
		try {
			List<User> users = userDao.searchUser(searchDataTable);
			result.setData(users);
			result.setDraw(searchDataTable.getDraw());
			result.setRecordsTotal(userDao.countUserTotal(searchDataTable));
			result.setRecordsFiltered(userDao.countUserFilter(searchDataTable));
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}

	@Override
	public void insertUser(User user) throws Exception {
		try {
			userDao.insertUser(user);
			emailService.sendEmailRegisterUser(user);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public void registerUser(User user) throws Exception {
		try {
			userDao.insertUser(user);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}
	
	@Override
	public User loadUserById(String userId) {
		User result = null;
		try {
			result = userDao.loadUserById(userId);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;	
	}

	@Override
	public void deleteUserById(String userId) {
		try {
			userDao.deleteUserById(userId);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public int checkDuplicateUser(String userId) {
		int result = 0;
		try {
			result = userDao.checkDuplicateUser(userId);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public List<PrivilegeJson> getRightUserDefault() {
		List<PrivilegeJson> result = null;
		try {
			result = userDao.getRightUserDefault();
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;	
	}

	@Override
	public void updateConfirmation(User user) {
		try {
			userDao.updateConfirmation(user);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			userDao.updateUser(user);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public int updatePassword(String userId, String oldPassword, String newPassword) {
		int result = 0;
		try {
			result = userDao.updatePassword(userId, oldPassword, newPassword);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public void updateUserAdmin(User user) {
		try {
			userDao.updateUserAdmin(user);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public String updateForgotPassword(String email) throws Exception {
		String result = "0";
		try {
			if(userDao.updateForgotPassword(email) == 1) {
				User user = userDao.selectUserByEmail(email);
				emailService.sendEmailForgotPassword(user);
				result = "1";
			}
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	
	@Override
	public void updatePasswordForgotPassword(User user) throws Exception {
		try {
			userDao.updatePasswordForgotPassword(user);
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}
	
	@Override
	public User selectUserByEmail(String email) {
		return userDao.selectUserByEmail(email);
	}

	@Override
	public User selectUserByEmailAndForgotPassword(User user) {
		return userDao.selectUserByEmailAndForgotPassword(user);
	}
	
	@Override
	public void editUserCustomer(User user) {
		userDao.editUserCustomer(user);
	}

}
