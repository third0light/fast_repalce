<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<%@include file="../public/include.jsp"%>
<title>修改[<A.c>]信息</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="<%=basePath%>resources/kindeditor/plugins/code/prettify.css" />
<script src="<%=basePath%>resources/kindeditor/kindeditor-all.js"
	charset="utf-8"></script>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		<legend>修改[<A.c>]信息</legend>
	</fieldset>
	<form class="layui-form" action="">
		<input type="hidden" id = "[<id>]" name = "[<id>]" value = "${[<A>].[<id>]}">

-->>for i, n_value in enumerate(n):
-->>	if n_value.n == False:
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label"><span class="layui-badge-dot"></span>
					[<n_value.c>]</label>
				<div class="layui-input-block">
					<input name="[<n_value>]" lay-verify="required"
						autocomplete="off" placeholder="请输入[<n_value.c>]名称" class="layui-input"
						type="text" value="${[<A>].[<n_value>]}">
				</div>
			</div>
		</div>
-->>		pass
-->>	else:
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label"><span class="layui-badge-dot"></span>性别：</label>
				<div class="layui-input-inline">
					<select name="[<n_value>]" id="[<n_value>]" lay-verify="required">
						<option value="">请选择[<n_value.c>]</option>
-->>		for n_n in n_value.n:
						<option value="[<n_n>]">[<n_n.c>]</option>
-->>			pass
					</select>
					<script>
						$("#[<n_value>]").find("option[value ='${[<A>].[<n_value>]}']").attr("selected","selected");	
					</script>
				</div>
			</div>
		</div>
-->>		pass
-->>	pass
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="mod">立即提交</button>
				<button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</body>
</html>
<script type="text/javascript" src="<%=basePath%>resources/js/admin/[<A>].js"></script>
<script type="text/javascript">
layui.use(
	['form', 'layedit', 'laydate'], function() {
		var form = layui.form, layer = layui.layer;
		//监听提交
		form.on('submit(mod)', function(data) {
			mod(data.field);
			return false;
		});

		//重置提醒
		$("#reset").on("click", function() {
			layer.msg('已重置', function() {
				//关闭后的操作
			});
		});
	});
</script>