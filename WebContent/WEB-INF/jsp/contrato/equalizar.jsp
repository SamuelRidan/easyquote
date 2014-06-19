<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.text.*, java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>

<script type="text/javascript">

function btnProd(r){
    $("#Indice"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-up'></i>");
    $(r).attr("onclick", "btnProd2(this);");
} 

function btnProd2(r){	
    $("#Indice"+r.id).toggle("fast");
    $(r).html("<i class='fa fa-angle-down'></i>");
    $(r).attr("onclick", "btnProd(this);");	
}

function aprovar(ok){
	$.ajax({
    	  url: "<c:url value='/contrato/aceitarIndices'/>",
    	  data: {
    		idContrato: ok.id,
    	  },
    	  success: function( data ) {
    		  location.href="<c:url value='/aditivo/listarAditivos'/>";
    	  }
    	}); 
}

$( document ).ready(function() {
	$(".indice").hide();
 
 });
</script>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Relatório de vencimento das cotações </small></h1>
    </div>
  </div><!-- /.row -->   
<br>
	
<c:set var="link" value="contrato/listarContratos" scope="request" />
		
<table class="table table-striped table-bordered tablesorter">
	<thead>
    	<tr>
             <th> # </th>  
             <th> Inicio da Vigência </th> 
             <th> Fim da Vigência </th>  
             <th> Fornecedor </th>
             <th>  </th>              
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${contratos}" var="contrato">
	
		<tr id="contrato_${contrato.id}">
                    <td> ${contrato.id} </td>
                    <td> <fmt:formatDate value="${contrato.inicioVigencia.time}"/> </td>	
                    <td> <fmt:formatDate value="${contrato.fimVigencia.time}"/> </td>
                    <c:forEach items="${fornecedores}" var="fornecedor">
						<c:if test="${contrato.fornecedor.id == fornecedor.id}">  ${fornecedor.razao_social}  </c:if>
					</c:forEach>
                    <td id="${contrato.id}" onclick="btnProd(this)" style="cursor:pointer;"> <i class="fa fa-angle-down"></i> </td>                                           
		</tr>
		<tr class="indice" id="Indice${contrato.id}">
		  <td colspan="7" style="background:#E5E5E5;">       	
			 	<table style="width:80%;" align="center" class="table table-hover tablesorter" >
				 			<tr>
								<th> # </th>
								<th> Produto </th>
								<th> Índice(%) </th>
								<th> </th>
					   		</tr>
					   		<c:forEach items="${indices}" var="indice">
						   	<tr>
								<td> ${indice.id} </td>
								<td> ${indice.produto.id} </td>
								<td> ${indice.indice} </td>
								<td> <a id="${contrato.id}" onclick="aprovar(this)" style="cursor:pointer;">Aprovar</a> </td>
						   	</tr>	
			 				</c:forEach>
			   </table>
		  </td>	 	 
		</tr>
		<tr>
		</c:forEach>
				<td colspan="6">
					<center>
						<iframe src="http://www.ipeadata.gov.br/ExibeSerie.aspx?serid=39616&module=M"  style="border:0px;  width:900px; height:7500px;"></iframe>
					</center>
				</td>
			</tr>
	</tbody>
</table>

</div>
<%@ include file="/EQbaseFim.jsp" %> 
		