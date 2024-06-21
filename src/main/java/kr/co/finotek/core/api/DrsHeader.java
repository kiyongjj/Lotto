/*
 *
 * PROJ : 대교 차세대 드림스 구축 프로젝트
 * Copyright © 2022 DAEKYO. All Rights Reserved
 *
 */

package kr.co.finotek.core.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Response Header.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrsHeader {
  @Schema(description = "메시지코드", example = "메시지코드(예:I00004CMM)")
  private String code;
  
  @Schema(description = "메세지", example = "메세지(예:정상 처리 되었습니다.)")
  private String message;
  
  @Schema(description = "타 인터페이스 에러코드", example = "타 인터페이스 에러코드")
  private String origin;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("header : {");
    sb.append("code : ").append(code).append(", ");
    sb.append("message : ").append(message).append(", ");
    sb.append("origin : ").append(origin).append(" }");
    return sb.toString();
  }
}

