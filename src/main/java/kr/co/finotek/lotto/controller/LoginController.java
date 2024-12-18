package kr.co.finotek.lotto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.finotek.lotto.dto.LoginDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class LoginController {
	

	@PostMapping("/login")
	public ResponseEntity<Object> selectLottoRoundNumber(@RequestBody LoginDto loginDto) {
		
		boolean rtn = false;
		
		return ResponseEntity.status(HttpStatus.OK).body(rtn);
	}
}
