<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Thanh Toán</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .table-container {
            margin: 20px auto;
            max-width: 1200px;
        }
        .btn-action {
            margin: 0 5px;
        }
        .form-control-smaller {
            width: 150px;
            display: inline-block;
        }
        .hoa-don-info {
            white-space: nowrap;
        }
    </style>
</head>

<body class="bg-light">
<div class="container table-container">
    <h2 class="text-center mb-4">Danh Sách Thanh Toán</h2>

    <!-- Bảng thanh toán -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID Thanh Toán</th>
            <th>ID Hóa Đơn</th>
            <th>Thông Tin Hóa Đơn</th>
            <th>Số Tiền Thanh Toán</th>
            <th>Phương Thức</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="thanhToan" items="${listThanhToan}">
            <tr>
                <form action="${pageContext.request.contextPath}/thanhtoan/update" method="post">
                    <td>
                        <input type="hidden"
                               name="idThanhToan"
                               value="${thanhToan.idThanhToan}">
                            ${thanhToan.idThanhToan}
                    </td>
                    <td>
                        <input type="number"
                               name="idHoaDon"
                               value="${thanhToan.idHoaDon}"
                               class="form-control form-control-smaller"
                               required>
                    </td>
                    <td class="hoa-don-info">
                            <%-- Hiển thị thông tin hóa đơn từ idHoaDon --%>
                        <c:set var="hoaDon" value="${hoaDonDAO.getHoaDonById(thanhToan.idHoaDon)}" scope="request"/>
                        <c:choose>
                            <c:when test="${hoaDon != null}">
                                Tổng tiền: ${hoaDon.tongTien}
                                <br>
                                Trạng thái:
                                <span class="badge ${hoaDon.trangThai == 'daTT' ? 'bg-success' : hoaDon.trangThai == 'quaHan' ? 'bg-danger' : 'bg-warning'}">
                                        ${hoaDon.trangThai == 'chuaTT' ? 'Chưa thanh toán' : hoaDon.trangThai == 'daTT' ? 'Đã thanh toán' : 'Quá hạn'}
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-muted">Không tìm thấy hóa đơn</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <input type="number"
                               name="soTienTT"
                               value="${thanhToan.soTienTT}"
                               step="0.01"
                               class="form-control form-control-smaller"
                               required>
                    </td>
                    <td>
                        <select name="phuongThuc"
                                class="form-select form-control-smaller">
                            <option value="tienMat" ${thanhToan.phuongThuc == 'tienMat' ? 'selected' : ''}>
                                Tiền Mặt
                            </option>
                            <option value="chuyenKhoan" ${thanhToan.phuongThuc == 'chuyenKhoan' ? 'selected' : ''}>
                                Chuyển Khoản
                            </option>
                            <option value="theTinDung" ${thanhToan.phuongThuc == 'theTinDung' ? 'selected' : ''}>
                                Thẻ Tín Dụng
                            </option>
                        </select>
                    </td>
                    <td>
                        <button type="submit"
                                class="btn btn-warning btn-sm btn-action">
                            Cập Nhật
                        </button>
                        <a href="${pageContext.request.contextPath}/thanhtoan/delete/${thanhToan.idThanhToan}"
                           class="btn btn-danger btn-sm btn-action"
                           onclick="return confirm('Bạn có chắc muốn xóa?')">
                            Xóa
                        </a>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nút điều hướng -->
    <div class="d-flex justify-content-between mt-3">
        <a href="${pageContext.request.contextPath}/thanhtoan/add"
           class="btn btn-primary">
            Thêm Thanh Toán
        </a>
        <a href="${pageContext.request.contextPath}/"
           class="btn btn-secondary">
            Quay Lại
        </a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>