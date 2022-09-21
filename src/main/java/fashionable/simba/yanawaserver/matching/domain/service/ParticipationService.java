package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final RecruitmentRepository recruitmentRepository;

    public ParticipationService(ParticipationRepository participationRepository, RecruitmentRepository recruitmentRepository) {
        this.participationRepository = participationRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public Participation createParticipation(Participation participation) {
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(participation.getRecruitmentId()).orElseThrow(() -> new IllegalArgumentException("모집정보가 없습니다."));
        if (recruitment.getStatus() == RecruitmentStatusType.CLOSED) {
            throw new IllegalArgumentException("모집이 종료되어 참가요청을 보낼 수 없습니다.");
        }

        if (participationRepository.findParticipationByUserIdAndRecruitmentId(participation.getUserId(), participation.getRecruitmentId()).isPresent()) {
            throw new IllegalArgumentException("참가요청 이력이 있다면 보낼 수 없습니다.");
        }

        Participation saveParticipation = new Participation(participation.getUserId(), participation.getRecruitmentId(), participation.getRequestDateTime(), ParticipationStatusType.WAITING);

        return participationRepository.save(saveParticipation);
    }

    public Participation acceptParticipation(Long participationId) {
        Participation participation = participationRepository.findParticipationById(participationId).orElseThrow();
        if (participation.getStatus() != ParticipationStatusType.WAITING) {
            throw new IllegalArgumentException("참여요청이 대기중이 아니므로 참가승인을 할 수 없습니다.");
        }
        participation.changeAcceptedParticipation();
        return participationRepository.save(participation);
    }

    public Participation rejectParticipation(Long participationId) {
        Participation participation = participationRepository.findParticipationById(participationId).orElseThrow();
        if (participation.getStatus() != ParticipationStatusType.WAITING) {
            throw new IllegalArgumentException("참여요청이 대기중이 아니므로 참가거절을 할 수 없습니다.");
        }
        participation.changeRejectedParticipation();
        return participationRepository.save(participation);
    }
}
