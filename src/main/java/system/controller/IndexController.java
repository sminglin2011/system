package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import system.WebSecurityConfig;
import system.domain.user.SysUser;


@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) SysUser sysUser, Model model){
		//model.addAttribute("name", "sming");
		return "system/index";
	}
	@RequestMapping("/desktop")
	public String myDesktop(@SessionAttribute(WebSecurityConfig.SESSION_KEY) SysUser sysUser) {
		return "system/desktop";
	}
	
}
