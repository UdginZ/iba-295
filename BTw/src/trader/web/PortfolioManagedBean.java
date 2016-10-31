package trader.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import trader.BrokerException;
import trader.BrokerModel;
import trader.Customer;
import trader.Share;
import javax.ejb.*;

@ManagedBean(name="portfolio")
@RequestScoped

public class PortfolioManagedBean {
	@EJB (mappedName="BT")
    private BrokerModel model;

    private String message = "";
    private String customerId = "";
    
    /** Creates a new instance of PortfolioManagedBean */
    public PortfolioManagedBean() {
    }

    public String getMessage() {
        return message;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        String name = "";
        try {
            Customer customer = model.getCustomer(customerId);
            name = customer.getCustName();
        } catch (BrokerException be) {
            message = be.getMessage();
        }
        return name;
    }

    public Share[] getShares() {
        Share[] shares = null;
        try {
            shares = model.getAllShares(customerId);
        } catch (BrokerException be) {
            message = be.getMessage();
        }
        return shares;
    }

}
