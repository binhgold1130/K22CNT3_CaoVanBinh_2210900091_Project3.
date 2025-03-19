<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Hóa Đơn</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <style>
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
        }
    </style>
</head>

<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">Thêm Hóa Đơn Mới</h2>

    <!-- Form thêm hóa đơn -->
    <div class="form-container bg-white shadow rounded">
        <form action="${pageContext.request.contextPath}/hoadon/insert" method="post">
            <div class="mb-3">
                <label for="idNguoiDung" class="form-label fw-bold">
                    Người Dùng:
                </label>
                <select id="idNguoiDung"
                        name="idNguoiDung"
                        class="form-select"
                        required>
                    <c:forEach var="nguoiDung" items="${nguoiDungs}">
                        <option value="${nguoiDung.idNguoiDung}">
                                ${nguoiDung.hoTen} (${nguoiDung.idNguoiDung})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="idChiSo" class="form-label fw-bold">
                    ID Chỉ Số:
                </label>
                <input type="number"
                       id="idChiSo"
                       name="idChiSo"
                       class="form-control"
                       required>
            </div>

            <div class="mb-3">
                <label for="tongTien" class="form-label fw-bold">
                    Tổng Tiền:
                </label>
                <input type="number"
                       id="tongTien"
                       name="tongTien"
                       step="0.01"
                       class="form-control"
                       required>
            </div>

            <div class="mb-3">
                <label for="trangThai" class="form-label fw-bold">
                    Trạng Thái:
                </label>
                <select id="trangThai"
                        name="trangThai"
                        class="form-select">
                    <option value="chuaTT">Chưa thanh toán</option>
                    <option value="daTT">Đã thanh toán</option>
                    <option value="quaHan">Quá hạn</option>
                </select>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit"
                        class="btn btn-primary">
                    Thêm Hóa Đơn
                </button>
                <a href="${pageContext.request.contextPath}/hoadon/list"
                   class="btn btn-secondary">
                    Quay Lại
                </a>
            </div>
        </form>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>