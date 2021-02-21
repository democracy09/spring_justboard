package project3.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.myboard.domain.Board;
import project3.myboard.domain.dto.BoardDto;
import project3.myboard.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void save(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

    @Transactional
    public List<BoardDto> getBoardList(){
        List<BoardDto> boards = boardRepository.findAll()
                .stream()
                .map(board -> BoardDto.of(board.getId(), board.getWriter(), board.getTitle(), board.getContent(), board.getCreatedAt(), board.getUpdatedAt()))
                .collect(Collectors.toList());

        return boards;
    }

}
