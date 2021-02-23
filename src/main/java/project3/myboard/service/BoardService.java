package project3.myboard.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.myboard.domain.Board;
import project3.myboard.domain.dto.BoardDto;
import project3.myboard.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void save(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

    @Transactional
    public List<BoardDto> getBoardList(Integer page){

        return boardRepository.findAll()
                .stream()
                .map(board -> BoardDto.of(board.getId(), board.getWriter(), board.getTitle(), board.getContent(), board.getCreatedAt(), board.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardDto getPost(Long id){
        Board result = boardRepository.findById(id).get();

        return BoardDto.of(result.getId(), result.getWriter(), result.getTitle(), result.getContent(), result.getCreatedAt(), result.getUpdatedAt());
    }

    @Transactional
    public void update(BoardDto boardDto){
        Optional<Board> optional = boardRepository.findById(boardDto.getId());

        optional.map(board -> {
            board.setTitle(boardDto.getTitle())
                    .setContent(boardDto.getContent())
                    .setWriter(boardDto.getWriter())
                    .setUpdatedAt(boardDto.getUpdatedAt());
            return board;
        }).map(boardRepository::save);
    }

    @Transactional
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> search(String keyword){
        return boardRepository.findByTitleContaining(keyword)
                .stream()
                .map(board -> BoardDto.of(board.getId(), board.getWriter(), board.getTitle(), board.getContent(), board.getCreatedAt(), board.getUpdatedAt()))
                .collect(Collectors.toList());
    }


}
