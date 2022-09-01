package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final ParticipationRepository participationRepository;

    public MatchingService(MatchingRepository matchingRepository, ParticipationRepository participationRepository) {
        this.matchingRepository = matchingRepository;
        this.participationRepository = participationRepository;
    }

    public Matching startMatching(Matching matching) {
        return matchingRepository.save(matching);
    }

    public void closeMatchingStatus(Long id) {
        Matching matching = matchingRepository.findMatchingById(id).orElseThrow();
        Integer numberOfParticipation = participationRepository.countParticipationsByMatchingId(matching.getId());
        if (numberOfParticipation < 1) {
            throw new IllegalStateException("최소 한명의 참가자가 있어야 한다.");
        }
        matching.setStatus(MatchingStatusType.CLOSING);
        matchingRepository.save(matching);
    }

    public void finishMatching(Long id) {
        Matching matching = matchingRepository.findMatchingById(id).orElseThrow();
        Integer numberOfParticipation = participationRepository.countParticipationsByMatchingId(matching.getId());
        if (matching.getStatus() != MatchingStatusType.OPENING) {
            throw new IllegalStateException("현재 모집중인 매칭이 아닙니다.");
        }
        if (numberOfParticipation + 1 < matching.getNumberOfRecruitment()) {
            throw new IllegalStateException("인원이 차지 않았습니다.");
        }
        matching.setStatus(MatchingStatusType.FINISH);
        matchingRepository.save(matching);
    }

    public void startMatching(Long id) {
        Matching matching = matchingRepository.findMatchingById(id).orElseThrow();
        if (matching.getStatus() != MatchingStatusType.CLOSING) {
            throw new IllegalStateException("모집이 완료되지 않았습니다.");
        }
        matching.setStatus(MatchingStatusType.ONGOING);
        matchingRepository.save(matching);
    }

    public void endMatching(Long id) {
        Matching matching = matchingRepository.findMatchingById(id).orElseThrow();
        matching.setStatus(MatchingStatusType.CLOSING);
        matchingRepository.save(matching);
    }
}
