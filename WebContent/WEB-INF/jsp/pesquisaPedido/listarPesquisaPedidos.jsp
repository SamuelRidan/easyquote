<%@ include file="/base.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<ul id="pesquisaPedido" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/pesquisaPedido/editarPesquisaPedido"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/pesquisaPedido/excluirPesquisaPedido"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/pesquisaPedido/criarPesquisaPedido"/>" > Criar pesquisaPedido </a>

<br><br>

<form class="well form-inline" action="<c:url value="/pesquisaPedido/listarPesquisaPedidos"/>" method="post" >
    <input type="text" class="input-small" name="pesquisaPedido.pergunta1" value="${sessaoGeral.valor.get('pesquisaPedido').pergunta1}" placeholder="Pergunta1">
    <input type="text" class="input-small" name="pesquisaPedido.pergunta2" value="${sessaoGeral.valor.get('pesquisaPedido').pergunta2}" placeholder="Pergunta2">
    <input type="text" class="input-small" name="pesquisaPedido.pergunta3" value="${sessaoGeral.valor.get('pesquisaPedido').pergunta3}" placeholder="Pergunta3">
    <input type="text" class="input-small" name="pesquisaPedido.pergunta4" value="${sessaoGeral.valor.get('pesquisaPedido').pergunta4}" placeholder="Pergunta4">
    <input type="text" class="input-small numero-inteiro" name="pesquisaPedido.pontuacao1" value="${sessaoGeral.valor.get('pesquisaPedido').pontuacao1}" placeholder="Pontuacao1">
    <input type="text" class="input-small numero-inteiro" name="pesquisaPedido.pontuacao2" value="${sessaoGeral.valor.get('pesquisaPedido').pontuacao2}" placeholder="Pontuacao2">
    <input type="text" class="input-small numero-inteiro" name="pesquisaPedido.pontuacao3" value="${sessaoGeral.valor.get('pesquisaPedido').pontuacao3}" placeholder="Pontuacao3">
    <input type="text" class="input-small numero-inteiro" name="pesquisaPedido.pontuacao4" value="${sessaoGeral.valor.get('pesquisaPedido').pontuacao4}" placeholder="Pontuacao4">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> PesquisaPedidos </h3>

<c:choose>
	<c:when test="${!empty pesquisaPedidos}">
		
		<c:set var="link" value="pesquisaPedido/listarPesquisaPedidos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Pergunta1 </th>
                    <th> Pergunta2 </th>
                    <th> Pergunta3 </th>
                    <th> Pergunta4 </th>
                    <th> Pontuacao1 </th>
                    <th> Pontuacao2 </th>
                    <th> Pontuacao3 </th>
                    <th> Pontuacao4 </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pesquisaPedidos}" var="item">
					<tr id="pesquisaPedido_${item.id}">
                        <td> ${item.pergunta1} </td>
                        <td> ${item.pergunta2} </td>
                        <td> ${item.pergunta3} </td>
                        <td> ${item.pergunta4} </td>
                        <td> ${item.pontuacao1} </td>
                        <td> ${item.pontuacao2} </td>
                        <td> ${item.pontuacao3} </td>
                        <td> ${item.pontuacao4} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>
