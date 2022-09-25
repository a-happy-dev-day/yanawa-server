package fashionable.simba.yanawaserver.matching.controller;

import fashionable.simba.yanawaserver.matching.application.MatchingApplicationService;
import fashionable.simba.yanawaserver.matching.application.RecruitmentRequsest;
import fashionable.simba.yanawaserver.matching.application.RecruitmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {
    @Autowired
    MatchingApplicationService matchingApplicationService;

    @PostMapping("/create")
    public RecruitmentResponse createMathingAndRecruitment(RecruitmentRequsest requsest) {
        return matchingApplicationService.createMatchingAndRecruitment(requsest);
    }
}
