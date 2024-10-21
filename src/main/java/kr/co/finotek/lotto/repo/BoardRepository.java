package kr.co.finotek.lotto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.finotek.lotto.domain.member.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findAllByOrderByIdDesc();

}
