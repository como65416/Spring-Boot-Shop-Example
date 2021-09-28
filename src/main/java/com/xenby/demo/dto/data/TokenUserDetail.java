package com.xenby.demo.dto.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenUserDetail {
    Long accountId;
    String name;
    String role;
}
