<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>


      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Relatórios Cotação </small></h1>
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
		                    <th> Descrição do produto </th>
		                    <th> Quantidade </th>
		                    <th> Data limite de resposta</th>
						</tr>
					</thead>
 
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
 
				</table>
			</c:when>
			<c:otherwise>
				<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
			</c:otherwise>
		</c:choose>      
		


							<%
					      		
					      		Cotacao cotacao = new Cotacao();
					      		HibernateUtil hibernateUtil = new HibernateUtil();
					      		List<Cotacao> cotacoes = hibernateUtil.buscar(cotacao);
					
								for (Cotacao c : cotacoes ) {			
								
							%>
								<c:forEach items="${tipoCotacao}" var="itemCod">
									<span>aaaaaaa<%=c.getId()%></span>
								</c:forEach>
							<%
								}
							%>		
									
		<c:choose>
			<c:when test="${!empty listaCotacaos}">		 
		<table class="table table-striped table-bordered tablesorter" >
			<tr>
				<th>Cod. Cotação</th>
				<th>Data de Abertura</th>
				<th>Data Limite de Resposta</th>
				<th>Forma de Pagamento</th>				
				<th>Status</th>
				<th>Setor</th>								
				<th>Observação</th>
				<th></th>
			</tr>
		 <c:forEach items="${listaCotacaos}" var="item">		
		   <c:forEach items="${tipoCotacao}" var="itemCot">	
			<tr>			
				<c:if test="${item.cotacao.id == itemCot.id}">
					<td><fmt:formatDate value="${itemCot.dataAbertura.time}"/></td>
					<td><fmt:formatDate value="${itemCot.dataLimiteResposta.time}"/></td>
					<td>${itemCot.cotacao.formaPgto}</td>				
					<td>${itemCot.cotacao.status}</td>
					<td>${itemCot.cotacao.setor}</td>								
					<td>${itemCot.cotacao.obs}</td>
				</c:if> 		
				
				
			</tr>		
			<tr>
					 <td colspan="8"> BBB</td>			 
			</tr>
		 </c:forEach>		
		</c:forEach>		
		</table >

			</c:when>
			<c:otherwise>
				<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
			</c:otherwise>
		</c:choose>      		

 		
		
<%@ include file="/EQbaseFim.jsp" %> 		
