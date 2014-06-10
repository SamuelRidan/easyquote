<%@page import="java.util.*, java.text.*"%>
<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

 <div id="page-wrapper">
   <div class="row">
     <div class="col-lg-12">
       <h1><small><span style="color:#31708F">//</span> Reajuste de índices de contrato </small></h1>
     </div>
   </div><!-- /.row -->   
       
<%
    Integer idCodigo = Integer.parseInt(request.getParameter("idContrato"));
%> 
        
<form class="form-horizontal" action="<c:url value="/indices/salvarIndices"/>" method="post">
  <fieldset>
  	
  	
  	<button type="submit" class="btn btn-primary">Salvar</button>
  	<a class="btn btn-danger" href="<c:url value="/contrato/equalizarForn"/>" > Cancelar </a>
  </fieldset>
</form>
</div>

<%@ include file="/EQbaseFim.jsp" %> 
		