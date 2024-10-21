package kr.co.finotek.lotto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.finotek.lotto.dto.BoardCreateRequestDto;
import kr.co.finotek.lotto.dto.BoardListResponseDto;
import kr.co.finotek.lotto.dto.BoardResponseDto;
import kr.co.finotek.lotto.dto.BoardUpdateRequestDto;
import kr.co.finotek.lotto.service.BoardService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	@PostMapping("/create")
	public Long create(@RequestBody BoardCreateRequestDto requestDto) {
		
		System.out.println(requestDto.getMember().getId());
		return boardService.create(requestDto);
	}

	@PostMapping("/update/{id}")
	public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {

		return boardService.update(id, requestDto);
	}
	
	@GetMapping("/search/{id}")
	public BoardResponseDto searchById(@PathVariable Long id) {
		System.out.println(id);
		return boardService.searchById(id);
	}
	
	@GetMapping("/searchAll")
	public List<BoardListResponseDto> searchAllDesc() {
		return boardService.searchAllDesc();
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		boardService.delete(id);
	}
}
