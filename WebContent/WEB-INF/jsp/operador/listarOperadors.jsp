<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Listar Operadors </small></h1>
          </div>
        </div><!-- /.row -->  

<ul id="operador" class="dropdown-menu">
	<li><a href="javascript:gerarLinkCompleto('<c:url value="/operador/editarOperador"/>')">Editar</a></li>
	<li><a href="javascript:deletar('<c:url value="/operador/excluirOperador"/>')">Excluir</a></li>
</ul>

<a class="btn" href="<c:url value="/operador/criarOperador"/>" > Criar operador </a>

<br><br>

<c:choose>
	<c:when test="${!empty operadors}">
		
		<c:set var="link" value="operador/listarOperadors" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Login </th>
                    <th> Senha </th>
                    <th> Ativo </th>
                    <th> Grupo </th>
		            <th> Editar </th>
		            <th> Remover </th>	                    
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${operadors}" var="item">
					<tr id="operador_${item.id}">
                        <td> ${item.login} </td>
                        <td> ${item.senha} </td>
                        <td> <tags:simNao valor="${item.ativo}" /> </td>
                        <td> 
                        	<c:forEach items="${grupooperador}" var="grupo">
								<c:if test="${item.grupoOperador.id == grupo.id}">  ${grupo.nome}  </c:if>
							</c:forEach>
                        </td>
                        <td> <a href="<c:url value="/operador/editarOperador/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			            <td> <a href="<c:url value="/operador/excluirOperador/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>		                         
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
