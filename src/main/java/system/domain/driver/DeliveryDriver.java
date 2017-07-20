package system.domain.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import system.domain.salesOrder.TingkatOrder;

@Data
@Entity
public class DeliveryDriver implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	private String name;
	@Column(nullable=false, unique=true)
	private String vehicleNumber;
	private String contactNumber;
	@OneToMany(mappedBy="driver", targetEntity=TingkatOrder.class, fetch=FetchType.LAZY)
	private List<TingkatOrder> tingkatOrders = new ArrayList<TingkatOrder>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public List<TingkatOrder> getTingkatOrders() {
		return tingkatOrders;
	}
	public void setTingkatOrders(List<TingkatOrder> tingkatOrders) {
		this.tingkatOrders = tingkatOrders;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

}
