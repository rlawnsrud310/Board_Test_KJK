package com.aloha.board_test_kjk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.board_test_kjk.domain.Board;

@Mapper
public interface BoardMapper {

    // 게시글 목록 조회
    public List<Board> list() throws Exception;

    // 특정 게시글 조회
    public Board select(int no) throws Exception;

    // 게시글 등록
    public int insert(Board board) throws Exception;

    // 게시글 수정
    public int update(Board board) throws Exception;

    // 게시글 삭제
    public int delete(int no) throws Exception;

}
