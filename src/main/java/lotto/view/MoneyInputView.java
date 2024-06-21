package lotto.view;

import java.math.BigDecimal;
import java.util.Scanner;

public class MoneyInputView {
    private final static Scanner SCANNER = new Scanner(System.in);

    private final BigDecimal money;

    /*
     * MoneyInputView 객체 생성자
     * 객체 생성 시 자동 호출됨
     */
    private MoneyInputView(BigDecimal money) {
        this.money = money;
    }

    public static MoneyInputView enterMoney() {
        return new MoneyInputView(getInput());
    }

    private static BigDecimal getInput() {
        System.out.println("구입 금액을 입력해 주세요.");
        return SCANNER.nextBigDecimal();
    }

    public BigDecimal getMoney() {
        return money;
    }

}
