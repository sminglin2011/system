package system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import system.tools.SystemConstant;
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
	private CustomerRepository customerRepository;
	@Autowired
	private AjaxResponseBody result;
	
	@RequestMapping("/tingkatOrderMaster")
	public String tingkatOrderMaster(Model model){
		model.addAttribute("tingkatList", tingkatRepository.findAll());
		return "system/tingkat/tingkatOrderMaster";
	}
	
	@RequestMapping("/addTingkat")
	@Transactional(readOnly = true) // try it, can delete
	public String addTingkat(String id, Model model){
		TingkatOrder tingkat = new TingkatOrder();
		if(id != null && !id.equals("0")){
			tingkat = tingkatRepository.findOne(Integer.valueOf(id));
		}
		model.addAttribute("tingkat", tingkat);
		model.addAttribute("menuList", menuRepository.findAll());
		return "system/tingkat/addTingkat";
	}
	
	@RequestMapping("/saveTingkat")
	@ResponseBody
	public AjaxResponseBody saveTingkat(TingkatOrder tingkat){
		try {
			System.out.println("参数tingkat.customer=" + tingkat.getCustomer());
			log.debug("customer ID=" + tingkat.getCustomer().getCustomerId());
			//Order number 为 null, 新建 TingKat Order
			if(tingkat.getOrderNumber() == null) {
				if (tingkat.getCustomer().getCustomerId() == null) { // new customer (there are not customer id on the add page)
					Customer customer = new Customer();
					customer.setName(tingkat.getCustomerName());
					customer.setAddress(tingkat.getAddress());
					customer.setAttention(tingkat.getAttention());
					customer.setFloor(tingkat.getFloor());
					customer.setUnit(tingkat.getUnit());
					customer.setMobile(tingkat.getMobile());
					customer.setPostalCode(tingkat.getPostalCode().toString());
					customer = customerRepository.save(customer);
					tingkat.setCustomer(customer); // if there are some error come out on the saving customer, transaction will rock back
				}
				tingkat.setStatus(SystemConstant.ORDER_STATUS_O); //init status is O
				//获取 Order Number
				tingkat.setOrderNumber(TingKatTools.generateSequenceNo());
			}
			tingkatRepository.save(tingkat);
		} catch (Exception e) {
			log.error("error-log=" + e.getMessage());
			log.debug("on error customer id = " + tingkat.getCustomer());
			result.setResult(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/deleteTingkat")
	@ResponseBody
	public AjaxResponseBody deleteTingkat(String ids, HttpServletResponse response){
		String[] tempId = ids.split(",");
		Integer[] arrayId = new Integer[tempId.length];
		
		for(int i=0;i<tempId.length;i++){
			System.out.println(tempId[i]);
			arrayId[i]=Integer.valueOf(tempId[i]);
		}
		List<Integer> idList = new ArrayList<Integer>();
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
