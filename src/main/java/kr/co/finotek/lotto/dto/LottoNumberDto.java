package kr.co.finotek.lotto.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LottoNumberDto {

	private String roundNo;
	private int firstNum;
	private int secondNum;
	private int thirdNum;
	private int fourthNum;
	private int fifthNum;
	private int sixthNum;
	private int bonusNum;
	private LocalDate drawDate;
}
