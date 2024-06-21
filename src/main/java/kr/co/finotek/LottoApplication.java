package kr.co.finotek;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "kr.co.finotek.lotto")
public class LottoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LottoApplication.class, args);
	}

}
