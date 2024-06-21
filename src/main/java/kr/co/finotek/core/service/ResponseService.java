/*
 *
 * PROJ : 대교 차세대 드림스 구축 프로젝트
 * Copyright © 2022 DAEKYO. All Rights Reserved
 *
 */

package kr.co.finotek.core.service;

import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kr.co.finotek.core.api.DrsHeader;
import kr.co.finotek.core.api.DrsResponseEntity;

/**
 * http Restful Response 처리.
 */
@Component
@RequiredArgsConstructor
public class ResponseService {
  private final RedisService redisService;
  @Value("${daekyo.bizName}")
  private String bizName;

  /**
   * Response 처리.
   *
   * @param code 결과 코드
   * @param arguments 패턴 값
   * @param body T
   * @return DrsResponseEntity
   */
  public <T> DrsResponseEntity<T> toResponseEntity(String code, String[] arguments, T body) {
    return DrsResponseEntity.<T>builder().header(DrsHeader.builder().code(getCode(code)).message(getMessage(code, arguments)).build()).body(body).build();
  }

  /**
   * Response 처리.
   *
   * @param code 결과 코드
   * @param body T
   * @return DrsResponseEntity
   */
  public <T> DrsResponseEntity<T> toResponseEntity(String code, T body) {
    return DrsResponseEntity.<T>builder().header(DrsHeader.builder().code(getCode(code)).message(getMessage(code, null)).build()).body(body).build();
  }

  /**
   * Response 처리.
   *
   * @param code 결과 코드
   * @param arguments 패턴 값
   * @param origin 에러 원천
   * @param body T
   * @return DrsResponseEntity
   */
  public <T> DrsResponseEntity<T> toResponseEntity(String code, String[] arguments, String origin, T body) {
    return DrsResponseEntity.<T>builder().header(DrsHeader.builder().code(getCode(code)).message(getMessage(code, arguments)).origin(origin).build()).body(body)
        .build();
  }

  public String getMessage(String code, String[] arguments) {
//    String message = redisService.getValue(CorePropertiesUtil.getErrorKeyPrefix().concat(code));
    String message = redisService.getValue("test");

    if (StringUtils.isBlank(message)) {
      message = "";
    }
    if (arguments != null) {
      message = MessageFormat.format(message, (Object[]) arguments);
    }
    System.out.println("#######ResponseService######## " + message);

    return message;
  }

  private String getCode(String code) {
    if (!StringUtils.isBlank(bizName)) {
      code = code.concat(bizName);
    }
    return code;
  }
}
