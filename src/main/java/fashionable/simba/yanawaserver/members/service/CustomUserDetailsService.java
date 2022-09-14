package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.auth.userdetails.User;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.KakaoMemberRepository;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final KakaoMemberRepository kakaoMemberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository, KakaoMemberRepository kakaoMemberRepository) {
        this.memberRepository = memberRepository;
        this.kakaoMemberRepository = kakaoMemberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        long id = Long.parseLong(username);

        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return new User(member.getId().toString(), member.getRoles());
    }

    @Override
    @Transactional
    public UserDetails saveKakaoMember(KakaoMember member) {
        KakaoMember kakaoMember = member;
        if (kakaoMemberRepository.findByKakaoId(kakaoMember.getKakaoId()).isEmpty()) {
            kakaoMember = kakaoMemberRepository.save(kakaoMember);
        }
        return new User(kakaoMember.getId().toString(), kakaoMember.getRoles());
    }
}
