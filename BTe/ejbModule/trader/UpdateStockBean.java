package trader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: UpdateStockBean
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "UpdateStock"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/UpdateStock")
public class UpdateStockBean implements MessageListener {

	@EJB private BrokerModel model;
    /**
     * Default constructor. 
     */
    public UpdateStockBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
    	TextMessage textMsg = (TextMessage)message;
        String text = textMsg.getText();
    	String[] recievedMessage = text.split(",");  
    	String symbol = recievedMessage[0];
    	float price = Float.parseFloat(recievedMessage[1]);
    	Stock stock = model.getStock(symbol);
    	stock.setPrice(price);
    	model.updateStock(stock);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        // TODO Auto-generated method stub
        
    }

}
