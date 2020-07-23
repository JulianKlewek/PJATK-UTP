/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;

public class Purchase {
	
	private String product;
	private String data;
	private double price;
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetoeSupport = new VetoableChangeSupport (this); 
	
	public Purchase() {
		
	}
	
	public Purchase(String prod, String data, double price) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.product = prod;
		this.price = price;
	}
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		// TODO Auto-generated method stub
		changeSupport.firePropertyChange ("data", this.data, data); 
		this.data = data;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws PropertyVetoException {
		// TODO Auto-generated method stub
		 vetoeSupport.fireVetoableChange ( "price", this.price, price);
		 changeSupport.firePropertyChange ( "price", this.price, price);
		 this.price = price;
	}
	
	public String toString() {
	 return "Purchase [prod=" + product.toString() + ", data=" + data.toString() + ", price=" + price + "]";
	}


	public void vetoableChange(PropertyChangeEvent arg0) throws PropertyVetoException {
		// TODO Auto-generated method stub		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
	
	public void addPropertyChangeListener (PropertyChangeListenerExample p) {
		changeSupport.addPropertyChangeListener (p);
	}
	public void removePropertyChangeListener (PropertyChangeListenerExample p) {
		changeSupport.removePropertyChangeListener (p);
	} 
	
	public void addVetoableChangeListener (VetoableChangeListenerExample v) {
		 vetoeSupport.addVetoableChangeListener (v);
	}
		public void removeVetoableChangeListener (VetoableChangeListenerExample v) {
		 vetoeSupport.removeVetoableChangeListener (v);
	} 
}  
