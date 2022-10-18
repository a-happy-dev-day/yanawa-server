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

        Member admin = new DefaultMember("admin", "admin@email.com", List.of(RoleType.ROLE_ADMIN.name()), false);
        Member member = new DefaultMember("member", "user@email.com", List.of(RoleType.ROLE_MEMBER.name()), false);
        Member user = new DefaultMember("test", 1010L, "user2@email.com", List.of(RoleType.ROLE_TEST.name()), false);
        Member firstUser = new DefaultMember("firstVisitMember", "firstUser@email.com", List.of(RoleType.ROLE_MEMBER.name()), true);

        Member adminUser = memberRepository.save(admin);
        Member memberUser = memberRepository.save(member);
        Member userUser = memberRepository.save(user);
        Member firstVisitUser = memberRepository.save(firstUser);

        members.put("admin", adminUser.getId());
        members.put("member", memberUser.getId());
        members.put("user", userUser.getId());
        members.put("firstVisitUser", firstVisitUser.getId());

        return members;
    }
}
