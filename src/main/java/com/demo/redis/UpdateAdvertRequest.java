package com.demo.redis;

import java.math.BigDecimal;

public record UpdateAdvertRequest(
        String id,
        String name,
        String title,
        BigDecimal price
) {
}
