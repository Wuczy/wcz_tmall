
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="herf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    // var DOM = (document.getElementById) ? 1 : 0;
    // var NS4 = (document.layers) ? 1 : 0;
    // var IE4 = 0;
    // if (document.all) {
    //     IE4 = 1;
    //     DOM = 0;
    // }
    // var win = window;
    // var n = 0;
    // function findIt() {
    //     if (document.getElementById("searchstr").value != "")
    //         findInPage(document.getElementById("searchstr").value);
    // }
    // function findInPage(str) {
    //     var txt, i, found;
    //     if (str == "")
    //         return false;
    //     if (DOM) {
    //         win.find(str, false, true);
    //         return true;
    //     }
	// 	if (NS4) {
    //         if (!win.find(str))
    //             while(win.find(str, false, true))
    //                 n++;
    //         else
    //             n++;
    //         if (n == 0)
    //             alert("未找到指定内容.");
    //     }
	// 	if (IE4) {
    //         txt = win.document.body.createTextRange();
	// 		for (i = 0; i <= n && (found = txt.findText(str)) != false; i++) {
    //             txt.moveStart("character", 1);
    //             txt.moveEnd("textedit");
    //         }
    //         if (found) {
    //             txt.moveStart("character", -1);
    //             txt.findText(str);
    //             txt.select();
    //             txt.scrollIntoView();
    //             n++;
    //         }
    //         else {
    //             if (n > 0) {
    //                 n = 0;
    //                 findInPage(str);
    //             }
    //             else
    //                 alert("未找到指定内容.");
    //         }
    //     }
    //     return false;
    // }
</script>

<title>用户管理</title>


<div class="workingArea">
	<h1 class="label label-info" >用户管理</h1>

	<br>
	<br>

	<form method="post" class="addFormDetail" action="admin_user_find" enctype="multipart/form-data">
		<table class="addTable">
			<tr class="submitTR">
				<td align="left">
					模糊查询：<input type="text" name="name"/>
					<button type="submit" class="btn btn-success">搜 索</button>
				</td>
			</tr>
		</table>
	</form>

	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
			<tr class="success">
				<th>ID</th>
				<th>用户名称</th>
				<th>用户密码</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${us}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.name}</td>
					<td>${u.password}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>


</div>

<%@include file="../include/admin/adminFooter.jsp"%>
