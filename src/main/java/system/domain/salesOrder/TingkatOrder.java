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
	@Column(name = "order_id", nullable = false)
	@GeneratedValue
	private Long orderId;
	@Column(nullable=false, unique=true)
	private String orderNumber;
	
	private Long postalCode;
	private String address;
	private String floor;
	private String unit;
	
	@Column(name = "customer_id", insertable=false, updatable=false)
	private Long customerId;
//	@ManyToOne(cascade={CascadeType.ALL})
//	@JoinColumn(name="customer_id")
//	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@ManyToOne(optional=false)
    @JoinColumn(name="customer_id",referencedColumnName="customer_id")
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
	@Column(columnDefinition="varchar(10) DEFAULT 'O'")
	private String status;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	
	@Column(name="menu_id", insertable=false, updatable=false)
	private Long menuId;
	@ManyToOne( optional=false)//(cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="menu_id", referencedColumnName="menu_id")
	private ProductMenu productMenu;
	private String menuName;
	private Long noOfPax;
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
	
	@Column(name="driver_id", insertable=false, updatable=false)
	private Long driverId;
	@ManyToOne(optional=false)//(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="driver_id", referencedColumnName="driver_id")
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
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
//	public Long getCustoemrId() {
//		return custoemrId;
//	}
//	public void setCustoemrId(Long custoemrId) {
//		this.custoemrId = custoemrId;
//	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
}
