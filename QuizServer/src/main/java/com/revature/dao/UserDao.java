package com.revature.dao;

import com.revature.model.User;

public interface UserDao {

	//We access the dao from here with the Userdao.currentimplementation
	UserDao currentImplementation = UserDaoSql.getInstance();
	
	User login(String username, String password);
	
}
