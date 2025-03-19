<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Công Tơ Điện</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .form-container {
            max-width: 500px;
            margin: 0 auto;
        }
        .btn-group {
            margin-top: 20px;
        }
    </style>
</head>

<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">Thêm Công Tơ Điện</h2>

    <!-- Thông báo lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>

    <!-- Form thêm công tơ -->
    <div class="form-container">
        <form method="post"
              action="${pageContext.request.contextPath}/congtodien/save"
              class="border p-4 rounded shadow bg-white">

            <div class="mb-3">
                <label for="idNguoiDung" class="form-label">
                    ID Người Dùng:
                </label>
                <input type="number"
                       id="idNguoiDung"
                       name="idNguoiDung"
                       class="form-control"
                       required>
            </div>

            <div class="mb-3">
                <label for="soCongTo" class="form-label">
                    Số Công Tơ:
                </label>
                <input type="text"
                       id="soCongTo"
                       name="soCongTo"
                       class="form-control"
                       required>
            </div>

            <div class="mb-3">
                <label for="trangThai" class="form-label">
                    Trạng Thái:
                </label>
                <select id="trangThai"
                        name="trangThai"
                        class="form-select">
                    <option value="Hoạt động">Hoạt động</option>
                    <option value="Không hoạt động">Không hoạt động</option>
                </select>
            </div>

            <!-- Nút hành động -->
            <div class="btn-group d-flex justify-content-between">
                <button type="submit"
                        class="btn btn-primary">
                    Lưu
                </button>
                <a href="${pageContext.request.contextPath}/congtodien/list"
                   class="btn btn-secondary">
                    Quay Lại
                </a>
            </div>
        </form>
    </div>
</div>

<!-- Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>