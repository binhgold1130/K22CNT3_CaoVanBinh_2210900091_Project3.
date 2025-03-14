<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>${empty param.id ? 'Thêm Chỉ Số Điện' : 'Sửa Chỉ Số Điện'}</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .form-container {
      max-width: 500px;
      margin: 0 auto;
      padding: 20px;
    }
    .error-message {
      min-height: 20px;
    }
  </style>
  <script>
    function tinhDienTieuThu() {
      const chiSoCu = parseInt(document.getElementById("chiSoCu").value) || 0;
      let chiSoMoi = parseInt(document.getElementById("chiSoMoi").value) || 0;
      const errorMessage = document.getElementById("error-message");
      const dienTieuThuInput = document.getElementById("dienTieuThu");

      if (chiSoMoi < chiSoCu) {
        errorMessage.textContent = "⚠️ Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ!";
        document.getElementById("chiSoMoi").value = chiSoCu;
        chiSoMoi = chiSoCu;
      } else {
        errorMessage.textContent = "";
      }

      dienTieuThuInput.value = chiSoMoi - chiSoCu;
    }
  </script>
</head>

<body class="bg-light">
<div class="container mt-5">
  <h2 class="text-center mb-4">
    ${empty param.id ? 'Thêm Chỉ Số Điện' : 'Sửa Chỉ Số Điện'}
  </h2>

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

  <!-- Form -->
  <div class="form-container bg-white shadow rounded">
    <form action="${pageContext.request.contextPath}/chisodien/${empty param.id ? 'add' : 'update'}"
          method="post">
      <c:if test="${not empty param.id}">
        <input type="hidden"
               name="idChiSo"
               value="${chiSoDien.idChiSo}">
      </c:if>

      <div class="mb-3">
        <label for="idCongTo" class="form-label fw-bold">
          ID Công Tơ:
        </label>
        <input type="number"
               id="idCongTo"
               name="idCongTo"
               value="${chiSoDien.idCongTo}"
               class="form-control"
               required>
      </div>

      <div class="mb-3">
        <label for="chiSoCu" class="form-label fw-bold">
          Chỉ Số Cũ:
        </label>
        <input type="number"
               id="chiSoCu"
               name="chiSoCu"
               value="${chiSoDien.chiSoCu}"
               class="form-control"
               required>
      </div>

      <div class="mb-3">
        <label for="chiSoMoi" class="form-label fw-bold">
          Chỉ Số Mới:
        </label>
        <input type="number"
               id="chiSoMoi"
               name="chiSoMoi"
               value="${chiSoDien.chiSoMoi}"
               class="form-control"
               oninput="tinhDienTieuThu()"
               required>
        <p id="error-message" class="text-danger error-message"></p>
        <input type="hidden"
               id="dienTieuThu"
               name="dienTieuThu"
               value="${chiSoDien.dienTieuThu}">
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit"
                class="btn btn-primary">
          ${empty param.id ? 'Thêm' : 'Cập Nhật'}
        </button>
        <a href="${pageContext.request.contextPath}/chisodien/list"
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