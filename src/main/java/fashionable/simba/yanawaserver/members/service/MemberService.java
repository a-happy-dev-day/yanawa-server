package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.members.domain.AccessToken;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public AccessToken login() {
        return null;
    }

    public void logout() {

    }

    public Optional<Member> findMemberByUserName(String username) {
        return memberRepository.findByEmail(username);
    }
}
