<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Thanh Toán Điện</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #222;
            text-align: center;
        }

        .container {
            width: 90%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        header {
            font-size: 1.8em;
            padding: 15px 0;
            border-bottom: 2px solid #444;
            color: #333;
        }

        nav ul {
            list-style: none;
            padding: 0;
            margin: 20px 0;
        }

        nav li {
            display: inline;
            margin: 0 15px;
        }

        nav a {
            color: #222;
            text-decoration: none;
            padding: 10px 15px;
            border: 1px solid #222;
            border-radius: 5px;
            transition: 0.3s;
        }

        nav a:hover {
            background-color: #222;
            color: #fff;
        }

        .login-button {
            background-color: #333;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }

        .login-button:hover {
            background-color: #555;
        }

        main {
            padding: 20px;
        }

        footer {
            margin-top: 20px;
            padding: 10px 0;
            border-top: 2px solid #444;
            font-size: 0.9em;
            color: #666;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>Quản Lý Thanh Toán Điện Dân Dụng</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/login" class="login-button">Đăng nhập</a></li>
        </ul>
    </nav>
    <main>
        <h2>Chào mừng quý khách</h2>
        <p>Vui lòng đăng nhập để xem thông tin.</p>
    </main>
    <footer>
        <p>&copy; 2024 Quản Lý Thanh Toán Điện Dân Dụng</p>
    </footer>
</div>
</body>
</html>
