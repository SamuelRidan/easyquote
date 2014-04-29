<%@ include file="/base.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<ul id="cotacao" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/cotacao/editarCotacao"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/cotacao/excluirCotacao"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/cotacao/criarCotacao"/>" > Criar cotação </a>

<br><br>

<form class="well form-inline" action="<c:url value="/cotacao/listarCotacaos"/>" method="post" >
    <input type="text" class="input-small data" name="cotacao.dataAbertura" value="<fmt:formatDate value="${sessaoGeral.valor.get('cotacao').dataAbertura.time}" />" placeholder="Abertura">
    <input type="text" class="input-small data" name="cotacao.dataLimiteResposta" value="<fmt:formatDate value="${sessaoGeral.valor.get('cotacao').dataLimiteResposta.time}" />" placeholder="Limite de Resposta">
    <input type="text" class="input-small numero-inteiro" name="cotacao.formaPgto" value="${sessaoGeral.valor.get('cotacao').formaPgto}" placeholder="Forma de Pagamento">
    <input type="text" class="input-small numero-inteiro" name="cotacao.status" value="${sessaoGeral.valor.get('cotacao').status}" placeholder="Status">
    <input type="text" class="input-small numero-inteiro" name="cotacao.setor" value="${sessaoGeral.valor.get('cotacao').setor}" placeholder="Setor">
    <input type="text" class="input-small" name="cotacao.obs" value="${sessaoGeral.valor.get('cotacao').obs}" placeholder="Obs">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Cotações </h3>

<c:choose>
	<c:when test="${!empty cotacaos}">
		
		<c:set var="link" value="cotacao/listarCotacaos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Data de Abertura </th>
                    <th> Limite para Resposta </th>
                    <th> Status </th>
                    <th> Setor </th>
                    <th> Valor Total </th>
                    <th> Observações </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cotacaos}" var="item">
					<tr id="cotacao_${item.id}">
                        <td> <fmt:formatDate value="${item.dataAbertura.time}" /> </td>
                        <td> <fmt:formatDate value="${item.dataLimiteResposta.time}" /> </td>
                        <c:forEach items="${tipoStatus}" var="itemStatus">
							<c:if test="${item.status == itemStatus.id}"> <td> ${itemStatus.descricao} </td> </c:if>
						</c:forEach>
                        <c:forEach items="${tipoSetor}" var="itemSetor">
							<c:if test="${item.setor == itemSetor.id}"> <td> ${itemSetor.descricao} </td> </c:if> 
						</c:forEach>
                        <td> ${item.valorTotal} </td>
                        <td> ${item.obs} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>
