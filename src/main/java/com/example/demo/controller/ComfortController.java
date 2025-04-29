package com.example.demo.controller;

import com.example.demo.service.ComfortService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComfortController {
    private final ComfortService comfortService;

    @Operation(summary = "Find min value in Excel")
    @GetMapping("/minvalue")
    public int findMin(@RequestParam("file") @NotNull String file,
                       @RequestParam("N") @NotNull Integer n) {
        return comfortService.findMin(file, n);
    }
}