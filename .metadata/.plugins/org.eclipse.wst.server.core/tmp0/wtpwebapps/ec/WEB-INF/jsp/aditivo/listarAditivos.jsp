<%@ include file="/EQbase.jsp" %> 


<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Contrato Aditivo </small></h1>
          </div>
        </div><!-- /.row -->    

		<ul id="aditivo" class="dropdown-menu">
			<li><a href="javascript:gerarLinkCompleto('<c:url value="/aditivo/editarAditivo"/>')">Editar</a></li>
			<li><a href="javascript:deletar('<c:url value="/aditivo/excluirAditivo"/>')">Excluir</a></li>
		</ul>
		
		<a class="btn" href="<c:url value="/aditivo/criarAditivo"/>" > Criar aditivo </a>
		
		<br><br>
		
		<form class="well form-inline" action="<c:url value="/aditivo/listarAditivos"/>" method="post" >
		    <input type="text" class="input-small numero-inteiro" name="aditivo.precoTotal" value="${sessaoGeral.valor.get('aditivo').precoTotal}" placeholder="PrecoTotal">
		
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Aditivos </h3>
		
		<c:choose>
			<c:when test="${!empty aditivos}">
				
				<c:set var="link" value="aditivo/listarAditivos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
		                    <th> PrecoTotal </th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${aditivos}" var="item">
							<tr id="aditivo_${item.id}">
		                        <td> ${item.precoTotal} </td>
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
