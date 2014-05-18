<%@ include file="/base.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<ul id="listaCotacaoFornecedor" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/listaCotacaoFornecedor/editarListaCotacaoFornecedor"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/listaCotacaoFornecedor/excluirListaCotacaoFornecedor"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/listaCotacaoFornecedor/criarListaCotacaoFornecedor"/>" > Criar listaCotacaoFornecedor </a>

<br><br>

<form class="well form-inline" action="<c:url value="/listaCotacaoFornecedor/listarListaCotacaoFornecedors"/>" method="post" >
    <input type="text" class="input-small numero-decimal" name="listaCotacaoFornecedor.preco" value="${sessaoGeral.valor.get('listaCotacaoFornecedor').preco}" placeholder="Preco">
    <input type="text" class="input-small numero-decimal" name="listaCotacaoFornecedor.precoTotal" value="${sessaoGeral.valor.get('listaCotacaoFornecedor').precoTotal}" placeholder="PrecoTotal">
    <input type="text" class="input-small numero-inteiro" name="listaCotacaoFornecedor.idListaCotacao" value="${sessaoGeral.valor.get('listaCotacaoFornecedor').idListaCotacao}" placeholder="IdListaCotacao">
    <input type="text" class="input-small numero-inteiro" name="listaCotacaoFornecedor.idFornecedor" value="${sessaoGeral.valor.get('listaCotacaoFornecedor').idFornecedor}" placeholder="IdFornecedor">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> ListaCotacaoFornecedors </h3>

<c:choose>
	<c:when test="${!empty listaCotacaoFornecedors}">
		
		<c:set var="link" value="listaCotacaoFornecedor/listarListaCotacaoFornecedors" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Preco </th>
                    <th> PrecoTotal </th>
                    <th> IdListaCotacao </th>
                    <th> IdFornecedor </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaCotacaoFornecedors}" var="item">
					<tr id="listaCotacaoFornecedor_${item.id}">
                        <td> <fmt:formatNumber value="${item.preco}" /> </td>
                        <td> <fmt:formatNumber value="${item.precoTotal}" /> </td>
                        <td> ${item.idListaCotacao} </td>
                        <td> ${item.idFornecedor} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>
