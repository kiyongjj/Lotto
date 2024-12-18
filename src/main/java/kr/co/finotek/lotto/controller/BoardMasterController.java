package kr.co.finotek.lotto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.finotek.lotto.dto.BoardMasterDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class BoardMasterController {

	
	@PostMapping("/registContents")
	public ResponseEntity<Object> registContents(@RequestBody BoardMasterDto boardMasterDto) {
		
		System.out.println("title : " + boardMasterDto.getTitle());
		System.out.println("content : " + boardMasterDto.getContent());
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
