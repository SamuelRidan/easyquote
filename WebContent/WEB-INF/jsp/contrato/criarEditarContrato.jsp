<%@ include file="/EQbase.jsp" %> 
<%@page import="java.util.*, java.text.*"%>

<script type="text/javascript">
function setVisible(valor){
	if (valor == 2 || valor == 3){
		document.getElementById("periodicidade").style.display="block";
	} else {
		document.getElementById("periodicidade").style.display="none";
	}
}

</script>
<%
	Integer idFornecedor = Integer.parseInt(request.getParameter("f"));
	Integer idCotacao = Integer.parseInt(request.getParameter("c"));
%>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Criar contrato </small></h1>
          </div>
        </div><!-- /.row -->   

		<form class="form-horizontal" action="<c:url value="/contrato/salvarContrato?f="/><%= idFornecedor %>" method="post">
		  <fieldset>
		    <div class="control-group">
		      <label class="control-label">Inicio de Vigência</label>
		      <div class="controls">
      	      	<%
					Date date = new Date();
					SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
					GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
					data.setTime(date);
				%>      
		  		<c:choose>
		      		<c:when test="${!empty cotacao.dataAbertura.time}">
		        		<input type="text" class="input-xlarge data" name="contrato.inicioVigencia" value="<fmt:formatDate value="${contrato.inicioVigencia.time}"/>" required>
		        	</c:when>
		        	<c:otherwise>
		        		<input type="text" class="input-xlarge data" name="contrato.inicioVigencia" value="<fmt:formatDate value="<%= dataFormatada.parse(dataFormatada.format(data.getTime())) %>"/>" required>
		       		</c:otherwise>
		        </c:choose>
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Fim da Vigência</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge data" name="contrato.fimVigencia" value="<fmt:formatDate value="${contrato.fimVigencia.time}"/>">
		      </div>
		    </div>
		    
		    <div class="control-group warning">
        		<label class="control-label">Tipo do Contrato</label>
        		<div class="controls">
          			<select name="contrato.tipoContrato.id" id="contrato.tipoContrato.id" onchange="setVisible(this.value)">
						<c:forEach items="${tipoContrato}" var="tipocontrato">
							<option <c:if test="${contrato.tipoContrato.id == tipocontrato.id}"> selected="selected" </c:if> value="${tipocontrato.id}"> ${tipocontrato.id} - ${tipocontrato.descricao} </option>
						</c:forEach>
		  			</select>
        		</div>
    		</div>
		    
		    <div class="control-group" id="periodicidade" style="display: none;">
		      <label class="control-label">Periodicidade de reajuste em meses</label>
		      <div class="controls">
		        <input type="number" class="input-xlarge numero-inteiro" name="contrato.periodicidadeReajuste" value="${contrato.periodicidadeReajuste}">
		      </div>
		    </div>
		    
		    <div class="control-group" style="display: none;">
		      <label class="control-label">Cotacao</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="contrato.cotacao.id" value="<%= idCotacao %>">
		      </div>
		    </div>
		    
		    <div class="control-group" style="display: none;">
		      <label class="control-label">Fornecedor</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="contrato.fornecedor.id" value="<%= idFornecedor %>">
		      </div>
		    </div>
		
            <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger"  href="<c:url value="/contrato/listarContratos"/>" > Cancelar </a>
		  </fieldset>
		</form>
		</div>

<%@ include file="/EQbaseFim.jsp" %> 		
