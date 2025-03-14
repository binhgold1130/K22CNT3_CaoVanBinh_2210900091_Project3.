package controller;

import model.CVB_HoaDon;
import model.CVB_NguoiDung;
import model.CVB_NguoiDungDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CVB_NguoiDungController {

    public static final String ADMIN_ROLE = "admin";
    public static final String KHACHHANG_ROLE = "khachhang";

    @Autowired
    private CVB_NguoiDungDAO nguoiDungDAO;

    @GetMapping("/login")
    public String showLoginForm() {
        return "khachhang/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        if (email == null || password == null) {
            model.addAttribute("error", "Vui lòng nhập email và mật khẩu.");
            return "khachhang/login";
        }

        CVB_NguoiDung nguoiDung = nguoiDungDAO.getNguoiDungByEmail(email);

        if (nguoiDung != null) {
            if (password.equals(nguoiDung.getPassword())) {
                session.setAttribute("nguoiDung", nguoiDung);
                if (ADMIN_ROLE.equals(nguoiDung.getVaiTro())) {
                    return "redirect:/admindashboard";
                } else if (KHACHHANG_ROLE.equals(nguoiDung.getVaiTro())) {
                    return "redirect:/khachhangdashboard";
                } else {
                    model.addAttribute("error", "Vai trò không xác định.");
                    return "khachhang/login";
                }
            } else {
                model.addAttribute("error", "Mật khẩu không đúng.");
                return "khachhang/login";
            }
        } else {
            model.addAttribute("error", "Email không tồn tại.");
            return "khachhang/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Hiển thị form thêm hóa đơn
    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && ADMIN_ROLE.equals(nguoiDung.getVaiTro())) {
            List<CVB_NguoiDung> nguoiDungs = nguoiDungDAO.getAllNguoiDung();
            model.addAttribute("nguoiDungs", nguoiDungs);
            return "hoadon/hoadon_add";
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    // Xử lý request POST đến /insert
    @PostMapping("/insert")
    public String insertHoaDon(@ModelAttribute("hoadon") CVB_HoaDon hoadon, Model model, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && ADMIN_ROLE.equals(nguoiDung.getVaiTro())) {
            // Lấy dữ liệu từ form và lưu vào database (hoặc xử lý logic khác)
            // Ví dụ:
            // System.out.println("Hoa don: " + hoadon);
            // ... (lưu vào database)

            return "redirect:/hoadon/list"; // Redirect về trang danh sách hóa đơn
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    @GetMapping("/admindashboard")
    public String adminDashboard(Model model, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && ADMIN_ROLE.equals(nguoiDung.getVaiTro())) {
            model.addAttribute("totalCongToDien", nguoiDungDAO.getTotalRooms());
            model.addAttribute("congToDienDangHoatDong", nguoiDungDAO.getAvailableRooms());
            model.addAttribute("totalKhachHang", nguoiDungDAO.getTotalTenants());
            model.addAttribute("doanhThuThangHienTai", nguoiDungDAO.getMonthlyRevenue());
            model.addAttribute("tongTienThuDuoc", nguoiDungDAO.getTongTienThuDuoc());
            model.addAttribute("tongTienThuDuocTheoThang", nguoiDungDAO.getTongTienThuDuocTheoThang());
            model.addAttribute("totalHoaDonChuaThanhToan", nguoiDungDAO.getTotalHoaDonChuaThanhToan());
            model.addAttribute("totalChiSoDien", nguoiDungDAO.getTotalChiSoDien());

            List<CVB_NguoiDung> danhSachKhachHang = nguoiDungDAO.getAllNguoiDung();
            model.addAttribute("danhSachKhachHang", danhSachKhachHang);

            return "admin/AdminDashboard";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/khachhangdashboard")
    public String khachHangDashboard(@RequestParam(value = "idNguoiDung", required = false) Integer idNguoiDung, Model model, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        System.out.println("NguoiDung: " + nguoiDung);
        if (nguoiDung != null && (ADMIN_ROLE.equals(nguoiDung.getVaiTro()) || KHACHHANG_ROLE.equals(nguoiDung.getVaiTro()))) {
            System.out.println("VaiTro: " + nguoiDung.getVaiTro());
            if (idNguoiDung != null) {
                model.addAttribute("congToDien", nguoiDungDAO.getCongToDienByNguoiDungId(idNguoiDung));
                model.addAttribute("nguoiDungHienThi", nguoiDungDAO.getNguoiDungById(idNguoiDung));
            } else if (ADMIN_ROLE.equals(nguoiDung.getVaiTro())) {
                model.addAttribute("danhSachKhachHang", nguoiDungDAO.getAllKhachHang());
            } else {
                model.addAttribute("congToDien", nguoiDungDAO.getCongToDienByNguoiDungId(nguoiDung.getIdNguoiDung()));
                model.addAttribute("nguoiDungHienThi", nguoiDung);
            }
            return "khachhang/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    // Hiển thị form đăng ký
    @GetMapping("/dangky")
    public String showDangKyForm() {
        return "khachhang/dangky";
    }

    // Xử lý đăng ký người dùng
    @PostMapping("/dangky")
    public String dangKyNguoiDung(CVB_NguoiDung nguoiDung, @RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session) {
        System.out.println("dangKyNguoiDung() được gọi."); // Kiểm tra phương thức được gọi

        // Kiểm tra email đã tồn tại
        if (nguoiDungDAO.getNguoiDungByEmail(nguoiDung.getEmail()) != null) {
            System.out.println("Email đã tồn tại."); // Kiểm tra email tồn tại
            model.addAttribute("error", "Email đã tồn tại.");
            return "khachhang/dangky";
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (!nguoiDung.getPassword().equals(confirmPassword)) {
            System.out.println("Mật khẩu không trùng khớp."); // Kiểm tra mật khẩu
            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không trùng khớp.");
            return "khachhang/dangky";
        }

        // Tạo người dùng mới
        nguoiDung.setVaiTro(KHACHHANG_ROLE); // Gán vai trò khách hàng
        nguoiDungDAO.insertNguoiDung(nguoiDung);
        System.out.println("Người dùng đã được thêm."); // Kiểm tra thêm người dùng

        // Tự động đăng nhập (nếu cần)
        session.setAttribute("nguoiDung", nguoiDung);

        return "redirect:/khachhangdashboard"; // Chuyển hướng đến trang dashboard khách hàng
    }
}