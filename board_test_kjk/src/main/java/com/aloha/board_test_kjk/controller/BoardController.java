package com.aloha.board_test_kjk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.board_test_kjk.domain.Board;
import com.aloha.board_test_kjk.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 등록 이동
    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }

    // 등록
    @PostMapping("/insert")
    public String insert(Board board) throws Exception {
        int result = boardService.insert(board);
        log.info("여기인가요? : " + result);
        if (result > 0) {
            return "redirect:/board/list";
        }
        return "redirect:/board/insert?error";
    }

    // 목록
    @GetMapping("/list")
    public String list(Model model, @RequestParam("pageNo") int pageNo) throws Exception {
        List<Board> boardList = boardService.list();
        log.info("List size = " + boardList.size());
        Page page = new Page();

        // 한페이지에 보여줄수있는 개수
        int pagesize = 5;
        // 한 페이지 그룹에 보여줄 페이지 번호 개수
        int pageGroupSize = 10;

        // 최대 페이지
        int maxPage = page.maxPage(boardList.size(), pagesize);

        // 페이지 목록을 이중배열로 저장
        List<List<Board>> boardList2 = page.page(boardList, pagesize, maxPage);

        // 시작 페이지
        int startPage = page.startPage(pageNo, pageGroupSize);

        // 마지막 페이지
        int endPage = page.endPage(pageNo, pageGroupSize, maxPage);

        // // 현재 페이지의 데이터 가져오기
        boardList = boardList2.get(pageNo - 1);

        // 모델에 데이터 추가
        model.addAttribute("boardList", boardList);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("currentPage", pageNo); // 현재 페이지 번호
        model.addAttribute("startPage", startPage); // 시작 페이지 번호
        model.addAttribute("endPage", endPage); // 끝 페이지 번호
        return "/board/list";
    }

    // 조회
    @GetMapping("/read")
    public String read(Model model, @RequestParam("no") int no) throws Exception {
        Board board = boardService.select(no);
        log.info("보드 : " + board);

        model.addAttribute("board", board);
        return "/board/read";
    }

    // 수정 이동
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/update";
    }

    // 수정
    @PostMapping("/update")
    public String update(Board board) throws Exception {
        int result = boardService.update(board);
        if (result > 0) {
            return "redirect:/board/list";
        }
        return "redirect:/board/update?error&no=" + board.getNo();
    }

    // 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("no") int no) throws Exception {
        int result = boardService.delete(no);
        log.info("왔나? : " + result);
        if (result > 0) {
            return "SUCCESS";
        }
        return "FALUS";
    }

}
