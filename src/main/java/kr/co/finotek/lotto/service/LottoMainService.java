package kr.co.finotek.lotto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import kr.co.finotek.lotto.dto.LottoMainDto;
import kr.co.finotek.lotto.dto.LottoNumberDto;
import kr.co.finotek.lotto.mapper.LottoMainMapper;
import lombok.AllArgsConstructor;

@Service("lottoMainService")
@AllArgsConstructor
public class LottoMainService {

	private static final int START_LOTTO_NUMBER = 1;
    private static final int END_LOTTO_NUMBER = 45;
    private List<LottoMainDto> cachedLottoNumbers = new ArrayList<>();

    private static final BigDecimal LOTTO_PRICE = BigDecimal.valueOf(1000);

	Map<String, List<LottoNumberDto>> lottoGroups = new HashMap<String, List<LottoNumberDto>>();
	
    private LottoMainMapper lottoMainMapper;
    
	public List<LottoNumberDto> choice(int count) {

		List<LottoNumberDto> result = new ArrayList<>();
		
		if(count > 0) {
//			for(int i = 0 ; i < count ; i++) {
				boolean rtn = true;

				LottoNumberDto lnd = new LottoNumberDto();
//				List<Integer> results = selectNumber();
				List<Integer> results = new ArrayList<>();
				results.add(6);
				results.add(14);
				results.add(30);
				results.add(31);
				results.add(40);
				results.add(41);
				rtn = checkLottoNumber(results);
				
				if(rtn) {
					count += 1;
					System.out.println("results :: " + results + ", rtn :: " + rtn + " , count :: " + count);
				} /*else {
					System.out.println("results :: " + results);
					lnd = convertLotto(results);
					lnd.setRoundNo((i + 1) + "회");
					result.add(lnd);
				}*/
//				if(!rtn) {
//					lnd = convertLotto(results);
//					lnd.setRoundNo((i + 1) + "회");
//					result.add(lnd);
//				}
//				System.out.println("count :: " + count);
			}
//		}
    	return result;
	}
	
	public List<Integer> selectNumber() {
		
		List<Integer> result = new ArrayList<Integer>();
		Random rand = new Random();

		@SuppressWarnings("removal")
		List<Integer> lottoNumberCollections = IntStream.rangeClosed(START_LOTTO_NUMBER, END_LOTTO_NUMBER)
				.mapToObj(Integer::new)				//람다 code :: .mapToObj(x -> new Integer(x))
				.collect(Collectors.toList());

    	for(int j = 0 ; j < 6 ; j++) {
    		int rdNum = (int)((rand.nextDouble()+ 1) *10000);
    		boolean rtn = true;
    		
	    	while(rtn) {
		    	for(int i = 0 ; i < lottoNumberCollections.size() ; i++) {
		        	Collections.shuffle(lottoNumberCollections);
		        	
		    		if(rdNum >= lottoNumberCollections.get(i)) {
			    		rdNum -= lottoNumberCollections.get(i);
		    		}
		    		else {
		    			result.add(lottoNumberCollections.get(i));
		    			lottoNumberCollections.remove(i);
		    			rtn = false;
		    			break;
		    		}
		    	}
	    	}
    	}
    	Collections.sort(result);
//    	System.out.println("result :: " + result);
		return result;
	}
	
    public boolean checkLottoNumber(List<Integer> lottoNumber) {
    	
    	boolean rtn = false;
    	
    	for(int i = 0 ; i < cachedLottoNumbers.size() ; i++) {
			List<Integer> tmpList = new ArrayList<>();
			tmpList.add(cachedLottoNumbers.get(i).getFirstNum());
			tmpList.add(cachedLottoNumbers.get(i).getSecondNum());
			tmpList.add(cachedLottoNumbers.get(i).getThirdNum());
			tmpList.add(cachedLottoNumbers.get(i).getFourthNum());
			tmpList.add(cachedLottoNumbers.get(i).getFifthNum());
			tmpList.add(cachedLottoNumbers.get(i).getSixthNum());

			rtn = lottoNumber.containsAll(tmpList);
			System.out.println("tmpList :: " + tmpList + ", rtn :: " + rtn);
			if(rtn) {
				System.out.println("tmpList :: " + tmpList + ", rtn :: " + rtn + " , count :: " + i);
				break;
			}
		}
    	
    	return rtn;
    }
	
