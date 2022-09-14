package fashionable.simba.yanawaserver.members;

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

    public Map<String, Long> loadData() {
        Map<String, Long> members = new HashMap<>();

        Member admin = new DefaultMember("admin@email.com", List.of(RoleType.ROLE_ADMIN.name()));
        Member member = new DefaultMember("user@email.com", List.of(RoleType.ROLE_MEMBER.name()));

        Member adminUser = memberRepository.save(admin);
        Member memberUser = memberRepository.save(member);
        members.put("admin", adminUser.getId());
        members.put("member", memberUser.getId());

        return members;
    }
}
