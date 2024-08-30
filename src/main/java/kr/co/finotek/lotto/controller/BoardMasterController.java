package kr.co.finotek.lotto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.finotek.core.api.DrsResponseEntity;
import kr.co.finotek.core.service.ResponseService;
import kr.co.finotek.lotto.dto.BoardMasterDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class BoardMasterController {

	private final ResponseService responseService;
	
	@PostMapping("/registContents")
	public DrsResponseEntity<Object> registContents(@RequestBody BoardMasterDto boardMasterDto) {
		
		System.out.println("title : " + boardMasterDto.getTitle());
		System.out.println("content : " + boardMasterDto.getContent());
		return responseService.toResponseEntity("조회가 완료되었습니다.", null);
	}
}
