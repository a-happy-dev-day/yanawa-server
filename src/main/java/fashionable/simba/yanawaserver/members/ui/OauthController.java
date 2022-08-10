package fashionable.simba.yanawaserver.members.ui;

import fashionable.simba.yanawaserver.members.domain.AccessToken;
import fashionable.simba.yanawaserver.members.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    private final MemberService memberService;

    public OauthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<AccessToken> login() {
        return ResponseEntity.ok(memberService.login());
    }


    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        memberService.logout();
        return ResponseEntity.ok().build();
    }
}
