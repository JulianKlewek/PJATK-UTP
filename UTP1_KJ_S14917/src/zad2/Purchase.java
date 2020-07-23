/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad2;

import java.util.StringTokenizer;

public class Purchase {
	
	StringTokenizer tokenFile;
	
	private String customerName; 
	private String customerId;
	private String productInList;
	private static final String LINE_SEPARATOR = ";";	
	private Double productPrice; 
	private Double quat;
	private Double valueOfProduct;

	Purchase(String orderInFileLine) {
		
		this.tokenFile = new StringTokenizer(orderInFileLine, LINE_SEPARATOR);		
		this.customerId = tokenFile.nextToken();
		this.customerName = tokenFile.nextToken();	
		this.productInList = tokenFile.nextToken();			
		this.productPrice = Double.parseDouble(tokenFile.nextToken());
		this.quat = Double.parseDouble(tokenFile.nextToken());
		valueOfProduct = this.quat * this.productPrice;
	}


	public String getId() {
		return this.customerId;
	}

	public String getName() {
		return this.customerName;
	}

	public String getProduct() {
		return this.productInList;
	}

	public Double getPrice() {
		return this.productPrice;
	}

	public Double getQty() {
		return this.quat;
	}

	public Double getValue() {
		return this.valueOfProduct;
	}

	public Purchase get() {
		return this;
	}
}