	public LottoNumberDto convertLotto(List<Integer> results) {
		
		LottoNumberDto lnd = new LottoNumberDto();
		
		lnd.setFirstNum(results.get(0));
    	lnd.setSecondNum(results.get(1));
    	lnd.setThirdNum(results.get(2));
    	lnd.setFourthNum(results.get(3));
    	lnd.setFifthNum(results.get(4));
    	lnd.setSixthNum(results.get(5));
    	
    	return lnd;
	}
	
	public void cachedLottoNumbers() {
		cachedLottoNumbers = lottoMainMapper.selectLottoAllRounds();
		for(int i = 0 ; i < cachedLottoNumbers.size() ; i++) {
			System.out.println(i + "  cachedLottoNumbers :: " + cachedLottoNumbers.get(i));
		}
	}
	
	public List<LottoNumberDto> cachedLottoNumbers2() {

		List<LottoNumberDto> cachedLottoNumbers = new ArrayList<LottoNumberDto>();
		
		cachedLottoNumbers = lottoMainMapper.selectLottoNumbers();
		
		return cachedLottoNumbers;
	}
	
	/**
	 * Mapper 호출
	 * @param lottoNumberDto
	 * @return
	 */
//    public boolean checkLottoNumber(LottoNumberDto lottoNumberDto) {
//        
//    	return lottoMainMapper.existLottoNumber(lottoNumberDto) > 0;
//    }
    
    public List<LottoNumberDto> selectLottoRoundNumber() {
        
    	return lottoMainMapper.selectLottoRoundNumber();
    }
    
    public List<LottoNumberDto> selectLottoNumbers() {
        
    	return lottoMainMapper.selectLottoNumbers();
    }
    
    public List<LottoNumberDto> selectLottoNumberByRound(String roundNo) {
        
    	System.out.println("selectLottoNumber :: " + roundNo);
    	return lottoMainMapper.selectLottoNumberByRound(roundNo);
    }
    
    public boolean insertLottoNumber(LottoNumberDto lottoNumberDto) {
    	
    	boolean rtn = false;
    	
    	if (ObjectUtils.isNotEmpty(lottoNumberDto)) {
        	rtn = lottoMainMapper.insertLottoNumber(lottoNumberDto) > 0;
    	}
    	
    	return rtn;
    }
    
    
    
    
    

	public List<Integer> choice2() {

		List<Integer> result = new ArrayList<>();
		
		boolean rtn = false;
		int count = 0;
		System.out.println("START");
		
//		while(!rtn) {
			count++;
			result  = selectNumber();
			rtn = checkLottoNumber(result);
//			for(int i = 0 ; i < cachedLottoNumbers.size() ; i++) {
//				List<Integer> tmpList = new ArrayList<>();
//				tmpList.add(cachedLottoNumbers.get(i).getFirstNum());
//				tmpList.add(cachedLottoNumbers.get(i).getSecondNum());
//				tmpList.add(cachedLottoNumbers.get(i).getThirdNum());
//				tmpList.add(cachedLottoNumbers.get(i).getFourthNum());
//				tmpList.add(cachedLottoNumbers.get(i).getFifthNum());
//				tmpList.add(cachedLottoNumbers.get(i).getSixthNum());
//				rtn = result.containsAll(tmpList);
//				if(count%1000 == 0) {
//					System.out.println("count :: " + count);
//				}
//				if(rtn) {
//					System.out.println(result + " , " + rtn + " , count :: " + count);
//					break;
//				}
//			}
//		}

		return result;
	}
	



	
    public int getNumberOfLottoByMoneyPaid(BigDecimal lottoMoney) {
    	
        return lottoMoney.divide(LOTTO_PRICE, RoundingMode.DOWN).intValue();
    }
    

    
    public List<LottoMainDto> selectLottoAllRounds() {
        
    	return lottoMainMapper.selectLottoAllRounds();
    }
    

    
    public int countOfLottoRound() {
        
    	return lottoMainMapper.countOfLottoRound();
    }
    

    


}
