package trader.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import trader.BrokerException;
import trader.BrokerModel;
import trader.Stock;
import javax.ejb.*;

@ManagedBean(name="stocks")
@RequestScoped
public class StocksManagedBean {
	
	@EJB (mappedName="BT")
    private BrokerModel model;

    private String message = "";
    
    /** Creates a new instance of StocksManagedBean */
    public StocksManagedBean() {
    }

    public String getMessage() {
        return message;
    }

    public Stock[] getAllStocks() {
        Stock[] stocks = null;
        try {
            stocks = model.getAllStocks();
        } catch (BrokerException be) {
            message = be.getMessage();
        }
        return stocks;
    }

}
