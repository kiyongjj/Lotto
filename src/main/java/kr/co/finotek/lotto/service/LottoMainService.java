package kr.co.finotek.lotto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int END_LOTTO_NUMBER = 45;
    private List<LottoMainDto> cachedLottoNumbers = new ArrayList<>();
    private List<Double> probability = new ArrayList<>();
	

    private static final BigDecimal LOTTO_PRICE = BigDecimal.valueOf(1000);

	Map<String, List<LottoNumberDto>> lottoGroups = new HashMap<String, List<LottoNumberDto>>();
	
    private LottoMainMapper lottoMainMapper;
    
    public void initProcesses() {
    	// 로또 번호 캐싱
		cachedLottoNumbers = selectLottoAllRounds();
		probability = getProbility(cachedLottoNumbers);
    }
    
    public List<LottoMainDto> choice(int count) {
    	List<LottoMainDto> result = new ArrayList<>();
    	LottoMainDto lotto = new LottoMainDto();
    	
    	for(int i = 0 ; i < count ; i++) {
    		lotto = choiceLottoNumbers();
    		boolean rtn = result.contains(lotto);
    		System.out.println("rtn :: " + rtn);
        	result.add(lotto);
    	}

    	System.out.println("result : " + result);
    	
    	return result;
    }
    
    public LottoMainDto choiceLottoNumbers() {
    	List<LottoMainDto> result = new ArrayList<>();

    	int randomNum = generateRandomNum(1000, 10000);
    	System.out.println("randomNum -1- : " + randomNum);
    	boolean rtn = true;
    	
    	for(int i = 0 ; i < randomNum ; i++) {
	    	List<Integer> lottoNumberCollections = collectNumbers();
	    	List<Integer> results = selectNumbers(lottoNumberCollections);
			rtn = checkLottoNumber(results);
			if(rtn) {
				System.out.println("result :: " + results + ", rtn :: "
						+ rtn + ", count :: " + i);
				i--;
			} else {
				result.add(convertLotto(results));
			}
    	}
    	randomNum = generateRandomNum(1000, randomNum);
    	
    	return result.get(randomNum);
    }
    
	public List<LottoMainDto> choiceOld(int count) {

		List<LottoMainDto> result = new ArrayList<>();

		if(count > 0) {
			for(int i = 0 ; i < count ; i++) {
				boolean rtn = true;

				LottoMainDto lnd = new LottoMainDto();

				List<Integer> lottoNumberCollections = collectNumbers();
				List<Integer> results = selectNumbers(lottoNumberCollections);
				
				rtn = checkLottoNumber(results);
				
				if(rtn) {
					count += 1;
					System.out.println("results :: " + results + ", rtn :: " + rtn + " , count :: " + count);
				} else {
					lnd = convertLotto(results);
					result.add(lnd);
				}
				System.out.println(i + " : " + results);
			}
		}
		System.out.println("result : " + result);
    	return result;
	}
	
	/**
	 * 로또 번호 6개 추출하는 함수
	 * probabilityBasedSelection() 함수를 6번 호출할 예정
	 * 리턴된 로또번호를 리스트에 답아서 리스트 리턴함 
	 * @param lottoNumberCollections
	 * @return
	 */
	public List<Integer> selectNumbers(List<Integer> lottoNumberCollections) {
		
		List<Integer> result = new ArrayList<Integer>();
		SecureRandom secureRandom = new SecureRandom();

		for(int i = 0 ; i < LOTTO_NUMBER_COUNT ; i++) {
			
			Collections.shuffle(lottoNumberCollections);

			double rand = secureRandom.nextDouble();
			double cumulativeProbability = 0.0;
			
			
			for(int j = 0 ; j < lottoNumberCollections.size() ; j++) {
				cumulativeProbability += probability.get(lottoNumberCollections.get(j) - 1);
				if(lottoNumberCollections.size() > 39 && rand >= cumulativeProbability) {
					j = 0;
				} else {
					result.add(lottoNumberCollections.get(j));
					lottoNumberCollections.remove(j);
					break;
				}
				Collections.shuffle(lottoNumberCollections);
			}
		}
		Collections.sort(result);
		//System.out.println("result : " + result);

		return result;
	}
	
	/**
	 * 로또 번호 collection 형식으로 구하는 로직
	 * @return
	 */
	public List<Integer> collectNumbers() {
		
		@SuppressWarnings("removal")
		List<Integer> lottoNumberCollections = IntStream.rangeClosed(START_LOTTO_NUMBER, END_LOTTO_NUMBER)
				.mapToObj(Integer::new)				//람다 code :: .mapToObj(x -> new Integer(x))
				.collect(Collectors.toList());
		
		Collections.sort(lottoNumberCollections);
		
		return lottoNumberCollections;
	}
	
	/**
	 * 추첨한 로또번호와 기 당첨번호 비교
	 * @param lottoNumber
	 * @return
	 */
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

			if(rtn) {
				System.out.println("tmpList :: " + tmpList + ", rtn :: " + rtn + " , count :: " + i);
				break;
			}
		}
    	
    	return rtn;
    }
	/**
	 * List 형식을 LottoNumberDto 형식으로 매칭
	 * @param results
	 * @return
	 */
	public LottoMainDto convertLotto(List<Integer> results) {
		
		LottoMainDto lnd = new LottoMainDto();
		
		lnd.setFirstNum(results.get(0));
    	lnd.setSecondNum(results.get(1));
    	lnd.setThirdNum(results.get(2));
    	lnd.setFourthNum(results.get(3));
    	lnd.setFifthNum(results.get(4));
    	lnd.setSixthNum(results.get(5));
    	
    	return lnd;
	}
	
	/**
	 * Lotto 당첨번호 누적 확률 계산
	 * @param cachedLottoNumbers
	 * @return
	 */
    public List<Double> getProbility(List<LottoMainDto> cachedLottoNumbers) {
    	
    	int[] count = new int[45]; // 각 숫자의 선택 횟수를 저장할 배열
    	int totalIterations  = cachedLottoNumbers.size() * 6;
    	List<Double> tmpProbability = new ArrayList<>(); // 각 숫자의 확률을 저장할 배열
    	
    	for(int i = 0 ; i < cachedLottoNumbers.size() ; i++) {
    		
    		count[cachedLottoNumbers.get(i).getFirstNum() - 1]++;
    		count[cachedLottoNumbers.get(i).getSecondNum() - 1]++;
    		count[cachedLottoNumbers.get(i).getThirdNum() - 1]++;
    		count[cachedLottoNumbers.get(i).getFourthNum() - 1]++;
    		count[cachedLottoNumbers.get(i).getFifthNum() - 1]++;
    		count[cachedLottoNumbers.get(i).getSixthNum() - 1]++;
    	}
    	for(int j = 0 ; j < count.length ; j++) {
    		tmpProbability.add((double)count[j] / totalIterations);
    	}

    	return tmpProbability;
    }
	
    public List<Integer> testLottoNumber() {

		List<Integer> result = new ArrayList<>();
		int count = 0;
		boolean rtn = true;
		
		do {
			List<Integer> lottoNumberCollections = collectNumbers();
			result = selectNumbers(lottoNumberCollections);
			
			rtn = checkLottoNumber(result);
			count++;
			if(rtn) {
				System.out.println("result :: " + result + ", rtn :: " + rtn + " , count :: " + count);
				return result;
			}
		} while(!rtn);
		
		return result;
	}
    
    public int generateRandomNum(int start, int end) {
		SecureRandom secureRandom = new SecureRandom();

		return secureRandom.nextInt(start, end);
	}
	
	/**
	 * Mapper 호출
	 * @param lottoNumberDto
	 * @return
	 */
    public List<String> selectLottoRoundNumber() {
        
    	return lottoMainMapper.selectLottoRoundNumber();
    }

    public List<LottoNumberDto> selectLottoNumbers() {
        
    	return lottoMainMapper.selectLottoNumbers();
    }
    
    public List<LottoMainDto> selectLottoAllRounds() {
        
    	return lottoMainMapper.selectLottoAllRounds();
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
    
    
    

    
    public int getNumberOfLottoByMoneyPaid(BigDecimal lottoMoney) {
    	
        return lottoMoney.divide(LOTTO_PRICE, RoundingMode.DOWN).intValue();
    }
    

    


//  public boolean checkLottoNumber(LottoNumberDto lottoNumberDto) {
//      
//  	return lottoMainMapper.existLottoNumber(lottoNumberDto) > 0;
//  }
	
  

    
	public int countOfLottoRound() {
	    
		return lottoMainMapper.countOfLottoRound();
	}

	/** 재귀 함수를 사용하여 확률 배열을 기반으로 새로운 랜덤 숫자 선택하는 메서드
	public int selectRandomNumberBasedOnProbability(List<Double> probability, int index, double rand) {
        if (rand < probability.get(index)) {
        	System.out.println(index);
            return index + 1; // 선택된 숫자 반환 (1부터 시작하므로 index + 1)
        } else {
            return selectRandomNumberBasedOnProbability(probability, index + 1, rand - probability.get(index));
        }
    }
    */
    
}
