<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<script type="text/javascript">

		function excluir(idListaCotacao,linha){
		    	
		    	$.ajax({
		      	  url: "<c:url value='/listaCotacao/excluirProduto'/>",
		      	  data: {
		      		id: idListaCotacao,
		      	  },
		      	  success: function( data ) {
		      		  $("#"+linha.id).remove();
		      	  }
		      	}); 
		}
</script>

   <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Lista de produtos da cotação </small></h1>
          </div>
        </div>    
        
        <a class="btn" href="<c:url value="/listaCotacao/criarListaCotacao"/>" > Incluir produto </a>

<br><br>

		<form class="well form-inline" action="<c:url value="/listaCotacao/listarListaCotacaos"/>" method="post" >
		    <input type="text" class="input-small numero-inteiro" name="listaCotacao.produto" value="${sessaoGeral.valor.get('listaCotacao').produto.descricao}" placeholder="Produto">
		
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Produtos cotados </h3>
		
		<c:choose>
			<c:when test="${!empty listaCotacaos}">
			
				<c:set var="link" value="listacotacao/listarListaCotacaos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
		
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
				    		<th> Sequencial </th>
				    		<th> Produto </th>
				    		<th> Quantidade </th>
				    		<th> &nbsp; </th>
				    		<th> &nbsp; </th>
						</tr>
					</thead>
					<tbody>
								<c:forEach items="${listaCotacaos}" var="item">
									<tr id="produto_${item.id}">
										<td> ${item.id} </td>
										<c:forEach items="${tipoProduto}" var="tipoproduto">
											<c:if test="${item.produto.id == tipoproduto.id}"> <td> ${tipoproduto.descricao} </td> </c:if>
										</c:forEach>
										<td> ${item.quantidade} </td>
										<td> <a href="<c:url value='/listaCotacao/criarListaCotacao'/>" >Editar</a></td>
										<td> <a href="#" onclick="excluir(${item.id},produto_${item.id})">Excluir</a> </td>
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
