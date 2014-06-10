<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<div id="page-wrapper">
   <div class="row">
     <div class="col-lg-12">
       <h1><small><span style="color:#31708F">//</span> Listar status de pedidos </small></h1>
     </div>
   </div><!-- /.row -->

<a class="btn" href="<c:url value="/statusPedido/criarStatusPedido"/>" > Criar statusPedido </a>

<br><br>

<c:choose>
	<c:when test="${!empty statusPedidos}">
		
		<c:set var="link" value="statusPedido/listarStatusPedidos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Descrição </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statusPedidos}" var="item">
					<tr id="statusPedido_${item.id}">
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