package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import fashionable.simba.yanawaserver.members.ui.MemberResponse;
import org.springframework.stereotype.Service;

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
        long id = Long.parseLong(username);

        return memberRepository.findById(id);
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll()
            .stream()
            .map(member -> new MemberResponse(member.getEmail()))
            .collect(Collectors.toList());
    }

    public void updateMember(String nickname, LocalDate localDate, BigDecimal level) {

    }
}
