<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Listar Cota��o </small></h1>
          </div>
        </div><!-- /.row -->     

		<ul id="listaCotacao" class="dropdown-menu">
			<li><a href="javascript:gerarLinkCompleto('<c:url value="/listaCotacao/editarListaCotacao"/>')">Editar</a></li>
			<li><a href="javascript:deletar('<c:url value="/listaCotacao/excluirListaCotacao"/>')">Excluir</a></li>
		</ul>
		
		<!--  <a class="btn" href="<c:url value="/listaCotacao/criarListaCotacao"/>" > Criar listaCotacao </a> -->
		
		<form class="well form-inline" action="<c:url value="/listaCotacao/listarListaCotacaos"/>" method="post" >
		    <input type="text" class="input-small numero-inteiro" name="listaCotacao.produto" value="${sessaoGeral.valor.get('listaCotacao').produto.descricao}" placeholder="Produto">
		    <input type="text" class="input-small numero-inteiro" name="listaCotacao.cotacao" value="${sessaoGeral.valor.get('listaCotacao').cotacao.id}" placeholder="Cotacao">
		
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Produtos cotados </h3>
		
		<c:choose>
			<c:when test="${!empty listaCotacaos}">
				
				<c:set var="link" value="listaCotacao/listarListaCotacaos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
		                    <th> Sequencial de Cotacao </th>
		                    <th> Descri��o do produto </th>
		                    <th> Quantidade </th>
		                    <th> Data limite de resposta</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaCotacaos}" var="item">
							<tr id="listaCotacao_${item.id}">
								<td> ${item.cotacao.id} </td>
		                        <c:forEach items="${tipoProduto}" var="itemProd">
									<c:if test="${item.produto == itemProd.id}"> <td> ${itemProd.descricao} </td> </c:if> 
								</c:forEach>
		                        <td> ${item.quantidade} </td>
		                        <c:forEach items="${tipoCotacao}" var="itemCot">
									<c:if test="${item.cotacao.id == itemCot.id}"> <td> <fmt:formatDate value="${itemCot.dataLimiteResposta.time}" /> </td> </c:if> 
								</c:forEach>
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
