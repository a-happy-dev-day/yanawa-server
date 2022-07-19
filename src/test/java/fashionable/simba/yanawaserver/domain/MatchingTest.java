package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.*;
import fashionable.simba.yanawaserver.error.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchingTest {
    @Test
    @DisplayName("요구사항에 맞는 값이 들어가면 매칭이 생성된다.")
    void 매칭생성_성공테스트() {
        Assertions.assertDoesNotThrow(() ->
                new Matching.MatchingBuilder(UUID.randomUUID())
                        .setCourtId(UUID.randomUUID())
                        .setDate(LocalDate.of(2022, 7, 29))
                        .setStartTime(LocalTime.of(19, 0))
                        .setEndTime(LocalTime.of(21, 0))
                        .setAnnual(AnnualType.TWO_YEARS_LESS)
                        .setlevel(new Level(5.0, 2.5))
                        .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                        .setSexOfRecruitment(GenderType.MALE)
                        .setPreferenceGame(PreferenceType.MATCHING)
                        .setNumberOfRecruitment(4)
                        .setCostOfCourtPerPerson(2.5)
                        .setStatus(MatchingStatusType.ONGOING)
                        .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                        .setHostId(UUID.randomUUID())
                        .build()
        );
    }

    @Test
    @DisplayName("시작 시간은 종료 시간보다 늦을 경우, MatchingTimeException이 발생한다.")
    void timeTest() {
        //
        Assertions.assertThrows(MatchingTimeException.class, () -> {
            new Matching.MatchingBuilder(UUID.randomUUID())
                    .setCourtId(UUID.randomUUID())
                    .setDate(LocalDate.of(2022, 7, 29))
                    .setStartTime(LocalTime.of(23, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(AnnualType.TWO_YEARS_LESS)
                    .setlevel(new Level(5.0, 2.5))
                    .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                    .setSexOfRecruitment(GenderType.MALE)
                    .setPreferenceGame(PreferenceType.MATCHING)
                    .setNumberOfRecruitment(4)
                    .setCostOfCourtPerPerson(2.5)
                    .setStatus(MatchingStatusType.ONGOING)
                    .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                    .setHostId(UUID.randomUUID())
                    .build();
        });
    }

    //
    @Test
    @DisplayName("구력은 2년, 2~5년, 5년이상 중 하나의 정보를 가진다.")
    void 구력_성공_테스트() {
        Set<AnnualType> annualTypes = new HashSet<>();
        annualTypes.add(AnnualType.TWO_YEARS_LESS);
        annualTypes.add(AnnualType.FIVE_YEARS_LESS);
        annualTypes.add(AnnualType.FIVE_YEARS_MORE);
        //
        Matching matching = new Matching.MatchingBuilder(UUID.randomUUID())
                .setCourtId(UUID.randomUUID())
                .setDate(LocalDate.of(2022, 7, 29))
                .setStartTime(LocalTime.of(19, 0))
                .setEndTime(LocalTime.of(21, 0))
                .setAnnual(AnnualType.TWO_YEARS_LESS)
                .setlevel(new Level(5.0, 2.5))
                .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                .setSexOfRecruitment(GenderType.MALE)
                .setPreferenceGame(PreferenceType.MATCHING)
                .setNumberOfRecruitment(4)
                .setCostOfCourtPerPerson(2.5)
                .setStatus(MatchingStatusType.ONGOING)
                .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                .setHostId(UUID.randomUUID())
                .build();
        //
        Assertions.assertTrue(annualTypes.contains(matching.getAnnual()));
    }

    @Test
    @DisplayName("레벨이 0 ~ 6 범위를 벗어나면, LevelSettingException이 발생한다.")
    void 레벨_실패_테스트1() {
        Assertions.assertThrows(LevelSettingException.class, () -> {
            new Matching.MatchingBuilder(UUID.randomUUID())
                    .setCourtId(UUID.randomUUID())
                    .setDate(LocalDate.of(2022, 7, 29))
                    .setStartTime(LocalTime.of(23, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(AnnualType.TWO_YEARS_LESS)
                    .setlevel(new Level(7.0, 2.5))
                    .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                    .setSexOfRecruitment(GenderType.MALE)
                    .setPreferenceGame(PreferenceType.MATCHING)
                    .setNumberOfRecruitment(4)
                    .setCostOfCourtPerPerson(2.5)
                    .setStatus(MatchingStatusType.ONGOING)
                    .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                    .setHostId(UUID.randomUUID())
                    .build();
        });
    }

    @Test
    @DisplayName("레벨이 0.5단위가 아닐경우, LevelSettingException이 발생한다.")
    void 레벨_실패_테스트2() {
        Assertions.assertThrows(LevelSettingException.class, () -> {
            new Matching.MatchingBuilder(UUID.randomUUID())
                    .setCourtId(UUID.randomUUID())
                    .setDate(LocalDate.of(2022, 7, 29))
                    .setStartTime(LocalTime.of(23, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(AnnualType.TWO_YEARS_LESS)
                    .setlevel(new Level(3.2, 2.3))
                    .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                    .setSexOfRecruitment(GenderType.MALE)
                    .setPreferenceGame(PreferenceType.MATCHING)
                    .setNumberOfRecruitment(4)
                    .setCostOfCourtPerPerson(2.5)
                    .setStatus(MatchingStatusType.ONGOING)
                    .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                    .setHostId(UUID.randomUUID())
                    .build();
        });
    }

    @Test
    @DisplayName("최소레벨이 최대레벨보다 클 경우, LevelSettingException이 발생한다.")
    void 레벨_실패_테스트3() {
        Assertions.assertThrows(LevelSettingException.class, () -> {
            new Matching.MatchingBuilder(UUID.randomUUID())
                    .setCourtId(UUID.randomUUID())
                    .setDate(LocalDate.of(2022, 7, 29))
                    .setStartTime(LocalTime.of(23, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(AnnualType.TWO_YEARS_LESS)
                    .setlevel(new Level(2.0, 4.5))
                    .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                    .setSexOfRecruitment(GenderType.MALE)
                    .setPreferenceGame(PreferenceType.MATCHING)
                    .setNumberOfRecruitment(4)
                    .setCostOfCourtPerPerson(2.5)
                    .setStatus(MatchingStatusType.ONGOING)
                    .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                    .setHostId(UUID.randomUUID())
                    .build();
        });
    }

    @Test
    @DisplayName("모집 연령은 20대, 30대, 40대, 50대 이상, 무관 중 하나의 정보를 가진다.")
    void 모집연령_성공_테스트() {
        //
        Set<AgeGroupType> ageGroupTypes = new HashSet<>();
        ageGroupTypes.add(AgeGroupType.TWENTIES);
        ageGroupTypes.add(AgeGroupType.THIRTIES);
        ageGroupTypes.add(AgeGroupType.FORTIES);
        ageGroupTypes.add(AgeGroupType.FIFTIES);
        ageGroupTypes.add(AgeGroupType.ETC);
        //
        Matching matching = new Matching.MatchingBuilder(UUID.randomUUID())
                .setCourtId(UUID.randomUUID())
                .setDate(LocalDate.of(2022, 7, 29))
                .setStartTime(LocalTime.of(19, 0))
                .setEndTime(LocalTime.of(21, 0))
                .setAnnual(AnnualType.TWO_YEARS_LESS)
                .setlevel(new Level(5.0, 2.5))
                .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                .setSexOfRecruitment(GenderType.MALE)
                .setPreferenceGame(PreferenceType.MATCHING)
                .setNumberOfRecruitment(4)
                .setCostOfCourtPerPerson(2.5)
                .setStatus(MatchingStatusType.ONGOING)
                .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                .setHostId(UUID.randomUUID())
                .build();
        //
        Assertions.assertTrue(ageGroupTypes.contains(matching.getAgeOfRecruitment()));
    }

    @Test
    @DisplayName("모집 성별은 남자만, 여자만, 상관없음 중 하나의 정보를 가진다.")
    void 성별_성공_테스트() {
        Set<GenderType> genderTypes = new HashSet<>(Arrays.asList(
                GenderType.MALE, GenderType.FEMALE, GenderType.NONE));
        //
        Matching matching = new Matching.MatchingBuilder(UUID.randomUUID())
                .setCourtId(UUID.randomUUID())
                .setDate(LocalDate.of(2022, 7, 29))
                .setStartTime(LocalTime.of(19, 0))
                .setEndTime(LocalTime.of(21, 0))
                .setAnnual(AnnualType.TWO_YEARS_LESS)
                .setlevel(new Level(5.0, 2.5))
                .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                .setSexOfRecruitment(GenderType.MALE)
                .setPreferenceGame(PreferenceType.MATCHING)
                .setNumberOfRecruitment(4)
                .setCostOfCourtPerPerson(2.5)
                .setStatus(MatchingStatusType.ONGOING)
                .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                .setHostId(UUID.randomUUID())
                .build();
        //
        Assertions.assertTrue(genderTypes.contains(matching.getSexOfRecruitment()));
    }

    @Test
    @DisplayName("선호게임은 매칭과 렐리중 하나의 정보를 가진다.")
    void 선호게임_성공_테스트() {
        Set<PreferenceType> preferenceTypes = new HashSet<>(Arrays.asList(
                PreferenceType.MATCHING, PreferenceType.RALLY
        ));
        //
        Matching matching = new Matching.MatchingBuilder(UUID.randomUUID())
                .setCourtId(UUID.randomUUID())
                .setDate(LocalDate.of(2022, 7, 29))
                .setStartTime(LocalTime.of(19, 0))
                .setEndTime(LocalTime.of(21, 0))
                .setAnnual(AnnualType.TWO_YEARS_LESS)
                .setlevel(new Level(5.0, 2.5))
                .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                .setSexOfRecruitment(GenderType.MALE)
                .setPreferenceGame(PreferenceType.MATCHING)
                .setNumberOfRecruitment(4)
                .setCostOfCourtPerPerson(2.5)
                .setStatus(MatchingStatusType.ONGOING)
                .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                .setHostId(UUID.randomUUID())
                .build();
        //
        Assertions.assertTrue(preferenceTypes.contains(matching.getPreferenceGame()));
    }

    @Test
    @DisplayName("모집 인원은 1명이상 8명이하가 아닐경우, InvalidNumberException이 발생한다.")
    void 모집인원_실패_테스트() {
        //
        Integer numberOfRecruitment = 10;
        //
        Assertions.assertThrows(InvalidNumberException.class, () -> {
            new Matching.MatchingBuilder(UUID.randomUUID())
                    .setCourtId(UUID.randomUUID())
                    .setDate(LocalDate.of(2022, 7, 29))
                    .setStartTime(LocalTime.of(19, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(AnnualType.TWO_YEARS_LESS)
                    .setlevel(new Level(5.0, 2.5))
                    .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                    .setSexOfRecruitment(GenderType.MALE)
                    .setPreferenceGame(PreferenceType.MATCHING)
                    .setNumberOfRecruitment(numberOfRecruitment)
                    .setCostOfCourtPerPerson(2.5)
                    .setStatus(MatchingStatusType.ONGOING)
                    .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                    .setHostId(UUID.randomUUID())
                    .build();
        });
    }

    @Test
    @DisplayName("코트 비용은 0원보다 작을경우, InvalidCostException이 발생한다.")
    void 코트비용_실패_테스트() {
        //
        Double costOfCourtPerPerson = -3.0;
        //
        Assertions.assertThrows(InvalidCostException.class, () -> {
            new Matching.MatchingBuilder(UUID.randomUUID())
                    .setCourtId(UUID.randomUUID())
                    .setDate(LocalDate.of(2022, 7, 29))
                    .setStartTime(LocalTime.of(19, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(AnnualType.TWO_YEARS_LESS)
                    .setlevel(new Level(5.0, 2.5))
                    .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                    .setSexOfRecruitment(GenderType.MALE)
                    .setPreferenceGame(PreferenceType.MATCHING)
                    .setNumberOfRecruitment(4)
                    .setCostOfCourtPerPerson(costOfCourtPerPerson)
                    .setStatus(MatchingStatusType.ONGOING)
                    .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                    .setHostId(UUID.randomUUID())
                    .build();
        });
    }

    @Test
    @DisplayName("매칭의 상태는 모집중, 모집 완료, 진행중, 종료 중 하나의 정보를 가진다.")
    void 매칭상태_성공_테스트() {
        Set<MatchingStatusType> statusTypes = new HashSet<>(Arrays.asList(
                MatchingStatusType.ONGOING, MatchingStatusType.OPENING, MatchingStatusType.CLOSING, MatchingStatusType.FINISH
        ));
        //
        Matching matching = new Matching.MatchingBuilder(UUID.randomUUID())
                .setCourtId(UUID.randomUUID())
                .setDate(LocalDate.of(2022, 7, 29))
                .setStartTime(LocalTime.of(19, 0))
                .setEndTime(LocalTime.of(21, 0))
                .setAnnual(AnnualType.TWO_YEARS_LESS)
                .setlevel(new Level(5.0, 2.5))
                .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                .setSexOfRecruitment(GenderType.MALE)
                .setPreferenceGame(PreferenceType.MATCHING)
                .setNumberOfRecruitment(4)
                .setCostOfCourtPerPerson(1.2)
                .setStatus(MatchingStatusType.ONGOING)
                .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                .setHostId(UUID.randomUUID())
                .build();
        //
        Assertions.assertTrue(statusTypes.contains(matching.getStatus()));
    }

    @Test
    @DisplayName("매칭인원이 1명이상 8명이하가 아닐경우, InvalidNumberException이 발생한다.")
    void 매칭인원_실패_테스트() {
        Assertions.assertThrows(InvalidNumberException.class, () ->
                new Matching.MatchingBuilder(UUID.randomUUID())
                        .setCourtId(UUID.randomUUID())
                        .setDate(LocalDate.of(2022, 7, 29))
                        .setStartTime(LocalTime.of(19, 0))
                        .setEndTime(LocalTime.of(21, 0))
                        .setAnnual(AnnualType.TWO_YEARS_LESS)
                        .setlevel(new Level(5.0, 2.5))
                        .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                        .setSexOfRecruitment(GenderType.MALE)
                        .setPreferenceGame(PreferenceType.MATCHING)
                        .setNumberOfRecruitment(10)
                        .setCostOfCourtPerPerson(1.2)
                        .setStatus(MatchingStatusType.ONGOING)
                        .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                        .setHostId(UUID.randomUUID())
                        .build()
        );
    }

    @Test
    @DisplayName("매칭아이디가 올바르지 않을경우, NoMatchingDataException이 발생한다.")
    void 매칭아이디_실패_테스트() {
        //
        UUID id = null;
        //
        Assertions.assertThrows(NoMatchingDataException.class, () -> {
                    new Matching.MatchingBuilder(id)
                            .setCourtId(UUID.randomUUID())
                            .setDate(LocalDate.of(2022, 7, 29))
                            .setStartTime(LocalTime.of(19, 0))
                            .setEndTime(LocalTime.of(21, 0))
                            .setAnnual(AnnualType.TWO_YEARS_LESS)
                            .setlevel(new Level(5.0, 2.5))
                            .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                            .setSexOfRecruitment(GenderType.MALE)
                            .setPreferenceGame(PreferenceType.MATCHING)
                            .setNumberOfRecruitment(6)
                            .setCostOfCourtPerPerson(1.2)
                            .setStatus(MatchingStatusType.ONGOING)
                            .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                            .setHostId(UUID.randomUUID())
                            .build();
                }
        );
    }

    @Test
    @DisplayName("코트 아이디가 올바르지 않을 경우, NoCourtDataException이 발생한다.")
    void 코트아이디_실패_테스트() {
        UUID id = null;
        //
        Assertions.assertThrows(NoCourtDataException.class, () ->
                new Matching.MatchingBuilder(UUID.randomUUID())
                        .setCourtId(id)
                        .setDate(LocalDate.of(2022, 7, 29))
                        .setStartTime(LocalTime.of(19, 0))
                        .setEndTime(LocalTime.of(21, 0))
                        .setAnnual(AnnualType.TWO_YEARS_LESS)
                        .setlevel(new Level(5.0, 2.5))
                        .setAgeOfRecruitment(AgeGroupType.TWENTIES)
                        .setSexOfRecruitment(GenderType.MALE)
                        .setPreferenceGame(PreferenceType.MATCHING)
                        .setNumberOfRecruitment(4)
                        .setCostOfCourtPerPerson(1.2)
                        .setStatus(MatchingStatusType.ONGOING)
                        .setDetails("안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.")
                        .setHostId(UUID.randomUUID())
                        .build()
        );
    }




}