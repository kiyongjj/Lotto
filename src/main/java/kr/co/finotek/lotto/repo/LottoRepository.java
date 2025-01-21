package kr.co.finotek.lotto.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.finotek.lotto.domain.lotto.Lotto;

public interface LottoRepository extends JpaRepository<Lotto, Long> {

}
