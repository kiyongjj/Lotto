package kr.co.finotek.lotto.dto.response;

import kr.co.finotek.lotto.domain.lotto.Lotto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LottoResponseDto {

	private Long roundNo;
	private int firstNum;
	private int secondNum;
	private int thirdNum;
	private int fourthNum;
	private int fifthNum;
	private int sixthNum;
	
	@Builder
	public LottoResponseDto(Long roundNo, int firstNum, int secondNum,
			int thirdNum, int fourthNum, int fifthNum, int sixthNum) {
		this.roundNo = roundNo;
		this.firstNum = firstNum;
		this.secondNum = secondNum;
		this.thirdNum = thirdNum;
		this.fourthNum = fourthNum;
		this.fifthNum = fifthNum;
		this.sixthNum = sixthNum;
	}
	
	/** entity(db) -> dto(web) */
	public static LottoResponseDto fromEntity(Lotto lotto) {
		return LottoResponseDto.builder()
				.roundNo(lotto.getRoundNo())
				.firstNum(lotto.getFirstNum())
				.secondNum(lotto.getSecondNum())
				.thirdNum(lotto.getThirdNum())
				.fourthNum(lotto.getFourthNum())
				.fifthNum(lotto.getFifthNum())
				.sixthNum(lotto.getSixthNum())
				.build();
	}
}
