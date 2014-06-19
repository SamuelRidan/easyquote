<%@page import="easyquote.controller.LoginController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>
<%
	int i = 1;
%>
<script type="text/javascript">

	function salvarPrecoLista(){
		sequencialCotacao = document.getElementById("SequencialCotacao");
		operador = document.getElementById("operador");
		formaPagamento = document.getElementById("formaPagamento");
		pagamento = formaPagamento.options[formaPagamento.options.selectedIndex];	
		tbl = document.getElementById("tabela");
		linhas = tbl.getElementsByTagName("tr");
		
		if (!pagamento.value==0){
			
			for (i=1; i<=linhas.length; i++){
				
				produto = document.getElementById(i);
				precoProduto = document.getElementById("valor_"+i);

				$.ajax({
		      	  url: "<c:url value='/listaCotacao/salvarListaFornecedor'/>",
		      	  data: {
			      		cotacao: sequencialCotacao.innerHTML,
			      		op: operador.innerHTML,
			      		formaPgto: pagamento.value,
			      		prod: produto.innerHTML,
			      		preco: precoProduto.value
		      	  },
		      	  	success: function( data ) {
		      	  		alert("Informações salvas com sucesso!");
		      	  	}
				});
			}
				
		} else {
			alert("Escolha a forma de pagamento!");
		}		
	}
</script>

   <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Lista de produtos da cotação </small></h1>
          </div>
        </div>    
		
		<c:choose>
			<c:when test="${!empty listaCotacaos}">
			
				<c:set var="link" value="listacotacao/listarListaCotacaos" scope="request" />
				<table class="table table-striped table-bordered tablesorter">
					<tbody>
						<tr>
							<c:choose>
								<c:when test="${!empty tipoCotacao}">
									<c:forEach items="${tipoCotacao}" var="tipocotacao">
										<td width="50px"> <b>Cotação:</b> </td>
										<td id="SequencialCotacao">${tipocotacao.id}</td>
										<%
											Operador op = new Operador();
											op = LoginController.RetornaOperador();
										%>
										<td width="50px"> <b>Operador:</b> </td>
										<td id="operador" width="5px"><%= op.getId() %></td>
										<td><%=op.getLogin() %>
									</c:forEach>
								</c:when>
							</c:choose>	
							<c:choose>
								<c:when test="${!empty tipoPagamento}">
									<td>
										<select id="formaPagamento">
											<option selected="selected" disabled="disabled" value="0">Selecione a forma de pgto</option>
											<c:forEach items="${tipoPagamento}" var="tipopagamento">
												<option value="${tipopagamento.id }"> ${tipopagamento.id} - ${tipopagamento.descricao}</option>
											</c:forEach>
										</select>
									</td>
								</c:when>
							</c:choose>						
						</tr>
					</tbody>
				</table>		
				<table class="table table-striped table-bordered tablesorter" id="tabela">
					<thead>
				    	<tr>
				    		<th> Sequencial </th>
				    		<th> Produto </th>
				    		<th> Quantidade </th>
				    		<th> Valor(R$) </th>
						</tr>
					</thead>
					<tbody>
								<c:forEach items="${listaCotacaos}" var="item">
									<tr id="produto_${item.id}">
										<td id="<%= i %>">${item.id}</td>
										<c:forEach items="${tipoProduto}" var="tipoproduto">
											<c:if test="${item.produto.id == tipoproduto.id}"> <td>${tipoproduto.descricao}</td></c:if>
										</c:forEach>
										<td> ${item.quantidade} </td>
											<c:choose>
												<c:when test="${!empty tipoLCF}">
													<c:forEach items="${tipoLCF}" var="tipolcf">
														<td> <input type="text" id="valor_<%= i++ %>" value="${tipolcf.preco}" placeholder="Valor(R$)"> </td>
													</c:forEach>
												</c:when>
												<c:otherwise>
														<td> <input type="text" id="valor_<%= i++ %>" value="" placeholder="Valor(R$)"> </td>
												</c:otherwise>
											</c:choose>
									</tr>									
								</c:forEach>
					</tbody>
				</table>
				<a class="btn btn-primary" href="#" onclick="salvarPrecoLista()">Salvar</a>
			</c:when>
			<c:otherwise>
				<br><br><h4> Nenhum registro foi encontrado </h4>
			</c:otherwise>
		</c:choose>
	</div>
		
<%@ include file="/EQbaseFim.jsp" %> 		
