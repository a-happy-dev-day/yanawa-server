package fashionable.simba.yanawaserver.members;

import fashionable.simba.yanawaserver.members.domain.DefaultMember;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    private final MemberRepository memberRepository;

    public DataLoader(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void loadData() {
        Member admin = new DefaultMember("admin@email.com", List.of(RoleType.ROLE_ADMIN.name()));
        Member member = new DefaultMember("user@email.com", List.of(RoleType.ROLE_MEMBER.name()));
        memberRepository.save(admin);
        memberRepository.save(member);
    }
}
