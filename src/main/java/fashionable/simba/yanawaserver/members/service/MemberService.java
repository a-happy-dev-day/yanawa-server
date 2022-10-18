package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberBirthDate;
import fashionable.simba.yanawaserver.members.domain.MemberLevel;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import fashionable.simba.yanawaserver.members.domain.MemberSex;
import fashionable.simba.yanawaserver.members.exception.NoMemberDateException;
import fashionable.simba.yanawaserver.members.ui.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findMemberByUserName(String username) {
        return memberRepository.findById(getId(username));
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll()
            .stream()
            .map(member -> new MemberResponse(member.getEmail()))
            .collect(Collectors.toList());
    }

    @Transactional
    public void updateMember(String username, String nickname, MemberSex sex, LocalDate birthDate, BigDecimal level) {
        Member member = memberRepository.findById(getId(username))
            .orElseThrow(() -> new NoMemberDateException("사용자가 존재하지 않습니다."));

        if (!member.isFirst()) {
            throw new AlreadyUpdatedException("이미 정보를 등록한 사용자입니다.");
        }

        member.updateInformation(nickname, sex, new MemberBirthDate(birthDate), new MemberLevel(level));
    }

    private long getId(String username) {
        return Long.parseLong(username);
    }
}
