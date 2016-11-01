package trader;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
@Table(name="CUSTOMER")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SSN")
	private String ssn;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="CUST_NAME")
	private String custName;
	@Version @Column(name="VERSION")
	private int version = 1;

	public Customer() {
	}
	
	public Customer(String ssn, String name, String addr){
		this.ssn= ssn;
		this.custName= name;
		this.address =addr;
	}
	
	public Customer(String ssn) {
        this(ssn, null, null);
    } 

	public Customer(String ssn, String name, String addr, int version){
		this(ssn, name, addr);
		this.version = version;
	}
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}