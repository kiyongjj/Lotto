package kr.co.finotek.lotto.domain.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", unique = true, nullable = false)
	private Long id;
	
	@Column(length = 15, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 20, nullable = false)
	private String contact;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Board> board = new ArrayList<>();
	
	@Builder
	public Member(Long id, String name, String password, String email, String contact) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}
}
