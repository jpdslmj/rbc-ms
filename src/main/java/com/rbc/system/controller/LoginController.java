package com.rbc.system.controller;

import com.rbc.common.annotation.Log;
import com.rbc.common.controller.BaseController;
import com.rbc.common.domain.FileDO;
import com.rbc.common.domain.Tree;
import com.rbc.common.service.FileService;
import com.rbc.common.utils.MD5Utils;
import com.rbc.common.utils.R;
import com.rbc.common.utils.ShiroUtils;
import com.rbc.system.domain.MenuDO;
import com.rbc.system.service.MenuService;
import com.rbc.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    MenuService menuService;
	@Autowired
    FileService fileService;
	@Autowired
	UserService userService;
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/favicon.png");
			}
		}else {
			model.addAttribute("picUrl","/img/favicon.png");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@PostMapping("/checkLogin2android")
	@ResponseBody
	R checkLogin2android(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username",username);
		params.put("password",password);
		//!userService.exit(params);
		if (userService.exit(params)){
			return R.ok();
		}else {
			return R.error();
		}
	}


	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
