package com.aloha.board_test_kjk.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Board {

    int no;
    String title;
    String writer;
    String content;
    Date createdAt;
    Date updatedAt;

}
