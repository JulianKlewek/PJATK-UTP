package zad2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Futil {

	public static void processDir(String dirName, String resultFileName) {
		// TODO Auto-generated method stub
		
        List<String> fileContent = new ArrayList<>();
        String Cp1250 = "Cp1250";
        Predicate<Path> isTextFile = e -> e.toString().endsWith(".txt");
        Predicate<Path> isFile = e -> Files.isRegularFile(e);
        
		 try {
	            
	            List<String> path = Files.walk(Paths.get(dirName))
	                    .filter(isFile.and(isTextFile))
	                    .map(p -> p.toAbsolutePath().toString())
	                    .collect(Collectors.toList());

	            path.forEach(p -> {
	                try {
	                    fileContent.add(Files.readAllLines(Paths.get(p), Charset.forName(Cp1250))
	                            .stream()
	                            .collect(Collectors.joining("\n")));
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
         BufferedWriter writer;
		try {
			writer = Files.newBufferedWriter(Paths.get(resultFileName), StandardCharsets.UTF_8);
			
	         for (String s : fileContent) {
	             if(!fileContent.get(fileContent.size() - 1).equals(s)){
	                 writer.write(s + "\n");
	             }else {
	                 writer.write(s);
	             }
	         }
	         writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    
	}

}
