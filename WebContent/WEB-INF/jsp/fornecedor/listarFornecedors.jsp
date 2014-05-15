<%@ include file="/base.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<ul id="fornecedor" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/fornecedor/editarFornecedor"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/fornecedor/excluirFornecedor"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/fornecedor/criarFornecedor"/>" > Criar fornecedor </a>

<br><br>

<form class="well form-inline" action="<c:url value="/fornecedor/listarFornecedors"/>" method="post" >
    <input type="text" class="input-small numero-inteiro" name="fornecedor.id" value="${sessaoGeral.valor.get('fornecedor').id}" placeholder="Id">
    <input type="text" class="input-small" name="fornecedor.cnpj" value="${sessaoGeral.valor.get('fornecedor').cnpj}" placeholder="Cnpj">
    <input type="text" class="input-small" name="fornecedor.razao_social" value="${sessaoGeral.valor.get('fornecedor').razao_social}" placeholder="Razao_social">
    <input type="text" class="input-small" name="fornecedor.telefone" value="${sessaoGeral.valor.get('fornecedor').telefone}" placeholder="Telefone">
    <input type="text" class="input-small" name="fornecedor.segmento" value="${sessaoGeral.valor.get('fornecedor').segmento}" placeholder="Segmento">
    <input type="text" class="input-small" name="fornecedor.reputacao" value="${sessaoGeral.valor.get('fornecedor').reputacao}" placeholder="Reputacao">
    <input type="text" class="input-small numero-inteiro" name="fornecedor.pessoa" value="${sessaoGeral.valor.get('fornecedor').pessoa}" placeholder="Pessoa">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Fornecedors </h3>

<c:choose>
	<c:when test="${!empty fornecedors}">
		
		<c:set var="link" value="fornecedor/listarFornecedors" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Id </th>
                    <th> Cnpj </th>
                    <th> Razao_social </th>
                    <th> Telefone </th>
                    <th> Segmento </th>
                    <th> Reputacao </th>
                    <th> Pessoa </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${fornecedors}" var="item">
					<tr id="fornecedor_${item.id}">
                        <td> ${item.id} </td>
                        <td> ${item.cnpj} </td>
                        <td> ${item.razao_social} </td>
                        <td> ${item.telefone} </td>
                        <td> ${item.segmento} </td>
                        <td> ${item.reputacao} </td>
                        <td> ${item.pessoa} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>
