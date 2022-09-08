package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;

public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final ParticipationRepository participationRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository, ParticipationRepository participationRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.participationRepository = participationRepository;
    }

    public Recruitment createRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public Recruitment completeRecritument(Long id) {
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(id).orElseThrow();
        Integer numberOfparticipations =  participationRepository.countParticipationsByMatchingId(recruitment.getMatchingId());
        if (numberOfparticipations < 1 ) {
            throw new IllegalArgumentException("참가자가 없어 모집을 완료할 수 없습니다.");
        }
        recruitment.changeClosed();
        return recruitmentRepository.save(recruitment);
    }
}
