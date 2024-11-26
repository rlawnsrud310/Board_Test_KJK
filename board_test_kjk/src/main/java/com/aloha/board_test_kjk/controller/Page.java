package com.aloha.board_test_kjk.controller;

import com.aloha.board_test_kjk.domain.Board;

import java.util.ArrayList;
import java.util.List;

public class Page {

    public int maxPage(int pagetotalSize, int pageSize) {

        int maxPage = (pagetotalSize % pageSize == 0)
                ? (pagetotalSize / pageSize)
                : (pagetotalSize / pageSize) + 1;

        return maxPage;
    }

    public List<List<Board>> page(List<Board> boardList, int pageSize, int maxPage) {

        // 이중 리스트 생성
        List<List<Board>> pages = new ArrayList<>();
        // 데이터 나누기
        for (int i = 0; i < maxPage; i++) {
            int startIndex = i * pageSize;
            int endIndex = Math.min(startIndex + pageSize, boardList.size());
            pages.add(boardList.subList(startIndex, endIndex));
        }
        return pages;
    }

    public int startPage(int pageNo, int pageGroupSize) {
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;

        return startPage;
    }

    public int endPage(int startPage, int pageGroupSize, int maxPage) {
        int endPage = Math.min(startPage + pageGroupSize - 1, maxPage);
        return endPage;
    }

}
