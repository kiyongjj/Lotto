/*
 *
 * PROJ : 대교 차세대 드림스 구축 프로젝트
 * Copyright © 2022 DAEKYO. All Rights Reserved
 *
 */

package kr.co.finotek.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Redis Service.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisService {
  private final RedisTemplate<String, String> redisTemplate;

  @Setter
  private ValueOperations<String, String> valOps;

  @PostConstruct
  public void init() {
    setValOps(redisTemplate.opsForValue());
  }

  public String getValue(String redisKey) {
    return valOps.get(redisKey);
  }
  
  public void setValue(String redisKey, String value) {
    valOps.set(redisKey, value);
  }
  
  public void setValue(String redisKey, String value, Duration time) {
    valOps.set(redisKey, value, time);
  }
  
  public void setExpire(String redisKey, Duration time) {
    valOps.getAndExpire(redisKey, time);
  }
  
  public void delete(String redisKey) {
    valOps.getAndDelete(redisKey);
  }
  
  public boolean hasKey(String redisKey) {
    return valOps.getOperations().hasKey(redisKey);
  }
  
  public <T> boolean setObject(String key, T data) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String value = mapper.writeValueAsString(data);
      setValue(key, value);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage());
      return false;
    }
  }

  public <T> T getObject(String key, Class<T> classType) {
    String value = getValue(key);

    if (value == null) {
      return null;
    }

    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(value, classType);
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }
  }

}
