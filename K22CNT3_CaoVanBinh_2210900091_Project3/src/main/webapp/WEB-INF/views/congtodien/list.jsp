<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách công tơ điện</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/style.css">
    <style>
        .table-container {
            margin: 20px;
        }
        .action-btn {
            margin: 0 5px;
        }
    </style>
</head>

<body>
<div class="container table-container">
    <h2 class="mb-4 text-center">Danh Sách Công Tơ Điện</h2>

    <!-- Nút thêm mới -->
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/congtodien/add"
           class="btn btn-success">
            Thêm Công Tơ Mới
        </a>
    </div>

    <!-- Bảng dữ liệu -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>ID Người Dùng</th>
            <th>Số Công Tơ</th>
            <th>Trạng Thái</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="congTo" items="${congToList}">
            <tr>
                <form method="post"
                      action="${pageContext.request.contextPath}/congtodien/update">
                    <input type="hidden"
                           name="idCongTo"
                           value="${congTo.idCongTo}">
                    <td>${congTo.idCongTo}</td>
                    <td>
                        <input type="number"
                               class="form-control"
                               name="idNguoiDung"
                               value="${congTo.idNguoiDung}"
                               required>
                    </td>
                    <td>
                        <input type="text"
                               class="form-control"
                               name="soCongTo"
                               value="${congTo.soCongTo}"
                               required>
                    </td>
                    <td>
                        <select class="form-select"
                                name="trangThai">
                            <option value="Hoạt động"
                                ${congTo.trangThai == 'Hoạt động' ? 'selected' : ''}>
                                Hoạt động
                            </option>
                            <option value="Không hoạt động"
                                ${congTo.trangThai == 'Không hoạt động' ? 'selected' : ''}>
                                Không hoạt động
                            </option>
                            <option value="Tạm ngưng"
                                ${congTo.trangThai == 'Tạm ngưng' ? 'selected' : ''}>
                                Tạm ngưng
                            </option>
                        </select>
                    </td>
                    <td>
                        <button type="submit"
                                class="btn btn-warning btn-sm action-btn">
                            Lưu
                        </button>
                        <a href="${pageContext.request.contextPath}/congtodien/delete/${congTo.idCongTo}"
                           class="btn btn-danger btn-sm action-btn"
                           onclick="return confirm('Bạn có chắc muốn xóa?')">
                            Xóa
                        </a>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Nút quay lại -->
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/admindashboard"
           class="btn btn-secondary">
            Quay Lại
        </a>
    </div>
</div>

<!-- Bootstrap JS (optional, nếu cần interactive features) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>