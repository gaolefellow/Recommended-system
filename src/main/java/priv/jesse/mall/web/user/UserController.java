package priv.jesse.mall.web.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.UserService;
import priv.jesse.mall.service.exception.LoginException;
import priv.jesse.mall.utils.FileUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 打开注册页面
	 *
	 * @return
	 */
	@RequestMapping("/toRegister.html")
	public String toRegister() {
		return "mall/user/register";
	}

	/**
	 * 打开登录页面
	 *
	 * @return
	 */
	@RequestMapping("/toLogin.html")
	public String toLogin() {
		return "mall/user/login";
	}

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 */
	@RequestMapping("/login.do")
	public void login(@RequestParam("username") String name, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		User user = userService.checkLogin(name, password);
		if (user != null) {
			if (user.getAuthorization().equals("buyer")) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/mall/index.html");
			} else {
				request.getSession().setAttribute("login_user", user);
				response.sendRedirect("/mall/admin/toSelerIndex.html");
			}
		} else {
			throw new LoginException("登录失败！ 用户名或者密码错误");

		}

	}

	/**
	 * 注册
	 * @throws Exception 
	 */
	@RequestMapping("/register.do")
	public void register(@RequestParam("image")MultipartFile image,@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("name") String name,
			@RequestParam("sex") String sex, @RequestParam("phone") String phone, @RequestParam("email") String email,
			@RequestParam("addr") String addr, @RequestParam("authorization") String authorization,
			@RequestParam("birthday") String birthday, HttpServletResponse response) throws Exception {
		User user = new User();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		Date birth = new Date();
		try {
			birth = sf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(image.getOriginalFilename());
		String imgUrl = FileUtil.saveFile(image);
//		user.setUsername(username);
//		user.setName(name);
//		user.setPassword(password);
//		user.setSex(sex);
//		user.setBirthday(birth);
//		user.setPhonenumber(phone);
//		user.setAddress(addr);
//		user.setEmail(email);
//		user.setAuthorization(authorization);
//		user.setImage(imgUrl);
		userService.creatUser(username, password, name, sex, birth, phone, addr, email, authorization, imgUrl);
		// 注册完成后重定向到登录页面
		response.sendRedirect("/mall/user/toLogin.html");
	}

	/**
	 * 登出
	 */
	@RequestMapping("/logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("/mall/index.html");
	}

	/**
	 * 验证用户名是否唯一
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUsername.do")
	public ResultBean<Boolean> checkUsername(String username) {
		List<User> users = userService.findByUsername(username);
		if (users == null || users.isEmpty()) {
			return new ResultBean<>(true);
		}
		return new ResultBean<>(false);
	}

	/**
	 * 如发生错误 转发到这页面
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/error.html")
	public String error(HttpServletResponse response, HttpServletRequest request) {
		return "error";
	}
	
	
	
	
}
