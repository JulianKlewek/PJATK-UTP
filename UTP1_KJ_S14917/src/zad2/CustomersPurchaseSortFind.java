/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
	
	List<Purchase> myOrdersList =  new ArrayList<Purchase>();
    
	public void readFile(String file_name) {
		
		try {
			
			BufferedReader readFileVar = new BufferedReader(new FileReader(file_name));
			String lineInFile;			
			while ((lineInFile = readFileVar.readLine()) != null ){
				
			Purchase orderLineInFile = new Purchase(lineInFile);
			myOrdersList.add(orderLineInFile);						
			}		

			readFileVar.close();
		
		} catch (IOException ex) {
			System.out.println("B³¹d odczytu pliku");
			ex.printStackTrace();
		}
		
		myOrdersList.sort(new Comparator<Purchase>() {

			@Override
			 public int compare(Purchase purchase1, Purchase purchase2){

	            return  purchase1.getId().compareTo(purchase2.getId());
	        }
		});

	}
	
	public void showPurchaseFor(String customerId) {
		
		System.out.println("Klient " + customerId);
		Collections.sort(myOrdersList, new Comparator<Purchase>() {
			
	        @Override
	        public int compare(Purchase valueToCompare1, Purchase valueToCompare2){
	            return  valueToCompare1.getQty().compareTo(valueToCompare2.getQty());
	        }
	    });	
		
		for (Purchase itemInList : myOrdersList) {
			
			if(itemInList.getId().equals(customerId)) {
				showResultsByItem(itemInList);
			}
		}
	}
	

	public void showSortedBy(String sortVar) {
		
		String nameVar = "Nazwiska";
		String costVar = "Koszty";
		
		System.out.println(sortVar);
		
		if (sortVar == nameVar){
			
			myOrdersList.sort(new Comparator<Purchase>() {
		        @Override
		        public int compare(Purchase value1, Purchase value2){
		            return  value1.getName().compareTo(value2.getName());
		        }
		    });		
			
			for (Purchase nameFromPurchase : myOrdersList) {
				sortedByNamesResults(nameFromPurchase );
			}
		}else 
			if (sortVar == costVar){
			
			Collections.sort(myOrdersList, new Comparator<Purchase>() {
		        @Override
		        public int compare(Purchase item1, Purchase item2){
		            return  item2.getValue().compareTo(item1.getValue());
		        }
		    });	
			
			for (Purchase customer_Order_in_file : myOrdersList) {
				sortedByCostResults(customer_Order_in_file);
			}
		}else {
			System.out.println("Nie mo¿na posortowaæ po podanym argumencie");
		}
	}

	
	public void sortedByNamesResults(Purchase purchase) {
		System.out.println(purchase.getId() + ";" 
					+ purchase.getName() + ";" 
					+ purchase.getProduct() + ";"
					+ purchase.getPrice() + ";" 
					+ purchase.getQty());

	}
	
	public void sortedByCostResults(Purchase purchase) {
		System.out.println(purchase.getId() + ";" 
				+ purchase.getName() + ";" 
				+ purchase.getProduct() + ";" 
				+ purchase.getPrice() + ";" 
				+ purchase.getQty() 
				+ " (koszt: " + purchase.getValue() + ")");
	}
	
	public void showResultsByItem(Purchase purchase) {
		System.out.println(purchase.getId() + ";"
						+ purchase.getName() + ";"
						+ purchase.getProduct() + ";"
						+ purchase.getPrice() + ";" 
						+ purchase.getQty());

	}
}
