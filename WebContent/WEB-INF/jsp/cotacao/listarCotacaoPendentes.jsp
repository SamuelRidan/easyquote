<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Relatório de cotações pendentes </small></h1>
    </div>
  </div><!-- /.row -->   


<br><br>
	
		<c:set var="link" value="cotacao/listarCotacaos" scope="request" />
		
<table class="table table-striped table-bordered tablesorter">
	<thead>
    	<tr>
                  <th> Data de Abertura </th>
                  <th> Limite para Resposta </th>
                  <th> Status </th>
                  <th> Setor </th>
                  <th> Observações </th>
                  <th> Lista de produtos </th>                    
		</tr>
	</thead>
	<tbody>
		<%					
			Date date = new Date();
			GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
			data.setTime(date);	
			data.add(Calendar.DAY_OF_MONTH, 7);	
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			List cotacaos = HibernateUtilTest.executarConsultaHQL("from Cotacao where status.id = :idStatus order by dataLimiteResposta desc", "idStatus", 1);
			for (Object obj: cotacaos) {
	            Cotacao c = (Cotacao)obj;
	            if (c.getDataLimiteResposta().getTime().before(data.getTime())) {	 
		%>
					<tr id="cotacao_<%= c.getId() %>">
	                      <td> <%= df.format(c.getDataAbertura().getTime()) %> </td>
	                      <td> <%= df.format(c.getDataLimiteResposta().getTime()) %> </td>
	                      <td> <%= c.getStatus().getDescricao() %></td>
						  <td> <%= c.getSetor().getDescricao() %></td> 						
	                      <td> <%= c.getObs() %> </td>
	                      <td> <a href="<c:url value="/listaCotacao/listarListaCotacaos/"/><%= c.getId() %>" ><i class="fa fa-edit"></i> Visualizar lista </a> </td>		                                           
					</tr>
		<%
	            }
			}
		%>
	</tbody>
</table>

</div>
<%@ include file="/EQbaseFim.jsp" %> 
		