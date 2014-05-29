<%@page import="java.util.*, java.text.*"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

 <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Criar/Editar Cotação </small></h1>
          </div>
        </div><!-- /.row -->   
        
        
<form class="form-horizontal" action="<c:url value="/cotacao/salvarCotacao"/>" method="post">
  <fieldset>
    <legend></legend>
    
    <div class="control-group">
      <label class="control-label">Data de abertura</label>
      <div class="controls">      
      	<%
			Date date = new Date();
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
			data.setTime(date);
		%>      
  		<c:choose>
      		<c:when test="${!empty cotacao.dataAbertura.time}">
        		<input type="text" class="input-xlarge data" name="cotacao.dataAbertura" value="<fmt:formatDate value="${cotacao.dataAbertura.time}"/>" required>
        	</c:when>
        	<c:otherwise>
        		<input type="text" class="input-xlarge data" name="cotacao.dataAbertura" value="<fmt:formatDate value="<%= dataFormatada.parse(dataFormatada.format(data.getTime())) %>"/>" required>
       		</c:otherwise>
        </c:choose> 
      </div>
    </div>
    
    <div class="control-group">
      <label class="control-label">Data Limite de Resposta</label>
      <div class="controls">
        <input type="text" class="input-xlarge data" name="cotacao.dataLimiteResposta" value="<fmt:formatDate value="${cotacao.dataLimiteResposta.time}"/>" required>
      </div>
    </div>
    
    <div class="control-group warning">
        <label class="control-label">Status</label>
        <div class="controls">
          <select name="cotacao.status.id" >
			<c:forEach items="${tipoStatus}" var="itemStatus">
				<option <c:if test="${cotacao.status == itemStatus.id}"> selected="selected" </c:if> value="${itemStatus.id}"> ${itemStatus.id} - ${itemStatus.descricao} </option>
			</c:forEach>
		  </select>
        </div>
    </div>
    
    
    <div class="control-group">
      <label class="control-label">Setor Requerente</label>
      <div class="controls">
        <select name="cotacao.setor.id">
        	<option selected="selected" disabled="disabled"> Selecione o setor </option>
			<c:forEach items="${tipoSetor}" var="itemSetor">
				<option <c:if test="${cotacao.setor == itemSetor.id}"> </c:if> value="${itemSetor.id}">${itemSetor.id} - ${itemSetor.descricao} </option>
			</c:forEach>
		</select>
      </div>
    </div>    
    
    <div class="control-group">
      <label class="control-label">Observações</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="cotacao.obs" value="${cotacao.obs}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/cotacao/listarCotacaos"/>" > Cancelar </a>
  </fieldset>
</form>
</div>

<%@ include file="/EQbaseFim.jsp" %> 
		