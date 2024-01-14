package com.demo.redis;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record CreateAdvertResponse(
        String id,
        String name,

        String title,

        BigDecimal price
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
