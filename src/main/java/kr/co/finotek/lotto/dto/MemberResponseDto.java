package kr.co.finotek.lotto.dto;

import kr.co.finotek.lotto.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

	private Long id;
	private String name;
	private String password;
	
	public MemberResponseDto(Member entity) {
		this.id= entity.getId();
		this.name = entity.getName();
		this.password = entity.getPassword();
	}
}
