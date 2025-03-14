<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Hóa Đơn</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <style>
        .table-container {
            margin: 20px auto;
            max-width: 1200px;
        }
        .btn-action {
            margin: 0 5px;
        }
        .form-control-smaller {
            width: 120px;
            display: inline-block;
        }
    </style>
</head>

<body class="bg-light">
<div class="container table-container">
    <h2 class="text-center mb-4">Danh Sách Hóa Đơn</h2>

    <!-- Thông báo lỗi -->
    <c:if test="${param.error == 'not_admin'}">
        <div class="alert alert-danger" role="alert">
            Bạn không có quyền thực hiện thao tác này.
        </div>
    </c:if>

    <!-- Bảng hóa đơn -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID Hóa Đơn</th>
            <th>ID Người Dùng</th>
            <th>ID Chỉ Số</th>
            <th>Tổng Tiền</th>
            <th>Trạng Thái</th>
            <c:if test="${nguoiDung.vaiTro == 'admin'}">
                <th>Hành Động</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hoaDon" items="${listHoaDon}">
            <tr>
                <c:choose>
                    <c:when test="${nguoiDung.vaiTro == 'admin'}">
                        <form action="${pageContext.request.contextPath}/hoadon/edit" method="post">
                            <td>
                                <input type="hidden"
                                       name="idHoaDon"
                                       value="${hoaDon.idHoaDon}">
                                    ${hoaDon.idHoaDon}
                            </td>
                            <td>
                                <input type="number"
                                       name="idNguoiDung"
                                       value="${hoaDon.idNguoiDung}"
                                       class="form-control form-control-smaller"
                                       required>
                            </td>
                            <td>
                                <input type="number"
                                       name="idChiSo"
                                       value="${hoaDon.idChiSo}"
                                       class="form-control form-control-smaller"
                                       required>
                            </td>
                            <td>
                                <input type="number"
                                       name="tongTien"
                                       value="${hoaDon.tongTien}"
                                       step="0.01"
                                       class="form-control form-control-smaller"
                                       required>
                            </td>
                            <td>
                                <select name="trangThai"
                                        class="form-select form-control-smaller">
                                    <option value="chuaTT" ${hoaDon.trangThai == 'chuaTT' ? 'selected' : ''}>
                                        Chưa thanh toán
                                    </option>
                                    <option value="daTT" ${hoaDon.trangThai == 'daTT' ? 'selected' : ''}>
                                        Đã thanh toán
                                    </option>
                                    <option value="quaHan" ${hoaDon.trangThai == 'quaHan' ? 'selected' : ''}>
                                        Quá hạn
                                    </option>
                                </select>
                            </td>
                            <td>
                                <button type="submit"
                                        class="btn btn-warning btn-sm btn-action">
                                    Cập Nhật
                                </button>
                                <a href="${pageContext.request.contextPath}/hoadon/delete?id=${hoaDon.idHoaDon}"
                                   class="btn btn-danger btn-sm btn-action"
                                   onclick="return confirm('Bạn có chắc muốn xóa?')">
                                    Xóa
                                </a>
                            </td>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <td>${hoaDon.idHoaDon}</td>
                        <td>${hoaDon.idNguoiDung}</td>
                        <td>${hoaDon.idChiSo}</td>
                        <td>${hoaDon.tongTien}</td>
                        <td>
                            <c:choose>
                                <c:when test="${hoaDon.trangThai == 'chuaTT'}">Chưa thanh toán</c:when>
                                <c:when test="${hoaDon.trangThai == 'daTT'}">Đã thanh toán</c:when>
                                <c:when test="${hoaDon.trangThai == 'quaHan'}">Quá hạn</c:when>
                            </c:choose>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nút điều hướng -->
    <div class="d-flex justify-content-between mt-3">
        <c:if test="${nguoiDung.vaiTro == 'admin'}">
            <a href="${pageContext.request.contextPath}/hoadon/add"
               class="btn btn-primary">
                Thêm Hóa Đơn
            </a>
        </c:if>
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