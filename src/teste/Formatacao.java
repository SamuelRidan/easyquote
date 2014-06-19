package teste;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Formatacao {
	
		
    public static String formatarNumero(double valor) {  
			NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  		
			String valor1;
			 valor1 = "R$ "+nf.format (valor);  
			
			return valor1;
     } 	
    
	
    public static String formatarNumero(BigDecimal valor) {  
			NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  		
			String valor1;
			 valor1 = "R$ "+nf.format (valor);  
			
			return valor1;
     } 	    
    
	

    public static String formatarData(GregorianCalendar valor) {  
    	System.out.println(valor);
    	String valor0;
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	valor0 = df.format(valor);   	

	    return valor0;
     } 	    
    
}
