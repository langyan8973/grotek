package com.grotek.service.imp;

import com.grotek.dao.UserMapper;
import com.grotek.pojo.User;
import com.grotek.service.UserService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("userService")
@org.springframework.transaction.annotation.Transactional
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}
	
}
