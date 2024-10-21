package kr.co.finotek.lotto.dto;

import kr.co.finotek.lotto.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequestDto {

	private Long id;
	private String name;
	private String password;
	private String email;
	private String contact;
	
	@Builder
	public MemberCreateRequestDto(String name, String password, String email, String contact) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}
	public Member toEntity() {
		return Member.builder()
				.name(name)
				.password(password)
				.email(email)
				.contact(contact)
				.build();
	}
}
