<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<ul id="seguimento" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/seguimento/editarSeguimento"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/seguimento/excluirSeguimento"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/seguimento/criarSeguimento"/>" > Criar seguimento </a>

<br><br>

<form class="well form-inline" action="<c:url value="/seguimento/listarSeguimentos"/>" method="post" >
    <input type="text" class="input-small numero-inteiro" name="seguimento.id" value="${sessaoGeral.valor.get('seguimento').id}" placeholder="Id">
    <input type="text" class="input-small" name="seguimento.descricao" value="${sessaoGeral.valor.get('seguimento').descricao}" placeholder="Descricao">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Seguimentos </h3>

<c:choose>
	<c:when test="${!empty seguimentos}">
		
		<c:set var="link" value="seguimento/listarSeguimentos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Id </th>
                    <th> Descricao </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${seguimentos}" var="item">
					<tr id="seguimento_${item.id}">
                        <td> ${item.id} </td>
                        <td> ${item.descricao} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>

<%@ include file="/EQbaseFim.jsp" %> 