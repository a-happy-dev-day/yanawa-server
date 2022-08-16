package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.error.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static fashionable.simba.yanawaserver.fixture.MatchingFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MatchingTest {

    @Test
    @DisplayName("요구사항에 맞는 값이 들어가면 매칭이 생성된다.")
    void 매칭생성_성공테스트() {
        Matching matching = assertDoesNotThrow(() ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(FIXTURE_COURT_ID)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );

        assertAll(
                () -> assertThat(matching.getMatchingId()).isEqualTo(FIXTURE_MATCHING_ID),
                () -> assertThat(matching.getCourtId()).isEqualTo(FIXTURE_COURT_ID),
                () -> assertThat(matching.getHostId()).isEqualTo(FIXTURE_HOST_ID),
                () -> assertThat(matching.getDate()).isEqualTo(FIXTURE_DATE),
                () -> assertThat(matching.getStartTime()).isEqualTo(FIXTURE_START_TIME),
                () -> assertThat(matching.getEndTime()).isEqualTo(FIXTURE_END_TIME),
                () -> assertThat(matching.getAnnual()).isEqualTo(FIXTURE_ANNUAL_TYPE),
                () -> assertThat(matching.getMaximumLevel()).isEqualTo(FIXTURE_MAXIMUM_LEVEL),
                () -> assertThat(matching.getMinimumLevel()).isEqualTo(FIXTURE_MINIMUM_LEVEL),
                () -> assertThat(matching.getSexOfRecruitment()).isEqualTo(FIXTURE_GENDER_TYPE),
                () -> assertThat(matching.getPreferenceGame()).isEqualTo(FIXTURE_PREFERENCE_TYPE),
                () -> assertThat(matching.getNumberOfRecruitment()).isEqualTo(FIXTURE_NUMBER_OF_RECRUITMENT),
                () -> assertThat(matching.getCostOfCourtPerPerson()).isEqualTo(FIXTURE_COST_OF_COURT_PER_PERSON),
                () -> assertThat(matching.getStatus()).isEqualTo(FIXTURE_MATCHING_STATUS_TYPE),
                () -> assertThat(matching.getDetails()).isEqualTo(FIXTURE_DETAILS)
        );
    }

    @Test
    @DisplayName("시작 시간은 종료 시간보다 늦을 경우, MatchingTimeException이 발생한다.")
    void timeTest() {
        //
        assertThrows(MatchingTimeException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(FIXTURE_MATCHING_ID)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(LocalTime.of(23, 0))
                    .setEndTime(LocalTime.of(21, 0))
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                    .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                    .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
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
        Matching matching = assertDoesNotThrow(() ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(FIXTURE_COURT_ID)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );
        //
        assertTrue(annualTypes.contains(matching.getAnnual()));
    }

    @Test
    @DisplayName("레벨이 0 ~ 6 범위를 벗어나면, LevelSettingException이 발생한다.")
    void 레벨_실패_테스트1() {
        assertThrows(LevelSettingException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(FIXTURE_MATCHING_ID)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(FIXTURE_START_TIME)
                    .setEndTime(FIXTURE_END_TIME)
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                    .setMinimumLevel(new Level(8.0))
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                    .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                    .build();
        });
    }

    @Test
    @DisplayName("레벨이 0.5단위가 아닐경우, LevelSettingException이 발생한다.")
    void 레벨_실패_테스트2() {
        assertThrows(LevelSettingException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(FIXTURE_MATCHING_ID)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(FIXTURE_START_TIME)
                    .setEndTime(FIXTURE_END_TIME)
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                    .setMinimumLevel(new Level(5.3))
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                    .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                    .build();
        });
    }

    @Test
    @DisplayName("최소레벨이 최대레벨보다 클 경우, IllegalArgumentException이 발생한다.")
    void 레벨_실패_테스트3() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(FIXTURE_MATCHING_ID)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(FIXTURE_START_TIME)
                    .setEndTime(FIXTURE_END_TIME)
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(new Level(1.0))
                    .setMinimumLevel(new Level(3.0))
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                    .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
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
        Matching matching = assertDoesNotThrow(() ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(FIXTURE_COURT_ID)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );
        //
        assertTrue(ageGroupTypes.contains(matching.getAgeOfRecruitment()));
    }

    @Test
    @DisplayName("모집 성별은 남자만, 여자만, 상관없음 중 하나의 정보를 가진다.")
    void 성별_성공_테스트() {
        Set<GenderType> genderTypes = new HashSet<>(Arrays.asList(
                GenderType.MALE, GenderType.FEMALE, GenderType.NONE));
        //
        Matching matching = assertDoesNotThrow(() ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(FIXTURE_COURT_ID)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );
        //
        assertTrue(genderTypes.contains(matching.getSexOfRecruitment()));
    }

    @Test
    @DisplayName("선호게임은 매칭과 렐리중 하나의 정보를 가진다.")
    void 선호게임_성공_테스트() {
        Set<PreferenceType> preferenceTypes = new HashSet<>(Arrays.asList(
                PreferenceType.MATCHING, PreferenceType.RALLY
        ));
        //
        Matching matching = assertDoesNotThrow(() ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(FIXTURE_COURT_ID)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );
        //
        assertTrue(preferenceTypes.contains(matching.getPreferenceGame()));
    }

    @ParameterizedTest
    @DisplayName("모집 인원은 1명이상 8명이하가 아닐경우, InvalidNumberException이 발생한다.")
    @ValueSource(ints = {0, 9})
    void 모집인원_실패_테스트(int numberOfRecruitment) {
        assertThrows(InvalidNumberException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(FIXTURE_MATCHING_ID)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(FIXTURE_START_TIME)
                    .setEndTime(FIXTURE_END_TIME)
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                    .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(numberOfRecruitment)
                    .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                    .build();
        });
    }

    @Test
    @DisplayName("코트 비용은 0원보다 작을경우, InvalidCostException이 발생한다.")
    void 코트비용_실패_테스트() {
        //
        Double costOfCourtPerPerson = -3.0;
        //
        assertThrows(InvalidCostException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(FIXTURE_MATCHING_ID)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(FIXTURE_START_TIME)
                    .setEndTime(FIXTURE_END_TIME)
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                    .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                    .setCostOfCourtPerPerson(costOfCourtPerPerson)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
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
        Matching matching = assertDoesNotThrow(() ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(FIXTURE_COURT_ID)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );
        //
        assertTrue(statusTypes.contains(matching.getStatus()));
    }

    @Test
    @DisplayName("매칭아이디가 올바르지 않을경우, NoMatchingDataException이 발생한다.")
    void 매칭아이디_실패_테스트() {
        //
        Long id = null;
        //
        assertThrows(NoMatchingDataException.class, () -> {
            new Matching.MatchingBuilder()
                    .setMatchingId(id)
                    .setCourtId(FIXTURE_COURT_ID)
                    .setHostId(FIXTURE_HOST_ID)
                    .setDate(FIXTURE_DATE)
                    .setStartTime(FIXTURE_START_TIME)
                    .setEndTime(FIXTURE_END_TIME)
                    .setAnnual(FIXTURE_ANNUAL_TYPE)
                    .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                    .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                    .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                    .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                    .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                    .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                    .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                    .setDetails(FIXTURE_DETAILS)
                    .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                    .build();
        });
    }

    @Test
    @DisplayName("코트 아이디가 올바르지 않을 경우, NoCourtDataException이 발생한다.")
    void 코트아이디_실패_테스트() {
        Long id = null;
        //
        assertThrows(NoCourtDataException.class, () ->
                new Matching.MatchingBuilder()
                        .setMatchingId(FIXTURE_MATCHING_ID)
                        .setCourtId(id)
                        .setHostId(FIXTURE_HOST_ID)
                        .setDate(FIXTURE_DATE)
                        .setStartTime(FIXTURE_START_TIME)
                        .setEndTime(FIXTURE_END_TIME)
                        .setAnnual(FIXTURE_ANNUAL_TYPE)
                        .setMaximumLevel(FIXTURE_MAXIMUM_LEVEL)
                        .setMinimumLevel(FIXTURE_MINIMUM_LEVEL)
                        .setAgeOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
                        .setSexOfRecruitment(FIXTURE_GENDER_TYPE)
                        .setPreferenceGame(FIXTURE_PREFERENCE_TYPE)
                        .setNumberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
                        .setCostOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
                        .setDetails(FIXTURE_DETAILS)
                        .setStatus(FIXTURE_MATCHING_STATUS_TYPE)
                        .build()
        );
    }
}
