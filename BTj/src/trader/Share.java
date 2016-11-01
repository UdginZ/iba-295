package trader;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHARES database table.
 * 
 */
@Entity
@Table(name="SHARES")
@NamedQuery(name="Share.findAll", query="SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") 
	private int id;
	 @Column(name = "QUANTITY") 
	private int quantity;
	@Column(name="SSN")
	private String ssn;
	@Column(name="SYMBOL")
	private String symbol;
	@Column(name="VERSION")
	private int version=1;

	public Share() {
	}

	public Share(int id, int quantity, String ssn, String symbol){
		this.id=id;
		this.quantity=quantity;
		this.ssn = ssn;
		this.symbol = symbol;
	}
	
	public Share(int id, int quantity, String ssn, String symbol, int version){
	this(id, quantity,ssn,symbol);
	this.version=version;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}