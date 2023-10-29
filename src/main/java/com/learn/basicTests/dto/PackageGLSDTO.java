package com.learn.basicTests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class PackageGLSDTO {

    Long recipientId;
    Long senderId;
    Double price;
}
