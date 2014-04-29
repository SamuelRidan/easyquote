<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>


      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar Produtos </small></h1>
          </div>
        </div><!-- /.row -->   


<ul id="produto" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/produto/editarProduto"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/produto/excluirProduto"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/produto/criarProduto"/>" > Criar produto </a>

<br><br>

<form class="well form-inline" action="<c:url value="/produto/listarProdutos"/>" method="post" >
    <input type="text" class="input-small" name="produto.descricao" value="${sessaoGeral.valor.get('produto').descricao}" placeholder="Descricao">
    <input type="text" class="input-small numero-inteiro" name="produto.preco" value="${sessaoGeral.valor.get('produto').preco}" placeholder="Preco">
    <input type="text" class="input-small numero-inteiro" name="produto.codSAP" value="${sessaoGeral.valor.get('produto').codSAP}" placeholder="CodSAP">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Produtos </h3>

<c:choose>
	<c:when test="${!empty produtos}">
		
		<c:set var="link" value="produto/listarProdutos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Descricao </th>
                    <th> Preco </th>
                    <th> CodSAP </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="item">
					<tr id="produto_${item.id}">
                        <td> ${item.descricao} </td>
                        <td> ${item.preco} </td>
                        <td> ${item.codSAP} </td>
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