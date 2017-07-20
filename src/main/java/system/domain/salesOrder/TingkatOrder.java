package system.domain.salesOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@Column(nullable=false, unique=true)
	private String orderNumber;
	
	private Integer postalCode;
	private String address;
	private String floor;
	private String unit;
	
//	@Column(name = "customer_id", insertable=false, updatable=false)
//	private Integer customerId;
//	@ManyToOne(optional=false)
//  @JoinColumn(name="customer_id",referencedColumnName="customer_id")
	
	/**
	 * @ManyToOne(cascade=CascadeType.REFRESH,optional=true)中将属性optional设置为true，这可以使得即使外键为空时仍可以向表中添加数据。
	 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name="customer_id")
	private Customer customer;
	private String customerName;
	private String attention;
	private String mobile;
		
	private String mealType;
	private String packMethod;
	private String weekdays;
	private Integer orderDays;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private Boolean newCustomer;
	private Boolean trial;
	private Boolean nonContinue;
	//can see here from web
	@Column(columnDefinition="varchar(10) DEFAULT 'O'")
	private String status;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	
//	@Column(name="menu_id", insertable=false, updatable=false)
//	private Integer menuId;
//	@ManyToOne( optional=false)//(cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumn(name="menu_id", referencedColumnName="menu_id")
	@ManyToOne(cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name="menu_id")
	private ProductMenu productMenu;
	private String menuName;
	private Integer noOfPax;
	@Column(columnDefinition="Double DEFAULT 0")
	private Double pricePerPax;
	@Column(columnDefinition="Double DEFAULT 0.00")
	private Double subTotalAmount;
	
	@Column(columnDefinition="Decimal(10,2) DEFAULT 0.00")
	private Double additionalRice;
	@Column(columnDefinition="Decimal(10,2) DEFAULT 0")
	private Double riceAmount;
	@Column(columnDefinition="Decimal(10,2) DEFAULT 0")
	private Double totalAmount;
	
//	@Column(name="driver_id", insertable=false, updatable=false)
//	private Integer driverId;
//	@ManyToOne(optional=false)//(cascade={CascadeType.PERSIST,CascadeType.MERGE})
//	@JoinColumn(name="driver_id", referencedColumnName="driver_id")
	@ManyToOne(cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name="driver_id")
	private DeliveryDriver driver;
	@OneToMany
	private List<PreSetRemark> preSetRemarks = new ArrayList<PreSetRemark>();
	
	@Column(name="status",  columnDefinition="O")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public Double getPricePerPax() {
		return pricePerPax;
	}
	public void setPricePerPax(Double pricePerPax) {
		this.pricePerPax = pricePerPax;
	}
	public Double getSubTotalAmount() {
		return subTotalAmount;
	}
	public void setSubTotalAmount(Double subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}
	public Double getAdditionalRice() {
		return additionalRice;
	}
	public void setAdditionalRice(Double additionalRice) {
		this.additionalRice = additionalRice;
	}
	public Double getRiceAmount() {
		return riceAmount;
	}
	public void setRiceAmount(Double riceAmount) {
		this.riceAmount = riceAmount;
	}
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
//	public Integer getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(Integer customerId) {
//		this.customerId = customerId;
//	}
	public Integer getOrderDays() {
		return orderDays;
	}
	public void setOrderDays(Integer orderDays) {
		this.orderDays = orderDays;
	}
//	public Integer getMenuId() {
//		return menuId;
//	}
//	public void setMenuId(Integer menuId) {
//		this.menuId = menuId;
//	}
	public Integer getNoOfPax() {
		return noOfPax;
	}
	public void setNoOfPax(Integer noOfPax) {
		this.noOfPax = noOfPax;
	}
//	public Integer getDriverId() {
//		return driverId;
//	}
//	public void setDriverId(Integer driverId) {
//		this.driverId = driverId;
//	}
}
