package project3.myboard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project3.myboard.domain.dto.BoardDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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

    @Test
    void getPost(){
        boardService.save(BoardDto.of(1L, "aa","gg","vzxcv", LocalDateTime.now(), LocalDateTime.now()));
        boardService.save(BoardDto.of(2L, "gg","ff","hjk", LocalDateTime.now(), LocalDateTime.now()));
        boardService.save(BoardDto.of(3L, "ss","ggjj","wear", LocalDateTime.now(), LocalDateTime.now()));

        BoardDto result = boardService.getPost(2L);
        assertThat(result.getTitle()).isEqualTo("ff");
    }

    @Test
    void update(){
        boardService.save(BoardDto.of(1L, "aa","gg","vzxcv", LocalDateTime.now(), LocalDateTime.now()));
        boardService.save(BoardDto.of(2L, "gg","ff","hjk", LocalDateTime.now(), LocalDateTime.now()));
        boardService.save(BoardDto.of(3L, "ss","ggjj","wear", LocalDateTime.now(), LocalDateTime.now()));

        boardService.update(BoardDto.of(2L, "cc","ff","hsdffafdsgsfajk", LocalDateTime.now(), LocalDateTime.now()));
        BoardDto boardDto = boardService.getPost(2L);

        assertThat(boardDto.getWriter()).isEqualTo("gg");
    }

}