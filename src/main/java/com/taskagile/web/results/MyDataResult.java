package com.taskagile.web.results;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taskagile.domain.model.board.Board;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.user.SimpleUser;

import org.springframework.http.ResponseEntity;

public class MyDataResult {

  public static ResponseEntity<ApiResult> build(SimpleUser currentUser, List<Team> teams, List<Board> boards) {
    Map<String, Object> user = new HashMap<>();
    user.put("name", currentUser.getUsername());
  }
}
