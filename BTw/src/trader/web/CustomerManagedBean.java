package trader.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import trader.BrokerException;
import trader.BrokerModel;
import trader.Customer;

@ManagedBean(name="customerDetails")
@RequestScoped
public class CustomerManagedBean {

    /** Creates a new instance of CustomerManagedBean */
    public CustomerManagedBean() {
    }
    @EJB (mappedName="BT")
    private BrokerModel model;

    private String message = "";
    private String customerId = "";
    private String customerName = "";
    private String customerAddress = "";
    private int version;

    public int getVersion(){
    	return version;
    }
    public void setVersion(int version){
    	 this.version = version;
    }
    
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMessage() {
        return message;
    }

    public Customer[] getAllCustomers() {
        Customer[] customers = null;
        try {
            customers = model.getAllCustomers();
        } catch (BrokerException ex) {
            message = ex.getLocalizedMessage();
        }
        return customers;
    }

    public String retrieveCustomer() {
        try {
            Customer customer = model.getCustomer(customerId);
            customerId = customer.getSsn();
            customerAddress = customer.getAddress();
            customerName = customer.getCustName();
            version = customer.getVersion();
        } catch (BrokerException ex) {
            message = ex.getLocalizedMessage();
        }
        return "CustomerDetails";
    }

    public String updateCustomer() {
        try {
            Customer customer = new Customer(customerId);
            customer.setAddress(customerAddress);
            customer.setCustName(customerName);
            model.updateCustomer(customer);
        } catch (BrokerException ex) {
            message = ex.getLocalizedMessage();
        }
        return "CustomerDetails";
    }

    public String addCustomer() {
        try {
            Customer customer = new Customer(customerId);
            customer.setAddress(customerAddress);
            customer.setCustName(customerName);
            model.addCustomer(customer);
        } catch (BrokerException ex) {
            message = ex.getLocalizedMessage();
        }
        return "CustomerDetails";
    }

    public String deleteCustomer() {
        try {
            Customer customer = new Customer(customerId);
            model.deleteCustomer(customer);
            customerId = "";
            customerAddress = "";
            customerName = "";
        } catch (BrokerException ex) {
            message = ex.getLocalizedMessage();
        }
        return "CustomerDetails";
    }

}
