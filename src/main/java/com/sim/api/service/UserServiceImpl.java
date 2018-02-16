package com.sim.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sim.api.dao.UserDao;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.User;
import com.sim.api.utils.AppUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AppUtils app;

	@Override
	public User checkLogin(User user) {
		User result = null;
		try {
			result = userDao.checkLogin(user);
			result.setTokenId(app.generateToken());
			userDao.updateTokenLogin(result);
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
	public void insertUser(User user) {
		try {
			userDao.insertUser(user);;
		}catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

}
