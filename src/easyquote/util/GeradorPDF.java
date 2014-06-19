package easyquote.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import easyquote.auxiliar.AuxiliarTexto;
import easyquote.modelo.Contrato;


public class GeradorPDF {    
    
    public static void GerarContratoPDF(Contrato contrato) throws FileNotFoundException, DocumentException, IOException{
        
        Document doc = null;
        OutputStream os = null;

        try {
            
            Rectangle rect = new Rectangle(600, 560);
            doc = new Document(rect, 0, 0, 0, 0);
            
            File pasta = new File("C:/ContratosGerados/");
            
            if ( ! pasta.exists()){
                pasta.mkdirs();
            }
            //cria a stream de saída
            os = new FileOutputStream("C:/ContratosGerados/" + contrato.getId() + "_" + contrato.getFornecedor().getCnpj() + ".pdf");
            
            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);
            
            //abre o documento
            doc.open();
            
            Image img1;
            img1 = Image.getInstance("C:/Users/JMJ/Dropbox/UCB/workspace/easyquote/WebContent/imagens/cabecaemail.jpg");
            img1.setAlignment(Element.ALIGN_CENTER);
            doc.add(img1);
            
            if (contrato.getTipoContrato().getId() == 1){
	            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	            Paragraph p1 = new Paragraph("Sequencial de contrato Nº: " + contrato.getId() + "    \n", f2);
	            Paragraph p2 = new Paragraph(AuxiliarTexto.contratoCompra(contrato), f);
	            p1.setAlignment(Element.ALIGN_RIGHT);
	            p2.setAlignment(Element.ALIGN_CENTER);
	            doc.add(p1);
	            doc.add(p2);
            } else {
            	if (contrato.getTipoContrato().getId() == 2){
            		Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    	            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    	            Paragraph p1 = new Paragraph("Sequencial de contrato Nº: " + contrato.getId() + "    \n", f2);
    	            Paragraph p2 = new Paragraph(AuxiliarTexto.contratoAluguel(contrato), f);
    	            p1.setAlignment(Element.ALIGN_RIGHT);
    	            p2.setAlignment(Element.ALIGN_CENTER);
    	            doc.add(p1);
    	            doc.add(p2);
            	} else {
            		Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    	            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    	            Paragraph p1 = new Paragraph("Sequencial de contrato Nº: " + contrato.getId() + "    \n", f2);
    	            Paragraph p2 = new Paragraph(AuxiliarTexto.contratoPrestadorServico(contrato), f);
    	            p1.setAlignment(Element.ALIGN_RIGHT);
    	            p2.setAlignment(Element.ALIGN_CENTER);
    	            doc.add(p1);
    	            doc.add(p2);
            	}
            }
            
            Image img2;
            img2 = Image.getInstance("C:/Users/JMJ/Dropbox/UCB/workspace/easyquote/WebContent/imagens/rodapeemail.jpg");
            img2.setAlignment(Element.ALIGN_CENTER);
            doc.add(img2);
            
            doc.close();
            
        } finally {
            if (os != null) {
               //fechamento da stream de saída
               os.close();
            }
        }

        
    }
    
    public static void GerarContratoAditivoPDF(Contrato contrato) throws FileNotFoundException, DocumentException, IOException{
        
        Document doc = null;
        OutputStream os = null;

        try {
            
            Rectangle rect = new Rectangle(600, 560);
            doc = new Document(rect, 0, 0, 0, 0);
            
            File pasta = new File("C:/ContratosGerados/");
            
            if ( ! pasta.exists()){
                pasta.mkdirs();
            }
            //cria a stream de saída
            os = new FileOutputStream("C:/ContratosGerados/aditivo_" + contrato.getId() + "_" + contrato.getFornecedor().getCnpj() + ".pdf");
            
            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);
            
            //abre o documento
            doc.open();
            
            Image img1;
            img1 = Image.getInstance("C:/Users/JMJ/Dropbox/UCB/workspace/easyquote/WebContent/imagens/cabecaemail.jpg");
            img1.setAlignment(Element.ALIGN_CENTER);
            doc.add(img1);
            
    		Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Paragraph p1 = new Paragraph("Sequencial de contrato Nº: " + contrato.getId() + "    \n", f2);
            Paragraph p2 = new Paragraph(AuxiliarTexto.contratoAditivo(contrato), f);
            p1.setAlignment(Element.ALIGN_RIGHT);
            p2.setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
            doc.add(p2);
            
            Image img2;
            img2 = Image.getInstance("C:/Users/JMJ/Dropbox/UCB/workspace/easyquote/WebContent/imagens/rodapeemail.jpg");
            img2.setAlignment(Element.ALIGN_CENTER);
            doc.add(img2);
            
            doc.close();
            
        } finally {
            if (os != null) {
               //fechamento da stream de saída
               os.close();
            }
        }

        
    }
    
}

