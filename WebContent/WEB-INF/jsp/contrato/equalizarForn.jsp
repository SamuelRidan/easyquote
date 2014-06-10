<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Relatório de vencimento das cotações </small></h1>
    </div>
  </div><!-- /.row -->   


<br><br>
	
		<c:set var="link" value="cotacao/listarCotacaos" scope="request" />
		
<table class="table table-striped table-bordered tablesorter">
	<thead>
    	<tr>
                  <th> # </th>  
                  <th> Inicio da Vigência </th> 
                  <th> Fim da Vigência </th>
                  <th> Reajustar até </th>  
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
	                      <td> <a href="<c:url value="/indices/criarIndices?idContrato="/><%= c.getId() %>">Reajustar</a> </td>                                           
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
		