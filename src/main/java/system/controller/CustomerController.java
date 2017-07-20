package system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.domain.customer.Customer;
import system.domain.customer.CustomerRepository;
import system.domain.menu.PreSetRemark;
import system.domain.menu.ProductMenu;
import system.tools.AjaxResponseBody;

@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AjaxResponseBody result;
	
	@RequestMapping("/customerMaster")
	public String custmerMaster(Model model){
		model.addAttribute("customerList", customerRepository.findAll());
		return "system/customer/customerMaster";
	}
	
	@RequestMapping("/addCustomer")
	public String addCustomer(String id, Model model){
		Customer customer = new Customer();
		if(id != null && !id.equals("0")){
			customer = customerRepository.findOne(Integer.valueOf(id));
		}
		model.addAttribute("customer", customer);
		return "system/customer/addCustomer";
	}
	@RequestMapping("/saveCustomer")
	@ResponseBody
	public AjaxResponseBody saveCustomer(Customer customer){
		System.out.println("customer ?= " + customer.getName());
		try {
			customerRepository.save(customer);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@RequestMapping("/deleteCustomer")
	@ResponseBody
	public AjaxResponseBody deleteCustomer(String ids, HttpServletResponse response){
		System.out.println("--------------ids="+ids);
		String[] tempId = ids.split(",");
		Integer[] arrayId = new Integer[tempId.length];
		
		for(int i=0;i<tempId.length;i++){
			System.out.println(tempId[i]);
			arrayId[i]=Integer.valueOf(tempId[i]);
		}
		List<Integer> idList = new ArrayList<Integer>();
		idList = java.util.Arrays.asList(arrayId);// 字符数组转lis
		Iterable<Customer> mes = customerRepository.findAll(idList);
		try {
			customerRepository.delete(mes);
		} catch (Exception e) {
			result.setMsg("Delete action error");
			result.setResult(false);
		}
		
		return result;
	}
	
	@RequestMapping("customerSelection")
	public String customerSelection(String customerName, Model model){
		model.addAttribute("customerList", customerRepository.findAll());
		return "system/customer/customerSelection";
	}
}
