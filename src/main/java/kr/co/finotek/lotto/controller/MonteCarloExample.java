package kr.co.finotek.lotto.controller;

import java.util.Random;

public class MonteCarloExample {
//
//	public static void main(String[] args) {
//
//        int[] count = new int[45]; // 각 숫자의 선택 횟수를 저장할 배열
//        double[] probability = new double[45]; // 각 숫자의 확률을 저장할 배열
//
//        Random random = new Random();
//
//        int totalIterations = 10000;
//        for (int i = 0; i < totalIterations; i++) {
//            int randomNumber = random.nextInt(45) + 1; // 1부터 45까지의 랜덤 숫자 선택
//            count[randomNumber - 1]++; // 선택된 숫자의 카운트 증가
//        }
//
//        for (int j = 0; j < 45; j++) {
//            probability[j] = (double) count[j] / totalIterations; // 각 숫자의 확률 계산
//        }
//
//        // 새로운 랜덤 숫자를 선택하는 예시 코드
//        int newRandomNumber = selectRandomNumberBasedOnProbability(probability);
//        System.out.println("New random number based on probability: " + newRandomNumber);
//    }
//
//    // 확률 배열을 기반으로 새로운 랜덤 숫자 선택하는 메서드
//    private static int selectRandomNumberBasedOnProbability(double[] probability) {
//        Random random = new Random();
//        double rand = random.nextDouble(); // 0부터 1 사이의 랜덤한 double 값 생성
//        double cumulativeProbability = 0.0;
//
//        System.out.println("rand : " + rand);
//        for (int i = 0; i < probability.length; i++) {
//            cumulativeProbability += probability[i];
//            if (rand < cumulativeProbability) {
//                System.out.println("count : " + i + ", cumulativeProbability : " + cumulativeProbability);
//                return i + 1; // 선택된 숫자 반환 (1부터 시작하므로 i + 1)
//            }
//        }
//
//        // 만약 여기까지 왔다면 무언가 잘못된 상황일 수 있으나, 여기서는 그냥 마지막 숫자 반환
//        return probability.length;
//	}

    private static Random random = new Random();

    public static void main(String[] args) {
        int[] count = new int[45]; // 각 숫자의 선택 횟수를 저장할 배열
        double[] probability = new double[45]; // 각 숫자의 확률을 저장할 배열

        int totalIterations = 10000;
        for (int i = 0; i < totalIterations; i++) {
            int randomNumber = random.nextInt(45) + 1; // 1부터 45까지의 랜덤 숫자 선택
            count[randomNumber - 1]++; // 선택된 숫자의 카운트 증가
        }

        for (int j = 0; j < 45; j++) {
            probability[j] = (double) count[j] / totalIterations; // 각 숫자의 확률 계산
        }

        // 새로운 랜덤 숫자를 선택하는 예시 코드
        int newRandomNumber = selectRandomNumberBasedOnProbability(probability, 0, random.nextDouble());
        System.out.println("New random number based on probability (recursive): " + newRandomNumber);
    }

    // 재귀 함수를 사용하여 확률 배열을 기반으로 새로운 랜덤 숫자 선택하는 메서드
    private static int selectRandomNumberBasedOnProbability(double[] probability, int index, double rand) {
        if (rand < probability[index]) {
        	System.out.println(index);
            return index + 1; // 선택된 숫자 반환 (1부터 시작하므로 index + 1)
        } else {
            return selectRandomNumberBasedOnProbability(probability, index + 1, rand - probability[index]);
        }
    }

}
