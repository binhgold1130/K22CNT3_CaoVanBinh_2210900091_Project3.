package controller;

import model.CVB_HoaDon;
import model.CVB_HoaDonDAO;
import model.CVB_ThanhToan;
import model.CVB_ThanhToanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/thanhtoan")
public class CVB_ThanhToanController {
    private final CVB_ThanhToanDAO thanhToanDAO;
    private final CVB_HoaDonDAO hoaDonDAO; // Thêm HoaDonDAO

    @Autowired
    public CVB_ThanhToanController(CVB_ThanhToanDAO thanhToanDAO, CVB_HoaDonDAO hoaDonDAO) {
        this.thanhToanDAO = thanhToanDAO;
        this.hoaDonDAO = hoaDonDAO; // Khởi tạo HoaDonDAO
    }

    @GetMapping("/list")
    public String listThanhToan(Model model) {
        List<CVB_ThanhToan> list = thanhToanDAO.getAllThanhToan();
        model.addAttribute("listThanhToan", list);
        return "thanhtoan/thanhtoan_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Lấy danh sách hóa đơn để hiển thị trong form
        List<CVB_HoaDon> hoaDons = hoaDonDAO.getAllHoaDon();
        model.addAttribute("hoaDons", hoaDons);
        return "thanhtoan/thanhtoan_add";
    }

    @PostMapping("/insert")
    public String insertThanhToan(CVB_ThanhToan thanhToan) {
        thanhToanDAO.insertThanhToan(thanhToan);
        // Cập nhật trạng thái hóa đơn khi thanh toán
        CVB_HoaDon hoaDon = hoaDonDAO.getHoaDonById(thanhToan.getIdHoaDon());
        if (hoaDon != null) {
            hoaDon.setTrangThai("daTT");
            hoaDonDAO.updateHoaDon(hoaDon);
        }
        return "redirect:/thanhtoan/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CVB_ThanhToan thanhToan = thanhToanDAO.getThanhToanById(id);
        model.addAttribute("thanhToan", thanhToan);
        // Lấy danh sách hóa đơn để hiển thị trong form edit
        List<CVB_HoaDon> hoaDons = hoaDonDAO.getAllHoaDon();
        model.addAttribute("hoaDons", hoaDons);
        return "thanhtoan/thanhtoan_edit";
    }

    @PostMapping("/update")
    public String updateThanhToan(CVB_ThanhToan thanhToan) {
        thanhToanDAO.updateThanhToan(thanhToan);
        // Cập nhật trạng thái hóa đơn khi thanh toán
        CVB_HoaDon hoaDon = hoaDonDAO.getHoaDonById(thanhToan.getIdHoaDon());
        if (hoaDon != null) {
            hoaDon.setTrangThai("daTT");
            hoaDonDAO.updateHoaDon(hoaDon);
        }
        return "redirect:/thanhtoan/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteThanhToan(@PathVariable("id") int id) {
        CVB_ThanhToan thanhToan = thanhToanDAO.getThanhToanById(id);
        if (thanhToan != null) {
            // Lấy hóa đơn liên quan để cập nhật trạng thái
            CVB_HoaDon hoaDon = hoaDonDAO.getHoaDonById(thanhToan.getIdHoaDon());
            if (hoaDon != null) {
                hoaDon.setTrangThai("chuaTT"); // Hoặc trạng thái phù hợp khi xóa thanh toán
                hoaDonDAO.updateHoaDon(hoaDon);
            }
            thanhToanDAO.deleteThanhToan(id);
        }
        return "redirect:/thanhtoan/list";
    }
}