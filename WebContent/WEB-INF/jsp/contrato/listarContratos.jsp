<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Criar contrato </small></h1>
          </div>
        </div><!-- /.row -->   

 
		<a class="btn" href="<c:url value="/contrato/criarContrato"/>" > Criar contrato </a>
		
		<br><br>
		
		<form class="well form-inline" action="<c:url value="/contrato/listarContratos"/>" method="post" >
		    <input type="text" class="input-small data" name="contrato.inicioVigencia" value="<fmt:formatDate value="${sessaoGeral.valor.get('contrato').inicioVigencia.time}" />" placeholder="InicioVigencia">
		    <input type="text" class="input-small data" name="contrato.fimVigencia" value="<fmt:formatDate value="${sessaoGeral.valor.get('contrato').fimVigencia.time}" />" placeholder="FimVigencia">
		    <input type="text" class="input-small numero-inteiro" name="contrato.periodicidadeReajuste" value="${sessaoGeral.valor.get('contrato').periodicidadeReajuste}" placeholder="PeriodicidadeReajuste">
		
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Contratos </h3>
		
		<c:choose>
			<c:when test="${!empty contratos}">
				
				<c:set var="link" value="contrato/listarContratos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
		                    <th> InicioVigencia </th>
		                    <th> FimVigencia </th>
		                    <th> PeriodicidadeReajuste </th>
		                    <th> Editar </th>
		                    <th> Remover </th>		                    
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contratos}" var="item">
							<tr id="contrato_${item.id}">
		                        <td> <fmt:formatDate value="${item.inicioVigencia.time}" /> </td>
		                        <td> <fmt:formatDate value="${item.fimVigencia.time}" /> </td>
		                        <td> ${item.periodicidadeReajuste} </td>
			                    <td> <a href="<c:url value="/contrato/editarContrato/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			                    <td> <a href="<c:url value="/contrato/excluirContrato/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>		                        
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
		