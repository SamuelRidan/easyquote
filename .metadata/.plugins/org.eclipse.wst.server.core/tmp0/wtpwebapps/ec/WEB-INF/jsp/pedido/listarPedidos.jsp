<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar Pedidos </small></h1>
          </div>
        </div><!-- /.row -->    

		<ul id="pedido" class="dropdown-menu">
			<li><a href="javascript:gerarLinkCompleto('<c:url value="/pedido/editarPedido"/>')">Editar</a></li>
			<li><a href="javascript:deletar('<c:url value="/pedido/excluirPedido"/>')">Excluir</a></li>
		</ul>
		
		<a class="btn" href="<c:url value="/pedido/criarPedido"/>" > Criar pedido </a>
		
		<br><br>
		
		<form class="well form-inline" action="<c:url value="/pedido/listarPedidos"/>" method="post" >
		    <input type="text" class="input-small data" name="pedido.dataEntrega" value="<fmt:formatDate value="${sessaoGeral.valor.get('pedido').dataEntrega.time}" />" placeholder="DataEntrega">
		
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Pedidos </h3>
		
		<c:choose>
			<c:when test="${!empty pedidos}">
				
				<c:set var="link" value="pedido/listarPedidos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
		                    <th> DataEntrega </th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pedidos}" var="item">
							<tr id="pedido_${item.id}">
		                        <td> <fmt:formatDate value="${item.dataEntrega.time}" /> </td>
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