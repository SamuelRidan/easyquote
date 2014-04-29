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

<form class="well form-inline" action="<c:url value="/operador/listarOperadors"/>" method="post" >
    <input type="text" class="input-small" name="operador.login" value="${sessaoGeral.valor.get('operador').login}" placeholder="Login">
    <input type="text" class="input-small" name="operador.senha" value="${sessaoGeral.valor.get('operador').senha}" placeholder="Senha">
    <input type="checkbox" name="operador.ativo" value="${sessaoGeral.valor.get('operador').ativo}" >

	<button type="submit" class="btn btn-info">Pesquisar</button>
</form>

<h3> Operadors </h3>

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
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${operadors}" var="item">
					<tr id="operador_${item.id}">
                        <td> ${item.login} </td>
                        <td> ${item.senha} </td>
                        <td> <tags:simNao valor="${item.ativo}" /> </td>
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
