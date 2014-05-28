<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Criar/editar produto </small></h1>
          </div>
        </div><!-- /.row -->     
        

		<form class="form-horizontal" action="<c:url value="/produto/salvarProduto"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">Descricao</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge" name="produto.descricao" value="${produto.descricao}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Preco</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="produto.preco" value="${produto.preco}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">CodSAP</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="produto.codSAP" value="${produto.codSAP}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Segmento</label>
		      <div class="controls">
		      		<select name="produto.seguimento.id">
		      			<option disabled="disabled" selected="selected">Selecione o segmento</option>
						<c:forEach items="${tipoSeg}" var="itemSeg">
							<option <c:if test="${produto.seguimento.id == itemSeg.id}"> selected="selected" </c:if> value="${itemSeg.id}"> ${itemSeg.id} - ${itemSeg.descricao} </option>
						</c:forEach>  
					</select>    
			  </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/produto/listarProdutos"/>" > Cancelar </a>
		  </fieldset>
		</form>


<%@ include file="/EQbaseFim.jsp" %> 