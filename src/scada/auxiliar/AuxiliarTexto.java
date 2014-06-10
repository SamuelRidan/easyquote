package scada.auxiliar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import scada.modelo.Contrato;
import scada.modelo.ListaCotacao;
import teste.HibernateUtilTest;

public class AuxiliarTexto {
	
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
			textoHTML += "Acesse http://localhost:8080/scada e verifique suas cotações<br>";
			textoHTML += "Equipe EasyQuote - Sistemas de cotação<br>";
			textoHTML += "<img src='http://imageshack.com/a/img836/8674/wwi4.jpg'><br></html>";
			
		} catch (Exception e) {
			
			System.out.println("Deu erro na criação do rodapé do Email!");
			e.printStackTrace();
			
		}
		
		return textoHTML;
		
	}

	public static String contratoCompra(Contrato contrato){
		String texto = "";
		DateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		
		texto += "Com início de vigência em " + formatada.format(contrato.getInicioVigencia().getTime()) + " e com fim de vigência em " + formatada.format(contrato.getFimVigencia().getTime());
		texto += "\n\nDeclaramos para os devidos fins que XXXXXXXXXXXXXXXXXXXX, portador do CNPJ xxx.xxx.xxx.xxxx-xx, " +
				"situado em xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, no bairro xxxxxxxxxxxxx da cidade xxxxxxxxxxxxxxxx, CEP xxxxx-xxx, " +
				"doravante demonimado comprador firma contrato de compra com " + contrato.getFornecedor().getRazao_social() + " portador do CNPJ "
				+ contrato.getFornecedor().getCnpj() + ", doravante denominado fornecedor a cumprir com as regulamentações deste contrato e a entregar o descrito abaixo:\n";
		
		List produto = HibernateUtilTest.executarConsultaHQL("from ListaCotacao where cotacao.id = :idCotacao", "idCotacao", contrato.getCotacao().getId());
		for (Object obj: produto){
			ListaCotacao lc = (ListaCotacao)obj;		
			texto += "\n" + lc.getProduto().getDescricao() + " na quantidade de " + lc.getQuantidade();
		}
		
		texto += "\n\n";
		texto += "Clausula 1: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 2: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 3: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 4: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 5: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n\n";
		texto += "Concordam com o texto ambas as partes";
		
		return texto;
	}
	
	public static String contratoAluguel(Contrato contrato){
		String texto = "";
		DateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		
		texto += "Com início de vigência em " + formatada.format(contrato.getInicioVigencia().getTime()) + " e com fim de vigência em " + formatada.format(contrato.getFimVigencia().getTime());
		texto += "\n\nDeclaramos para os devidos fins que XXXXXXXXXXXXXXXXXXXX, portador do CNPJ xxx.xxx.xxx.xxxx-xx, " +
				"situado em xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, no bairro xxxxxxxxxxxxx da cidade xxxxxxxxxxxxxxxx, CEP xxxxx-xxx, " +
				"doravante demonimado comprador firma contrato de aluguel com " + contrato.getFornecedor().getRazao_social() + " portador do CNPJ "
				+ contrato.getFornecedor().getCnpj() + ", doravante denominado fornecedor a cumprir com as regulamentações deste contrato e a entregar o descrito abaixo:\n";

		List produto = HibernateUtilTest.executarConsultaHQL("from ListaCotacao where cotacao.id = :idCotacao", "idCotacao", contrato.getCotacao().getId());
		for (Object obj: produto){
			ListaCotacao lc = (ListaCotacao)obj;		
			texto += "\n" + lc.getProduto().getDescricao() + " na quantidade de " + lc.getQuantidade();
		}
		
		texto += "\n\n";
		texto += "Clausula 1: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 2: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 3: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 4: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 5: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n\n";
		texto += "Concordam com o texto ambas as partes";
		
		return texto;
		
	}

	public static String contratoPrestadorServico(Contrato contrato){
		String texto = "";
		DateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		
		texto += "Com início de vigência em " + formatada.format(contrato.getInicioVigencia().getTime()) + " e com fim de vigência em " + formatada.format(contrato.getFimVigencia().getTime());
		texto += "\n\nDeclaramos para os devidos fins que XXXXXXXXXXXXXXXXXXXX, portador do CNPJ xxx.xxx.xxx.xxxx-xx, " +
				"situado em xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, no bairro xxxxxxxxxxxxx da cidade xxxxxxxxxxxxxxxx, CEP xxxxx-xxx, " +
				"doravante demonimado comprador firma contrato de aluguel com " + contrato.getFornecedor().getRazao_social() + " portador do CNPJ "
				+ contrato.getFornecedor().getCnpj() + ", doravante denominado fornecedor a cumprir com as regulamentações deste contrato e a prestar o serviço descrito abaixo:\n";

		List produto = HibernateUtilTest.executarConsultaHQL("from ListaCotacao where cotacao.id = :idCotacao", "idCotacao", contrato.getCotacao().getId());
		for (Object obj: produto){
			ListaCotacao lc = (ListaCotacao)obj;		
			texto += "\n" + lc.getProduto().getDescricao() + " na quantidade de " + lc.getQuantidade();
		}
		
		texto += "\n\n";
		texto += "Clausula 1: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 2: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 3: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 4: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n";
		texto += "Clausula 5: Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. " +
				"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.";
		texto += "\n\n";
		texto += "Concordam com o texto ambas as partes";
		
		return texto;
		
	}
}
