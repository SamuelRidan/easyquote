<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar Principais </small></h1>
          </div>
        </div><!-- /.row -->     

<ul id="principal" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/principal/editarPrincipal"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/principal/excluirPrincipal"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/principal/criarPrincipal"/>" > Criar principal </a>

<br><br>

<form class="well form-inline" action="<c:url value="/principal/listarPrincipals"/>" method="post" >
    <input type="text" class="input-small numero-inteiro" name="principal.precoTotal" value="${sessaoGeral.valor.get('principal').precoTotal}" placeholder="PrecoTotal">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Principals </h3>

<c:choose>
	<c:when test="${!empty principals}">
		
		<c:set var="link" value="principal/listarPrincipals" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> PrecoTotal </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${principals}" var="item">
					<tr id="principal_${item.id}">
                        <td> ${item.precoTotal} </td>
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
