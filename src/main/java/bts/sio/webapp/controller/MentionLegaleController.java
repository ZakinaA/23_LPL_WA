package bts.sio.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class MentionLegaleController {
    @GetMapping("/mentionlegale")
    public String mentionsLegales() {
        return "mentionlegale";
    }
}
