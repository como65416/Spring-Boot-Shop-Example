package com.comoco.demoshop.dto.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserData {
    private String username;

    private String password;

    private String email;

    private String name;
}
