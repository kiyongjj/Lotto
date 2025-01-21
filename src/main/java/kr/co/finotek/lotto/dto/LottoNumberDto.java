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
public class LottoNumberDto extends LottoMainDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roundNo;
	private int bonusNum;
	private LocalDate drawDate;
	
}
