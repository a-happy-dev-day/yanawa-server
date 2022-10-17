package fashionable.simba.yanawaserver.members.ui;

import fashionable.simba.yanawaserver.global.authorization.AuthenticationPrincipal;
import fashionable.simba.yanawaserver.global.authorization.secured.Secured;
import fashionable.simba.yanawaserver.global.userdetails.User;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 닉네임과 데이터 등록 여부를 반환합니다.
     * @return
     */
    @GetMapping("me")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_TEST"})
    public ResponseEntity<MemberResponse> membersMe(@AuthenticationPrincipal User user) {
        Member member = memberService.findMemberByUserName(user.getUsername()).orElseThrow(IllegalArgumentException::new);
        MemberResponse response = new MemberResponse(member.getEmail());
        return ResponseEntity.ok(response);
    }

    /**
     * 사용자의 정보를 입력합니다.
     * @param informationRequest
     * @return
     */
    @PostMapping("me")
    public ResponseEntity<Void> getInformation(InformationRequest informationRequest) {
        memberService.updateMember(informationRequest.getNickname(), informationRequest.getBirthDate(), informationRequest.getLevel());

        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Secured(value = "ROLE_ADMIN")
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }
}
