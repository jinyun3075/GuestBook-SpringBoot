package com.sbj.guestbook.controller;

import com.sbj.guestbook.controller.dto.GuestbookDto;
import com.sbj.guestbook.controller.dto.PageRequestDto;
import com.sbj.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {
    private final GuestbookService service;
    @GetMapping("/")
    public String index(){
        log.info("list.....");
        return "redirect:/guestbook/list";
    }
    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model){
        log.info("list.............."+pageRequestDto);
        model.addAttribute("result",service.getList(pageRequestDto));
    }
    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }
    @PostMapping("/register")
    public String registerPost(GuestbookDto dto, RedirectAttributes redirectAttributes){
        log.info("dto...."+dto);
        Long gno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg",gno);
        return "redirect:/guestbook/list";
    }

}
