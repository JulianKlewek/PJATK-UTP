/**
 *
 *  @author Klewek Julian S14917
 *
 */

package zad1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Calc {
	
	public String doCalc(String cmd) {
		// TODO Auto-generated method stub
		
		Map<String, BiFunction<BigDecimal,BigDecimal,BigDecimal>> operation = new HashMap<>();		
		String[] args = cmd.split("\\s+");
		
		operation.put("+",BigDecimal::add);
		operation.put("-",BigDecimal::subtract);
		operation.put("*",BigDecimal::multiply);
		operation.put("/",BigDecimal::divide);
		
		BiFunction<BigDecimal, BigDecimal, BigDecimal> operator = operation.get(args[1]);
		BigDecimal first = new BigDecimal(args[0]);
		BigDecimal second = new BigDecimal(args[2]);
		
        try {
        	return operator.apply(first, second).toString();
       	}catch(Exception  e) {
       		try {
                return first.divide(second, 7, RoundingMode.HALF_UP).toString();	
       		}catch(IllegalArgumentException e1) {
       			return "Invalid command to calc";
       		}catch(ArithmeticException e2) {
       			return "Invalid command to calc";
       		}
            
        }

	}
}  
