package com.comoco.demoshop.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private String name;
    private String email;
}
