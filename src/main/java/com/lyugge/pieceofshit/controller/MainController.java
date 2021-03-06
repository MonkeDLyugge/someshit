package com.lyugge.pieceofshit.controller;

import com.lyugge.pieceofshit.domain.Message;
import com.lyugge.pieceofshit.domain.User;
import com.lyugge.pieceofshit.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String main(Model model) {
        Iterable<Message> allMessages = messageRepo.findAll();
        model.addAttribute("allMessages", allMessages);
        return "index";
    }

    @PostMapping
    public String addMessage(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Model model) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> allMessages = messageRepo.findAll();
        model.addAttribute("allMessages", allMessages);
        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messages;
        if (filter == null || filter.isEmpty()) {
            messages = messageRepo.findAll();
        } else {
            messages = messageRepo.findByTag(filter);
        }
        model.addAttribute("allMessages", messages);
        return "index";
    }
}
