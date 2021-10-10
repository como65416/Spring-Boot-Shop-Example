package com.comoco.demoshop.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdatePasswordRequest {
    @Length(min = 8, max = 30)
    private String password;
}
