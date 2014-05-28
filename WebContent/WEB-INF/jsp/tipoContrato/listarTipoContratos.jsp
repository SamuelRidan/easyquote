<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Listar Tipos de Contrato </small></h1>
    </div>
  </div><!-- /.row -->

<a class="btn" href="<c:url value="/tipoContrato/criarTipoContrato"/>" > Criar tipo de contrato </a>

<br><br>

<form class="well form-inline" action="<c:url value="/tipoContrato/listarTipoContratos"/>" method="post" >
    <input type="text" class="input-small" name="tipoContrato.descricao" value="${sessaoGeral.valor.get('tipoContrato').descricao}" placeholder="Descricao">

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Tipos de Contrato </h3>

<c:choose>
	<c:when test="${!empty tipoContratos}">
		
		<c:set var="link" value="tipoContrato/listarTipoContratos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Descricao </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tipoContratos}" var="item">
					<tr id="tipoContrato_${item.id}">
                        <td> ${item.descricao} </td>
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
