<%@ include file="/EQbase.jsp" %> 

<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Contratos Aditivos </small></h1>
          </div>
        </div><!-- /.row -->    
		
		<c:choose>
			<c:when test="${!empty aditivos}">
				
				<c:set var="link" value="aditivo/listarAditivos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 
				
				<table class="table table-striped table-bordered tablesorter">
					<thead>
				    	<tr>
				    		<th> Aditivo do contrato nº </th>
		                    <th> Preço Total </th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${aditivos}" var="item">
							<tr id="aditivo_${item.id}">
								<td> ${item.contrato.id } </td>
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
		</div>
<%@ include file="/EQbaseFim.jsp" %> 		
