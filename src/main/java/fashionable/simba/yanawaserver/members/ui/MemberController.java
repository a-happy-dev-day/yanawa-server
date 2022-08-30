package fashionable.simba.yanawaserver.members.ui;

import fashionable.simba.yanawaserver.auth.authorization.AuthenticationPrincipal;
import fashionable.simba.yanawaserver.auth.authorization.secured.Secured;
import fashionable.simba.yanawaserver.auth.userdetails.User;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("me")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER"})
    public ResponseEntity<MemberResponse> membersMe(@AuthenticationPrincipal User user) {
        Member member = memberService.findMemberByUserName(user.getUsername()).orElseThrow(IllegalArgumentException::new);
        MemberResponse response = new MemberResponse(member.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Secured(value = "ROLE_ADMIN")
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }
}
