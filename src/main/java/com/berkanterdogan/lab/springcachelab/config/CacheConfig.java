package com.berkanterdogan.lab.springcachelab.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    @Primary
    public CacheManager defaultCacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setAllowNullValues(false);

        return new TransactionAwareCacheManagerProxy(concurrentMapCacheManager);
    }

    // We can define multiple CacheManager bean here.
}
