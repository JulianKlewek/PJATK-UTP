package zad1;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {

	  private static String canonicalize(String s) {
	        return Stream.of(s.split("")).sorted().collect(Collectors.joining());
	    }
	  
	    public static  List<Set<String>> getAnagrams(Reader reader) {
	    	

	    	
	        Map<String, Set<String>> map = new BufferedReader(reader).lines()
	                                         .flatMap(Pattern.compile("\\W+")::splitAsStream)
	                                         .collect(Collectors.groupingBy(Anagrams::canonicalize, Collectors.toSet()));
	            return map.values().stream().filter(list -> list.size() > 4).collect(Collectors.toList());
	        
	    }
	    
	    private static int getMax(Map<String, List<String>> anagramy){
	        int max = 0;
	        for (Entry<String, List<String>> entries : anagramy.entrySet()){
	            if(entries.getValue().size() > max)
	                max = entries.getValue().size();
	        }
	        return max;
	    }
}
