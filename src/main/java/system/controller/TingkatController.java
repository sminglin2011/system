package system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.domain.customer.Customer;
import system.domain.customer.CustomerRepository;
import system.domain.menu.PreSetRemark;
import system.domain.menu.ProductMenu;
import system.domain.menu.ProductMenuRepository;
import system.domain.salesOrder.TingkatOrder;
import system.domain.salesOrder.TingkatOrderRepository;
import system.tools.AjaxResponseBody;
import system.tools.TingKatTools;

@Controller
@RequestMapping("tingkat")
public class TingkatController {
	
	Logger log = Logger.getLogger(TingkatController.class);

	@Autowired
	private TingkatOrderRepository tingkatRepository;
	@Autowired
	private ProductMenuRepository menuRepository;
	@Autowired
	private AjaxResponseBody result;
	
	@RequestMapping("/tingkatOrderMaster")
	public String tingkatOrderMaster(Model model){
		model.addAttribute("tingkatList", tingkatRepository.findAll());
		return "system/tingkat/tingkatOrderMaster";
	}
	
	@RequestMapping("/addTingkat")
	public String addTingkat(String id, Model model){
		TingkatOrder tingkat = new TingkatOrder();
		if(id != null && !id.equals("0")){
			tingkat = tingkatRepository.findOne(Long.valueOf(id));
		}
		model.addAttribute("tingkat", tingkat);
		model.addAttribute("menuList", menuRepository.findAll());
		return "system/tingkat/addTingkat";
	}
	@RequestMapping("/saveTingkat")
	@ResponseBody
	public AjaxResponseBody saveTingkat(TingkatOrder tingkat){
		try {
			System.out.println("参数tingkat=" + tingkat);
			//Order number 为 null, 新建 TingKat Order
			if(tingkat.getOrderNumber() == null) {
				//获取 Order Number
				tingkat.setOrderNumber(TingKatTools.generateSequenceNo());
			}
			tingkatRepository.save(tingkat);
		} catch (Exception e) {
			System.out.println("error="+ e.getMessage());
			log.error("error-log=" + e.getMessage());
			result.setResult(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/deleteTingkat")
	@ResponseBody
	public AjaxResponseBody deleteTingkat(String ids, HttpServletResponse response){
		String[] tempId = ids.split(",");
		Long[] arrayId = new Long[tempId.length];
		
		for(int i=0;i<tempId.length;i++){
			System.out.println(tempId[i]);
			arrayId[i]=Long.valueOf(tempId[i]);
		}
		List<Long> idList = new ArrayList<Long>();
		idList = java.util.Arrays.asList(arrayId);// 字符数组转lis
		Iterable<TingkatOrder> mes = tingkatRepository.findAll(idList);
		try {
			tingkatRepository.delete(mes);
		} catch (Exception e) {
			System.out.println("error="+ e.getMessage());
			log.error("error-log=" + e.getMessage());
			result.setMsg("Delete action error");
			result.setResult(false);
		}
		
		return result;
	}
}