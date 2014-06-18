<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar Contratos Principais </small></h1>
          </div>
        </div><!-- /.row -->     

<c:choose>
	<c:when test="${!empty principals}">
		
		<c:set var="link" value="principal/listarPrincipals" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
		    		<th> Contrato Nº: </th>
                    <th> PrecoTotal </th>
		            <th> Editar </th>	                    
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${principals}" var="item">
					<tr id="principal_${item.id}">
						<td> ${item.contrato.id } </td>
                        <td> ${item.precoTotal} </td>
                        <td> <a href="<c:url value="/principal/editarPrincipal/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>                        
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
