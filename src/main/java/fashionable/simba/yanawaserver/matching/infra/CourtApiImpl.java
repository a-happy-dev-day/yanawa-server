package fashionable.simba.yanawaserver.matching.infra;

import fashionable.simba.yanawaserver.court.domain.CourtRepository;
import fashionable.simba.yanawaserver.matching.domain.CourtApi;
import org.springframework.stereotype.Component;

@Component
public class CourtApiImpl implements CourtApi {

    private final CourtRepository courtRepository;

    public CourtApiImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public boolean isCourtExist(Long id) {
        return courtRepository.findById(id).isPresent();
    }
}
