package com.learn.basicTests.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn.basicTests.dto.PackageGLSDTO;
import com.learn.basicTests.model.PackageGLS;
import com.learn.basicTests.service.PackageService;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;

    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PackageGLSDTO packageGLSDTO) {

        PackageGLS packageGLS = PackageGLS
                .builder()
                .recipientId(packageGLSDTO.getRecipientId())
                .senderId(packageGLSDTO.getSenderId())
                .price(packageGLSDTO.getPrice())
                .build();

        packageGLS = packageService.save(packageGLS);

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(packageGLS.getId())
                                .toUri())
                .build();
    }

}