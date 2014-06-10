<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

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
		           	<%		    
		           		Double total = 0.0;
			    		List contrato = HibernateUtilTest.RetornaUmValorEmConsultaHQL("from Contrato order by id desc");		    		
			    		for (Object obj: contrato) {
			    			Contrato c = (Contrato)obj;
			    			List preco = HibernateUtilTest.executarConsultaHQL("from ListaCotacaoFornecedor where cotacao.id="+c.getCotacao().getId()+" and fornecedor.id="+c.getFornecedor().getId());
			    			for (Object ob: preco){
			    				ListaCotacaoFornecedor lcf = (ListaCotacaoFornecedor)ob;
								total = total + lcf.getPreco();
			    			}
		           	%>		           	
		      				<input type="text" class="input-xlarge numero-inteiro" id="principal.contrato.id" name="principal.contrato.id" value="<%= c.getId() %>">
		      		<%		      		
		    			}		    		
		      		%>			      	
		      	 </div>
		    	</div>
		  
		    <div class="control-group">
		      <label class="control-label">Preço total da compra</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="principal.precoTotal" value="<%= total%>">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Gerar pedido</button>

		  </fieldset>
		</form>
		
		</div>
		
<%@ include file="/EQbaseFim.jsp" %> 