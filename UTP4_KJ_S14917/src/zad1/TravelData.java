package zad1;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class TravelData {

    List<String> list;
	List<String> dataToBaseList;

	public TravelData(File catalog) {

        List<String> filesList=new ArrayList<>();

        List<String> pliki=new ArrayList<>();
        pliki.addAll(Arrays.asList(catalog.list()));
        pliki.sort(Collections.reverseOrder());

        for(int x=pliki.size()-1;x>=0;x--){
            List<String> line=new ArrayList<>();
            try{
                FileReader fr=new FileReader(catalog + "/" + pliki.get(x));
                BufferedReader br=new BufferedReader(fr);
                line.add(br.readLine());
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            filesList.addAll(line);
        }
        this.list=filesList;
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
    
        String[] listaKraj;
        listaKraj=locale.split("_");

        List<String> info=new ArrayList<>();
        List<String> dataToBase=new ArrayList<>();
        String[] tab;
        
        for(int x=0;x<list.size();x++){
            List<String> data=new ArrayList<>();
            List<String> napis=new ArrayList<>();
            napis.add(list.get(x));
            
            for(String slowa: napis){ 
	            tab=slowa.split("\t");
	            for(int i=0;i<tab.length;i++){
	                data.add(tab[i]);
	            }
	            String output="";
	            String toBase=""; 
	            
//	            jak local pl
                if(locale.equals(listaKraj[0] + "_" + listaKraj[1])){

                    Locale language=new Locale(listaKraj[0]);
                    Locale.setDefault(language);

                    String[] lang;
                    lang=data.get(0).split("_");

                    Number number;
                    String num="";
                    String country;
                    country= data.get(1);


                    //KRAJE
                    Locale[] loc =Locale.getAvailableLocales();
                    Locale panstwo;
                    for(int i=0;i<loc.length;i++){
                        String nazwKraju=loc[i].getDisplayCountry();
                        if(nazwKraju.equals(data.get(1))){
                            String countryCode=loc[i].getCountry();
                            panstwo=new Locale(listaKraj[0],countryCode);
                            country=panstwo.getDisplayCountry();
                        }
                    }

                    if(locale.equals("pl_PL")){
                        Locale ang=new Locale("en");
                        Locale.setDefault(ang);
                    }
                    if(locale.equals("en_GB")){
                        Locale pl=new Locale("pl");
                        Locale.setDefault(pl);
                    }

                    Locale[] loc2=Locale.getAvailableLocales();
                    for(int i=0;i<loc2.length;i++) {
                        String countryName = loc[i].getDisplayCountry();
                        if (countryName.equals(data.get(1))) {
                            String countryCode=loc[i].getCountry();
                            panstwo=new Locale(listaKraj[0], countryCode);
                            Locale.setDefault(language);
                            country=panstwo.getDisplayCountry();
                        }
                    }


                    //Formatuje liczby w zaleznosci od locale
                    if(lang[0].equals("pl")) {
                        try {
                            DecimalFormat df = (DecimalFormat)
                                    NumberFormat.getInstance(new Locale(lang[0]));
                            number = df.parse(data.get(5));
                            if(locale.equals("pl_PL")){
                                NumberFormat nf = NumberFormat.getInstance(
                                        new Locale(lang[0])
                                );
                                nf.setMinimumFractionDigits(1);
                                num = nf.format(number);
                            }
                            if(locale.equals("en_GB")){
                                NumberFormat nf = NumberFormat.getInstance();
                                nf.setMinimumFractionDigits(1);
                                num = nf.format(number);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if(lang[0].equals("en")) {
                        try{
                            DecimalFormat df = (DecimalFormat)
                            NumberFormat.getInstance(new Locale(lang[0]));
                            DecimalFormatSymbols sym=df.getDecimalFormatSymbols();
                            sym.setDecimalSeparator('.');
                            df.setDecimalFormatSymbols(sym);
                            number=df.parse(data.get(5));
                            if(locale.equals("pl_PL")){
                                NumberFormat nf=NumberFormat.getInstance();
                                nf.setMinimumFractionDigits(1);
                                num = nf.format(number);
                            }
                            if(locale.equals("en_GB")){
                                NumberFormat nf=NumberFormat.getInstance(
                                        new Locale(lang[0])
                                );
                                nf.setMinimumFractionDigits(1);
                                num = nf.format(number);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    Locale.setDefault(language);
                    Locale wyrazy=Locale.getDefault();

//                    	sout
                    for(int i=1;i<data.size();i++){
                        if(i==1){
                                output+=country + " ";
                                toBase+=country+"/t";
                        }
                        else if(i==5){
                            output+=num + " ";
                            toBase+=num+"/t";
                        }
                        else if(i==4){
                            ResourceBundle msgs= ResourceBundle.getBundle("Warianty",wyrazy);
                            String var=msgs.getString(data.get(i));
                            output+=var + " ";
                            toBase+=var+"/t";
                        }
                        else {
                        	output+=data.get(i) + " ";
                            toBase+=data.get(i)+"/t";
                        }
                        
                    }
                    info.add(output);
                    dataToBase.add(toBase);
                }
                
            }
            
        }
        this.dataToBaseList=dataToBase;
        return info;
    }
    
    public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

    public List<String> getDataToBaseList() {
		return dataToBaseList;
	}

	public void setDataToBaseList(List<String> dataToBaseList) {
		this.dataToBaseList = dataToBaseList;
	}

}
