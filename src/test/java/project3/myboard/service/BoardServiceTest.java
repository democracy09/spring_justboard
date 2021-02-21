package project3.myboard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project3.myboard.domain.dto.BoardDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    void getBoardList(){
        boardService.save(BoardDto.of(1L, "aa","gg","vzxcv", LocalDateTime.now(), LocalDateTime.now()));
        boardService.save(BoardDto.of(2L, "gg","ff","hjk", LocalDateTime.now(), LocalDateTime.now()));
        boardService.save(BoardDto.of(3L, "ss","ggjj","wear", LocalDateTime.now(), LocalDateTime.now()));

        List<BoardDto> boardDtoList = boardService.getBoardList();

        boardDtoList.forEach(System.out::println);

    }

}