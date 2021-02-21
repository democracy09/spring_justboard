package project3.myboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project3.myboard.domain.dto.BoardDto;
import project3.myboard.service.BoardService;

import java.util.List;

@Controller
@Slf4j
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);

        return "board/list.html";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.save(boardDto);

        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);

        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);

        return "board/update.html";
    }

    @RequestMapping(value = "/post/edit/{id}", method = {RequestMethod.POST, RequestMethod.POST})
    public String update(BoardDto boardDto){
        boardService.update(boardDto);

        return "redirect:/";
    }

    @RequestMapping(value = "/post/{id}", method = {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){
        boardService.delete(id);

        return "redirect:/";
    }

}
