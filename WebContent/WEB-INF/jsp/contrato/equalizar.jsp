<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.text.*, java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<script type="text/javascript">

function btnProd(r){
    $("#Indice"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-up'></i>");
    $(r).attr("onclick", "btnProd2(this);");
} 

function btnProd2(r){	
    $("#Indice"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-down'></i>");
    $(r).attr("onclick", "btnProd(this);");	
}

function aprovar(ok){
	$.ajax({
    	  url: "<c:url value='/contrato/aceitarIndices'/>",
    	  data: {
    		idContrato: ok.id,
    	  },
    	  success: function( data ) {
    		  location.href="<c:url value='/aditivo/listarAditivos'/>";
    	  }
    	}); 
}

$( document ).ready(function() {
	$(".indice").hide();
 
 });
</script>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Relatório de vencimento das cotações </small></h1>
    </div>
  </div><!-- /.row -->   
<br>
	
<c:set var="link" value="contrato/listarContratos" scope="request" />
		
<table class="table table-striped table-bordered tablesorter">
	<thead>
    	<tr>
                  <th> # </th>  
                  <th> Inicio da Vigência </th> 
                  <th> Fim da Vigência </th>
                  <th> Reajustar até </th>  
                  <th> Fornecedor </th>
                  <th>  </th>              
		</tr>
	</thead>
	<tbody>
		<%					
			Date date = new Date();
			GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
			GregorianCalendar dataAtual = (GregorianCalendar) GregorianCalendar.getInstance();
			GregorianCalendar dataMesAdiante = (GregorianCalendar) GregorianCalendar.getInstance();
			dataAtual.setTime(date);
			dataMesAdiante.setTime(date);
			dataMesAdiante.add(Calendar.MONTH, 1);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			List contratos = HibernateUtilTest.executarConsultaHQL("from Contrato where tipoContrato.id <> :idStatus", "idStatus", 1);
			for (Object obj: contratos) {
	            Contrato c = (Contrato)obj;
	            data.setTime(c.getInicioVigencia().getTime());
	            do {
		            data.add(Calendar.MONTH, c.getPeriodicidadeReajuste());
		            if ((data.getTime().after(dataAtual.getTime())) && (data.getTime().before(dataMesAdiante.getTime())) && (c.getFimVigencia().getTime().after(dataAtual.getTime()))) {	 
		%>
					<tr id="contrato_<%= c.getId() %>">
	                      <td> <%= c.getId() %> </td>
	                      <td> <%= df.format(c.getInicioVigencia().getTime()) %> </td>	
	                      <td> <%= df.format(c.getFimVigencia().getTime()) %> </td>
	                      <td> <%= df.format(data.getTime()) %> </td>	
	                      <td> <%= c.getFornecedor().getRazao_social() %> </td>
	                      <td id="<%= c.getId() %>" onclick="btnProd(this)" style="cursor:pointer;"> <i class="fa fa-angle-down"></i> </td>                                           
					</tr>
					<tr class="indice" id="Indice<%=c.getId()%>">
					  <td colspan="7" style="background:#E5E5E5;">       	
						 	<table style="width:80%;" align="center" class="table table-hover tablesorter" >
						 		<%
						 			List indices = HibernateUtilTest.executarConsultaHQL("from Indices where fornecedor.id = " + c.getFornecedor().getId());
						 			if (indices.size()!=0){
						 		%>
							 			<tr>
											<th> # </th>
											<th> Produto </th>
											<th> Índice(%) </th>
											<th> </th>
								   		</tr>
						 		<%
							 			for (Object ob: indices){
							 				Indices i = (Indices)ob;
						 		%>
										   	<tr>
												<td> <%= i.getId() %> </td>
												<td> <%= i.getProduto().getProduto().getDescricao() %> </td>
												<td> <%= i.getIndice() %> </td>
												<td> <a id="<%= c.getId() %>" onclick="aprovar(this)" style="cursor:pointer;">Aprovar</a> </td>
										   	</tr>	
							   	<%
							 			} 
						 			} else {
							 				
							 	%>
							 				<tr>
							 					<th colspan="4"> <center> Nenhum índice encontrato </center> </th>
							 				</tr>
							 	<%			
						 			}
							   	%> 
						   </table>
					  </td>	 	 
					</tr>
					<tr>
 						<td colspan="6">
 							<center>
 								<iframe src="http://www.ipeadata.gov.br/ExibeSerie.aspx?serid=39616&module=M"  style="border:0px;  width:900px; height:7500px;"></iframe>
 							</center>
 						</td>
 					</tr>
		<%
	            	}
	            } while (data.getTime().before(c.getFimVigencia().getTime()));    
			}
		%>
	</tbody>
</table>

</div>
<%@ include file="/EQbaseFim.jsp" %> 
		