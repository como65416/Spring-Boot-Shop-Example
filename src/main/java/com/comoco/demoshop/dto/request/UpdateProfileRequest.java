package com.comoco.demoshop.dto.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;

    private String email;
}
