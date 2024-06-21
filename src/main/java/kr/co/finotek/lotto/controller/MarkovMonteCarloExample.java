package kr.co.finotek.lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkovMonteCarloExample {

    // 초기 상태를 나타내는 변수
    private static int currentState = 0;

    // 마르코프 전이 확률을 나타내는 변수
    private static double[][] transitionMatrix = {
        {0.0, 0.1, 0.1, 0.1, 0.1, 0.6},  // 상태 1에서의 전이 확률
        {0.2, 0.0, 0.2, 0.2, 0.2, 0.2},  // 상태 2에서의 전이 확률
        {0.2, 0.2, 0.0, 0.2, 0.2, 0.2},  // 상태 3에서의 전이 확률
        {0.2, 0.2, 0.2, 0.0, 0.2, 0.2},  // 상태 4에서의 전이 확률
        {0.6, 0.1, 0.1, 0.1, 0.1, 0.0}   // 상태 5에서의 전이 확률
    };

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        
        // 1에서 45 사이의 숫자를 출력하기 위해 반복
        for (int i = 0; i < 10; i++) { // 예시로 10번만 반복
            int nextNumber = getNextNumber();
            if (!numbers.contains(nextNumber)) {
                numbers.add(nextNumber);
                System.out.println("Next number: " + nextNumber);
            } else {
                i--; // 중복된 숫자는 다시 반복해야 하므로 카운터를 감소시킴
            }
        }
    }

    // 다음 상태를 결정하는 메서드
    private static int getNextNumber() {
        Random random = new Random();
        double[] probabilities = transitionMatrix[currentState];
        double cumulativeProbability = 0.0;
        double randomValue = random.nextDouble();

        // 다음 상태 결정
        for (int nextState = 0; nextState < probabilities.length; nextState++) {
            cumulativeProbability += probabilities[nextState];
            if (randomValue <= cumulativeProbability) {
                currentState = nextState;
                break;
            }
        }

        return currentState + 1; // 상태가 0부터 시작하므로 1을 더해 반환
    }
}

