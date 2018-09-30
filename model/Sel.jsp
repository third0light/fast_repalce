<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../public/include.jsp" %> 
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
</head>
<body>
	<form id="thisform" action="<%=basePath%>admin/[<A>]Sel" method="get">
		<input id="page"  name="page" type="hidden" value="1"/>

-->>for i, like_value in enumerate(like):
-->>	if like_value.n == False:
		<div class="layui-inline">
			<label class="layui-form-label">[<like_value.c>]</label>
			<div class="layui-input-inline">
				<input class="layui-input" name="[<like_value>]" id="[<like_value>]"
					value="${[<A>].[<like_value>]}" placeholder="请输入搜索[<like_value.c>]">
			</div>
		</div>
-->>		pass
-->>	else:
		<div class="layui-inline">
			<label class="layui-form-label"><span class="layui-badge-dot"></span>性别：</label>
			<div class="layui-input-inline">
				<select name="[<like_value>]" id="[<like_value>]" lay-verify="required">
					<option value="">请选择搜索[<like_value.c>]</option>
-->>		for like_n in like_value.n:
					<option value="[<like_n>]">[<like_n.c>]</option>
-->>			pass
				</select>
			</div>
		</div>
-->>		pass
-->>	pass
		<div class="layui-inline">
			<label class="layui-form-label">
				<button class="layui-btn" type="submit">搜&nbsp;&nbsp;索</button>
			</label>
		</div>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;height:15px">
		<legend>[<A.c>]管理</legend> 
	</fieldset>

	<div style="float: right">
		<label class="layui-form-label">
			<a href="<%=basePath%>admin/show[<A>]Add">
				<button class="layui-btn layui-btn-normal">增加[<A.c>]</button>
			</a>
		</label>
	</div>
	
	<table class="layui-table" >
		<thead>
		  <tr>
			<td  align="center" valign="middle"></td>
			<td  align="center" valign="middle">[<n>]</td>
			<td  align="center" valign="middle" colspan='4'>操作</td>
		  </tr>
		</thead>
		<c:choose>
			<c:when test="${list == null || fn:length(list)==0}">
				<tr>
					<td align="center" valign="middle" colspan="13">无[<A.c>]数据</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="list">
					<tr>
						<td align="center" valign="middle">${list.rowNum}</td>
						<td align="center" valign="middle">${list.[<n>]}</a></td>
						<td align="center" valign="middle">
						<a class="layui-btn layui-btn-xs" href="<%=basePath%>admin/show[<A>]Mod?[<id>]=${list.[<id>]}" ><i class="layui-icon">&#xe642;</i>编辑</a>
						<a class="layui-btn layui-btn-danger layui-btn-xs"  href="javascript:del('${list.[<id>]}');"><i class="layui-icon">&#xe640;</i>删除</a></td> 
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	  	<!-- 分页 -->
		<tr>
			<td colspan="13" align="center" valign="middle">
				<span id="pageNav"></span>
			</td>
		</tr>
		<script type="text/javascript" src="<%=basePath%>resources/js/admin/[<A>].js"></script>
		<script type="text/javascript">
			$(function(){
				pageHelp('${pages.page}','${pages.totalPage}','${pages.rows}');
			}); 
			layui.use('layer',function(){
				var layer=layui.layer;
			});
		</script>
	</body>
</html>