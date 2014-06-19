<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>

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
		<c:forEach items="${cotacoes}" var="cotacao">
			<tr id="cotacao_${cotacao.id}">
                     <td> <fmt:formatDate value="${cotacao.dataAbertura.time}" /> </td>
                     <td> <fmt:formatDate value="${cotacao.dataLimiteResposta.time}" /> </td>
                     <td>
	                     <c:forEach items="${tipoStatus}" var="itemStatus">
								<c:if test="${cotacao.status.id == itemStatus.id}">  ${itemStatus.descricao}  </c:if>
						 </c:forEach>
					 </td>
					 <td>						
	                     <c:forEach items="${tipoSetor}" var="itemSetor">
						 	<c:if test="${cotacao.setor.id == itemSetor.id}">  ${itemSetor.descricao} </c:if> 
						 </c:forEach>
					 </td> 						
                     <td> ${cotacao.obs} </td>
                     <td> <a href="<c:url value="/listaCotacao/listarListaCotacaos/${cotacao.id}"/>" ><i class="fa fa-edit"></i> Visualizar lista </a> </td>		                                           
			</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<%@ include file="/EQbaseFim.jsp" %> 
		