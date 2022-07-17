package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.*;
import fashionable.simba.yanawaserver.error.InvaildCostException;
import fashionable.simba.yanawaserver.error.InvaildNumberException;
import fashionable.simba.yanawaserver.error.LevelSettingException;
import fashionable.simba.yanawaserver.error.MatchingTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchingTest {
    //
    UUID id = UUID.randomUUID();
    UUID courtId = UUID.randomUUID();
    LocalDate date = LocalDate.now();
    LocalTime startTime = LocalTime.now();
    LocalTime endTime = LocalTime.now().plusHours(2);
    AnnualType annual = AnnualType.TWO_YEARS_LESS;
    Double minimumLevel = 2.5;
    Double maximumLevel = 5.0;
    AgeGroupType ageOfRecruitment = AgeGroupType.TWENTIES;
    GenderType sexOfRecruitment = GenderType.MALE;
    PreferenceType preferenceGame = PreferenceType.MATCHING;
    Integer numberOfRecruitment = 4;
    Double costOfCourtPerPerson = 2.5;
    MatchingStatusType status = MatchingStatusType.ONGOING;
    UUID hostId = UUID.randomUUID();
    String details = "안녕하세요 부산테니스장에서 매치하실분 4명 구합니다.";

    @Test
    @DisplayName("매칭생성 테스트")
    void 매칭생성_테스트() {
        //
        Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status ,hostId)
                .setDetails(details)
                .build();
        //
    }

    @Test
    @DisplayName("시작 시간은 종료 시간보다 늦을 경우, MatchingTimeException이 발생한다.")
    void timeTest() {
        //
        LocalTime sTime = LocalTime.now();
        LocalTime eTime = LocalTime.now().minusHours(1);
        //
        Assertions.assertThrows(MatchingTimeException.class, () -> {
            Matching matching = new Matching
                    .MatchingBuilder(id, courtId, date, sTime, eTime, annual, minimumLevel,
                    maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                    numberOfRecruitment, costOfCourtPerPerson, status ,hostId)
                    .setDetails(details)
                    .build();
        });
    }

    @Test
    @DisplayName("구력은 2년, 2~5년, 5년이상 중 하나의 정보를 가진다.")
    void Test() {
        //
        Set<AnnualType> annualTypes = new HashSet<>();
        annualTypes.add(AnnualType.TWO_YEARS_LESS);
        annualTypes.add(AnnualType.FIVE_YEARS_LESS);
        annualTypes.add(AnnualType.FIVE_YEARS_MORE);
        annual = AnnualType.FIVE_YEARS_LESS;
        //
        Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status ,hostId)
                .setDetails(details)
                .build();
        //
        Assertions.assertTrue(annualTypes.contains(matching.getAnnual()));
    }

    @Test
    @DisplayName("레벨은 0.5단위로 0 ~ 6이 아니면, LevelSettingException이 발생한다.")
    void 레벨_테스트() {
        //
        double minimumLevel = 1.0;
        double maximumLevel = 7.0;
        //
        Assertions.assertThrows(LevelSettingException.class, () -> {
            Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                    maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                    numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                .setDetails(details)
                .build();
                });
        //
    }

    @Test
    @DisplayName("최소 레벨이 최대 레벨보다 클 경우, LevelSettingException이 발생한다.")
    void 레벨_실패_테스트() {
        //
        double minimumLevel = 3.0;
        double maximumLevel = 0.5;
        //
        Assertions.assertThrows(LevelSettingException.class, () -> {
            Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                    maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                    numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                .setDetails(details)
                .build();
        });
    }

    @Test
    @DisplayName("모집 연령은 20대, 30대, 40대, 50대 이상, 무관 중 하나의 정보를 가진다.")
    void ageOfRecruitment_테스트() {
        //
        Set<AgeGroupType> ageGroupTypes = new HashSet<>();
        ageGroupTypes.add(AgeGroupType.TWENTIES);
        ageGroupTypes.add(AgeGroupType.THIRTIES);
        ageGroupTypes.add(AgeGroupType.FORTIES);
        ageGroupTypes.add(AgeGroupType.FIFTIES);
        ageGroupTypes.add(AgeGroupType.ETC);
        //
        Matching matching = new Matching
            .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status, hostId)
            .setDetails(details)
            .build();
        //
        Assertions.assertTrue(ageGroupTypes.contains(matching.getAgeOfRecruitment()));
    }

    @Test
    @DisplayName("모집 성별은 남자만, 여자만, 상관없음 중 하나의 정보를 가진다.")
    void gender_테스트() {
        Set<GenderType> genderTypes = new HashSet<>(Arrays.asList(
                GenderType.MALE, GenderType.FEMALE, GenderType.NONE));
        //
        Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                .setDetails(details)
                .build();
        //
        Assertions.assertTrue(genderTypes.contains(matching.getSexOfRecruitment()));
    }

    @Test
    @DisplayName("선호게임은 매칭과 렐리중 하나의 정보를 가진다.")
    void preferenceGame_테스트() {
        Set<PreferenceType> preferenceTypes = new HashSet<>(Arrays.asList(
                PreferenceType.MATCHING, PreferenceType.RALLY
        ));
        //
        Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                .setDetails(details)
                .build();
        //
        Assertions.assertTrue(preferenceTypes.contains(matching.getPreferenceGame()));
    }

    @Test
    @DisplayName("모집 인원은 1명이상 8명이하가 아닐경우, InvaildNumberException이 발생한다.")
    void numberofRecruiment_테스트() {
        //
        Integer numberOfRecruitment = 10;
        //
        Assertions.assertThrows(InvaildNumberException.class, () -> {
            Matching matching = new Matching
                    .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                    maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                    numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                    .setDetails(details)
                    .build();
        });
    }

    @Test
    @DisplayName("코트 비용은 0원보다 작을경우, InvaildCostException이 발생한다.")
    void costOfCourt_테스트() {
        //
        Double costOfCourtPerPerson = -3.0;
        //
        Assertions.assertThrows(InvaildCostException.class, () -> {
            Matching matching = new Matching
                    .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                    maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                    numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                    .setDetails(details)
                    .build();
        });
    }

    @Test
    @DisplayName("매칭 상세 설명은 작성해도 되고, 작성하지 않아도 된다.")
    void details_테스트() {
        Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                .build();
        Assertions.assertEquals(null,matching.getDetails());
    }

    @Test
    @DisplayName("매칭의 상태는 모집중, 모집 완료, 진행중, 종료 중 하나의 정보를 가진다.")
    void status_테스트() {
        Set<MatchingStatusType> statusTypes = new HashSet<>(Arrays.asList(
                MatchingStatusType.ONGOING, MatchingStatusType.OPENING, MatchingStatusType.CLOSING, MatchingStatusType.FINISH
        ));
        //
        Matching matching = new Matching
                .MatchingBuilder(id, courtId, date, startTime, endTime, annual, minimumLevel,
                maximumLevel, ageOfRecruitment, sexOfRecruitment, preferenceGame,
                numberOfRecruitment, costOfCourtPerPerson, status, hostId)
                .build();
        //
        Assertions.assertTrue(statusTypes.contains(matching.getStatus()));
    }
}
