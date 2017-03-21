
<#assign basePath=rc.contextPath/><#--例："/scama"-->
<#assign webPath=basePath+"/web"/><#--例："/scama/web"-->
<#assign fullPath=basePath + subPath/><#--例："/scama/pages_broker/broker_group"-->
<#assign jsPath=fullPath + ("/" + fileName + ".js")/><#--例："/scama/pages_broker/broker_group/list_view.js"-->
<meta name="viewport" content="width=device-width, initial-scale=1" />

<script type="text/javascript">
var basePath = '${basePath}';
var webPath = '${webPath}';

</script>