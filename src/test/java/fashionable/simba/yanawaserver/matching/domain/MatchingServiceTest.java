package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.domain.MatchingService;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatchingServiceTest {
    MemoryMatchingRepository matchingRepository;
    MemoryParticipationRepository participationRepository;
    MatchingService matchingService;

    @BeforeEach
    public void setUp() {
        matchingRepository = new MemoryMatchingRepository();
        participationRepository = new MemoryParticipationRepository();
        matchingService = new MatchingService(matchingRepository);
    }

    @Test
    @DisplayName("매칭을 생성한다.")
    void create_matching_test() {
        //given

    }





    @Test
    @DisplayName("모집이 완료된 매칭(CLOSING)이 매칭 시작 시간이 되면 매칭을 진행한다")
    void start_matching_time() {
//        given
//        matchingRepository.save(모집완료_매칭);
//        //when
//        matchingService.startMatching(모집완료_매칭.getId());
//        //then
//        assertThat(matchingRepository.findMatchingById(모집완료_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }

    @Test
    @DisplayName("진행중인 매칭(ONGOING)이 종료 시간이 되면 매칭을 종료한다.")
    void end_matching_time() {
//        //given
//        matchingRepository.save(모집완료_매칭);
//        //when
//        matchingService.endMatching(모집완료_매칭.getId());
//        //then
//        assertThat(matchingRepository.findMatchingById(모집완료_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }


}
