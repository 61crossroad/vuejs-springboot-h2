package com.taskagile.web.results;

import org.springframework.http.ResponseEntity;

public final class Result {
  private Result() {}

  public static ResponseEntity<ApiResult> created() {
    return ResponseEntity.status(201).build();
  }

  public static ResponseEntity<ApiResult> failure(String message) {
    return ResponseEntity.badRequest().body(ApiResult.message(message));
  }
}
