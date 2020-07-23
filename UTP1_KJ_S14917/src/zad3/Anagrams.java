/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Anagrams {
	
	private File allwordsFile;
	private Scanner allwordsFileScanner;
	
	@SuppressWarnings("unused")
	private String anagramsInFileInput;
	
    protected List<String> allwords = new ArrayList<String>();
    protected List<String> allwordsForInput = new ArrayList<String>();
    protected List<ArrayList<String>> wrodsToFindResultList = new ArrayList<ArrayList<String>>();

    private static final int INDEX_ALLWORDS = 0;
    private static final boolean nonEMPTY_ALLWORDS = false;
    
    public Anagrams(String constructorInput) {
        
    	this.anagramsInFileInput = constructorInput;
    	this.allwordsFile = new File(constructorInput);
        
    	try {
		
    		this.allwordsFileScanner = new Scanner(allwordsFile);
		
    	} catch (FileNotFoundException ex) {
			
			// TODO Auto-generated catch block			
			System.out.println("Nie mozna znalezc pliku!");
			ex.printStackTrace();
		
		}
        
        while (allwordsFileScanner.hasNext()) {
            
        	String wordInFile = allwordsFileScanner.next();
            allwords.add(wordInFile);
            allwordsForInput.add(wordInFile);       
        }
 
        allwordsFileScanner.close();
    }
 
    public void sortAnagramList() {
        
    	while (allwords.isEmpty() == nonEMPTY_ALLWORDS) {
        
        	ArrayList<String> allwordsAnagramList = new ArrayList<String>();
            
        	String getWord = allwords.remove(INDEX_ALLWORDS);
            allwordsAnagramList.add(getWord);
            
            char[] wordsArray = (getWord).toCharArray();
            Arrays.sort(wordsArray);
            
            for (int i = 0; i < allwords.size(); i++) {
                 
            	char[] wordChars = allwords.get(i).toCharArray();
                Arrays.sort(wordChars);
                
                if (Arrays.equals(wordsArray, wordChars)) {
                	allwordsAnagramList.add(allwords.remove(i));
                    i -= 1;
                }
            }
 
            allwordsAnagramList.sort((itemToCompare1, itemToCompare2) -> itemToCompare1.compareTo(itemToCompare2));
 
            wrodsToFindResultList.add(allwordsAnagramList);
        }
    }
 
    public List<ArrayList<String>> getSortedByAnQty() {
        
    	this.sortAnagramList();
        
    	List<ArrayList<String>> anagramsResult = wrodsToFindResultList.stream().sorted((compareItem1, compareItem2) -> compareItem1.get(INDEX_ALLWORDS).compareTo(compareItem2.get(INDEX_ALLWORDS)))
                .sorted((item1, item2) -> item2.size() - item1.size()).collect(Collectors.toList());
 
        return anagramsResult;
    }
 
    public String getAnagramsFor(String word) {
       
    	String findVariable = word;
        ArrayList<String> arrayAnagramsList = new ArrayList<String>();
        
        char[] wordsArray = findVariable.toCharArray();
        Arrays.sort(wordsArray);
        
        for (int i = 0; i < allwordsForInput.size(); i++) {
            
        	if ((allwordsForInput.get(i)).equals(findVariable) == nonEMPTY_ALLWORDS) {
                
        		String foundWord = allwordsForInput.get(i);
                char[] wordChars = foundWord.toCharArray();
                
                Arrays.sort(wordChars);
                
                if (Arrays.equals(wordsArray, wordChars)) {

                	arrayAnagramsList.add(foundWord);
                }
            }
        }

        ArrayList<String> foundWordsList = (ArrayList<String>) arrayAnagramsList.stream().sorted((s1, s2) 
        										-> s1.compareTo(s2)).collect(Collectors.toList());
 
        return showResultWordsToFind(findVariable, foundWordsList);
    }
    

    public String showResultWordsToFind(String wordToFind, ArrayList<String> findWordsList) {
		
    	final String srednik = ": ";
    	String result = wordToFind + srednik + findWordsList.toString();
    	
    	return result;
    }
}  
