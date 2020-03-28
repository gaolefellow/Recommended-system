package priv.jesse.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import priv.jesse.mall.dao.UserDao;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User findById(int id) {
		return userDao.getOne(id);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userDao.findAll(pageable);
	}

	@Override
	public List<User> findAllExample(Example<User> example) {
		return userDao.findAll(example);
	}

	@Override
	public void update(User user) {
		userDao.save(user);
	}

	@Override
	public int create(User user) {
		return userDao.save(user).getId();
	}
	
	
	

	@Override
	public void delById(int id) {
		userDao.delete(id);
	}

	/**
	 * 根据用户名查询
	 *
	 * @param username
	 * @return
	 */
	@Override
	public List<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * 检查登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public User checkLogin(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	public void creatUser(String username, String password, String name, String sex, Date birthday, String phonenumber,
			String address, String email, String authorization, String image) {
		userDao.creatUser(username, password, name, sex, birthday, phonenumber, address, email, authorization, image);
		
	}

	@Override
	public void updateUser(String username, String password, String name, String phonenumber, String address,
			String email, String image, int id) {
		userDao.updateUser(username, password, name, phonenumber, address, email, image, id);
		
	}
	
	

}
