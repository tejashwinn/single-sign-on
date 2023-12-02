package com.tejashwin.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/1")
    @PreAuthorize("hasAnyRole('client_user')")
    public String getStatus() {
        return "Working";
    }

    @GetMapping("/2")
    @PreAuthorize("hasAnyRole('client_admin')")
    public String getStatus1() {
        return "Working";
    }
}
