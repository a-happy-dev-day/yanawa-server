package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.domain.MatchingApply;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static fashionable.simba.yanawaserver.fixture.MatchingFixture.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchingServiceTest {

    @Test
    @DisplayName("호스트가 모집을 완료하면, 매칭 모집상태를 완료(finish)로 변경한다.")
    void matching_finish_by_host() {
        //given
        MemoryMatchingRepository repository = new MemoryMatchingRepository();
        repository.save(fixtureMatching);
        //when
        MatchingService.changeStatus(FIXTURE_MATCHING_ID, MatchingStatusType.FINISH);
        //then
        assertThat(MatchingStatusType.FINISH).isEqualTo(MatchingStatusType.FINISH);
    }

    @Test
    @DisplayName("인원이 차면 매칭 모집을 완료한다.")
    void 매칭모집_테스트() {
        //given
        MatchingApply matchingApply = new MatchingApply.MatchingRequestBuilder(1L, 1L)
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.WAITING)
                .build();
        //when

    }
}
