<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Khách Hàng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .dashboard-container {
            max-width: 1200px;
            margin: 0 auto;
        }
        .table-container {
            margin-top: 30px;
        }
        .user-info {
            font-size: 1.1rem;
        }
        .btn-action {
            margin: 0 5px;
        }
    </style>
</head>

<body class="bg-light">
<div class="container dashboard-container mt-5">
    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-center flex-grow-1">Dashboard Khách Hàng</h2>
        <div class="user-info text-end">
            Chào mừng, ${sessionScope.nguoiDung.hoTen}
            <a href="${pageContext.request.contextPath}/logout"
               class="btn btn-danger btn-sm ms-2">
                Đăng Xuất
            </a>
        </div>
    </div>

    <!-- Danh sách khách hàng -->
    <c:if test="${not empty danhSachKhachHang}">
        <div class="table-container">
            <h3>Danh Sách Khách Hàng</h3>
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Họ Tên</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="khachHang" items="${danhSachKhachHang}">
                    <tr>
                        <td>${khachHang.idNguoiDung}</td>
                        <td>${khachHang.hoTen}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/khachhangdashboard?idNguoiDung=${khachHang.idNguoiDung}"
                               class="btn btn-primary btn-sm btn-action">
                                Xem Chi Tiết
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <!-- Danh sách công tơ điện -->
    <c:if test="${not empty congToDien}">
        <div class="table-container">
            <h3>Danh Sách Công Tơ Điện của ${nguoiDungHienThi.hoTen}</h3>
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>ID Công Tơ</th>
                    <th>ID Người Dùng</th>
                    <th>Số Công Tơ</th>
                    <th>Trạng Thái</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ctd" items="${congToDien}">
                    <tr>
                        <td>${ctd.idCongTo}</td>
                        <td>${ctd.idNguoiDung}</td>
                        <td>${ctd.soCongTo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ctd.trangThai eq 'Hoạt động'}">
                                    <span class="badge bg-success">Hoạt động</span>
                                </c:when>
                                <c:when test="${ctd.trangThai eq 'Không hoạt động'}">
                                    <span class="badge bg-secondary">Không hoạt động</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-warning">Tạm ngưng</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/hoadon/list?congToId=${ctd.idCongTo}"
                               class="btn btn-primary btn-sm btn-action">
                                Xem Hóa Đơn
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <!-- Nút quay lại -->
    <div class="text-center mt-4">
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