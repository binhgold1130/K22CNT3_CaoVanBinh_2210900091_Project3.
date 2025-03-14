package controller;

import model.CVB_CongToDien;
import model.CVB_CongToDienDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/congtodien")
public class CVB_CongToDienController {

    private static final Logger logger = LoggerFactory.getLogger(CVB_CongToDienController.class);

    @Autowired
    private CVB_CongToDienDAO congToDienDAO;

    @GetMapping("/list")
    public String listCongTo(Model model) {
        List<CVB_CongToDien> list = congToDienDAO.getAllCongTo();
        model.addAttribute("congToList", list);
        return "congtodien/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("congTo", new CVB_CongToDien());
        return "congtodien/add";
    }

    @PostMapping("/save")
    public String saveCongTo(@ModelAttribute CVB_CongToDien congTo, RedirectAttributes redirectAttributes) {
        try {
            congToDienDAO.addCongTo(congTo);
            redirectAttributes.addFlashAttribute("success", "Thêm công tơ điện thành công.");
            logger.info("CongToDien added successfully: {}", congTo);
        } catch (DuplicateKeyException e) {
            redirectAttributes.addFlashAttribute("error", "Số công tơ đã tồn tại. Vui lòng nhập số công tơ khác.");
            logger.error("DuplicateKeyException adding congToDien: {}", e.getMessage());
            return "redirect:/congtodien/add"; // Redirect về trang add để người dùng nhập lại
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm công tơ điện.");
            logger.error("Error adding congToDien: {}", e.getMessage());
        }
        return "redirect:/congtodien/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CVB_CongToDien congTo = congToDienDAO.getCongToById(id);
        model.addAttribute("congTo", congTo);
        return "congtodien/edit";
    }

    @PostMapping("/update")
    public String updateCongTo(@ModelAttribute CVB_CongToDien congTo, RedirectAttributes redirectAttributes) {
        try {
            congToDienDAO.updateCongTo(congTo);
            redirectAttributes.addFlashAttribute("success", "Cập nhật công tơ điện thành công.");
            logger.info("CongToDien updated successfully: {}", congTo);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật công tơ điện.");
            logger.error("Error updating congToDien: {}", e.getMessage());
        }
        return "redirect:/congtodien/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCongTo(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            congToDienDAO.deleteCongTo(id);
            redirectAttributes.addFlashAttribute("success", "Xóa công tơ điện thành công.");
            logger.info("CongToDien deleted successfully with id: {}", id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa công tơ điện.");
            logger.error("Error deleting congToDien with id: {}", id, e);
        }
        return "redirect:/congtodien/list";
    }
}