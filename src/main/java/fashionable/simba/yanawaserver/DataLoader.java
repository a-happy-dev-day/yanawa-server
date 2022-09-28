package fashionable.simba.yanawaserver;

import fashionable.simba.yanawaserver.members.domain.DefaultMember;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader {

    private final MemberRepository memberRepository;

    public DataLoader(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isValid() {
        return memberRepository.findAll().isEmpty();
    }

    public Map<String, Long> loadData() {
        Map<String, Long> members = new HashMap<>();

        Member admin = new DefaultMember("admin@email.com", List.of(RoleType.ROLE_ADMIN.name()));
        Member member = new DefaultMember("user@email.com", List.of(RoleType.ROLE_MEMBER.name()));
        Member user = new DefaultMember(1010L, "user2@email.com", List.of(RoleType.ROLE_TEST.name()));

        Member adminUser = memberRepository.save(admin);
        Member memberUser = memberRepository.save(member);
        Member userUser = memberRepository.save(user);
        members.put("admin", adminUser.getId());
        members.put("member", memberUser.getId());
        members.put("user", userUser.getId());

        return members;
    }
}
