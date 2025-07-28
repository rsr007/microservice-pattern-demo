package com.ravidev.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {


    /**
     * KeyResolver is functional interface used to extract key from incoming request.
     * exchange.getRequest() → gets the request
     * getRemoteAddress() → gets the client IP socket info
     * getAddress().getHostAddress() → gets the string IP address
     * Mono.just(...) → wraps that IP in a Mono<String> for reactive handling
     * This key (IP) is then used by the rate limiter
     * So in Redis, you’ll see entries like, rate_limit:192.168.1.101
     * @return instance of KeyResolver
     */
    @Bean
    public KeyResolver ipKeyResolver(){
        return exchange -> {
            String ip = exchange.getRequest()
                    .getRemoteAddress()
                    .getAddress()
                    .getHostAddress();
            System.out.println("Rate limiting key (IP): " + ip);
            return Mono.just(ip);
        };
    }

    /**
     * This bean is used to test Redis connection on application startup.
     * this log "Redis test value: testValue" should be displayed in application start.
     * @param redisOps
     * @return commandLineRunner
     */
    @Bean
    public CommandLineRunner testRedisConnection(ReactiveRedisOperations<String, String> redisOps) {
        return args -> {
            redisOps.opsForValue().set("testKey", "testValue")
                    .then(redisOps.opsForValue().get("testKey"))
                    .subscribe(value -> System.out.println("Redis test value: " + value));
        };
    }
}
