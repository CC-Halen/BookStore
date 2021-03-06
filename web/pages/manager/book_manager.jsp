<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>


    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("a.deleteClass").click(function () {
                return confirm("你确定要删除[" + $(this).parent().parent().find("td:first").text() + "]?");
            });
        });
    </script>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>


</div>


<div id="main">
    <table>
        <tr>
            <th>名称</th>
            <th>价格</th>
            <th>作者</th>
            <th>销量</th>
            <th>库存</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">

            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a>
                </td>
                <td><a class="deleteClass"
                       href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
        </tr>
    </table>


    <div id="page_nav">
        <%--    大于首页才显示--%>
        <c:if test="${requestScope.page.pageNo >1}">
            <a href="${requestScope.page.url}&pageNo=1">首页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>

        <%--页码输出的开始--%>
        <c:choose>
            <%--情况1：如果总页码小于等于5 的情况，页码的范围是：1-总页码--%>
            <c:when test="${requestScope.page.pageTotal <=5}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>

            <%--小情况1：当前页码为前面3 个：1，2，3 的情况，页码范围是：1-5.--%>
            <c:when test="${requestScope.page.pageTotal > 5}">
                <c:choose>
                    <c:when test="${requestScope.page.pageNo<=3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>

                    <c:when test="${requestScope.page.pageNo>3}">
                        <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${requestScope.page.pageNo> requestScope.page.pageTotal-3}">
                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
            <c:otherwise>
                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
            </c:otherwise>

        </c:choose>

        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${i == requestScope.page.pageNo}">
                [${i}]
            </c:if>

            <c:if test="${i!=requestScope.page.pageNo}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>


        <%--    不是最后一页就显示下一页--%>
        <c:if test="${requestScope.page.pageNo <requestScope.page.pageTotal}">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>

        共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
        到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
        <input id="searchPageBtn" type="button" value="确定"/>

        <script type="text/javascript">
            $(function () {
                //跳到指定页面
                $("#searchPageBtn").click(function () {
                    const pageNo = $("#pn_input").val();
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                });
            });

        </script>

    </div>


</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>