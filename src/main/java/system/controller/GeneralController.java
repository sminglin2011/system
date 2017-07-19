package system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import system.domain.menu.PreSetRemark;
import system.domain.menu.PreSetRemarkRepository;
import system.domain.menu.ProductMenu;
import system.domain.menu.ProductMenuRepository;
import system.exception.MyException;
import system.tools.AjaxResponseBody;

@Controller
public class GeneralController {
	
	@Autowired
	private ProductMenuRepository productMenuRepository;
	@Autowired
	private PreSetRemarkRepository preSetRemarkRepository;
	@Autowired
	private AjaxResponseBody result;
	
	@InitBinder("productMenu")    
    public void initBinder(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("productMenu.");    
    } 
	@InitBinder("preSetRemark")    
    public void initBinder1(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("preSetRemark.");    
    }
	
	/************************ product Menu *******************************/
	@RequestMapping("/productMenuMaster")
	public String ProductMenuMaster(Model model){
		model.addAttribute("menuList", productMenuRepository.findAll());
		return "system/productMenuMaster";
	}
	@RequestMapping("/addProductMenu")
	public String addProductMenu(String id, Model model) {
		System.out.println(id.equals("0"));
		System.out.println("id="+id);
		ProductMenu menu = new ProductMenu();
		if(id != null && !id.equals("0")){
			menu = productMenuRepository.findOne(Long.valueOf(id));
		}
		model.addAttribute("menu", menu);
		return "system/addProductMenu";
	}
	
	@RequestMapping("/productMenu/saveProductMenu")
	@ResponseBody
	public AjaxResponseBody saveProductMenu(ProductMenu menu, HttpServletResponse response){
		System.out.println(menu + "--------------description="+menu.getDescription() + "-" + menu.getId());
		try {
			productMenuRepository.save(menu);
		} catch (DataIntegrityViolationException e) {
			result.setMsg("Duplicate Description");
			result.setResult(false);
		}
		
		return result;
	}
	@RequestMapping("/deleteProductMenu")
	@ResponseBody
	public AjaxResponseBody deleteProductMenu(String ids, HttpServletResponse response){
		System.out.println("--------------ids="+ids);
		String[] tempId = ids.split(",");
		Long[] arrayId = new Long[tempId.length];
		
		for(int i=0;i<tempId.length;i++){
			System.out.println(tempId[i]);
			arrayId[i]=Long.valueOf(tempId[i]);
		}
		List<Long> idList = new ArrayList<Long>();
		idList = java.util.Arrays.asList(arrayId);// 字符数组转lis
		Iterable<ProductMenu> mes = productMenuRepository.findAll(idList);
		try {
			productMenuRepository.delete(mes);
		} catch (Exception e) {
			result.setMsg("Delete action error");
			result.setResult(false);
		}
		
		return result;
	}
	/************************ product Menu end *******************************/
	
	/************************ preSetRemark *******************************/
	@RequestMapping("/preSetRemarkMaster")
	public String preSetRemarkMaster(Model model){
		model.addAttribute("remarkList", preSetRemarkRepository.findAll());
		return "system/preSetRemarkMaster";
	}
	@RequestMapping("/addPreSetRemark")
	public String addPreSetRemark(String id, Model model) {
		System.out.println(id.equals("0"));
		System.out.println("id="+id);
		PreSetRemark remark = new PreSetRemark();
		if(id != null && !id.equals("0")){
			remark = preSetRemarkRepository.findOne(Long.valueOf(id));
		}
		model.addAttribute("remark", remark);
		return "system/addPreSetRemark";
	}
	
	@RequestMapping("/preSetRemark/savePreSetRemark")
	@ResponseBody
	public AjaxResponseBody savePreSetRemark(PreSetRemark remark, HttpServletResponse response){
		try {
			preSetRemarkRepository.save(remark);
		} catch (DataIntegrityViolationException e) {
			result.setMsg("Duplicate Description");
			result.setResult(false);
		}
		
		return result;
	}
	@RequestMapping("/deletePreSetRemark")
	@ResponseBody
	public AjaxResponseBody deletePreSetRemark(String ids, HttpServletResponse response){
		String[] tempId = ids.split(",");
		Long[] arrayId = new Long[tempId.length];
		
		for(int i=0;i<tempId.length;i++){
			System.out.println(tempId[i]);
			arrayId[i]=Long.valueOf(tempId[i]);
		}
		List<Long> idList = new ArrayList<Long>();
		idList = java.util.Arrays.asList(arrayId);// 字符数组转lis
		Iterable<PreSetRemark> mes = preSetRemarkRepository.findAll(idList);
		try {
			preSetRemarkRepository.delete(mes);
		} catch (Exception e) {
			result.setMsg("Delete action error");
			result.setResult(false);
		}
		
		return result;
	}
	/************************ preSetRemark end *******************************/
}
