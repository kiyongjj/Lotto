package kr.co.finotek.lotto.domain.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
	@JoinColumn(name = "member_id", updatable = false)
	private Member member;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Builder
	public Board(Member member, String title, String content) {
		this.member = member;
		this.title = title;
		this.content = content;
	}

	public void update(String title, String content) {

		this.title = title;
		this.content = content;
	}
}
