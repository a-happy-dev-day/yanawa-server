package fashionable.simba.yanawaserver.matching.controller;

import fashionable.simba.yanawaserver.matching.application.MatchingApplicationService;
import fashionable.simba.yanawaserver.matching.application.RecruitmentRequsest;
import fashionable.simba.yanawaserver.matching.application.RecruitmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {
    MatchingApplicationService matchingApplicationService;

    @PostMapping("/create")
    public ResponseEntity<RecruitmentResponse> createMathingAndRecruitment(RecruitmentRequsest requsest) {
        RecruitmentResponse response = matchingApplicationService.createMatchingAndRecruitment(requsest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
