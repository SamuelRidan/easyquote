<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>

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
                  <th>  </th>              
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${contratos}" var="contrato"> 
			<tr id="contrato_${contrato.id}">
                     <td> ${contrato.id} </td>
                     <td> <fmt:formatDate value="${contrato.inicioVigencia.time}"/> </td>	
                     <td> <fmt:formatDate value="${contrato.fimVigencia.time}"/> </td>	
                     <td> <a href="<c:url value="/indices/reajustarIndices/${contrato.id}"/>">Reajustar</a> </td>                                           
			</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<%@ include file="/EQbaseFim.jsp" %> 
		