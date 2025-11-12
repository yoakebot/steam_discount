package com.steam.discount.service;

import com.steam.discount.model.Boy;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Cacheable(value = "IndexController", key = "'STEAM::INDEX::'+ #boy.id()", condition = "#boy.id() != null && #boy.id() > 100", sync = true, cacheManager = "cacheManager")
    public Object cache(Boy boy) {
        return boy + "success";
    }

    @CacheEvict(value = "IndexController", key = "'STEAM::INDEX::' + #id")
    public Object del(Integer id) {
        return id + "del";
    }
}
