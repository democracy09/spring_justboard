package project3.myboard.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project3.myboard.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContaining(String keyword);


}
