package zad1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Database{
	
    static String dataFromBase;
    String url;
    TravelData td;

    public Database(String url, TravelData td) {
    	this.url=url;
    	this.td=td;
    }
    
    public void create() {
    	Connection conn = null;
    	String tabel= "CREATE TABLE wycieczki ( Kraj varchar(30) NOT NULL,"
    			+ "Data_Przyjazdu date NOT NULL,"
    			+ "Data_Powrotu date NOT NULL,"
    			+ "Miejsce varchar(30) NOT NULL,"
    			+ "Cena varchar(30) NOT NULL,"
    			+ "Symbol_Waluty varchar(30) NOT NULL ); ";
    	String show= "Select * FROM wycieczki";
        
        try {	       
            String country;
            String arrivalDate;
            String returnDate;
            String place;
            String price;
            String currency; 
            String[] tab;
            
            List<String> dataToBase=td.dataToBaseList;
            Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(url);      
            Statement statement = conn.createStatement();
            statement.executeUpdate(tabel);
                
                       
            
            for(int x=0;x<dataToBase.size();x++){
                List<String> text=new ArrayList<>();
                text.add(dataToBase.get(x));
                for(String var: text){   //Kazda oferte splitujemy oddzielnie
                    tab=var.split("/t");
                    country=tab[0];
                    arrivalDate=tab[1];
                    returnDate=tab[2];
                	place=tab[3];
                	price= tab[4];
                	currency=tab[5];
                    statement.executeUpdate(
                    		"INSERT INTO wycieczki VALUES("
                    				+"'"+country+"',"
                    				+" STR_TO_DATE('"+ arrivalDate +"','%Y-%m-%d'),"
                    				+" STR_TO_DATE('"+ returnDate +"','%Y-%m-%d'),"
                    				+"'"+ place +"',"
                    				+"'"+ price +"'," 
                    				+"'"+ currency +"');");
                }
            }
 
                ResultSet rs = statement.executeQuery(show);
                
                rs.close();
                conn.close();
        }                  

        catch(SQLException exc) {
        	System.out.println("SQLException: " + exc.getMessage());
            System.out.println("SQLState: " + exc.getSQLState());
            System.out.println("VendorError: " + exc.getErrorCode());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    static void showData(ResultSet resultSet){
        try{
        	dataFromBase = resultSet.getString(1);
            System.out.println("\n" + dataFromBase + " ");
            dataFromBase = resultSet.getString(2);
            System.out.println(dataFromBase + " ");
            dataFromBase = resultSet.getString(3);
            System.out.println(dataFromBase + " ");
            dataFromBase = resultSet.getString(4);
            System.out.println(dataFromBase + " ");
            dataFromBase = resultSet.getString(5);
            System.out.println(dataFromBase + " ");
            dataFromBase = resultSet.getString(6);
            System.out.println(dataFromBase);
        }catch(SQLException exc) {
                exc.printStackTrace();
        }
    }
    


    public void showGui() {
    	MyJFrame mjf=new MyJFrame(td);
    	mjf.setVisible(true);
    	mjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mjf.pack();
    	
    }
}
