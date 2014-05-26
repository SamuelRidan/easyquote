<%@page import="scada.controller.ListaCotacaoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Criar/editar operador</small></h1>
          </div>
        </div><!-- /.row --> 
		
		<form class="form-horizontal" action="<c:url value="/operador/salvarOperador"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">Login</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge" name="operador.login" value="${operador.login}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Senha</label>
		      <div class="controls">
		        <input type="password" class="input-xlarge" name="operador.senha" value="${operador.senha}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Ativo</label>
		      <div class="controls">
		        <input type="checkbox" <c:if test="${operador.ativo}"> checked="checked" </c:if> class="input-xlarge" name="operador.ativo">
		      </div>
		    </div>
		    
		    <div class="control-group">
		      <label class="control-label">Grupo de Operador</label>
		      <div class="controls">
		        <select name="operador.grupoOperador.id">
		        	<option selected="selected" disabled="disabled"> Selecione o grupo </option>
					<c:forEach items="${grupoOperador}" var="grupoOperador">
						<option <c:if test="${operador.grupoOperador.id == grupoOperador.id}"> </c:if> value="${grupoOperador.id}">${grupoOperador.id} - ${grupoOperador.nome} </option>
					</c:forEach>
				</select>
		      </div>
		    </div> 
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/operador/listarOperadors"/>" > Cancelar </a>
		  </fieldset>
		</form>
	</div>

<%@ include file="/EQbaseFim.jsp" %> 