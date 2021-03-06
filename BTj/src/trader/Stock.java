package trader;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STOCK database table.
 * 
 */
@Entity
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
@Table(name="STOCK")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String symbol;
	@Column(name="PRICE")
	private float price;
	@Column(name="VERSION")
	private int version=1;

	public Stock() {
	}
	
	public Stock(String symbol, float price){
		this.symbol=symbol;
		this.price=price;
	}
	
	public Stock(String symbol, float price, int version){
	this(symbol, price);
	this.version=version;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}