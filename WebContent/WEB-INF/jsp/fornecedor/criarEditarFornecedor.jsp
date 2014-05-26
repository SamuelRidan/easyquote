<%@page import="scada.controller.ListaCotacaoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<form class="form-horizontal" action="<c:url value="/fornecedor/salvarFornecedor"/>" method="post">
  <fieldset>
    <legend>Criar/editar fornecedor</legend>
    <div class="control-group">
      <label class="control-label">Operador</label>
      <div class="controls">
      	<%
		    List operador = HibernateUtilTest.RetornaUmValorEmConsultaHQL("from Operador order by id desc");    		
		    for (Object obj: operador) {
		    	Operador op = (Operador)obj;
		%>      	
		    	<input type="text" class="input-xlarge numero-decimal" name="fornecedor.operador.id" value="<%= op.getId() %>">   	
		<%  		
		  	}		
		%>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Cnpj</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.cnpj" value="${fornecedor.cnpj}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Razao_social</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.razao_social" value="${fornecedor.razao_social}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Telefone</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.telefone" value="${fornecedor.telefone}">
      </div>
    </div>
        <div class="control-group">
      <label class="control-label">Email</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.email" value="${fornecedor.email}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Segmento</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.segmento" value="${fornecedor.segmento}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Reputacao</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.reputacao" value="${fornecedor.reputacao}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
  </fieldset>
</form>

<%@ include file="/EQbaseFim.jsp" %> 
