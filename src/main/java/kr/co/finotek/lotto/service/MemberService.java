package kr.co.finotek.lotto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.finotek.lotto.domain.member.Member;
import kr.co.finotek.lotto.dto.LoginDto;
import kr.co.finotek.lotto.dto.MemberCreateRequestDto;
import kr.co.finotek.lotto.dto.MemberResponseDto;
import kr.co.finotek.lotto.mapper.MemberMapper;
import kr.co.finotek.lotto.repo.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private MemberMapper memberMapper;

	@Transactional
	public Member registMember(MemberCreateRequestDto memberDto) {

		return memberRepository.save(memberDto.toEntity());
	}
	@Transactional(readOnly = true)
	public MemberResponseDto loginChk(MemberCreateRequestDto memberDto) {

		boolean rtn = false;

//		List<LoginDto> ld = memberMapper.selectMembers();
//		System.out.println(">> " + memberRepository.findById(id).get().getName());
		Member member = memberRepository.findById(memberDto.getId())
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
		
		if(member.getPassword().equals(memberDto.getPassword())) {
			System.out.println("true >> " + member.getPassword());
		}
		return new MemberResponseDto(member);
	}
}
