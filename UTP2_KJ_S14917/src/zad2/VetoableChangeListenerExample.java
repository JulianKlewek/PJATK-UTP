package zad2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;


public class VetoableChangeListenerExample implements VetoableChangeListener {

	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		// TODO Auto-generated method stub
		Double newVal = (Double) evt.getNewValue();
		   int val = newVal.intValue();
		   int min = 1000;
		   if (val < min) {
			   throw new PropertyVetoException("Price change to: " + evt.getNewValue() + " not allowed", evt);
		   }
			     
	}
}
