package com.learn.basicTests.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.basicTests.dto.PackageGLSDTO;
import com.learn.basicTests.model.PackageGLS;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.model.Person.TypePersonEnum;
import com.learn.basicTests.service.PackageService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PackageController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PackageControllerTest {

    private final String BASE_URL = "/packages";

    @InjectMocks
    private PackageController packageController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PackageService packageService;

    @Test
    public void shoudlCreatedPackage() throws Exception {

        var packageMock = buildPackageGLS();
        var packageMockDTO = buildPackageGLSDTO();

        when(packageService.save(any(PackageGLS.class))).thenReturn(packageMock);

        mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(packageMockDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public static PackageGLSDTO buildPackageGLSDTO() {
        return PackageGLSDTO.builder()
                .price(50.00)
                .recipientId(1L)
                .senderId(2L)
                .build();
    }

    public static PackageGLS buildPackageGLS() {

        return PackageGLS.builder()
                .id(1L)
                .price(50.00)
                .recipient(buildSender())
                .sender(buildRecipient())
                .build();
    }

    public static Person buildSender() {
        return Person.builder()
                .id(1L)
                .name("Pedro")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.SENDER)
                .build();
    }

    public static Person buildRecipient() {
        return Person.builder()
                .id(2L)
                .name("Carlos")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.RECIPIENT)
                .build();
    }
}