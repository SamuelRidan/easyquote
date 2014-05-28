<%@page import="scada.controller.ListaCotacaoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Criar fornecedor </small></h1>
    </div>
  </div><!-- /.row -->

<form class="form-horizontal" action="<c:url value="/fornecedor/salvarFornecedor"/>" method="post">
  <fieldset>
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
      <label class="control-label">Razao Social</label>
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
      		<select name="fornecedor.seguimento.id">
      			<option disabled="disabled" selected="selected">Selecione o segmento</option>
				<c:forEach items="${tipoSeg}" var="itemSeg">
					<option <c:if test="${fornecedor.seguimento.id == itemSeg.id}"> selected="selected" </c:if> value="${itemSeg.id}"> ${itemSeg.id} - ${itemSeg.descricao} </option>
				</c:forEach>  
			</select>    
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
</div>
<%@ include file="/EQbaseFim.jsp" %> 
