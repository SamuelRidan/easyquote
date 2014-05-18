<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>

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
 
 });
</script>

<%
        out.println("Hello <b>"+request.getParameter("id")+"</b>!");  
%>
      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Proposta Fornecedor</small></h1>
          </div>
        </div><!-- /.row -->     
				<!--  <a class="btn" href="<c:url value="/listaCotacao/criarListaCotacao"/>" > Criar listaCotacao </a> -->

				<c:set var="link" value="listaCotacao/listarListaCotacaos" scope="request" />
				<%@ include file="/paginacao.jsp" %> 	
				
		<%          	
		   List propFornecedor = HibernateUtilTest.TesteexecutarConsultaHQL("FROM listacotacaofornecedor GROUP BY idfornecedor, cotacao where cotacao="+request.getParameter("id"));
		 %>
<%@ include file="/EQbaseFim.jsp" %> 		
