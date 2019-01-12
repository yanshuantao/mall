<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="urls" value="<%=request.getContextPath()%>"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>


</head>
<body style="background-color: #F3F3F3">
    <div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<form action="${urls }/loginDo.action" id="form1" name="form1" method="post">
	       	<div style="margin-left: 50px;margin-top: 50px;">
	       		<div style="margin-bottom:20px;">
		            <div>
		            	用户名: <input name="username" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value="admin"/>
		            </div>
		        </div>
		        <div style="margin-bottom:20px">
		            <div>
		            	密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="" value="admin"/>
		            </div>
		        </div>
		        <div>
		            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px">登录</a>
		        </div>
	       	</div>
       	
       	</form>
    </div>
        <script type="text/javascript">
    	$("#login").click(function(){
    		$.ajax({
    				type:"POST",
    				url:"${urls}/login.action",
   					data:$("#form1").serialize(),
   					dataType:"json",
   					success: function(data){
						if(data.code == 0000){
							$.messager.alert('提示','登录成功');
						}else{
							$.messager.alert('错误',data.resultStr);
							return ;
						}
		    		window.location.href="${urls}/index.action";
				},error:function(XMLHttpRequest, textStatus, errorThrown){
					 // 状态码
                    console.log(XMLHttpRequest.status);
                    // 状态
                    console.log(XMLHttpRequest.readyState);
                    // 错误信息   
                    console.log(textStatus);
                    console.log(errorThrown);
				}
    		}); 
    		
    		/* $.post("${urls}/loginDo.action",$("#form1").serialize(), function(data){
    			alert(data);
    		}); */
    		
    	});
    </script>
    
</body>
</html>