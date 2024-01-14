package com.demo.redis;

import java.math.BigDecimal;

public record CreateAdvertRequest(
        String name,

        String title,

        BigDecimal price
) {
}
