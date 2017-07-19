package system.controller;

import java.applet.AppletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import system.WebSecurityConfig;
import system.domain.user.SysUser;
import system.tools.SessionUserListener;


@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping("/loginProcess")
	public String login(HttpServletRequest request, Model model, String email, String password, HttpSession session){
		SysUser user = new SysUser(email, "username", password);
		
		//验证该用户ID，是否已经登录。当前用户比较已登录到系统的静态变量中的值，是否存在。 
        Boolean hasLogin = SessionUserListener.checkIfHasLogin(user);
        if(hasLogin) {
        	//用户已经登录了，不能再登录。
        	model.addAttribute("errorMsg", "User having Login, please sign out first!");
        	return "login";
        }else {
        	if (user.getEmail().equals("sming") || user.getPassword().equals("sming")) {
        		// 如果没有重复登录，则将该登录的用户信息添加入session中  
//                request.getSession().setAttribute("sysUser", user); 
        		request.getSession().setAttribute(WebSecurityConfig.SESSION_KEY, user);
//        		session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getEmail());
                // 比较保存所有用户session的静态变量中，是否含有当前session的键值映射，如果含有就删除  
                if (SessionUserListener.containsKey(request.getSession().getId())) {  
                    SessionUserListener.removeSession(request.getSession().getId());  
                }  
                //把当前用户封装的session按，sessionID和session进行键值封装，添加到静态变量map中。  
                SessionUserListener.addUserSession(request.getSession());  
                
//        		return "redirect:/index";
                return "redirect:index";
        	} else {
        		model.addAttribute("errorMsg", "email or password was wrong!");
        		return "login";
        	}
        }
//		return "";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		
//		SysUser user = (SysUser)request.getSession().getAttribute("sysUser");
//		System.out.println("what is it=????????= " + user.getEmail());
//		SessionUserListener.removeUserSession(user.getEmail());
		SessionUserListener.removeSession(request.getSession().getId());
		return "login";
	}
}
