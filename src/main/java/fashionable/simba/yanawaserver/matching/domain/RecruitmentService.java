package fashionable.simba.yanawaserver.matching.domain;

public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    public Recruitment createRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public Recruitment completeRecritument(Long id) {
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(id).orElseThrow();
        recruitment.changeClosed();
        return recruitmentRepository.save(recruitment);
    }
}
