package easyquote.auxiliar;

public class AuxiliarEmail {
	
	private static String textoHTML = "";
	
	public static String cabecalhoHTML(){
		
		textoHTML = "";
		
		try {
			
			textoHTML = "<html>";
			textoHTML += "<img src='http://imageshack.com/a/img844/72/4lgh.jpg'><br>";
			textoHTML += "Olá comprador! <br><br>";
			textoHTML += "Você tem cotações pendentes para verificar. Segue abaixo a lista das cotações: <br><br><br>";
			
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
			textoHTML += "Acesse htt://localhost:8080/scada e verifique suas cotações<br>";
			textoHTML += "Equipe EasyQuote - Sistemas de cotação<br>";
			textoHTML += "<img src='http://imageshack.com/a/img836/8674/wwi4.jpg'><br></html>";
			
		} catch (Exception e) {
			
			System.out.println("Deu erro na criação do rodapé do Email!");
			e.printStackTrace();
			
		}
		
		return textoHTML;
		
	}

}
