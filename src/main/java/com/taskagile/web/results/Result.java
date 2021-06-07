package com.taskagile.web.results;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

public final class Result {
  private Result() {}

  public static ResponseEntity<ApiResult> created() {
    return ResponseEntity.status(201).build();
  }

  public static ResponseEntity<ApiResult> ok() {
    return ResponseEntity.ok().build();
  }

  public static ResponseEntity<ApiResult> ok(String message) {
    Assert.hasText(message, "Parameter `message` must not be blank");

    return ok(ApiResult.message(message));
  }

  public static ResponseEntity<ApiResult> ok(ApiResult payload) {
    Assert.notNull(payload, "Parameter `payload` must not be null");

    return ResponseEntity.ok(payload);
  }

  public static ResponseEntity<ApiResult> failure(String message) {
    return ResponseEntity.badRequest().body(ApiResult.message(message));
  }
}
