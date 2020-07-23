package zad1;

import java.awt.Button;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class MyJFrame  extends JFrame{

	private static final long serialVersionUID = 1L;
	private TravelData td;
	private JTable table;

	
	public MyJFrame(TravelData travelData) {
		this.td=travelData;
		List<String> data=travelData.getDataToBaseList();
		DefaultTableModel model= new DefaultTableModel();
		String[] tab;
		model.addColumn("Kraj");
		model.addColumn("Data wyjazdu");
		model.addColumn("Data powrotu");
		model.addColumn("Miejsce");
		model.addColumn("Cena");
		model.addColumn("Symbol_waluty");
	
		for(int i=0;i<data.size();i++) {
			List<String> result=new ArrayList<>();
			result.add(data.get(i));
			for(String s: result) {
				tab=s.split("/t");
				String[]resultTab=new String[tab.length];
				for(int x=0;x<tab.length;x++) {
					resultTab[x]=tab[x];
				}
				model.addRow(resultTab);
			}
		}	

		table=new JTable(model);
		
		JScrollPane sp=new JScrollPane(table);
//		JButton button = new JButton("Zmień język");
//		button.setBounds(1200, 600, 100, 50);
    	this.add(sp);
//    	this.add(button);
    	this.setTitle("Oferty");
	}
}
