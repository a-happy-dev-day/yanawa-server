package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingApply;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingApplyRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static fashionable.simba.yanawaserver.fixture.MatchingFixture.FIXTURE_MATCHING_ID;
import static fashionable.simba.yanawaserver.fixture.MatchingFixture.fixtureMatching;
import static org.assertj.core.api.Assertions.assertThat;

public class MatchingServiceTest {

    @Test
    @DisplayName("호스트가 모집을 완료하면, 매칭 모집상태를 완료(closing)로 변경한다.")
    void matching_finish_by_host() {
        //given
        MemoryMatchingRepository repository = new MemoryMatchingRepository();
        MatchingService matchingService = new MatchingService(repository);
        repository.save(fixtureMatching);
        //when
        matchingService.changeStatus(FIXTURE_MATCHING_ID, MatchingStatusType.CLOSING);
        //then
        assertThat(MatchingStatusType.FINISH).isEqualTo(MatchingStatusType.CLOSING);
    }

    @Test
    @DisplayName("인원이 차면 매칭 모집을 완료(Finish)한다.")
    void 요청인원_완료_상태변화() {
        //given
        MemoryMatchingRepository matchingRepository = new MemoryMatchingRepository();
        MatchingService matchingService = new MatchingService(matchingRepository);
        MemoryMatchingApplyRepository applyRepository = new MemoryMatchingApplyRepository();
        MatchingApplyService applyService = new MatchingApplyService(applyRepository);

        Matching matching = new Matching.MatchingBuilder()
                .setMatchingId(1L).setCourtId(1L)
                .setDate(LocalDate.now()).setStartTime(LocalTime.now()).setEndTime(LocalTime.now().plusMinutes(1))
                .setAnnual(AnnualType.FIVE_YEARS_LESS).setMinimumLevel(new Level(1.0)).setMaximumLevel(new Level(2.0))
                .setAgeOfRecruitment(AgeGroupType.FORTIES).setSexOfRecruitment(GenderType.NONE).setPreferenceGame(PreferenceType.MATCHING)
                .setNumberOfRecruitment(4).setCostOfCourtPerPerson(1.5).setStatus(MatchingStatusType.OPENING).build();
        MatchingApply matchingApply1 = new MatchingApply.MatchingRequestBuilder(1L, 1L, 1L)
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.WAITING)
                .build();
        MatchingApply matchingApply2 = new MatchingApply.MatchingRequestBuilder(2L, 2L, 1L)
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.WAITING)
                .build();
        MatchingApply matchingApply3 = new MatchingApply.MatchingRequestBuilder(3L, 3L, 1L)
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.WAITING)
                .build();
        MatchingApply matchingApply4 = new MatchingApply.MatchingRequestBuilder(4L, 4L, 1L)
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.WAITING)
                .build();
        matchingRepository.save(matching);
        //when
        applyService.checkAvailableApply(matchingApply1);
        applyService.checkAvailableApply(matchingApply2);
        applyService.checkAvailableApply(matchingApply3);
        applyService.checkAvailableApply(matchingApply4);
        System.out.println(matchingRepository.findMatchingById(matching.getMatchingId()).getStatus());
    }
}
