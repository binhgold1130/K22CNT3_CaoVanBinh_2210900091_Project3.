<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Chỉ Số Điện</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table-container {
            margin: 20px auto;
            max-width: 1200px;
        }
        .btn-action {
            margin: 0 5px;
        }
        .chi-so-input {
            width: 100px;
            display: inline-block;
        }
    </style>
    <script>
        function tinhDienTieuThu(input) {
            const row = input.closest("tr");
            const chiSoCu = parseInt(row.querySelector(".chiSoCu").textContent) || 0;
            let chiSoMoi = parseInt(input.value) || 0;

            if (chiSoMoi < chiSoCu) {
                alert("⚠️ Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ!");
                input.value = chiSoCu;
                chiSoMoi = chiSoCu;
            }

            const dienTieuThuCell = row.querySelector(".dienTieuThu");
            dienTieuThuCell.textContent = chiSoMoi - chiSoCu;
        }

        function submitForm(form) {
            form.submit();
            return false;
        }
    </script>
</head>

<body class="bg-light">
<div class="container table-container">
    <h2 class="text-center mb-4">Danh Sách Chỉ Số Điện</h2>

    <!-- Thông báo -->
    <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
                ${success}
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>

    <!-- Bảng dữ liệu -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID Chỉ Số</th>
            <th>ID Công Tơ</th>
            <th>Chỉ Số Cũ</th>
            <th>Chỉ Số Mới</th>
            <th>Điện Tiêu Thụ</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="chiSo" items="${listChiSoDien}">
            <tr>
                <td>${chiSo.idChiSo}</td>
                <td>${chiSo.idCongTo}</td>
                <td class="chiSoCu">${chiSo.chiSoCu}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/chisodien/updateChiSoMoi"
                          method="post"
                          class="d-flex align-items-center">
                        <input type="hidden"
                               name="idChiSo"
                               value="${chiSo.idChiSo}">
                        <input type="number"
                               name="chiSoMoi"
                               value="${chiSo.chiSoMoi}"
                               class="form-control chi-so-input me-2"
                               oninput="tinhDienTieuThu(this)"
                               required>
                        <button type="submit"
                                class="btn btn-success btn-sm btn-action">
                            Lưu
                        </button>
                    </form>
                </td>
                <td class="dienTieuThu">${chiSo.dienTieuThu}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/chisodien/delete?id=${chiSo.idChiSo}"
                       class="btn btn-danger btn-sm btn-action"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">
                        Xóa
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nút điều hướng -->
    <div class="d-flex justify-content-between mt-3">
        <a href="${pageContext.request.contextPath}/chisodien/add"
           class="btn btn-primary">
            Thêm Chỉ Số Điện
        </a>
        <a href="${pageContext.request.contextPath}/admindashboard"
           class="btn btn-secondary">
            Quay Lại
        </a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>