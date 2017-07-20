package system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.domain.salesOrder.TingkatOrder;
import system.domain.salesOrder.TingkatOrderRepository;
import system.domain.user.SysUser;
import system.domain.user.SysUserRepository;
import system.tools.AjaxResponseBody;
import system.tools.SystemConstant;
import system.tools.TingKatTools;

@Controller
@RequestMapping("user")
public class UserController {
	
	Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private SysUserRepository userRepository;
	@Autowired
	private AjaxResponseBody result;
	
	@RequestMapping("/userMaster")
	public String tingkatOrderMaster(Model model){
		model.addAttribute("userList", userRepository.findAll());
		log.debug("show debug info");
		return "system/user/userMaster";
	}
	
	@RequestMapping("/addUser")
	public String addUser(String id, Model model){
		SysUser user = new SysUser();
		if(id != null && !id.equals("0")){
			user = userRepository.findOne(Integer.valueOf(id));
		}
		model.addAttribute("user", user);
		return "system/user/addUser";
	}
	
	@RequestMapping("/saveUser")
	@ResponseBody
	public AjaxResponseBody saveUser(SysUser user){
		try {
			System.out.println("参数user=" + user);
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println("error="+ e.getMessage());
			log.error("error-log=" + e.getMessage());
			result.setResult(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
