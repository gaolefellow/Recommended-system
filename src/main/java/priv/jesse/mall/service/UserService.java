package priv.jesse.mall.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import priv.jesse.mall.entity.User;

public interface UserService {
	
	/**
     * 根据id查询
     *
     * @param user_id
     * @return
     */
    User findById(int user_id);

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<User> findAll(Pageable pageable);

    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<User> findAllExample(Example<User> example);

    /**
     * 更新
     *
     * @param user
     * @return
     */
    void update(User user);

    /**
     * 创建
     *
     * @param user
     * @return
     */
    int create(User user);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int user_id);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * 检查登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username,String password);
    
    
    void creatUser(String username,String password,String name,String sex,Date birthday,String phonenumber,String address,
    		String email,String authorization,String image);
    
    
    public void updateUser(String username,String password,String name,String phonenumber,String address,
    		String email,String image,int id);
   
}
