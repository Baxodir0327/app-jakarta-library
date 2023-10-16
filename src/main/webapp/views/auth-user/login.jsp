<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Logout</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h1>Login</h1>
            <form action="${pageContext.request.contextPath}/auth/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                    <label class="form-check-label" for="rememberMe">Remember me ?</label>
                </div>
                <button type="submit" class="btn btn-success">Login</button>
                <a href="${pageContext.request.contextPath}/auth/register" class="btn btn-warning">Register</a>
            </form>
        </div>
<%--        <div class="col-md-6">--%>
<%--            <h1>Register</h1>--%>
<%--            <form action="${pageContext.request.contextPath}/auth/register" method="post">--%>
<%--                <div class="mb-3">--%>
<%--                    <label for="p_email" class="form-label">Email address</label>--%>
<%--                    <input type="text" class="form-control" id="p_email" name="email">--%>
<%--                </div>--%>
<%--                <div class="mb-3">--%>
<%--                    <label for="p_password" class="form-label">Password</label>--%>
<%--                    <input type="password" class="form-control" id="p_password" name="password">--%>
<%--                </div>--%>
<%--                <div class="mb-3">--%>
<%--                    <label for="p2_password" class="form-label">Confirm Password</label>--%>
<%--                    <input type="password" class="form-control" id="p2_password" name="confirm_password">--%>
<%--                </div>--%>
<%--                <button type="submit" class="btn btn-primary">Register</button>--%>
<%--            </form>--%>
<%--        </div>--%>
    </div>
</div>

<jsp:include page="/fragments/js.jsp"/>
</body>
</html>