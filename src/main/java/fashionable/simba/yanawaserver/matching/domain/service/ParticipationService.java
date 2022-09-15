package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

//    public Participation createParticipation(Participation participation) {
//        if (participation.)
//        return participationRepository.save(participation);
//    }
//
//    public Participation acceptParticipation(Long participationId) {
//
//    }
}
