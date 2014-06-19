<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
 
<script type="text/javascript">
function btnProd(r){	
    $("#Prod"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-up'></i>");
    $(r).attr("onclick", "btnProd2(this);");
} 

function btnProd2(r){	
    $("#Prod"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-down'></i>");
    $(r).attr("onclick", "btnProd(this);");	
}

$( document ).ready(function() {
	$(".prod").hide();
	
	var idCotacao = $("#idCotacao").val();

	console.log("IDcot:"+idCotacao);
	
 $.ajax({
		      	  url:"<c:url value='/listaCotacao/propostaFornecedor1'/>",
		      	  data: {
			      	cotacao: idCotacao,
		      	  },
		      	  success: function(dt){
		      		var obj = jQuery.parseJSON("["+dt+"]");
			 			Morris.Bar ({
			 				  element: 'morris-chart-bar',
			 				  data: obj ,
			 				  xkey: 'fornecedor',
			 				  labels: ['Fornecedor'],
			 				  ykeys: ['reputacao'],
			 				  labels: ['Reputação Fornecedor'],
			 				  barRatio: 0.4,
			 				  xLabelAngle: 35,
			 				  hideHover: 'auto'
			 				});			 
		      	  }
		      	  });

}); 

 function morris(dt){
	        alert(dt); 
			html = $.parseHTML(dt);
	};
	
</script>

<%
            Integer idCodigo = Integer.parseInt(request.getParameter("id"));
%>
    <div id="page-wrapper">
	 <c:set var="idcot" value="<%=idCodigo%>"/>
	 <input type="hidden" id="idCotacao" value="<%=idCodigo%>"/>

        <div class="row">
          
        <div class="row">
          <div class="col-lg-12">
            <h1>Propostas Fornecedor <small>Cotações</small></h1>
            <ol class="breadcrumb">
              <li><a href="#"><i class="icon-dashboard"></i> Cotações</a></li>
              <li class="active"><i class="icon-file-alt"></i> Propostas</li>
            </ol>
          </div>
        </div><!-- /.row -->        
      
      <div class="row"> 
      <div class="col-lg-8" style="width: 50%">
		       <div class="panel panel-primary" style="height:417px;">
		              <div class="panel-heading">
		                <h3 class="panel-title">Detalhes Cotação</h3>
		              </div>
				     <c:choose>
					  <c:when test="${!empty tipoCotacao}">
							<c:forEach items="${tipoCotacao}" var="itemCot">	
				              <c:choose>
					            <c:when test="${itemCot.id == idcot}">
				              <div class="panel-body">
						          <div class="col-lg-4">
						            <p><strong>Código da Cotação: </strong> ${itemCot.id}</p>
						            <p><strong> Data da Abertura:</strong> <fmt:formatDate value="${itemCot.dataAbertura.time}"/></p>
						            <p><strong> Data Limite de Resposta:</strong>  <fmt:formatDate value="${itemCot.dataLimiteResposta.time}"/></p>
						            <p><strong>Status:</strong> 
									<c:forEach items="${tipoStatus}" var="itemStatus">			
										<c:if test="${itemCot.status.id == itemStatus.id}">${itemStatus.descricao}</c:if>
									</c:forEach></p>
						            <p><strong> Setor:</strong>
									<c:forEach items="${tipoSetor}" var="itemSetor">	
										<c:if test="${itemCot.setor.id == itemSetor.id}"><td>${itemSetor.descricao}</td></c:if>							
									</c:forEach>			            
						            </p>
						            <p> <strong> Observação: </strong>${itemCot.obs}</p>
						          </div>                
				              </div>
				               </c:when>
				               </c:choose>
				           </c:forEach>
				           </c:when>
				       </c:choose>
		        </div>
          </div>
        
          <div class="col-lg-4" style="margin-left:-20px;width: 50%">
            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Reputação Fornecedor</h3>
              </div>
              <div class="panel-body">
                <div id="morris-chart-bar"></div>
              </div>
            </div>
          </div>
         </div><!-- /.row --> 
        
 
		<%   
			HibernateUtil hibernateUtil = new HibernateUtil();
		   List propFornecedor = hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor where cotacao_id= "+idCodigo+"group by fornecedor_id");
		   
		 %>
         <div class="alert alert-info alert-dismissable">
              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
              Nº de Proposta(s) para cotação : <% out.println(propFornecedor.size());%>
         </div>		  
        
 
		<table  class="table table-hover tablesorter"  >
			<tr>
				<th>Cod. Fornecedor</th>
				<th>Razão Social</th>
				<th>Qualificação</th>	
				<th>Valor Total Cotação</th>	
				<th> </th>	
				<th> </th>
			</tr>	
		<%
		      for (Object obj: propFornecedor) {
				ListaCotacaoFornecedor lc = (ListaCotacaoFornecedor)obj;            
	            if(lc.getCotacao().getId() == idCodigo){
	            	Double total = 0.0;
	                 List totalCotacao = hibernateUtil.retorna1HQL("FROM ListaCotacaoFornecedor WHERE cotacao.id="+lc.getCotacao().getId()+"and fornecedor_id="+lc.getFornecedor().getId()); 
						for (Object ob: totalCotacao){
							ListaCotacaoFornecedor lcf = (ListaCotacaoFornecedor)ob;
							total = total + lcf.getPreco();
						}
		%>		
						<tr>
						    <td><%= lc.getFornecedor().getId() %></td>
						    <td><%= lc.getFornecedor().getRazao_social() %></td>
						    <td><%= lc.getFornecedor().getReputacao() %></td>
						    <td><%= Formatacao.formatarNumero(total) %> </td>
						    <td> <a href="<c:url value='/contrato/criarContrato?f='/><%=lc.getFornecedor().getId() %>&c=<%=lc.getCotacao().getId() %>" >Fechar contrato</a> </td>
						    <td id="<%=lc.getId()%>" onclick="btnProd(this)" style="cursor:pointer;"> <i class="fa fa-angle-down"></i> </td>
						</tr>
						<tr id="Prod<%=lc.getId()%>" class="prod">
						   <td colspan="6">
						   
				            <div class="bs-example">
				              <ul class="nav nav-tabs" style="margin-bottom: -1px;">
				                <li class="active"><a href="#produtos<%=lc.getFornecedor().getId()%>" data-toggle="tab">Produtos Cotados</a></li>
				                <li><a href="#historico<%=lc.getFornecedor().getId()%>" data-toggle="tab">Histórico do Fornecedor</a></li>
				              </ul>
				              
				              <div id="myTabContent" class="tab-content">
				              
				                <div class="tab-pane fade active in" style="border:1px solid #D8D8D8; padding:30px;" id="produtos<%=lc.getFornecedor().getId()%>">
								   		<table style="width:80%;" align="center" class="table table-striped table-bordered tablesorter" >
								   		<tr>
								   		 	<th>Item</th>
								   		 	<th>Descrição Produto</th>
								   		 	<th>Quantidade </th>
								   		 	<th>Valor Unitário </th>						   		 							   		 	
								   		</tr>
								   		<%
								   		Integer i; 
								   		i = 0;
								   		List listaCotacao = hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor where fornecedor_id = "+ lc.getFornecedor().getId() +" and cotacao.id = "+ lc.getCotacao().getId());
						            	for (Object ob: listaCotacao){
						            		ListaCotacaoFornecedor lista = (ListaCotacaoFornecedor)ob;
						            		i++;
						            	%>
						            		<tr>
						            			<td><%= i %></td>
						            			<td><%= lista.getListaCotacao().getProduto().getDescricao() %></td>
						            			<td><%= lista.getListaCotacao().getQuantidade() %></td>
						            			<td><%= Formatacao.formatarNumero(lista.getPreco()) %></td>
						            		</tr> 
						            	<%
						            	}
								   		
								   		%>
								   		</table>

				                </div>
				                <div class="tab-pane fade"  style="border:1px solid #D8D8D8; padding:30px;" id="historico<%=lc.getFornecedor().getId()%>">
						            <div class="bs-example">
						              <div class="list-group">	
						              <c:set var="idped" value="<%=lc.getFornecedor().getId()%>"/>		
							<c:forEach items="${tipopedido}" var="itemped" varStatus="l">	
				                <c:choose>
					              <c:when test="${itemped.fornecedor.id == idped}">					              		              
						                <span class="list-group-item">
						                  <h4 class="list-group-item-heading">Cod. Pedido: ${itemped.pedido.id} </h4>
						                  <p class="list-group-item-text"><strong> Tempo de Entrega:</strong> ${itemped.pergunta1}    </p>
						                  <p class="list-group-item-text"><strong> Qualidade dos Produtos/Serviços no Setor:</strong> ${itemped.pergunta2}  </p>
						                  <p class="list-group-item-text"><strong> Comunicação do Fornecedor:</strong> ${itemped.pergunta3}</p>
						                  <p class="list-group-item-text"><strong> Observação:</strong> ${itemped.obs} </p>
						                </span>            
						           </c:when>
						      <c:otherwise>
						       <c:choose>
						      	  <c:when test="${empty tipopedido }" >
						           		<center> <h5> Nenhum pedido anterior </h5></center>
						     		</c:when>
						        </c:choose>
						      </c:otherwise>
						      </c:choose>						         
						      </c:forEach>   

						              </div>
						            </div>				                  
				                </div>
				              </div>
                             </div>						   

						   </td>
						</tr>		 			
	    <%			
                 }
		      }
		
		hibernateUtil.fecharSessao();
		  %>				
					
		     </table>	
  
       </div><!-- /.row -->     
       
      </div><!-- /#page-wrapper -->

    <!-- JavaScript -->


    <!-- Page Specific Plugins -->
    <script src="<c:url value="/EQ-js/raphael-min.js"/>"></script>  
    <script src="<c:url value="/EQ-js/morris-0.4.3.min.js"/>"></script>


 
  		
  </body>
</html>
