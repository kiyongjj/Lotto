package kr.co.finotek.lotto.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.finotek.lotto.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
