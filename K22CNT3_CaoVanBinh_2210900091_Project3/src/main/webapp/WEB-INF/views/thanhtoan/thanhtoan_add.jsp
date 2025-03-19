<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${thanhToan != null ? 'Chỉnh Sửa Thanh Toán' : 'Thêm Thanh Toán'}</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
    <h2 class="text-center mb-4">
        ${thanhToan != null ? 'Chỉnh Sửa Thanh Toán' : 'Thêm Thanh Toán Mới'}
    </h2>

    <!-- Form thêm/chỉnh sửa thanh toán -->
    <div class="form-container bg-white shadow rounded">
        <form action="${pageContext.request.contextPath}/thanhtoan/${thanhToan != null ? 'update' : 'insert'}"
              method="post">
            <c:if test="${thanhToan != null}">
                <input type="hidden"
                       name="idThanhToan"
                       value="${thanhToan.idThanhToan}">
            </c:if>

            <div class="mb-3">
                <label for="idHoaDon" class="form-label fw-bold">
                    Hóa Đơn:
                </label>
                <select id="idHoaDon"
                        name="idHoaDon"
                        class="form-select"
                        required>
                    <c:forEach var="hoaDon" items="${hoaDons}">
                        <option value="${hoaDon.idHoaDon}"
                            ${thanhToan != null && thanhToan.idHoaDon == hoaDon.idHoaDon ? 'selected' : ''}>
                                ${hoaDon.idHoaDon} - Tổng tiền: ${hoaDon.tongTien} -
                            Trạng thái:
                                ${hoaDon.trangThai == 'chuaTT' ? 'Chưa thanh toán' :
                                        hoaDon.trangThai == 'daTT' ? 'Đã thanh toán' : 'Quá hạn'}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="soTienTT" class="form-label fw-bold">
                    Số Tiền Thanh Toán:
                </label>
                <input type="number"
                       id="soTienTT"
                       name="soTienTT"
                       step="0.01"
                       value="${thanhToan != null ? thanhToan.soTienTT : ''}"
                       class="form-control"
                       required>
            </div>

            <div class="mb-3">
                <label for="phuongThuc" class="form-label fw-bold">
                    Phương Thức:
                </label>
                <select id="phuongThuc"
                        name="phuongThuc"
                        class="form-select">
                    <option value="tienMat" ${thanhToan != null && thanhToan.phuongThuc == 'tienMat' ? 'selected' : ''}>
                        Tiền Mặt
                    </option>
                    <option value="chuyenKhoan" ${thanhToan != null && thanhToan.phuongThuc == 'chuyenKhoan' ? 'selected' : ''}>
                        Chuyển Khoản
                    </option>
                    <option value="theTinDung" ${thanhToan != null && thanhToan.phuongThuc == 'theTinDung' ? 'selected' : ''}>
                        Thẻ Tín Dụng
                    </option>
                </select>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit"
                        class="btn btn-primary">
                    ${thanhToan != null ? 'Cập Nhật' : 'Thêm Thanh Toán'}
                </button>
                <a href="${pageContext.request.contextPath}/thanhtoan/list"
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