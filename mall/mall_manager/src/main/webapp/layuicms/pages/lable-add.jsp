<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="lableAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>标签名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"/></td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		if(!$('#lableAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("<%=request.getContextPath()%>/lable/addDo.action",$("#lableAddForm").serialize(), function(data){
			if(data.code == 0000){
				$.messager.alert('提示','添加成功!','info',function(){
					$("#lableAddWindow").window('close');
					$("#lableList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.resultStr);
			}
		});
	}
</script>
