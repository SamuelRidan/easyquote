<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<div id="page-wrapper">
   <div class="row">
     <div class="col-lg-12">
       <h1><small><span style="color:#31708F">//</span> Listar compradores </small></h1>
     </div>
   </div><!-- /.row -->

<form class="well form-inline" action="<c:url value="/comprador/listarCompradors"/>" method="post" >
    <input type="text" class="input-small numero-decimal" name="comprador.cpf" value="${sessaoGeral.valor.get('comprador').cpf}" placeholder="CPF">
	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Compradores </h3>

<c:choose>
	<c:when test="${!empty compradors}">
		
		<c:set var="link" value="comprador/listarCompradors" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> CPF </th>
                    <th> Telefone </th>
                    <th> Email </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${compradors}" var="item">
					<tr id="comprador_${item.id}">
                        <td> <fmt:formatNumber value="${item.cpf}" /> </td>
                        <td> ${item.telefone} </td>
                        <td> ${item.email} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>

</div>

<%@ include file="/EQbaseFim.jsp" %> 
