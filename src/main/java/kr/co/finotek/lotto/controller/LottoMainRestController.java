package kr.co.finotek.lotto.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.finotek.core.api.DrsResponseEntity;
import kr.co.finotek.core.service.ResponseService;
import kr.co.finotek.lotto.dto.LottoNumberDto;
import kr.co.finotek.lotto.service.LottoMainService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lotto")
public class LottoMainRestController {
	
	private final ResponseService responseService;
	private final LottoMainService lottoMainService;
	
	@PostMapping("/selectLottoRoundNumber")
	public DrsResponseEntity<Object> selectLottoRoundNumber(@RequestBody LottoNumberDto lottoNumberDto) {
		
		lottoMainService.initProcesses();
		
		List<LottoNumberDto> result = lottoMainService.selectLottoRoundNumber(lottoNumberDto);
		
		return responseService.toResponseEntity("조회가 완료되었습니다.", result);
	}
	
	@PostMapping("/buyingLottoTicket/{cost}")
	public DrsResponseEntity<Object> buyingLottoTicket(@PathVariable int cost) {
		
		List<LottoNumberDto> result;

		result = lottoMainService.choice(cost);
		
		if(ObjectUtils.isNotEmpty(result)) {
			return responseService.toResponseEntity("구매가 완료되었습니다.", result);
		} else {
			return responseService.toResponseEntity("구매를 실패하였습니다.", result);
		}
	}
	
	@PostMapping("/selectLottoNumber")
	public DrsResponseEntity<Object> selectLottoNumber(@RequestBody LottoNumberDto lottoNumberDto) {
		
		System.out.println(lottoNumberDto.getRoundNo());
		
		List<LottoNumberDto> result = lottoMainService.selectLottoNumberByRound(lottoNumberDto.getRoundNo());
		System.out.println("result :: " + result);
		
		if(result.isEmpty()) {
			System.out.println("데이터가 없습니다.");
		}
		
		return responseService.toResponseEntity("조회가 완료되었습니다.", result);
	}
	
	@PostMapping("/testLottoNumber")
	public DrsResponseEntity<Object> testLottoNumber(@RequestBody LottoNumberDto lottoNumberDto) {
		
		List<Integer> result;
		
		result = lottoMainService.testLottoNumber();
		
		if(result.isEmpty()) {
			System.out.println("데이터가 없습니다.");
		}
		
		return responseService.toResponseEntity("조회가 완료되었습니다.", result);
	}
	
	@PostMapping("/insertLottoNumber")
	public DrsResponseEntity<Object> insertLottoNumbers(@RequestBody LottoNumberDto lottoNumberDto) {
		
		System.out.println("winnginLottoNumbers :: " + lottoNumberDto);
		
		boolean result = lottoMainService.insertLottoNumber(lottoNumberDto);
		
		return responseService.toResponseEntity("조회가 완료되었습니다.", result);
	}
	
//	@PostMapping("/insertLottoNumbers")
//	public DrsResponseEntity<Object> insertLottoNumbers(@RequestBody LottoNumberDto lottoNumberDto) {
//		try {
//			FileInputStream file = new FileInputStream("c:/temp/로또당첨번호.xlsx");
//			// 엑셀 파일로 Workbook instance를 생성한다.
//			XSSFWorkbook workbook = new XSSFWorkbook(file);
//			// workbook의 첫번째 sheet를 가져온다.
//			XSSFSheet sheet = workbook.getSheetAt(1);
//			LottoNumberDto ltd = new LottoNumberDto();
//			for(Row row : sheet) { // 행을 받아옴
//				List<Object> lst = new ArrayList<Object>();
//				for(Cell cell : row) { // n번째 행에 열 값들을 받음
//					switch (cell.getCellType()) {
//						case NUMERIC:
//							lst.add((int)cell.getNumericCellValue());
//							break;
//						case STRING:
//							lst.add(cell.getStringCellValue());
//							break;
//						default:
//							throw new IllegalStateException("Unexpected value: " + cell.getCellType());
//					}
//				}
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//				ltd.setRoundNo((String)lst.get(0));
//				ltd.setFirstNum((int)lst.get(1));
//				ltd.setSecondNum((int)lst.get(2));
//				ltd.setThirdNum((int)lst.get(3));
//				ltd.setFourthNum((int)lst.get(4));
//				ltd.setFifthNum((int)lst.get(5));
//				ltd.setSixthNum((int)lst.get(6));
//				ltd.setBonusNum((int)lst.get(7));
//				ltd.setDrawDate(LocalDate.parse((CharSequence) lst.get(8), formatter));
//				boolean result = lottoMainService.insertLottoNumber(ltd);
//			}
//			file.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return responseService.toResponseEntity("조회가 완료되었습니다.", null);
//	}
}
