package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final RecruitmentRepository recruitmentRepository;

    public ParticipationService(ParticipationRepository participationRepository, RecruitmentRepository recruitmentRepository) {
        this.participationRepository = participationRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public Participation createParticipation(Participation participation) {
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(participation.getRecruitmentId()).orElseThrow();
        if (recruitment.getStatus() == RecruitmentStatusType.CLOSED) {
            throw new IllegalArgumentException("모집이 종료되어 참가요청을 보낼 수 없습니다.");
        }
        Optional<Participation> beforeParticipation = participationRepository.findParticipationByUser(participation.getUserId(), participation.getRecruitmentId());
        if (getParticipationStatus(beforeParticipation) == ParticipationStatusType.ACCEPTED
                || getParticipationStatus(beforeParticipation) == ParticipationStatusType.REJECTED) {
            throw new IllegalArgumentException("이전 참가요청에 대해 승인, 거절에 대하여 재요청을 보낼 수 없습니다.");
        }
        return participationRepository.save(participation);
    }

    public Participation acceptParticipation(Long participationId) {
        Participation participation = participationRepository.findParticipationById(participationId).orElseThrow();
        if(participation.getStatus() != ParticipationStatusType.WAITING) {
            throw new IllegalArgumentException("참여요청이 대기중이 아니므로 참가승인을 할 수 없습니다.");
        }
        return participation.changeAcceptedParticipation();
    }

    public Participation rejectParticipation(Long participationId) {
        Participation participation = participationRepository.findParticipationById(participationId).orElseThrow();
        if(participation.getStatus() != ParticipationStatusType.WAITING) {
            throw new IllegalArgumentException("참여요청이 대기중이 아니므로 참가거절을 할 수 없습니다.");
        }
        return participation.changeRejectedParticipation();
    }

    public ParticipationStatusType getParticipationStatus(Optional<Participation> participation) {
        if(participation.isPresent()) {
            return participation.orElseThrow().getStatus();
        }
        return null;
    }
}
