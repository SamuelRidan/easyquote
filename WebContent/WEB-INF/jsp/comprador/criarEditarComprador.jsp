<%@page import="scada.controller.ListaCotacaoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<form class="form-horizontal" action="<c:url value="/comprador/salvarComprador"/>" method="post">
  <fieldset>
    <legend>Criar/editar comprador</legend>
    <div class="control-group">
      <label class="control-label">Operador</label>
      <div class="controls">
      	<%
		    List operador = HibernateUtilTest.RetornaUmValorEmConsultaHQL("from Operador order by id desc");    		
		    for (Object obj: operador) {
		    	Operador op = (Operador)obj;
		%>      	
		    	<input type="text" class="input-xlarge numero-decimal" name="comprador.operador.id" value="<%= op.getId() %>">   	
		<%  		
		  	}		
		%>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">CPF</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-decimal" name="comprador.cpf" value="<fmt:formatNumber value="${comprador.cpf}"/>">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Telefone</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="comprador.telefone" value="${comprador.telefone}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Email</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="comprador.email" value="${comprador.email}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/comprador/listarCompradors"/>" > Cancelar </a>
  </fieldset>
</form>

<%@ include file="/EQbaseFim.jsp" %> 
