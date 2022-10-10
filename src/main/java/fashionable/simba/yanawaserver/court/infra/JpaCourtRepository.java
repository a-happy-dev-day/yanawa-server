package fashionable.simba.yanawaserver.court.infra;

import fashionable.simba.yanawaserver.court.domain.Court;
import fashionable.simba.yanawaserver.court.domain.CourtRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCourtRepository extends JpaRepository<Court, Long>, CourtRepository {
    @Override
    <S extends Court> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Court> findById(UUID aLong);

    List<Court> findCourtByAreaNameContainingOrPlaceNameContainingOrderByAreaNameAsc(String name, String region);

    @Override
    List<Court> findAll();

    @Override
    void deleteAllInBatch();

    @Override
    long count();
}
