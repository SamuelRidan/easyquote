<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Gerar pedido </small></h1>
          </div>
        </div><!-- /.row -->    

		<form class="form-horizontal" action="<c:url value="/principal/salvarPrincipal"/>" method="post">
		  <fieldset>
		  	
		  	<div class="control-group">
		      	<label class="control-label">Contrato de número: </label>
		      	<div class="controls">         	
		      		<input type="text" class="input-xlarge numero-inteiro" id="principal.contrato.id" name="principal.contrato.id" value="${contrato}">		      	
		      	 </div>
		    	</div>
		  
		    <div class="control-group">
		      <label class="control-label">Preço total da compra</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="principal.precoTotal" value="${total}">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Gerar pedido</button>

		  </fieldset>
		</form>
		
		</div>
		
<%@ include file="/EQbaseFim.jsp" %> 