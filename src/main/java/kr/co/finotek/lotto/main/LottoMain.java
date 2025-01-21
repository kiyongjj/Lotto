package kr.co.finotek.lotto.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kr.co.finotek.lotto.dto.LottoMainDto;

public class LottoMain {

	private static final int START_LOTTO_NUMBER = 1;
	private static final int END_LOTTO_NUMBER = 45;
	private static final int LOTTO_NUMBER_COUNT = 6;
//	private List<Double> probability = new ArrayList<>();
	static int[] NUMBERS_COUNT = {159 , 147 , 158 , 152 , 144 , 154 , 157 , 146 , 124 , 154 , 158 ,
			168 , 167 , 165 , 156 , 154 , 161 , 166 , 159 , 159 , 158 , 136 , 136 , 153 , 140 , 155 ,
			163 , 142 , 144 , 147 , 156 , 135 , 165 , 177 , 153 , 154 , 161 , 155 , 154 , 163 , 140 ,
			145 , 158 , 155 , 165};
	static double[] PROBABILITY = {0.02298352124891587, 0.021248915871639202, 0.02283897080080948,
			0.021971668112171147, 0.020815264527320035, 0.022260769008383925, 0.022694420352703092,
			0.021104365423532813, 0.01792425556519225, 0.022260769008383925, 0.02283897080080948,
			0.024284475281873375, 0.024139924833766986, 0.023850823937554208, 0.022549869904596703,
			0.022260769008383925, 0.02327262214512865, 0.023995374385660597, 0.02298352124891587,
			0.02298352124891587, 0.02283897080080948, 0.019658860942468923, 0.019658860942468923,
			0.022116218560277536, 0.02023706273489448, 0.022405319456490314, 0.023561723041341427,
			0.020526163631107257, 0.020815264527320035, 0.021248915871639202, 0.022549869904596703,
			0.019514310494362534, 0.023850823937554208, 0.025585429314830876, 0.022116218560277536,
			0.022260769008383925, 0.02327262214512865, 0.022405319456490314, 0.022260769008383925,
			0.023561723041341427, 0.02023706273489448, 0.020959814975426424, 0.02283897080080948,
			0.022405319456490314, 0.023850823937554208};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<LottoMainDto> result = new ArrayList<>();
		int[] cntNum = new int[45];
		
		List<Integer> lottoNumberCollections = collectNumbers();
		List<Double> probability = getProbability(NUMBERS_COUNT);
		result = selectNumbers(lottoNumberCollections, probability);
		
		System.out.println("result :: " + result);
		
//		cntNum = countingNumbers(NUMBERS_COUNT, results);

//		for(int k = 0 ; k < PROBABILITY.length ; k++) {
//			System.out.print(PROBABILITY[k] + " , ");
//		}
//		System.out.println("probability :: \n" + probability);
	}
	
	public static List<Integer> collectNumbers() {
		
		@SuppressWarnings("removal")
		List<Integer> lottoNumberCollections = IntStream.rangeClosed(START_LOTTO_NUMBER, END_LOTTO_NUMBER)
				.mapToObj(Integer::new)				//람다 code :: .mapToObj(x -> new Integer(x))
				.collect(Collectors.toList());
		
		Collections.sort(lottoNumberCollections);
		
		return lottoNumberCollections;
	}
	
	public static List<Double> getProbability(int[] cntNum) {
		List<Double> probability = new ArrayList<>();
		
		for(double prob : cntNum) {
			probability.add(prob);
		}
		return probability;
	}
	
	public static List<LottoMainDto> selectNumbers(List<Integer> lottoNumberCollections, List<Double> probability) {

		List<LottoMainDto> results = new ArrayList<>();

		Random random = new Random();
		
		for(int cnt = 0 ; cnt < 10 ; cnt++) {
			
			List<Integer> result = new ArrayList<Integer>();
			LottoMainDto lnd = new LottoMainDto();
			
			for(int i = 0 ; i < LOTTO_NUMBER_COUNT ; i++) {
				
				Collections.shuffle(lottoNumberCollections);
	
				double rand = random.nextDouble(); // 0부터 1 사이의 랜덤한 double 값 생성
				double cumulativeProbability = 0.0;
				
				
				for(int j = 0 ; j < lottoNumberCollections.size() ; j++) {
					cumulativeProbability += probability.get(lottoNumberCollections.get(j) - 1);
					if(lottoNumberCollections.size() > 39 && rand >= cumulativeProbability) {
						j = 0;
					}
					
					if (rand < cumulativeProbability) {
						result.add(lottoNumberCollections.get(j));
						lottoNumberCollections.remove(j);
						break;
					}
					Collections.shuffle(lottoNumberCollections);
				}
			}
			Collections.sort(result);
			//System.out.println("result : " + result);
			lnd = convertLotto(result);
			results.add(lnd);
		}
		return results;
	}
	
	public static LottoMainDto convertLotto(List<Integer> results) {
		
		LottoMainDto lnd = new LottoMainDto();
		
		lnd.setFirstNum(results.get(0));
    	lnd.setSecondNum(results.get(1));
    	lnd.setThirdNum(results.get(2));
    	lnd.setFourthNum(results.get(3));
    	lnd.setFifthNum(results.get(4));
    	lnd.setSixthNum(results.get(5));
    	
    	return lnd;
	}

	public static int[] countingNumbers(int[] cntNum, List<Integer> results) {
		
		for(int k = 0 ; k < cntNum.length ; k++) {
			System.out.print(cntNum[k] + " , ");
		}
		System.out.println("\n\n");
		for(int j = 0 ; j < results.size() ; j++) {
			cntNum[results.get(j) - 1]++;
		}
		
		for(int k = 0 ; k < cntNum.length ; k++) {
			System.out.print(cntNum[k] + " , ");
		}
		return cntNum;
	}

	public static List<Integer> selectNumbers2(List<Integer> lottoNumberCollections) {
		
		List<Integer> result = new ArrayList<Integer>();

		Random random = new Random();
		
		for(int i = 0 ; i < LOTTO_NUMBER_COUNT ; i++) {
			
			Collections.shuffle(lottoNumberCollections);

			int rand2 = random.nextInt(2000);
			
			for(int k = 0 ; k < lottoNumberCollections.size() ; k++) {
				
				if(lottoNumberCollections.size() > 39 && rand2 > lottoNumberCollections.get(k)) {
					rand2 -= lottoNumberCollections.get(k);
					k = 0;
				} else {
					result.add(lottoNumberCollections.get(k));
					lottoNumberCollections.remove(k);
					break;
				}
				Collections.shuffle(lottoNumberCollections);
			}
		}
		Collections.sort(result);

		return result;
	}
	
}
