<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
     
     
      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Listar Pessoa </small></h1>
          </div>
        </div><!-- /.row --> 
        

			
			<ul id="pessoa" class="dropdown-menu">
				<li><a href="javascript:gerarLinkCompleto('<c:url value="/pessoa/editarPessoa"/>')">Editar</a></li>
				<li><a href="javascript:deletar('<c:url value="/pessoa/excluirPessoa"/>')">Excluir</a></li>
			</ul>
			
			<a class="btn" href="<c:url value="/pessoa/criarPessoa"/>" > Criar pessoa </a>
			
			<br><br>
			
			<form class="well form-inline" action="<c:url value="/pessoa/listarPessoas"/>" method="post" >
			    <input type="text" class="input-small" name="pessoa.nome" value="${sessaoGeral.valor.get('pessoa').nome}" placeholder="Nome">
			    <input type="text" class="input-small numero-inteiro" name="pessoa.codSAP" value="${sessaoGeral.valor.get('pessoa').codSAP}" placeholder="CodSAP">
			
				<button type="submit" class="btn btn-info">Pesquisar</button>
			</form>
			
			<h3> Pessoas </h3>
			
			<c:choose>
				<c:when test="${!empty pessoas}">
					
					<c:set var="link" value="pessoa/listarPessoas" scope="request" />
					<%@ include file="/paginacao.jsp" %> 
					
					<table class="table table-striped table-bordered tablesorter">
						<thead>
					    	<tr>
			                    <th> Nome </th>
			                    <th> CodSAP </th>
		                        <th> Editar </th>
		                        <th> Remover </th>			                    
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pessoas}" var="item">
								<tr id="pessoa_${item.id}">
			                        <td> ${item.nome} </td>
			                        <td> ${item.codSAP} </td>
			                        <td> <a href="<c:url value="/pessoa/editarPessoa/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			                        <td> <a href="<c:url value="/pessoa/excluirPessoa/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>				                        
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
