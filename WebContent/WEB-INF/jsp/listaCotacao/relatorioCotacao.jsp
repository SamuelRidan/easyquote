<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %> 
 
<script type="text/javascript">

function btnProd(r){	
    $("#Prod"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-up'></i>");
    $(r).attr("onclick", "btnProd2(this);");
} 

function btnProd2(r){	
    $("#Prod"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-down'></i>");
    $(r).attr("onclick", "btnProd(this);");	
}

$( document ).ready(function() {
	$(".prod").hide();
 
 });
</script>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Relatórios Cotação </small></h1>
          </div>
        </div><!-- /.row -->     

				<c:set var="link" value="listaCotacao/listarListaCotacaos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 	
	<c:choose>
	  <c:when test="${!empty tipoCotacao}">
		<table class="table table-striped table-bordered tablesorter" >
			<tr>
				<th>Cod. Cotação</th>
				<th>Data de Abertura</th>
				<th>Data Limite de Resposta</th>
				<th>Status</th>
				<th>Setor</th>								
				<th>Observação</th>
				<th></th>
			</tr>	
		   <c:forEach items="${tipoCotacao}" var="itemCot">	
			<tr>			
					<td>${itemCot.id}</td>
					<td><fmt:formatDate value="${itemCot.dataAbertura.time}"/></td>
					<td><fmt:formatDate value="${itemCot.dataLimiteResposta.time}"/></td>
					<c:forEach items="${tipoStatus}" var="itemStatus">			
						<c:if test="${itemCot.status.id == itemStatus.id}"> <td>${itemStatus.descricao}</td></c:if>
					</c:forEach>
					<c:forEach items="${tipoSetor}" var="itemSetor">	
						<c:if test="${itemCot.setor.id == itemSetor.id}"><td>${itemSetor.descricao}</td></c:if>							
					</c:forEach>	
					<td>${itemCot.obs}</td>
					<td id="${itemCot.id}" onclick="btnProd(this)" style="cursor:pointer;"> <i class="fa fa-angle-down"></i> </td>
			</tr>		
			<tr class="prod" id="Prod${itemCot.id}">
			  <td colspan="8" style="background:#E5E5E5;">       	
		          <span> Quantidade de Propostas: ${itemCot.quantidadePropostas} - 
		          <a href="<c:url value="/listaCotacao/propostaFornecedor?id=${itemCot.id}"/>"> Click aqui para vê-las </a></span>
			  </td>	 	 
			</tr>					
		 </c:forEach>				 
		</table >

			</c:when>
			<c:otherwise>
				<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
			</c:otherwise>
		</c:choose>      		
		
<%@ include file="/EQbaseFim.jsp" %> 		
