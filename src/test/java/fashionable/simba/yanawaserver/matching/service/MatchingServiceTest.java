package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
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
        assertThat(repository.findMatchingById(fixtureMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.CLOSING);
    }

    @Test
    @DisplayName("모집이 완료되지 않은 상태에서, 인원이 차면 매칭 모집을 완료(Finish)한다.")
    void 요청인원_완료_상태변화() {
        //given
        MemoryMatchingRepository matchingRepository = new MemoryMatchingRepository();
        MatchingService matchingService = new MatchingService(matchingRepository);
        MemoryParticipationRepository applyRepository = new MemoryParticipationRepository();
        ParticipationService applyService = new ParticipationService(applyRepository, matchingRepository);

        Matching matching = new Matching.Builder()
                .id(1L).courtId(1L)
                .date(LocalDate.now()).startTime(LocalTime.now()).endTime(LocalTime.now().plusMinutes(1))
                .annual(AnnualType.FIVE_YEARS_LESS).minimumLevel(new Level(1.0)).maximumLevel(new Level(2.0))
                .ageOfRecruitment(AgeGroupType.FORTIES).sexOfRecruitment(GenderType.NONE).preferenceGame(PreferenceType.MATCHING)
                .numberOfRecruitment(4).costOfCourtPerPerson(1.5).status(MatchingStatusType.OPENING).build();
        Participation matchingApply1 = new Participation.Builder(1L, 1L, 1L)
                .requestDateTime(LocalDateTime.now())
                .status(RequestStatusType.WAITING)
                .build();
        Participation matchingApply2 = new Participation.Builder(2L, 2L, 1L)
                .requestDateTime(LocalDateTime.now())
                .status(RequestStatusType.WAITING)
                .build();
        Participation matchingApply3 = new Participation.Builder(3L, 3L, 1L)
                .requestDateTime(LocalDateTime.now())
                .status(RequestStatusType.WAITING)
                .build();
        Participation matchingApply4 = new Participation.Builder(4L, 4L, 1L)
                .requestDateTime(LocalDateTime.now())
                .status(RequestStatusType.WAITING)
                .build();
        matchingRepository.save(matching);
        applyRepository.save(matchingApply1);
        applyRepository.save(matchingApply2);
        applyRepository.save(matchingApply3);
        applyRepository.save(matchingApply4);
        //when
        Integer numberOfRecruitment = matchingRepository.findMatchingById(1L).orElseThrow().getNumberOfRecruitment();
        Integer numberOfApplies = applyRepository.countParticipationsById(1L);
        //then


        if (!applyService.checkAvailableParticipation(numberOfRecruitment, numberOfApplies)) {
            matchingRepository.findMatchingById(1L).orElseThrow().setStatus(MatchingStatusType.FINISH);
        }

        assertThat(matchingRepository.findMatchingById(1L).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.FINISH);
    }
}
