package com.sm.mustache.Board;

import com.sm.mustache.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository br;

    // 게시글 목록 화면
    @GetMapping("/")
    public String boardList(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
        PageRequest pageRequest = PageRequest.of(page, 3, Sort.Direction.DESC, "createdAt");
        Page<Board> boardPage = br.findAll(pageRequest);

        request.setAttribute("boardList", boardPage.getContent());
        request.setAttribute("currentPage", boardPage.getNumber());
        request.setAttribute("totalPages", boardPage.getTotalPages());

        // 이전/다음 페이지 여부
        request.setAttribute("currentPageIsNotFirst", !boardPage.isFirst());
        request.setAttribute("currentPageIsNotLast", !boardPage.isLast());
        request.setAttribute("prevPage", boardPage.getNumber() - 1);
        request.setAttribute("nextPage", boardPage.getNumber() + 1);

        // 페이지 번호 리스트
        List<Object> pageNumbers = new java.util.ArrayList<>();
        for (int i = 0; i < boardPage.getTotalPages(); i++) {
            java.util.Map<String, Object> pageItem = new java.util.HashMap<>();
            pageItem.put("number", i);
            pageItem.put("displayNumber", i + 1);
            pageItem.put("isCurrent", i == boardPage.getNumber());
            pageNumbers.add(pageItem);
        }
        request.setAttribute("pageNumbers", pageNumbers);

        return "index";
    }

    // 게시글 상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = br.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. id=" + id));

        model.addAttribute("board", board);
        return "board/detail";
    }

    // 수정 폼 이동
    @GetMapping("/board/edit/{id}")
    public String editForm(@PathVariable Long id, HttpServletRequest request) {
        Board board = br.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다. id=" + id));
        request.setAttribute("board", board);
        return "board/edit-form";
    }

    // 수정 처리
    @PostMapping("/board/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Board updatedBoard) {
        Board board = br.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());

        br.save(board);
        return "redirect:/detail/" + id;
    }

    // 삭제 처리
    @PostMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id) {
        br.deleteById(id);
        return "redirect:/";
    }


    // 글쓰기 폼 화면
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // 글쓰기 처리
    @PostMapping("/board/save")
    public String save(@RequestParam String title,
                       @RequestParam String content,
                       HttpSession session) {

        // 로그인한 유저 정보 가져오기 (세션에서)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/login-form";
        }

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setUsername(sessionUser.getUsername());
        board.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        br.save(board);

        return "redirect:/";
    }
}
