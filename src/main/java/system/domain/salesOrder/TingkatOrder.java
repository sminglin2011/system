package system.domain.salesOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import system.domain.customer.Customer;
import system.domain.driver.DeliveryDriver;
import system.domain.menu.PreSetRemark;
import system.domain.menu.ProductMenu;

@Data
@Entity
public class TingkatOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private String orderNumber;
	
	private Long postalCode;
	private String address;
	private String floor;
	private String unit;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="customer_id")
	private Customer customer;
	private String customerName;
	private String attention;
	private String mobile;
	
	private String mealType;
	private String packMethod;
	private String weekdays;
	private Long orderDays;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private Boolean newCustomer;
	private Boolean trial;
	private Boolean nonContinue;
	//can see here from web
	
	private String status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="product_menu_id")
	private ProductMenu productMenu;
	private String menuName;
	private Long noOfPax;
	private Double pricePerPax;
	private Double subTotalAmount;
	
	
	private Double additionalRice;
	private Double riceAmount;
	private Double totalAmount;
	
	
	private DeliveryDriver driver;
	@OneToMany
	private List<PreSetRemark> preSetRemarks = new ArrayList<PreSetRemark>();
	
	
	@Column(columnDefinition="O",nullable=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Long getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getPackMethod() {
		return packMethod;
	}
	public void setPackMethod(String packMethod) {
		this.packMethod = packMethod;
	}
	public String getWeekdays() {
		return weekdays;
	}
	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	public Long getOrderDays() {
		return orderDays;
	}
	public void setOrderDays(Long orderDays) {
		this.orderDays = orderDays;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Boolean getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(Boolean newCustomer) {
		this.newCustomer = newCustomer;
	}
	public Boolean getTrial() {
		return trial;
	}
	public void setTrial(Boolean trial) {
		this.trial = trial;
	}
	public Boolean getNonContinue() {
		return nonContinue;
	}
	public void setNonContinue(Boolean nonContinue) {
		this.nonContinue = nonContinue;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public ProductMenu getProductMenu() {
		return productMenu;
	}
	public void setProductMenu(ProductMenu productMenu) {
		this.productMenu = productMenu;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Long getNoOfPax() {
		return noOfPax;
	}
	public void setNoOfPax(Long noOfPax) {
		this.noOfPax = noOfPax;
	}
	@Column(columnDefinition="0")
	public Double getPricePerPax() {
		return pricePerPax;
	}
	public void setPricePerPax(Double pricePerPax) {
		this.pricePerPax = pricePerPax;
	}
	@Column(columnDefinition="0")
	public Double getSubTotalAmount() {
		return subTotalAmount;
	}
	public void setSubTotalAmount(Double subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}
	@Column(columnDefinition="0")
	public Double getAdditionalRice() {
		return additionalRice;
	}
	public void setAdditionalRice(Double additionalRice) {
		this.additionalRice = additionalRice;
	}
	@Column(columnDefinition="0")
	public Double getRiceAmount() {
		return riceAmount;
	}
	public void setRiceAmount(Double riceAmount) {
		this.riceAmount = riceAmount;
	}
	@Column(columnDefinition="0")
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public DeliveryDriver getDriver() {
		return driver;
	}
	public void setDriver(DeliveryDriver driver) {
		this.driver = driver;
	}
	public List<PreSetRemark> getPreSetRemarks() {
		return preSetRemarks;
	}
	public void setPreSetRemarks(List<PreSetRemark> preSetRemarks) {
		this.preSetRemarks = preSetRemarks;
	}
	
}
