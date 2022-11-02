package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.global.userdetails.User;
import fashionable.simba.yanawaserver.global.userdetails.UserDetails;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        log.info("Load user by Username, username is {}", username);
        long id = Long.parseLong(username);

        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return getUser(member.getId(), member.getRoles());
    }

    @Override
    @Transactional
    public UserDetails saveKakaoMember(KakaoMember member) {
        Optional<KakaoMember> kakaoMemberInDatabase = getMemberByKakaoId(member);
        if (kakaoMemberInDatabase.isEmpty()) {
            log.info("Save kakao member, member kakao id is {}", member.getKakaoId());
            KakaoMember newMember = memberRepository.save(member);
            return getUser(newMember.getId(), newMember.getRoles());
        }

        KakaoMember kakaoMember = kakaoMemberInDatabase.get();
        log.info("Get member, member id is {}", kakaoMember.getId());
        return getUser(kakaoMember.getId(), kakaoMember.getRoles());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isValidUser(String username) {
        log.info("Validate user, username is {}", username);
        long id = Long.parseLong(username);
        return memberRepository.findById(id).isPresent();
    }

    private User getUser(Long id, List<String> roles) {
        return new User(id.toString(), roles);
    }

    private Optional<KakaoMember> getMemberByKakaoId(KakaoMember member) {
        return memberRepository.findByKakaoId(member.getKakaoId());
    }
}
