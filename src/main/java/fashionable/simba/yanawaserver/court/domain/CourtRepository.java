package fashionable.simba.yanawaserver.court.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourtRepository {
    <S extends Court> List<S> saveAll(Iterable<S> entities);

    Optional<Court> findById(UUID aLong);

    List<Court> findCourtByAreaNameContainingOrPlaceNameContainingOrderByAreaNameAsc(String name, String region);

    List<Court> findAll();

    void deleteAllInBatch();

    long count();

}
