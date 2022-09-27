package fashionable.simba.yanawaserver.matching.controller;

import fashionable.simba.yanawaserver.matching.application.MatchingApplicationService;
import fashionable.simba.yanawaserver.matching.application.RecruitmentRequsest;
import fashionable.simba.yanawaserver.matching.application.RecruitmentResponse;
import fashionable.simba.yanawaserver.matching.application.RecruitmentResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("matchings")
public class MatchingController {
    private final MatchingApplicationService matchingApplicationService;

    public MatchingController(MatchingApplicationService matchingApplicationService) {
        this.matchingApplicationService = matchingApplicationService;
    }

    @PostMapping
    public ResponseEntity<RecruitmentResponse> createMathingAndRecruitment(RecruitmentRequsest requsest) {
        RecruitmentResponse response = matchingApplicationService.createMatchingAndRecruitment(requsest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<RecruitmentResponses> getMatchingList() {
        RecruitmentResponses response = matchingApplicationService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{matchingId}")
    public ResponseEntity<RecruitmentResponse> getMatching(@PathVariable Long matchingId) {
        RecruitmentResponse response = matchingApplicationService.findOne(matchingId);
        return ResponseEntity.ok(response);
    }
}
