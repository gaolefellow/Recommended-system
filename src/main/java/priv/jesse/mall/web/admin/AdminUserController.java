package priv.jesse.mall.web.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.UserService;
import priv.jesse.mall.utils.FileUtil;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	@Autowired
	private UserService userService;

	/**
	 * 打开用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/toList.html")
	public String toList() {
		return "admin/user/list";
	}

	/**
	 * 打开编辑页面
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/toEdit.html")
	public String toEdit(int id, Map<String, Object> map) {
		User user = userService.findById(id);
		map.put("user", user);
		return "admin/user/edit";
	}

	/**
	 * 获取所有用户列表
	 *
	 * @param pageindex
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public ResultBean<List<User>> findAllUser(int pageindex,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
		Pageable pageable = new PageRequest(pageindex, pageSize, null);
		List<User> users = userService.findAll(pageable).getContent();
		return new ResultBean<>(users);
	}

	@ResponseBody
	@RequestMapping("/getTotal.do")
	public ResultBean<Integer> geTotal() {
		Pageable pageable = new PageRequest(1, 15, null);
		int total = (int) userService.findAll(pageable).getTotalElements();
		return new ResultBean<>(total);
	}

	@ResponseBody
	@RequestMapping("/del.do")
	public ResultBean<Boolean> del(int id) {
		userService.delById(id);
		return new ResultBean<>(true);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/update.do")
	public void update(@RequestParam("id")int id,@RequestParam("username")String username, 
			@RequestParam("password")String password, @RequestParam("name")String name, @RequestParam("phone")String phone, @RequestParam("email")String email, @RequestParam("addr")String addr
			,@RequestParam("image")MultipartFile image,HttpServletResponse response) throws Exception {
		
		User user = userService.findById(id);	
		System.out.println(user);
		String imgUrl = FileUtil.saveFile(image);
//		if (StringUtils.isNotBlank(imgUrl)) {
//			user.setImage(imgUrl);
//        }
//		user.setUsername(username);
//		user.setName(name);
//		user.setPhonenumber(phone);
//		user.setPassword(password);
//		user.setEmail(email);
//		user.setAddress(addr);
//		userService.update(user);
		
		userService.updateUser(username, password, name, phone, addr, email, imgUrl, id);
		response.sendRedirect("/mall/admin/user/toList.html");
	}
}
