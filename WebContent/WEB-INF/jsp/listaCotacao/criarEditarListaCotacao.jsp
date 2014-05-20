<%@page import="scada.controller.ListaCotacaoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

<script LANGUAGE="JavaScript">
    totals = 0;
    
    function adiciona(){
    	totals++;
        tbl = document.getElementById("tabela");
        prod = document.getElementById("produto");
    	produto = prod.options[prod.options.selectedIndex];
    	idProduto = produto.value;
        quant = document.getElementById("quantidade").value; 
        idCotacao = document.getElementById("listaCotacao.cotacao.id").value;
        
        if (quant == 0){
        	alert('Por favor preencha o quantidade desejada!');
        } else {
        
		        $.ajax({
		      	  url: "<c:url value='/listaCotacao/salvarProdutoLista'/>",
		      	  data: {
		      		prod: idProduto,
		      	    quantidade: quant,
		      	    idCot: idCotacao
		      	  },
		      	  success: function( data ) {
		      		  	var novaLinha = tbl.insertRow(-1);
		      	        var novaCelula;
		      	 
		      	        novaCelula = novaLinha.insertCell(0);
		      	        novaCelula.align = "left";
		      	        novaLinha.setAttribute("id", "listaCotacao_" + totals);
		      	        novaCelula.innerHTML = totals;
		      	 
		      	        novaCelula = novaLinha.insertCell(1);
		      	        novaCelula.align = "left";
		      	        novaCelula.innerHTML = produto.text;
		      	 
		      	        novaCelula = novaLinha.insertCell(2);
		      	        novaCelula.align = "left";
		      	        novaCelula.innerHTML = quant;
		      	        
		      	        novaCelula = novaLinha.insertCell(3);
		      	        novaCelula.align = "left";
		      	        novaCelula.innerHTML = '<a href="#" onclick="deleta(' + idProduto +',listaCotacao_' + totals + ')">Excluir</a>';
		      	  }
		      }); 
        
        }
    }
    
    function deleta(id,linha){
    	
    	idCotacao = document.getElementById("listaCotacao.cotacao.id").value;
    	idProduto = id;
    	
    	$.ajax({
      	  url: "<c:url value='/listaCotacao/excluirProdutoLista'/>",
      	  data: {
      		prod: idProduto,
      		idCot: idCotacao
      	  },
      	  success: function( data ) {
      		  $("#"+linha.id).remove();
      	  }
      	}); 
    	
    	
    	
    }
    </script>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Criar/editar lista de produtos para cotação </small></h1>
          </div>
        </div><!-- /.row -->  
        
        <ul id="listaCotacao" class="dropdown-menu">
			<li><a href="javascript:deletar('<c:url value="/listaCotacao/excluirProdutoLista"/>')">Excluir</a></li>
		</ul>

		<form class="form-horizontal" action="<c:url value="/listaCotacao/salvarListaCotacao"/>" method="post">
		  <fieldset>
		  
		    <legend></legend>
		    
		   <table width="80%" border="0" align="center" id="tabela" class="table table-striped table-bordered tablesorter">
  			<tr>
    			<td colspan="4">
    			<div class="control-group">
		      	<label class="control-label">Sequencial de Cotacao</label>
		      	<div class="controls">
		           	<%
		           	
		    		List cotacao = HibernateUtilTest.RetornaUmValorEmConsultaHQL("from Cotacao order by id desc");
		    		
		    		for (Object obj: cotacao) {
		                Cotacao c = (Cotacao)obj;
		           	
		           	%>
		           	
		      		<input type="text" class="input-xlarge numero-inteiro" id="listaCotacao.cotacao.id" name="listaCotacao.cotacao.id" value="<%= c.getId() %>">
		      	
		      		<%
		      		
		    		}
		    		
		      		%>	
		      	
		      	 </div>
		    	</div>	    
		    
		    <div class="control-group">
		      <label class="control-label">Produto</label>
		      <div class="controls">
		      
		      	<c:choose>
					<c:when test="${empty listaCotacao.produto}">
		      
				        <select name="listaCotacao.produto" id="produto">
				        	<option selected="selected" disabled="disabled" value="0">Escolha seu produto</option>
				        	<%
					      		
					      		Produto produto = new Produto();
					      		HibernateUtil hibernateUtil = new HibernateUtil();
					      		List<Produto> produtos = hibernateUtil.buscar(produto);
					
								for (Produto p : produtos ) {
								
							%>
									<option value="<%= p.getId() %>"><%=p.getDescricao() %></option>
							<%
								}
							%>
				        </select> 
				        
				    </c:when>
				    <c:otherwise>
				    
				    	<select name="listaCotacao.produto" id="produto">					
							<%
					      		
					      		Produto produto = new Produto();
					      		HibernateUtil hibernateUtil = new HibernateUtil();
					      		List<Produto> produtos = hibernateUtil.buscar(produto);
					
								for (Produto p : produtos ) {
								
							%>
								<c:forEach items="${tipoProduto}" var="itemProd">
									<c:choose>
									 
										<c:when test="${listaCotacao.produto == itemProd.id}">
											<option selected="selected" value="${listaCotacao.produto}"> <%= p.getDescricao() %></option>
										</c:when>
									 
										<c:otherwise>
											<option value="<%= p.getId() %>"><%= p.getDescricao() %></option>
										</c:otherwise>	
																					
									</c:choose>
									
								</c:forEach>
							<%
								}
							%>
						</select>
						
					</c:otherwise>   
				</c:choose>
				 
		      </div>
		    </div>	      	
			      			      			
  
    
    <div class="control-group">
      <label class="control-label">Quantidade</label>
      <div class="controls">
        <input type="text" id="quantidade" class="input-xlarge numero-inteiro" name="listaCotacao.quantidade" value="${listaCotacao.quantidade}">
		&nbsp;&nbsp;&nbsp;
      	<input type="button" id="incluir" class="btn btn-default" value="Adicionar produto" onclick="adiciona()"/>
      </div>
    </div>
    </td>
    </tr>
  	<tr>
    	<td colspan="4">&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="10%">#</td>
    	<td width="55%">Descrição do Produto</td>
    	<td width="20%">Quantidade</td>
    	<td width="15">&nbsp;</td>
  	</tr>

	</table>
	
   <!--
    <button type="submit" class="btn btn-default">Salvar</button>
    <a class="btn btn-default" href="<c:url value="/listaCotacao/listarListaCotacaos"/>" > Cancelar </a>    
    -->
    
  </fieldset>
</form>



		
<%@ include file="/EQbaseFim.jsp" %> 