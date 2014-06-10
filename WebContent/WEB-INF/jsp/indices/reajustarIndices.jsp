<%@page import="teste.HibernateUtilTest"%>
<%@page import="java.util.*, java.text.*"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
<%@page import="java.util.*, java.text.*, scada.modelo.*"%>

 <div id="page-wrapper">
   <div class="row">
     <div class="col-lg-12">
       <h1><small><span style="color:#31708F">//</span> Reajuste de índices de contrato </small></h1>
     </div>
   </div><!-- /.row -->   
       
<%
	int i = 1;
%>
<script type="text/javascript">

	function salvarIndices(){
		fornecedor = document.getElementById("fornecedor");	
		tbl = document.getElementById("tabela");
		linhas = tbl.getElementsByTagName("tr");
			
		for (i=1; i<linhas.length; i++){
			
			produtos = document.getElementById(i);
			indices = document.getElementById("valor_"+i);

			$.ajax({
	      	  url: "<c:url value='/indice/salvarListaIndice'/>",
	      	  data: {
		      		forn: fornecedor.innerHTML,
		      		produto: produtos.innerHTML,
		      		indice: indices.value
	      	  },
	      	  	success: function( data ) {
	      	  		alert("Informações salvas com sucesso!");
	      	  	}
			});
		}
						
	}
</script>
        
<form class="form-horizontal" action="<c:url value="/indices/salvarIndices"/>" method="post">
  <fieldset>
  	<c:choose>
		<c:when test="${!empty contrato}">
		
			<c:set var="link" value="indices/reajustarIndices" scope="request" />
			<table class="table table-striped table-bordered tablesorter">
				<c:forEach items="${contrato}" var="contrato">
					<tbody>
						<tr>
							<td style="width: 50px"> Fornecedor: </td>
							<c:forEach items="${tipoFornecedor}" var="fornecedor">
								<c:if test="${contrato.fornecedor.id == fornecedor.id}"> <td id="fornecedor" style="width: 10px">${fornecedor.id }</td><td>${fornecedor.razao_social}</td></c:if>
							</c:forEach>
						</tr>
						<tr>
							<table class="table table-striped table-bordered tablesorter" id="tabela">
								<thead>
							    	<tr>
							    		<th> Sequencial </th>
							    		<th> Produto </th>
							    		<th> Índice(%) </th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listaCotacaos}" var="item">
										<tr id="produto_${item.id}">
											<td id="<%= i %>">${item.id}</td>
											<c:forEach items="${tipoProduto}" var="tipoproduto">
												<c:if test="${item.produto.id == tipoproduto.id}"> <td>${tipoproduto.descricao}</td></c:if>
											</c:forEach>
											<c:choose>
												<c:when test="${!empty indices}">
													<c:forEach items="${indices}" var="indices">
														<td> <input type="text" id="valor_<%= i++ %>" value="${indices.indice}" placeholder="Índice(%)"> </td>
													</c:forEach>
												</c:when>
												<c:otherwise>
														<td> <input type="text" id="valor_<%= i++ %>" value="" placeholder="Índice(%)"> </td>
												</c:otherwise>
											</c:choose>
										</tr>									
									</c:forEach>
								</tbody>
							</table>							
	  					</tr>
	  				</tbody>
	  			</c:forEach>
  			</table>
  		</c:when>
  		<c:otherwise>
			<br><br><h4> Nenhum registro foi encontrado </h4>
		</c:otherwise>
  	</c:choose>
  	
  	<a class="btn btn-primary" href="#" onclick="salvarIndices()">Salvar</a>
  	<a class="btn btn-danger" href="<c:url value="/contrato/equalizarForn"/>" > Cancelar </a>
  </fieldset>
</form>
</div>

<%@ include file="/EQbaseFim.jsp" %> 
		