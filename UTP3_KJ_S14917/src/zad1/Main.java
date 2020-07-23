/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
	
	    
  public static void main(String[] args) throws Exception {
	  
	  URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
	  BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
	  Map<String, List<String>> anagramy = new LinkedHashMap<>();
	  List<Set<String>> anagram = new ArrayList<>();
	  List<String> result = new ArrayList<>();
	  int max;
          Anagrams.getAnagrams(br)
       	  .forEach(System.out::println);

      br.lines().forEach(p -> anagramy.computeIfAbsent(p.chars()
    		  .sorted()
    		  .mapToObj(c -> String.valueOf((char)c))
    		  .collect(Collectors.joining()), l -> new ArrayList<String>()).add(p));
      
      
      max = anagramy.values().stream().max(Comparator.comparing(List::size)).get().size();
      
      anagramy.entrySet().stream()
      		.filter(stringListEntry -> stringListEntry.getValue().size() == max)
      		.forEach((p) -> {
                  for (int i = 0; i <p.getValue().size() ; i++)
                      System.out.print(p.getValue().get(i) + " ");
                  System.out.println();
      });
  }
}
