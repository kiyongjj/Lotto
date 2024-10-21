package kr.co.finotek.lotto.dto;

import kr.co.finotek.lotto.domain.member.Board;
import lombok.Getter;

@Getter
public class BoardListResponseDto {

	private Long id;
	private String member;
	private String title;
	
	public BoardListResponseDto(Board entity) {
		this.id = entity.getId();
		this.member = entity.getMember().getName();
		this.title = entity.getTitle();
	}
}
