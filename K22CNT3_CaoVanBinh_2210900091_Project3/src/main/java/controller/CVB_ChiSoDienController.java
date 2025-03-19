package controller;

import model.CVB_ChiSoDien;
import model.CVB_ChiSoDienDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/chisodien")
public class CVB_ChiSoDienController {

    private static final Logger logger = LoggerFactory.getLogger(CVB_ChiSoDienController.class);

    @Autowired
    private CVB_ChiSoDienDAO chiSoDienDAO;

    @GetMapping("/list")
    public String listChiSoDien(Model model) {
        List<CVB_ChiSoDien> list = chiSoDienDAO.getAllChiSoDien();
        model.addAttribute("listChiSoDien", list);
        return "chisodien/chisodien_list";
    }

    @GetMapping("/add")
    public String showAddOrEditForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            CVB_ChiSoDien chiSoDien = chiSoDienDAO.getChiSoDienById(id);
            if (chiSoDien == null) {
                return "redirect:/chisodien/list";
            }
            model.addAttribute("chiSoDien", chiSoDien);
        } else {
            model.addAttribute("chiSoDien", new CVB_ChiSoDien());
        }
        return "chisodien/chisodien_add";
    }

    @PostMapping("/add")
    public String addChiSoDien(@ModelAttribute CVB_ChiSoDien chiSoDien, Model model, RedirectAttributes redirectAttributes) {
        logger.info("Adding chiSoDien: {}", chiSoDien);
        if (chiSoDien.getChiSoMoi() < chiSoDien.getChiSoCu()) {
            redirectAttributes.addFlashAttribute("error", "Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ.");
            return "redirect:/chisodien/add";
        }
        try {
            chiSoDienDAO.addChiSoDien(chiSoDien);
            redirectAttributes.addFlashAttribute("success", "Thêm chỉ số điện thành công.");
            logger.info("ChiSoDien added successfully: {}", chiSoDien);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm chỉ số điện.");
            logger.error("Error adding chiSoDien: {}", e.getMessage());
        }
        return "redirect:/chisodien/list";
    }

    @PostMapping("/update")
    public String updateChiSoDien(@ModelAttribute CVB_ChiSoDien chiSoDien, RedirectAttributes redirectAttributes) {
        logger.info("Updating chiSoDien: {}", chiSoDien);
        if (chiSoDien.getChiSoMoi() < chiSoDien.getChiSoCu()) {
            redirectAttributes.addFlashAttribute("error", "Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ.");
            return "redirect:/chisodien/add?id=" + chiSoDien.getIdChiSo();
        }
        try {
            chiSoDien.setDienTieuThu(chiSoDien.getChiSoMoi() - chiSoDien.getChiSoCu());
            chiSoDienDAO.updateChiSoDien(chiSoDien);
            redirectAttributes.addFlashAttribute("success", "Cập nhật chỉ số điện thành công.");
            logger.info("ChiSoDien updated successfully: {}", chiSoDien);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật chỉ số điện.");
            logger.error("Error updating chiSoDien: {}", e.getMessage());
        }
        return "redirect:/chisodien/list";
    }

    @GetMapping("/delete")
    public String deleteChiSoDien(@RequestParam("id") int id) {
        try {
            chiSoDienDAO.deleteChiSoDien(id);
            logger.info("ChiSoDien deleted successfully with id: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting chiSoDien with id: {}", id, e);
        }
        return "redirect:/chisodien/list";
    }

    @PostMapping("/updateChiSoMoi")
    public String updateChiSoMoi(@RequestParam("idChiSo") int idChiSo, @RequestParam("chiSoMoi") int chiSoMoi, RedirectAttributes redirectAttributes) {
        logger.info("Updating chiSoMoi for idChiSo: {}, new chiSoMoi: {}", idChiSo, chiSoMoi);
        try {
            CVB_ChiSoDien chiSoDien = chiSoDienDAO.getChiSoDienById(idChiSo);
            if (chiSoDien != null) {
                if (chiSoMoi < chiSoDien.getChiSoCu()) {
                    redirectAttributes.addFlashAttribute("error", "Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ.");
                    return "redirect:/chisodien/list";
                }
                chiSoDien.setChiSoMoi(chiSoMoi);
                chiSoDien.setDienTieuThu(chiSoMoi - chiSoDien.getChiSoCu());
                chiSoDienDAO.updateChiSoDien(chiSoDien);
                redirectAttributes.addFlashAttribute("success", "Cập nhật chỉ số mới thành công.");
                logger.info("ChiSoMoi updated successfully for idChiSo: {}", idChiSo);
            } else {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy chỉ số điện với ID: " + idChiSo);
                logger.warn("ChiSoDien not found with id: {}", idChiSo);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật chỉ số mới.");
            logger.error("Error updating chiSoMoi for idChiSo: {}", idChiSo, e);
        }
        return "redirect:/chisodien/list";
    }
}