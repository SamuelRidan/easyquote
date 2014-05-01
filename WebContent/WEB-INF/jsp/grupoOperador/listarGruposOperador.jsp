<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
      
      
      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Criar grupo de operador </small></h1>
          </div>
        </div><!-- /.row -->  
 

		<ul id="grupoOperador" class="dropdown-menu">
			<li><a href="javascript:gerarLinkCompleto('<c:url value="/grupoOperador/vincularFuncionalidades"/>')">Vincular funcionalidades</a></li>
			<li><a href="javascript:gerarLinkCompleto('<c:url value="/grupoOperador/editarGrupoOperador"/>')">Editar</a></li>
			<li><a href="javascript:deletar('<c:url value="/grupoOperador/excluirGrupoOperador"/>')">Excluir</a></li>
		</ul>
		
		<a class="btn" href="<c:url value="/grupoOperador/criarGrupoOperador"/>" > Criar grupo de operador </a>
		
		<br><br>
		
		<form class="well form-inline" action="<c:url value="/grupoOperador/listarGruposOperador"/>" method="post" >
			<input type="text" class="input-small" name="grupoOperador.nome" value="${sessaoGeral.valor.get('grupoOperador').nome}" placeholder="Nome">
			<button type="submit" class="btn btn-info">Pesquisar</button>
		</form>
		
		<h3> Grupos de operadores </h3>
		
		<c:set var="link" value="grupoOperador/listarGruposOperador" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
		  <thead>
		    <tr>
		      <th>Nome</th>
		      <th>Permissão Especial</th>
		      <th> Vincular </th>  
		      <th> Editar </th>
		      <th> Remover </th>	 
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${gruposOperador}" var="item">
				<tr id="grupoOperador_${item.id}">
				  <td> ${item.nome} </td>
				  <td> <tags:simNao valor="${item.permissaoEspecial}"/> </td>
				  <td> <a href="<c:url value="/grupoOperador/vincularFuncionalidades/${item.id}"/>" ><i class="fa fa-magnet"></i> Vincular </a> </td>
                  <td> <a href="<c:url value="/grupoOperador/editarGrupoOperador/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			      <td> <a href="<c:url value="/grupoOperador/excluirGrupoOperador/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>		 				  
				</tr>
		  	</c:forEach>  	
		  </tbody>
		</table>
		
<%@ include file="/EQbaseFim.jsp" %> 
		
