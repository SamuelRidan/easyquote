package scada.util;

import java.net.MalformedURLException;  
import org.apache.commons.mail.EmailAttachment;  
import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.HtmlEmail;  
import org.apache.commons.mail.MultiPartEmail;  
import org.apache.commons.mail.SimpleEmail; 

public class CommonsMail {
	
	private static String usuario = "syseasyquote@gmail.com";
	private static String senha = "87ca3be2776f9eb58c719f51b334d5c";
	
	public CommonsMail() throws EmailException, MalformedURLException { 
    } 

	/** 
     * envia email simples(somente texto) 
     * @throws EmailException 
     */  
    public void enviaEmailSimples(String destinatario, String nomeDestinatario) throws EmailException {  
          
        try {
        	System.out.println("Iniciando processo!");
        	
			SimpleEmail email = new SimpleEmail();  
			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
			email.addTo(destinatario, nomeDestinatario); //destinatário  
			email.setFrom(usuario, "EasyQuote - Sistema de Cotações"); // remetente  
			email.setSubject("Teste -> Email simples"); // assunto do e-mail  
			email.setMsg("Teste de Email utilizando commons-email"); //conteudo do e-mail  
			email.setAuthentication(usuario, senha);  
			email.setSmtpPort(465);  
			email.setSSL(true);  
			email.setTLS(true);  
			email.send();
			
			System.out.println("Processo concluído!");
			
		} catch (Exception e) {
			System.out.println("Deu erro!");
			e.printStackTrace();
		}     
    }  
      
      
    /** 
     * envia email com arquivo anexo 
     * @throws EmailException 
     */  
    public void enviaEmailComAnexo() throws EmailException{  
          
        // cria o anexo 1.  
        EmailAttachment anexo1 = new EmailAttachment();  
        anexo1.setPath("teste/teste.txt"); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)  
        anexo1.setDisposition(EmailAttachment.ATTACHMENT);  
        anexo1.setDescription("Exemplo de arquivo anexo");  
        anexo1.setName("teste.txt");          
          
        // cria o anexo 2.  
        EmailAttachment anexo2 = new EmailAttachment();  
        anexo2.setPath("teste/teste2.jsp"); //caminho do arquivo (RAIZ_PROJETO/teste/teste2.jsp)  
        anexo2.setDisposition(EmailAttachment.ATTACHMENT);  
        anexo2.setDescription("Exemplo de arquivo anexo");  
        anexo2.setName("teste2.jsp");         
          
        // configura o email  
        MultiPartEmail email = new MultiPartEmail();  
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
        email.addTo("teste@gmail.com", "Guilherme"); //destinatário  
        email.setFrom("teste@gmail.com", "Eu"); // remetente  
        email.setSubject("Teste -> Email com anexos"); // assunto do e-mail  
        email.setMsg("Teste de Email utilizando commons-email"); //conteudo do e-mail  
        email.setAuthentication("teste", "xxxxx");  
        email.setSmtpPort(465);  
        email.setSSL(true);  
        email.setTLS(true);  
          
        // adiciona arquivo(s) anexo(s)  
        email.attach(anexo1);  
        email.attach(anexo2);  
        // envia o email  
        email.send();  
    }  
      
      
    /** 
     * Envia email no formato HTML 
     * @throws EmailException  
     * @throws MalformedURLException  
     */  
    public static void enviaEmailFormatoHtml(String destinatario, String nomeDestinatario, String assuntoEmail, String html) throws EmailException, MalformedURLException {  
          
        try {
        	
        	System.out.println("Iniciando processo!");
        	
			HtmlEmail email = new HtmlEmail();      
			  
			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
			email.addTo(destinatario, nomeDestinatario); //destinatário  
			email.setFrom(usuario, "EasyQuote - Sistemas de Cotação"); // remetente  
			email.setSubject(assuntoEmail); // assunto do e-mail   
			email.setHtmlMsg(html); // configura a mensagem para o formato HTML    
			email.setAuthentication(usuario, senha);  
			email.setSmtpPort(465);  
			email.setSSL(true);  
			email.setTLS(true);  
			  
			email.send();  // envia email
			
			System.out.println("Processo concluído!");
			
		} catch (Exception e) {
			System.out.println("Erro no processo!");
			e.printStackTrace();
		}
    }  
      
      
    /** 
     * @param args 
     * @throws EmailException  
     * @throws MalformedURLException  
     */  
    public static void main(String[] args) throws EmailException, MalformedURLException {  
         new CommonsMail();
         
    }  
	
}
