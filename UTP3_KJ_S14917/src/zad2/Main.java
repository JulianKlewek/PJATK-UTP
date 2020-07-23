/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad2;


public class Main {
  public static void main(String[] args) {
    String dirName = System.getProperty("user.home")+"/UTP3dir";
    String resultFileName = "UTP3res.txt";
    Futil.processDir(dirName, resultFileName);
  }
}
