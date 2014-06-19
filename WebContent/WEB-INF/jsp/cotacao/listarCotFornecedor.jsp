<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Listar cotação </small></h1>
          </div>
        </div><!-- /.row -->  

<br><br>

<form class="well form-inline" action="<c:url value="/cotacao/listarCotacaos"/>" method="post" >
    <input type="text" class="input-small data" name="cotacao.dataAbertura" value="<fmt:formatDate value="${sessaoGeral.valor.get('cotacao').dataAbertura.time}" />" placeholder="Abertura">
    <input type="text" class="input-small data" name="cotacao.dataLimiteResposta" value="<fmt:formatDate value="${sessaoGeral.valor.get('cotacao').dataLimiteResposta.time}" />" placeholder="Limite de Resposta">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>


<c:choose>
	<c:when test="${!empty cotacaos}">
		
		<c:set var="link" value="cotacao/listarCotacaos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Data de Abertura </th>
                    <th> Limite para Resposta </th>
                    <th> Observações </th>
                    <th> Lista de produtos </th>                  
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cotacaos}" var="item">
					<tr id="cotacao_${item.id}">
                        <td> <fmt:formatDate value="${item.dataAbertura.time}" /> </td>
                        <td> <fmt:formatDate value="${item.dataLimiteResposta.time}" /> </td>
                        <td> ${item.obs} </td>
                        <td> <a href="<c:url value="/listaCotacao/listarListaFornecedor/${item.id}"/>" ><i class="fa fa-edit"></i> Visualizar lista </a> </td>		                                           
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
		