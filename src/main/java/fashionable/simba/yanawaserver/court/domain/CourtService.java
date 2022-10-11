package fashionable.simba.yanawaserver.court.domain;

import fashionable.simba.yanawaserver.court.exception.NoCourtDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourtService {

    private final CourtRepository courtRepository;
    private static final Logger log = LoggerFactory.getLogger(CourtService.class);

    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Transactional
    public List<Court> saveCourts(List<Court> courts) {
        if (courtRepository.count() > 0L) {
            log.info("Delete all in Repository");
            courtRepository.deleteAllInBatch();
        }

        log.info("Save court in Repository");
        try {
            return courtRepository.saveAll(courts);
        } catch (Exception e) {
            log.info("Failed to save court in Repository, Message is {}", e.getMessage());
            throw new IllegalStateException("저장에 실패했습니다.");
        }
    }

    @Transactional(readOnly = true)
    public Court findCourt(Long id) {
        log.info("Find court by id in repository, Id is {}", id);
        return courtRepository.findById(id)
            .orElseThrow(() -> {
                log.info("Failed to find court in Repository, Id is {}", id);
                throw new NoCourtDataException("코트장 정보가 존재하지 않습니다.");
            });
    }

    @Transactional(readOnly = true)
    public List<Court> findCourts(String params) {
        log.info("Find courts by param in repository, Para is {}", params);
        return courtRepository.findCourtByAreaNameContainingOrPlaceNameContainingOrderByAreaNameAsc(params, params);
    }

    @Transactional(readOnly = true)
    public List<Court> findCourts() {
        log.info("Find courts in repository");
        return courtRepository.findAll();
    }
}
