<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar Status</small></h1>
          </div>
        </div><!-- /.row -->    
        

<a class="btn" href="<c:url value="/status/criarStatus"/>" > Criar status </a>

<br><br>

<form class="well form-inline" action="<c:url value="/status/listarStatuss"/>" method="post" >
    <input type="text" class="input-small" name="status.descricao" value="${sessaoGeral.valor.get('status').descricao}" placeholder="Descricao">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Status </h3>

<c:choose>
	<c:when test="${!empty statuss}">
		
		<c:set var="link" value="status/listarStatuss" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Descricao </th>
		            <th> Editar </th>
		            <th> Remover </th>                    
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statuss}" var="item">
					<tr id="status_${item.id}">
                        <td> ${item.descricao} </td>
			             <td> <a href="<c:url value="/status/editarStatus/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			              <td> <a href="<c:url value="/status/excluirStatus/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>                        
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
