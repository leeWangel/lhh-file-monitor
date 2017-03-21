<#include "include.ftl">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath}/jscript/jquery.min.js"></script>
<title>----</title>
<style type="text/css">
ul,li,i{ list-style:none; padding:0; margin:0; font-style:normal;}  
i{ width:14px; height:14px; float:left;}  
.box{ width:100%; max-width:300px; margin:0 auto;}  
.nav-ml{ width:100%;}  
.nav-ml ul{ margin-left:20px;}  
.nav-ml i{width:14px; height:14px; background-color:red; margin-right:10px;}  
.nav-ml i.unfold{width:14px; height:14px;  background-color:red;;}  
.nav-ml a{display:block; font-size:14px; height:20px; padding:3px 0; color:#666; overflow:hidden;}  
.nav-first,.nav-second,.nav-three{ margin-left:20px;}  
.nav-three li{ background:url(../images/b-dot.jpg) no-repeat 0 8px; padding-left:10px;}  
.fold{ display:none;}  
.nav-three li:hover{ background-color:#fffceb;}  
</style>

<script type="text/javascript">

</script>
</head>
<body>
  <div class="nav-ml">  
  <ul id="ul-tree">  
  <#list nodeList as node>
   <li>  
    <ul class="nav-first">  
      <li >
      	<a  class="item-1"><i></i>${node.text}</a>  
        <ul class="nav-second <#if node.selectable = 'false' >fold</#if> ">  
	  		<#list node.nodes as item>
	          <li> <a ><i></i>${item.text}</a></li>  
	         </#list>
	        </ul>  
      </li> 
    </ul>  
    </li>  
    </#list>
    </ul>  
  </div>  
</div>  
</body>
</html>
<script language="javascript">  
      $(".item-1").click(function(){  
        $(this).parent().find(".nav-second").slideToggle(500);  
        $(this).children("i").toggleClass("unfold");  
    });  
</script> 