<%@ include file="/EQbase.jsp" %>  

<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar pagamento </small></h1>
          </div>
        </div><!-- /.row -->   


		
		<a class="btn" href="<c:url value="/pagamento/criarPagamento"/>" > Criar pagamento </a>
		
		<br><br>
		
		<form class="well form-inline" action="<c:url value="/pagamento/listarPagamentos"/>" method="post" >
		    <input type="text" class="input-small" name="pagamento.descricao" value="${sessaoGeral.valor.get('pagamento').descricao}" placeholder="Descricao">
		
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Pagamentos </h3>
		
		<c:choose>
			<c:when test="${!empty pagamentos}">
				
				<c:set var="link" value="pagamento/listarPagamentos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
		                    <th> Descricao </th>
		                    <th> Editar </th>
		                    <th> Remover </th>			                    
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagamentos}" var="item">
							<tr id="pagamento_${item.id}">
		                        <td> ${item.descricao} </td>
			                    <td> <a href="<c:url value="/pagamento/editarPagamento/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			                    <td> <a href="<c:url value="/pagamento/excluirPagamento/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>		                        
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
