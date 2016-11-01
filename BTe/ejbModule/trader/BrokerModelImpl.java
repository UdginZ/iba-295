package trader;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 * Session Bean implementation class BrokerToolImpl
 */
@Stateless(mappedName = "BT")
@LocalBean
//@TransactionManagement(TransactionManagementType.BEAN)
public class BrokerModelImpl implements BrokerModel {
	//@Resource UserTransaction ut;
	
	@PersistenceContext
    private EntityManager em;
	
    /** Creates a new instance of BrokerModelImpl */
    public BrokerModelImpl() {
    }

    // Customer segment state change methods
    /**----------------------------------------------------------
     * Adds the Customer to the broker model
     */
    public void addCustomer(Customer cust)
            throws BrokerException {
        try {
            em.persist(cust);
        } catch (EntityExistsException ex) {
            throw new BrokerException("Duplicate Id : " + cust.getSsn());
        }
    }

    /**-------------------------------------------------------------
     * deletes the customer from the broker model
     */
   // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCustomer(Customer cust)
            throws BrokerException {
        String id = cust.getSsn();
        cust = em.find(Customer.class, id);

        if (cust == null) {
            throw new BrokerException("Record for " + id + " not found");
        } else {
            em.remove(cust);
        }

    }

    /**-------------------------------------------------------------
     * Updates the customer in the broker model
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateCustomer(Customer cust)
            throws BrokerException {
        Customer c = em.find(Customer.class, cust.getSsn());
        if (c == null) {
            throw new BrokerException("Customer : " + cust.getSsn() + " not found");
        } else {
        	try{
            em.merge(cust);
        	}catch(OptimisticLockException ole){
        		throw new BrokerException("Record for " + cust.getSsn() + " has been modified since retrieval");
        	}
        }
    }

    // Customer segment state query methods
    /**-------------------------------------------------------------
     * Given an id, returns the Customer from the model
     */
    public Customer getCustomer(String id)
            throws BrokerException {
        Customer cust = em.find(Customer.class, id);
        if (cust != null) {
            return cust;
        } else {
            throw new BrokerException("Customer : " + id + " not found");
        }
    }

    /**-------------------------------------------------------------
     * Returns all customers in the broker model
     */
    public Customer[] getAllCustomers()
            throws BrokerException {
        Query query = em.createNativeQuery("SELECT * FROM CUSTOMER", Customer.class);
        List customers = query.getResultList();
        return (Customer[]) customers.toArray(new Customer[0]);
    }

    public Share[] getAllShares(String customerId) throws BrokerException {
        Query query = em.createNativeQuery("SELECT * FROM SHARES WHERE SSN = '" + customerId + "'", Share.class);
        List shares = query.getResultList();

        if (shares.size() == 0) {
            throw new BrokerException("Shares for " + customerId + " not found");
        } else {
            return (Share[]) shares.toArray(new Share[0]);
        }
    }

    public void addShare(Share cs) throws BrokerException {
        Share[] shares = getAllShares(cs.getSsn());
        for (int i = 0; i < shares.length; i++) {
            if (cs.getSymbol().equals(shares[i].getSymbol())) {
                throw new BrokerException("Duplicate Share : " + cs.getSsn() + " " + cs.getSymbol());
            }
        }
        em.persist(cs);
    }

    public void updateShare(Share cs) throws BrokerException {
        Share share = em.find(Share.class, cs.getId());
        if (share == null) {
            throw new BrokerException("Share : " + cs.getSsn() + " " + cs.getSymbol() + " not found.");
        } else {
            em.merge(cs);
        }
    }

    public Stock[] getAllStocks() throws BrokerException {
        Query query = em.createNativeQuery("SELECT * FROM STOCK", Stock.class);
        List stocks = query.getResultList();
        return (Stock[]) stocks.toArray(new Stock[0]);
    }

    public Stock getStock(String symbol) throws BrokerException {
        Stock stock = em.find(Stock.class, symbol);
        if (stock == null) {
            throw new BrokerException("Stock : " + symbol + " not found");
        } else {
            return stock;
        }
    }

    public void addStock(Stock stock) throws BrokerException {
        try {
            em.persist(stock);
        } catch (EntityExistsException exe) {
            throw new BrokerException("Duplicate Stock : " + stock.getSymbol());
        }
    }

    public void updateStock(Stock stock) throws BrokerException {
        Stock s = em.find(Stock.class, stock.getSymbol());
        if (s == null) {
            throw new BrokerException("Stock : " + stock.getSymbol() + " not found");
        } else {
            em.merge(stock);
        }
    }

    public void deleteStock(Stock stock) throws BrokerException {
        String id = stock.getSymbol();
        stock = em.find(Stock.class, id);
        if (stock == null) {
            throw new BrokerException("Stock : " + stock.getSymbol() + " not found");
        } else {
            em.remove(stock);
        }
    } 

}
