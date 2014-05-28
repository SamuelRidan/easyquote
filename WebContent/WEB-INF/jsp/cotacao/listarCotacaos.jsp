<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Listar cotação </small></h1>
    </div>
  </div><!-- /.row -->   



<a class="btn" href="<c:url value="/cotacao/criarCotacao"/>" > Criar cotação </a>

<br><br>

<form class="well form-inline" action="<c:url value="/cotacao/listarCotacaos"/>" method="post" >
    <input type="text" class="input-small data" name="cotacao.dataAbertura" value="<fmt:formatDate value="${sessaoGeral.valor.get('cotacao').dataAbertura.time}" />" placeholder="Abertura">
    <input type="text" class="input-small data" name="cotacao.dataLimiteResposta" value="<fmt:formatDate value="${sessaoGeral.valor.get('cotacao').dataLimiteResposta.time}" />" placeholder="Limite de Resposta">
    <input type="text" class="input-small numero-inteiro" name="cotacao.formaPgto" value="${sessaoGeral.valor.get('cotacao').formaPgto}" placeholder="Forma de Pagamento">
    <input type="text" class="input-small numero-inteiro" name="cotacao.status" value="${sessaoGeral.valor.get('cotacao').status}" placeholder="Status">
    <input type="text" class="input-small numero-inteiro" name="cotacao.setor" value="${sessaoGeral.valor.get('cotacao').setor}" placeholder="Setor">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Cotações </h3>

<c:choose>
	<c:when test="${!empty cotacaos}">
		
		<c:set var="link" value="cotacao/listarCotacaos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Data de Abertura </th>
                    <th> Limite para Resposta </th>
                    <th> Status </th>
                    <th> Setor </th>
                    <th> Valor Total </th>
                    <th> Observações </th>
                    <th> Lista de produtos </th>
		            <th> Editar </th>
		            <th> Remover </th>                    
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cotacaos}" var="item">
					<tr id="cotacao_${item.id}">
                        <td> <fmt:formatDate value="${item.dataAbertura.time}" /> </td>
                        <td> <fmt:formatDate value="${item.dataLimiteResposta.time}" /> </td>
                        <td>
                        <c:forEach items="${tipoStatus}" var="itemStatus">
							<c:if test="${item.status.id == itemStatus.id}">  ${itemStatus.descricao}  </c:if>
						</c:forEach>
						</td>
						<td>						
                        <c:forEach items="${tipoSetor}" var="itemSetor">
							<c:if test="${item.setor.id == itemSetor.id}">  ${itemSetor.descricao} </c:if> 
						</c:forEach>
						</td> 						
                        <td> ${item.valorTotal} </td>
                        <td> ${item.obs} </td>
                        <td> <a href="<c:url value="/listaCotacao/listarListaCotacaos/${item.id}"/>" ><i class="fa fa-edit"></i> Visualizar lista </a> </td>
			            <td> <a href="<c:url value="/cotacao/editarCotacao/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			            <td> <a href="<c:url value="/cotacao/excluirCotacao/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>		                                           
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
<%@ include file="/EQbaseFim.jsp" %> 
		