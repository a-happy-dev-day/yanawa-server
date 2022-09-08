package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final RecruitmentRepository recruitmentRepository;

    public MatchingService(MatchingRepository matchingRepository, RecruitmentRepository recruitmentRepository) {
        this.matchingRepository = matchingRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public Matching createMatching(Matching matching) {
        return matchingRepository.save(matching);
    }

    public Matching startMatching(Long id) {
        Matching matching = matchingRepository.findMatchingById(id).orElseThrow();
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(matching.getId()).orElseThrow(() -> new IllegalArgumentException("모집 정보가 없습니다."));
        if (!recruitment.isClosed()) {
            throw new RuntimeException("모집이 종료되지 않아 매칭을 시작할 수 없습니다.");
        }
        matching.changeOngoing();
        return matchingRepository.save(matching);
    }

    public void endMatching(Long id) {
        Matching matching = matchingRepository.findMatchingById(id).orElseThrow();
        if (matching.getStatus() != MatchingStatusType.ONGOING) {
            throw new RuntimeException("매칭이 시작되지 않아 매칭을 종료할 수 없습니다.");
        }
        matching.changeFinished();
        matchingRepository.save(matching);
    }
}
