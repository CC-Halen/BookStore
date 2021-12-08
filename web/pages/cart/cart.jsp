<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">

        $(function () {
            $("a.deleteItem").click(function () {

                return confirm("你确定要删除[" + $(this).parent().parent().find("td:first").text() + "]吗?")
            });


            $("a.clearCart").click(function () {

                return confirm("你确定要删除要清空购物车吗?")
            });

            $(".updateCount").change(function () {
                let name = $(this).parent().parent().find("td:first").text();
                let count  = this.value;
                let id = $(this).attr("bookId");
                if (confirm("你确定要把["+name+"]的数量修改为"+count+"？")){
                    //发起请求，进行修改
                    location.href = "http://localhost:8989/book/cartServlet?action=updateCount&id="+id+"&count="+count;

                }else {
                    this.value = this.defaultValue;
                }
            })
        })


    </script>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>


</div>

<div id="main">

    <c:if test="${ empty sessionScope.cart.items}">
        <h2 align="center"><a href="index.jsp">亲，你还没有加入任何商品哦。。。</a></h2>
    </c:if>
    <c:if test="${not empty sessionScope.cart.items}">
        <table>
            <tr>
                <th>商品名称</th>
                <th>数量</th>
                <th>单价</th>
                <th>金额</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${sessionScope.cart.items}" var="entry">

                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                    <input class="updateCount" style="width: 40px" type="text"
                           bookId="${entry.value.id}"
                           name="count" value="${entry.value.count}">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>


        </table>


        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>

</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>