package kr.co.finotek.lotto.dto;

import java.io.Serializable;
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
public class LottoNumberDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roundNo;
	private int firstNum;
	private int secondNum;
	private int thirdNum;
	private int fourthNum;
	private int fifthNum;
	private int sixthNum;
	private int bonusNum;
	private LocalDate drawDate;
	
	@Builder.Default
	private int currentPage = 1;
	@Builder.Default
	private int pageSize = 10;
}
