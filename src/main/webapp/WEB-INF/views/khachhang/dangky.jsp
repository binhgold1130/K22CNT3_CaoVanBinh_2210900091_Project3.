<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center justify-content-center bg-light" style="min-height: 100vh;">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4 shadow">
                <h2 class="text-center mb-4">Đăng Ký Tài Khoản</h2>
                <form action="${pageContext.request.contextPath}/dangky" method="post" onsubmit="return validateForm()">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="hoTen" name="hoTen" placeholder="Nhập họ tên" required>
                        <label for="hoTen">Họ Tên</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
                        <label for="email">Email</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                        <label for="password">Mật Khẩu</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Xác nhận mật khẩu" required>
                        <label for="confirmPassword">Xác Nhận Mật Khẩu</label>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                ${error}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="submit" class="btn btn-primary">Đăng Ký</button>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">Quay Lại Đăng Nhập</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function validateForm() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        if (password !== confirmPassword) {
            alert("Mật khẩu và xác nhận mật khẩu không trùng khớp.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>