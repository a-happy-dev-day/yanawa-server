package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.*;
import fashionable.simba.yanawaserver.error.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class MatchingTest {
    @Test
    @DisplayName("시작 시간은 종료 시간보다 늦을 경우, MatchingTimeException이 발생한다.")
    void timeTest() {
        //given
        //then
        Assertions.assertThrows(MatchingTimeException.class, () -> {
            Matching matching = new Matching(
                    UUID.randomUUID(),
                    new Court(UUID.randomUUID(), "서울테니스장", "서울"),
                    LocalDate.now(),
                    LocalTime.now(),
                    LocalTime.now().minusHours(1),
                    AnnualType.TWO_YEARS_LESS,
                    2.0,
                    3.0,
                    AgeGroupType.FORTIES,
                    GenderType.MALE,
                    PreferenceType.MATCHING,
                    4,
                    4.0,
                    "안녕하세요.",
                    StatusType.ONGOING,
                    UUID.randomUUID()
            );
        });
    }

    @Test
    @DisplayName("최소 레벨이 최대 레벨보다 클 경우, LevelSettingException이 발생한다.")
    void 구력설정_테스트() {
        //given
        Double minimumLevel = 3.0;
        Double maximumLevel = 2.5;
        //then
        Assertions.assertThrows(LevelSettingException.class, () -> {
            Matching matching = new Matching(
                    UUID.randomUUID(),
                    new Court(UUID.randomUUID(), "서울테니스장", "서울"),
                    LocalDate.now(),
                    LocalTime.now(),
                    LocalTime.now().plusHours(1),
                    AnnualType.TWO_YEARS_LESS,
                    minimumLevel,
                    maximumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.MALE,
                    PreferenceType.MATCHING,
                    4,
                    4.0,
                    "안녕하세요.",
                    StatusType.ONGOING,
                    UUID.randomUUID()
            );
        });
    }

    @Test
    @DisplayName("모집 인원이 1명이상 8명이하가 아닐경우, InvaildNumberException이 발생한다.")
    void 모집인원_테스트() {
        //given
        Integer numberOfRecruitment = 10;
        //then
        Assertions.assertThrows(InvaildNumberException.class, () -> {
            Matching matching = new Matching(
                    UUID.randomUUID(),
                    new Court(UUID.randomUUID(), "서울테니스장", "서울"),
                    LocalDate.now(),
                    LocalTime.now(),
                    LocalTime.now().plusHours(1),
                    AnnualType.TWO_YEARS_LESS,
                    2.0,
                    4.0,
                    AgeGroupType.TWENTIES,
                    GenderType.MALE,
                    PreferenceType.MATCHING,
                    numberOfRecruitment,
                    4.0,
                    "안녕하세요.",
                    StatusType.ONGOING,
                    UUID.randomUUID()
            );
        });
    }

    @Test
    @DisplayName("코트 비용이 0원 미만일 경우, InvaildCostException이 발생한다.")
    void 코트비용_테스트() {
        //given
        Double costOfCourtPerPerson = 0.0;
        //then
        Assertions.assertThrows(InvaildCostException.class, () -> {
            Matching matching = new Matching(
                    UUID.randomUUID(),
                    new Court(UUID.randomUUID(), "서울테니스장", "서울"),
                    LocalDate.now(),
                    LocalTime.now(),
                    LocalTime.now().plusHours(1),
                    AnnualType.TWO_YEARS_LESS,
                    2.0,
                    4.0,
                    AgeGroupType.TWENTIES,
                    GenderType.MALE,
                    PreferenceType.MATCHING,
                    5,
                    costOfCourtPerPerson,
                    "안녕하세요.",
                    StatusType.ONGOING,
                    UUID.randomUUID()
            );
        });
    }

    @Test
    @DisplayName("코트장 이름을 작성하지 않을경우, InvaildCourtNameException이 발생한다.")
    void 코트장이름_테스트() {
        //given
        //then
        Assertions.assertThrows(InvaildCourtNameException.class, () -> {
            Matching matching = new Matching(
                    UUID.randomUUID(),
                    new Court(UUID.randomUUID(), "", "서울"),
                    LocalDate.now(),
                    LocalTime.now(),
                    LocalTime.now().plusHours(1),
                    AnnualType.TWO_YEARS_LESS,
                    2.0,
                    4.0,
                    AgeGroupType.TWENTIES,
                    GenderType.MALE,
                    PreferenceType.MATCHING,
                    5,
                    4.0,
                    "안녕하세요.",
                    StatusType.ONGOING,
                    UUID.randomUUID()
            );
        });
    }
}
