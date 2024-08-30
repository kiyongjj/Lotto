package kr.co.finotek.lotto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.finotek.core.api.DrsResponseEntity;
import kr.co.finotek.core.service.ResponseService;
import kr.co.finotek.lotto.dto.LoginDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class LoginController {
	
	private final ResponseService responseService;

	@PostMapping("/login")
	public DrsResponseEntity<Object> selectLottoRoundNumber(@RequestBody LoginDto loginDto) {
		
		boolean rtn = false;
		System.out.println(loginDto.getId());
		System.out.println(loginDto.getPassword());
		
		if(loginDto.getId().equals("admin")) {
			System.out.println("id is admin!");
			rtn = true;
		} else {
			System.out.println("ID is Different!");
			rtn =false;
			return responseService.toResponseEntity("아이디가 다릅니다.", rtn);
		}
		
		if(loginDto.getPassword().equals("123")) {
			System.out.println("password is OK!");
			rtn = true;
		} else {
			System.out.println("password is Different!");
			rtn =false;
			return responseService.toResponseEntity("비밀번호가 다릅니다.", rtn);
		}
		
		return responseService.toResponseEntity("로그인이 완료되었습니다.", rtn);
	}
}
