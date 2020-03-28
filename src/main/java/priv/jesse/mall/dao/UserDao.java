package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import priv.jesse.mall.entity.User;

import java.util.Date;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
	

	
    /**
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    List<User> findByUsername(String username);
    
    
    @Transactional
    @Modifying
    @Query(value="insert into user(username,password,name,sex,birthday,phonenumber,address,email,authorization,image) values(?1,AES_ENCRYPT(?2,'key'),?3,?4,?5,?6,?7,?8,?9,?10)",nativeQuery = true)
    int creatUser(String username,String password,String name,String sex,Date birthday,String phonenumber,String address,
    		String email,String authorization,String image);
    
    @Query(value="select * from user where username=? and AES_DECRYPT(password,'key') = ?",nativeQuery = true)
    User login(String username,String password);
    
    @Transactional
    @Modifying
    @Query(value="update user set username=?1, password = AES_ENCRYPT(?2,'key'), name=?3, phonenumber=?4, address=?5, email=?6, image=?7 where id=?8 ",nativeQuery = true)
    int updateUser(String username,String password,String name,String phonenumber,String address,
    		String email,String image,int id);
}
