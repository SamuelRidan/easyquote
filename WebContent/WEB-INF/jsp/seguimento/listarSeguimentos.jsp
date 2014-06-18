<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Listar segmentos </small></h1>
    </div>
  </div><!-- /.row --> 

<a class="btn" href="<c:url value="/seguimento/criarSeguimento"/>" > Criar segmento </a>

<br><br>

<form class="well form-inline" action="<c:url value="/seguimento/listarSeguimentos"/>" method="post" >
    <input type="text" class="input-small" name="seguimento.descricao" value="${sessaoGeral.valor.get('seguimento').descricao}" placeholder="Descrição">
	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>



<c:choose>
	<c:when test="${!empty seguimentos}">
		
		<c:set var="link" value="seguimento/listarSeguimentos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Id </th>
                    <th> Descricao </th>
                    <th> Editar </th>
		            <th> Remover </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${seguimentos}" var="item">
					<tr id="seguimento_${item.id}">
                        <td> ${item.id} </td>
                        <td> ${item.descricao} </td>
                        <td> <a href="<c:url value="/seguimento/editarSeguimento/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			            <td> <a href="<c:url value="/seguimento/excluirSeguimento/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>
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