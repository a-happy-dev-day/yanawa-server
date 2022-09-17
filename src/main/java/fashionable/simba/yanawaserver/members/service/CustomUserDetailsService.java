package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.auth.userdetails.User;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        long id = Long.parseLong(username);

        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return getUser(member.getId(), member.getRoles());
    }

    @Override
    @Transactional
    public UserDetails saveKakaoMember(KakaoMember member) {
        if (getMemberByKakaoId(member).isEmpty()) {
            KakaoMember kakaoMember = memberRepository.save(member);
            return getUser(kakaoMember.getId(), kakaoMember.getRoles());
        }

        KakaoMember kakaoMember = getMemberByKakaoId(member)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        kakaoMember.updateAccessToken(member.getMemberAccessToken());

        memberRepository.save(kakaoMember);

        return getUser(kakaoMember.getId(), kakaoMember.getRoles());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(String username, String refreshToken) {
        long id = Long.parseLong(username);
//        KakaoMember member = memberRepository.findById(id).orElseThrow();
        return false;
    }

    private User getUser(Long id, List<String> roles) {
        return new User(id.toString(), roles);
    }

    private Optional<KakaoMember> getMemberByKakaoId(KakaoMember member) {
        return memberRepository.findByKakaoId(member.getKakaoId());
    }
}
