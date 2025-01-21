package kr.co.finotek.lotto.domain.lotto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="lotto_temp")
public class Lotto {

	@Id
	@GeneratedValue
	@Column(name = "ROUND_NO", nullable = false)
	private Long roundNo;
	
	@Column
	private int firstNum;
	
	@Column
	private int secondNum;
	
	@Column
	private int thirdNum;
	
	@Column
	private int fourthNum;
	
	@Column
	private int fifthNum;
	
	@Column
	private int sixthNum;
	
	@Builder
	public Lotto(Long roundNo, int firstNum, int secondNum,
			int thirdNum, int fourthNum, int fifthNum, int sixthNum) {
		this.roundNo = roundNo;
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.thirdNum = thirdNum;
		this.fourthNum = fourthNum;
		this.fifthNum = fifthNum;
		this.sixthNum = sixthNum;
	}
}
