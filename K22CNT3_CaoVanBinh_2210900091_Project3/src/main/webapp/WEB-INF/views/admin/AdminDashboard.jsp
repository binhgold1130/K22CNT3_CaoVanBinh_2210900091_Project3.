<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Quản lý Thanh toán Điện Dân dụng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        .sidebar {
            min-height: 100vh;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }
        .sidebar .nav-link {
            color: #333;
            padding: 10px 15px;
        }
        .sidebar .nav-link:hover {
            background-color: #f8f9fa;
            color: #007bff;
        }
        .card-stats {
            transition: transform 0.2s;
        }
        .card-stats:hover {
            transform: translateY(-5px);
        }
        .main-content {
            background-color: #f8f9fa;
            min-height: 100vh;
        }
    </style>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
            <div class="position-sticky pt-4">
                <div class="text-center mb-4">
                    <i class="fas fa-user-shield fa-3x text-primary"></i>
                    <h4 class="mt-2">Admin</h4>
                </div>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/khachhangdashboard">
                            <i class="fas fa-users me-2"></i> Quản lý Khách hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link content-link"
                           href="#"
                           data-url="${pageContext.request.contextPath}/hoadon/list">
                            <i class="fas fa-file-invoice me-2"></i> Quản lý Hóa đơn
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link content-link"
                           href="#"
                           data-url="${pageContext.request.contextPath}/chisodien/list">
                            <i class="fas fa-chart-bar me-2"></i> Quản lý Chỉ số Điện
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link content-link"
                           href="#"
                           data-url="${pageContext.request.contextPath}/congtodien/list">
                            <i class="fas fa-tachometer-alt me-2"></i> Quản lý Công tơ Điện
                        </a>
                    </li>
                    <li class="nav-item mt-3">
                        <a href="${pageContext.request.contextPath}/logout"
                           class="btn btn-danger w-100">
                            <i class="fas fa-sign-out-alt me-2"></i> Đăng xuất
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">
            <div id="mainContent" class="pt-3">
                <div id="dashboardContent">
                    <h2 class="text-center mb-4">
                        Admin Dashboard - Quản lý Thanh toán Điện Dân dụng
                    </h2>

                    <!-- Thống kê -->
                    <div class="row mt-4">
                        <div class="col-md-3">
                            <div class="card card-stats text-dark bg-light mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Tổng số Công tơ</h5>
                                    <p class="card-text fs-4">
                                        <%= request.getAttribute("totalCongToDien") != null ? request.getAttribute("totalCongToDien") : "0" %>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card card-stats text-dark bg-light mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Công tơ hoạt động</h5>
                                    <p class="card-text fs-4">
                                        <%= request.getAttribute("congToDienDangHoatDong") != null ? request.getAttribute("congToDienDangHoatDong") : "0" %>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card card-stats text-dark bg-light mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Tổng số Khách hàng</h5>
                                    <p class="card-text fs-4">
                                        <%= request.getAttribute("totalKhachHang") != null ? request.getAttribute("totalKhachHang") : "0" %>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card card-stats text-dark bg-light mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Doanh thu tháng</h5>
                                    <p class="card-text fs-4">
                                        <% if (request.getAttribute("doanhThuThangHienTai") != null) { %>
                                        <%= java.text.NumberFormat.getInstance(new java.util.Locale("vi", "VN")).format(request.getAttribute("doanhThuThangHienTai")) %> VNĐ
                                        <% } else { %>
                                        0 VNĐ
                                        <% } %>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Biểu đồ doanh thu -->
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <canvas id="revenueChart" height="100"></canvas>
                        </div>
                    </div>

                    <!-- Thông tin tài khoản Admin -->
                    <div class="mt-4">
                        <h3 class="fs-4">Thông tin tài khoản Admin</h3>
                        <div class="card bg-light p-3">
                            <p class="mb-1"><strong>Tên:</strong> ${sessionScope.nguoiDung.hoTen}</p>
                            <p class="mb-0"><strong>Email:</strong> ${sessionScope.nguoiDung.email}</p>
                        </div>
                    </div>

                    <!-- Danh sách khách hàng -->
                    <div class="mt-4">
                        <h3 class="fs-4">Danh Sách Khách Hàng</h3>
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered">
                                <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Họ Tên</th>
                                    <th>Email</th>
                                    <th>Số Điện Thoại</th>
                                    <th>Địa Chỉ</th>
                                    <th>Vai Trò</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="khachHang" items="${danhSachKhachHang}">
                                    <tr>
                                        <td>${khachHang.idNguoiDung}</td>
                                        <td>${khachHang.hoTen}</td>
                                        <td>${khachHang.email}</td>
                                        <td>${khachHang.sdt}</td>
                                        <td>${khachHang.diaChi}</td>
                                        <td>${khachHang.vaiTro}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    $(document).ready(function() {
        $(".content-link").click(function(e) {
            e.preventDefault();
            const url = $(this).data("url");
            $.ajax({
                url: url,
                method: "GET",
                success: function(response) {
                    $("#mainContent").html(response);
                    $("#dashboardContent").hide();
                },
                error: function(xhr, status, error) {
                    alert("Không thể tải nội dung: " + error);
                }
            });
        });
    });
</script>
</body>
</html>