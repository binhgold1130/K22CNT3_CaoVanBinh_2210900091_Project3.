package controller;

import model.CVB_HoaDon;
import model.CVB_HoaDonDAO;
import model.CVB_NguoiDung;
import model.CVB_NguoiDungDAO;
import model.CVB_ThanhToan;
import model.CVB_ThanhToanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/hoadon")
public class CVB_HoaDonController {
    private final CVB_HoaDonDAO hoaDonDAO;
    private final CVB_NguoiDungDAO nguoiDungDAO;
    private final CVB_ThanhToanDAO thanhToanDAO; // Thêm ThanhToanDAO

    @Autowired
    public CVB_HoaDonController(CVB_HoaDonDAO hoaDonDAO, CVB_NguoiDungDAO nguoiDungDAO, CVB_ThanhToanDAO thanhToanDAO) {
        this.hoaDonDAO = hoaDonDAO;
        this.nguoiDungDAO = nguoiDungDAO;
        this.thanhToanDAO = thanhToanDAO; // Khởi tạo ThanhToanDAO
    }

    // Hiển thị danh sách hóa đơn
    @GetMapping("/list")
    public String listHoaDon(Model model, HttpSession session) {
        List<CVB_HoaDon> list = hoaDonDAO.getAllHoaDon();
        model.addAttribute("listHoaDon", list);
        model.addAttribute("nguoiDung", (CVB_NguoiDung) session.getAttribute("nguoiDung"));
        return "hoadon/hoadon_list";
    }

    // Xóa hóa đơn
    @GetMapping("/delete")
    public String deleteHoaDon(@RequestParam("id") int id, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && "admin".equals(nguoiDung.getVaiTro())) {
            hoaDonDAO.deleteHoaDon(id);
            return "redirect:/hoadon/list";
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    // Cập nhật hóa đơn (trạng thái)
    @PostMapping("/update")
    public String updateHoaDon(@RequestParam("id") int id, @RequestParam("trangThai") String trangThai, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && "admin".equals(nguoiDung.getVaiTro())) {
            CVB_HoaDon hoaDon = hoaDonDAO.getHoaDonById(id);
            if (hoaDon != null) {
                hoaDon.setTrangThai(trangThai);
                hoaDonDAO.updateHoaDon(hoaDon);
            }
            return "redirect:/hoadon/list";
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    // Hiển thị form thêm hóa đơn
    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && "admin".equals(nguoiDung.getVaiTro())) {
            List<CVB_NguoiDung> nguoiDungs = nguoiDungDAO.getAllNguoiDung();
            model.addAttribute("nguoiDungs", nguoiDungs);
            return "hoadon/hoadon_add";
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    // Thêm hóa đơn
    @PostMapping("/insert")
    public String insertHoaDon(CVB_HoaDon hoaDon, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && "admin".equals(nguoiDung.getVaiTro())) {
            hoaDonDAO.insertHoaDon(hoaDon);
            return "redirect:/hoadon/list";
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    @PostMapping("/edit")
    public String editHoaDon(CVB_HoaDon hoaDon, HttpSession session) {
        CVB_NguoiDung nguoiDung = (CVB_NguoiDung) session.getAttribute("nguoiDung");
        if (nguoiDung != null && "admin".equals(nguoiDung.getVaiTro())) {
            hoaDonDAO.updateHoaDon(hoaDon);
            return "redirect:/hoadon/list";
        } else {
            return "redirect:/hoadon/list?error=not_admin";
        }
    }

    // Hiển thị form thanh toán hóa đơn
    @GetMapping("/thanhtoan/{id}")
    public String showThanhToanForm(@PathVariable("id") int id, Model model) {
        CVB_HoaDon hoaDon = hoaDonDAO.getHoaDonById(id);
        if (hoaDon != null) {
            model.addAttribute("hoaDon", hoaDon);
            return "hoadon/hoadon_thanhtoan";
        } else {
            return "redirect:/hoadon/list?error=hoadon_not_found";
        }
    }

    // Xử lý thanh toán hóa đơn
    @PostMapping("/thanhtoan")
    public String thanhToanHoaDon(@RequestParam("idHoaDon") int idHoaDon, @RequestParam("soTienTT") double soTienTT, @RequestParam("phuongThuc") String phuongThuc) {
        CVB_ThanhToan thanhToan = new CVB_ThanhToan();
        thanhToan.setIdHoaDon(idHoaDon);
        thanhToan.setSoTienTT(soTienTT);
        thanhToan.setPhuongThuc(phuongThuc);
        thanhToanDAO.insertThanhToan(thanhToan);

        CVB_HoaDon hoaDon = hoaDonDAO.getHoaDonById(idHoaDon);
        if (hoaDon != null) {
            hoaDon.setTrangThai("daTT");
            hoaDonDAO.updateHoaDon(hoaDon);
        }

        return "redirect:/hoadon/list";
    }
}