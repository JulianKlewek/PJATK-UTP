package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class XList<T> extends ArrayList{
	private Collection<T> coll;
	private List <T> list;

	public XList(T... val) {
		// TODO Auto-generated constructor stub
		this.addAll(Arrays.asList(val));
	}
	
    public XList(Collection<T> coll){
        
        for(T arg : coll) {
           this.add(arg);
        }
    }

	public static <T> XList<T> charsOf(String str) {

		List<String> list = new ArrayList<>();
		String[] splitedTab = str.split("");
		
		for(int i = 0; i<splitedTab.length; i++) {
			list.add(splitedTab[i]);
		}

		
		return new XList(list);
	}

	public static <T> XList<T> tokensOf(String str, String... sep) {

		List<String> list = new ArrayList<>();
		String[] splitedTab;
		
		if(sep.length == 0) {
			splitedTab = str.split(" ");
			for(int i = 0; i<splitedTab.length; i++) {
				list.add(splitedTab[i]);
			}
		}else {
			splitedTab = str.split(sep[0]);
			for(int i = 0; i<splitedTab.length; i++) {
				list.add(splitedTab[i]);
			
		}

	}
		return new XList(list);
	}

	public <T> XList<T> union(Collection<T> col) {

		XList<T> xlist1 = new XList(this);		
		xlist1.addAll(col);		
		return xlist1;
	}
		
	
	public XList<T> union(T... values){
		
		XList<T> xlist1 = new XList(this);
		xlist1.addAll(Arrays.asList(values));
		return xlist1;
	}

	public XList<T> diff(Collection<T> col) {

		XList<T> xlist1 = new XList(this);
		xlist1.removeAll(col);
		return xlist1;
	}

	public XList<T> unique() {

		XList<T> xlist1 = new XList(this);
		LinkedHashSet<T> linkedSet = new LinkedHashSet<>(xlist1);
		xlist1.clear();
		xlist1.addAll(linkedSet);
		return xlist1;
	}
	
    public static <T> XList<T> of(T... array) {

        XList<T> tmp = new XList<>(array);
        return tmp;
    }

    public static <T> XList<T> of(Collection<T> coll) {
    	
        XList<T> tmp = new XList<>(coll);
        return tmp;
    }

	public XList<XList<String>> combine() {
		
		XList<XList<String>> combinedList = new XList();
		XList<XList<String>> tmpList = new XList(this);
		XList<String> first;
		XList<String> second;
		XList<String> third;
		
//		for(int i = 0; i<tmpList.size(); i++) {
//			for(int j = 0; j)
//		}
		
		return null;
	}
	
	

}
