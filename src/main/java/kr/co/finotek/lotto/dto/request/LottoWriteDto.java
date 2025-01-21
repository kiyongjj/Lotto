package kr.co.finotek.lotto.dto.request;

import kr.co.finotek.lotto.domain.lotto.Lotto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LottoWriteDto {

	private Long roundNo;
	private int firstNum;
	private int secondNum;
	private int thirdNum;
	private int fourthNum;
	private int fifthNum;
	private int sixthNum;
	
	public LottoWriteDto(Long roundNo, int firstNum, int secondNum,
			int thirdNum, int fourthNum, int fifthNum, int sixthNum) {
		this.roundNo = roundNo;
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.thirdNum = thirdNum;
		this.fourthNum = fourthNum;
		this.fifthNum = fifthNum;
		this.sixthNum = sixthNum;
	}
	
	/** dto -> entity */
	@Builder
	public static Lotto ofEntity(LottoWriteDto dto) {
		return Lotto.builder()
				.roundNo(dto.roundNo)
				.firstNum(dto.firstNum)
				.secondNum(dto.secondNum)
				.thirdNum(dto.thirdNum)
				.fourthNum(dto.fourthNum)
				.fifthNum(dto.fifthNum)
				.sixthNum(dto.sixthNum)
				.build();
	}
}
