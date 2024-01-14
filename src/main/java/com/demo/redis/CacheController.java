package com.demo.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
@Profile("!prod")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cache")
public class CacheController {

    private final CacheService cacheService;


    @DeleteMapping("/delete")
    public Boolean evictCountryNamesCache() {
        cacheService.evictCacheValues(CacheName.ADVERT);
        return true;
    }
}
