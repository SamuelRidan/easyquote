package scada.auxiliar;

public class AuxiliarEmail {
	
	private static String textoHTML = "";
	private static String textoPURO = "";
	
	public static String cabecalhoHTML(){
		
		textoHTML = "";
		
		try {
			
			textoHTML = "<html>";
			textoHTML += "<img src='<c:url value='/imagens/cabeca_email.jpg'/>'><br>";
			textoHTML += "Olá comprador! <br><br>";
			textoHTML += "Você tem cotações pendentes para verificar. Segue abaixo a lista das cotações: <br><br>";
			
		} catch (Exception e) {
			
			System.out.println("Deu erro na criação do cabeçalho do Email!");
			e.printStackTrace();
			
		}
		
		return textoHTML;
		
	}
	
	public static String rodapeHTML(){
		
		textoHTML = "";
		
		try {
			
			textoHTML += "Entre o mais rápido possível em seu sistema para verificar suas pendências. <br>";
			textoHTML += "Equipe EasyQuote - Sistemas de cotação<br>";
			textoHTML += "<img src='<c:url value='/imagens/rodape_email.jpg'/>'><br></html>";
			
		} catch (Exception e) {
			
			System.out.println("Deu erro na criação do rodapé do Email!");
			e.printStackTrace();
			
		}
		
		return textoHTML;
		
	}
	
	public static String cabecalhoTEXTO(){
		
		try {
			
			textoPURO += "Olá comprador!";
			textoPURO += "Você tem cotações pendentes para verificar. Segue abaixo a lista das cotações: ";
			
		} catch (Exception e) {
			
			System.out.println("Deu erro na criação do corpo do Email!");
			e.printStackTrace();
			
		}
		
		return textoPURO;
		
	}
	
	public static String rodapeTEXTO(){
		
		try {
			
			textoPURO += "Entre o mais rápido possível em seu sistema para verificar suas pendências.";
			textoPURO += "Equipe EasyQuote - Sistemas de cotação";
			
		} catch (Exception e) {
			
			System.out.println("Deu erro na criação do corpo do Email!");
			e.printStackTrace();
			
		}
		
		return textoPURO;
		
	}

}
