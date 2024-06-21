/*
 *
 * PROJ : 대교 차세대 드림스 구축 프로젝트
 * Copyright © 2022 DAEKYO. All Rights Reserved
 *
 */

package kr.co.finotek.core.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Response VO Header와 Body를 구성.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrsResponseEntity<T> {

  private DrsHeader header;
  private T body;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DrsResponseEntity : {");
    if (header != null) {
      sb.append(header.toString());
    } else {
      sb.append("headerVo : null");
    }
    sb.append(", ");
    if (body != null) {
      sb.append("body : {").append(body.toString()).append("}");
    } else {
      sb.append("body : null");
    }
    sb.append("}");

    return sb.toString();
  }
}
