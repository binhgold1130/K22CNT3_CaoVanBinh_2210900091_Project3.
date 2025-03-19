<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-container {
            max-width: 400px;
            margin: auto;
        }
        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-floating label {
            color: #6c757d;
        }
    </style>
</head>

<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">
<div class="container login-container">
    <div class="card p-4">
        <h2 class="text-center mb-4">Đăng Nhập</h2>

        <!-- Form đăng nhập -->
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-floating mb-3">
                <input type="email"
                       class="form-control"
                       id="email"
                       name="email"
                       placeholder="Nhập email"
                       autofocus
                       required>
                <label for="email">Email</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password"
                       class="form-control"
                       id="password"
                       name="password"
                       placeholder="Nhập mật khẩu"
                       required>
                <label for="password">Mật Khẩu</label>
            </div>

            <!-- Thông báo lỗi -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${error}
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="alert"
                            aria-label="Close"></button>
                </div>
            </c:if>

            <!-- Nút hành động -->
            <div class="d-flex justify-content-between mt-4">
                <button type="submit"
                        class="btn btn-primary">
                    Đăng Nhập
                </button>
                <a href="${pageContext.request.contextPath}/index.jsp"
                   class="btn btn-secondary">
                    Quay Lại
                </a>
            </div>
        </form>

        <!-- Liên kết bổ sung -->
        <div class="mt-3 text-center">
            <a href="#" class="text-muted">Quên mật khẩu?</a> |
            <a href="#" class="text-muted">Đăng ký</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>