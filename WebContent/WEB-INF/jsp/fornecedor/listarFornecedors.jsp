<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Relatório Fornecedor </small></h1>
          </div>
        </div><!-- /.row -->   
        
        


		<form class="well form-inline" action="<c:url value="/fornecedor/listarFornecedors"/>" method="post" >
		    <input type="text" class="input-small" name="fornecedor.cnpj" value="${sessaoGeral.valor.get('fornecedor').cnpj}" placeholder="Cnpj">
		    <input type="text" class="input-small" name="fornecedor.razao_social" value="${sessaoGeral.valor.get('fornecedor').razao_social}" placeholder="Razao_social">
		    <input type="text" class="input-small" name="fornecedor.telefone" value="${sessaoGeral.valor.get('fornecedor').telefone}" placeholder="Telefone">
		    <input type="text" class="input-small" name="fornecedor.segmento" value="${sessaoGeral.valor.get('fornecedor').segmento}" placeholder="Segmento">
		    <input type="text" class="input-small" name="fornecedor.reputacao" value="${sessaoGeral.valor.get('fornecedor').reputacao}" placeholder="Reputacao">
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>

		<c:choose>
			<c:when test="${!empty fornecedors}">
				
				<c:set var="link" value="fornecedor/listarFornecedors" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
		                    <th> CNPJ </th>
		                    <th> Razao social </th>
		                    <th> Telefone </th>
		                    <th> Email </th>
		                    <th> Segmento </th>
		                    <th> Reputação </th>
						</tr>
					</thead>
					<tbody>
		 			
						<c:forEach items="${fornecedors}" var="item">
							<tr id="fornecedor_${item.id}">
		                        <td> ${item.cnpj} </td>
		                        <td> ${item.razao_social} </td>
		                        <td> ${item.telefone} </td>
		                        <td> ${item.email} </td>
		                        <td> ${item.segmento} </td>
		                        <td> ${item.reputacao} </td>
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
