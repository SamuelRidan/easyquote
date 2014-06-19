<%@ include file="/EQbase.jsp" %> 

<%@ taglib uri="/tags/tags" prefix="tags"%>

<ul id="pesquisaPedido" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/pesquisaPedido/editarPesquisaPedido"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/pesquisaPedido/excluirPesquisaPedido"/>')">Excluir</a></li>
</ul>

      <div id="page-wrapper">
	        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Pesquisa de Satisfação </small></h1>
          </div>
        </div><!-- /.row -->   

              <div class="panel-body">
<div class="row">
          <div class="col-lg-12">
 
<form class="well form-inline" action="<c:url value="/pesquisaPedido/listarPesquisaPedidos"/>" method="post" >
   <label>Cod. Pedido:</label> <input type="text" class="input-small" name="pesquisaPedido.pedido.id" value="${sessaoGeral.valor.get('pesquisaPedido').pedido.id}" placeholder="codPedido">
	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>



<c:choose>
	<c:when test="${!empty pesquisaPedidos}">
		
		<c:set var="link" value="pesquisaPedido/listarPesquisaPedidos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
		    	    <th>Cod. Pedido</th>
                    <th>Entregou no Prazo ?</th>
                    <th>Qualidade dos Produtos/Serviços no Setor</th>
                    <th>Comunicação do Fornecedor</th>
                    <th>Pontuação 1</th>
                    <th>Pontuação 2</th>
                    <th>Pontuação 3</th>
                    <th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pesquisaPedidos}" var="item">
					<tr id="pesquisaPedido_${item.id}">
                        <td> ${item.pedido.id} </td>
                        <td> ${item.pergunta1} </td>
                        <td> ${item.pergunta2} </td>
                        <td> ${item.pergunta3} </td>
                        <td> ${item.pontuacao1} </td>
                        <td> ${item.pontuacao2} </td>
                        <td> ${item.pontuacao3} </td>
						<td> <a href="<c:url value='/pesquisaPedido/editarPesquisaPedido/'/>${item.id}" >Editar</a></td>                     
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
  </div>

<%@ include file="/EQbaseFim.jsp" %> 		

