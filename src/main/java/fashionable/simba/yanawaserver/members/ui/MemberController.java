package fashionable.simba.yanawaserver.members.ui;

import fashionable.simba.yanawaserver.members.service.MemberService;
import fashionable.simba.yanawaserver.members.userdetails.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("me")
    public ResponseEntity<MemberResponse> membersMe(User user) {
        User member = memberService.findMemberByUserName(user.getUsername());
        MemberResponse response = new MemberResponse(member.getUsername());
        return ResponseEntity.ok(response);
    }
}
