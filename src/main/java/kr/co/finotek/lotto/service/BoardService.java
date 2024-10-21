package kr.co.finotek.lotto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.finotek.lotto.domain.member.Board;
import kr.co.finotek.lotto.dto.BoardCreateRequestDto;
import kr.co.finotek.lotto.dto.BoardListResponseDto;
import kr.co.finotek.lotto.dto.BoardResponseDto;
import kr.co.finotek.lotto.dto.BoardUpdateRequestDto;
import kr.co.finotek.lotto.repo.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public Long create(BoardCreateRequestDto requestDto) {

		return boardRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, BoardUpdateRequestDto requestDto) {

		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
		
		board.update(requestDto.getTitle(), requestDto.getContent());
		
		return id;
	}

	@Transactional(readOnly = true)
	public BoardResponseDto searchById(Long id) {

		System.out.println("BoardService : " + id);
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
		
		return new BoardResponseDto(board);
	}

	@Transactional(readOnly = true)
	public List<BoardListResponseDto> searchAllDesc() {

		System.out.println("searchAllDesc()");
		return boardRepository.findAllByOrderByIdDesc().stream()
				.map(BoardListResponseDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
		
		boardRepository.delete(board);
	}

}
