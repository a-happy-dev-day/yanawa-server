package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.error.InvalidCostException;
import fashionable.simba.yanawaserver.matching.error.InvalidNumberException;
import fashionable.simba.yanawaserver.matching.error.LevelSettingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecruitmentTest {

    @Test
    @DisplayName("참가 도메인 생성 테스트")
    void 모집_생성_테스트() {
        //
        Level maximumLevel = new Level(4.0);
        Level minimumLevel = new Level(1.5);
        //
        Recruitment recruitment = assertDoesNotThrow(() ->
                new Recruitment.Builder()
                        .id(1L)
                        .matchingId(1L)
                        .maximumLevel(maximumLevel)
                        .minimumLevel(minimumLevel)
                        .ageOfRecruitment(AgeGroupType.TWENTIES)
                        .sexOfRecruitment(GenderType.NONE)
                        .preferenceGame(PreferenceType.RALLY)
                        .numberOfRecruitment(4)
                        .costOfCourtPerPerson(2.0)
                        .annual(AnnualType.FIVE_YEARS_LESS)
                        .status(RecruitmentStatusType.OPENING)
                        .details("4명이서 랠리해요~")
                        .build()
        );

        assertAll(
                () -> assertThat(recruitment.getId()).isEqualTo(1L),
                () -> assertThat(recruitment.getMatchingId()).isEqualTo(1L),
                () -> assertThat(recruitment.getMaximumLevel()).isEqualTo(maximumLevel),
                () -> assertThat(recruitment.getMinimumLevel()).isEqualTo(minimumLevel),
                () -> assertThat(recruitment.getAgeOfRecruitment()).isEqualTo(AgeGroupType.TWENTIES),
                () -> assertThat(recruitment.getSexOfRecruitment()).isEqualTo(GenderType.NONE),
                () -> assertThat(recruitment.getPreferenceGame()).isEqualTo(PreferenceType.RALLY),
                () -> assertThat(recruitment.getNumberOfRecruitment()).isEqualTo(4),
                () -> assertThat(recruitment.getCostOfCourtPerPerson()).isEqualTo(2.0),
                () -> assertThat(recruitment.getAnnual()).isEqualTo(AnnualType.FIVE_YEARS_LESS),
                () -> assertThat(recruitment.getStatus()).isEqualTo(RecruitmentStatusType.OPENING),
                () -> assertThat(recruitment.getDetails()).isEqualTo("4명이서 랠리해요~")
        );
    }

    @Test
    @DisplayName("인원이 1~8이 아닐 경우, InvalidNumberException 밸생한다.")
    void 모집_인원_테스트() {
        assertThrows(InvalidNumberException.class, () -> {
            new Recruitment.Builder()
                    .id(1L)
                    .matchingId(1L)
                    .maximumLevel(new Level(4.0))
                    .minimumLevel(new Level(1.5))
                    .ageOfRecruitment(AgeGroupType.TWENTIES)
                    .sexOfRecruitment(GenderType.NONE)
                    .preferenceGame(PreferenceType.RALLY)
                    .numberOfRecruitment(10)
                    .costOfCourtPerPerson(2.0)
                    .annual(AnnualType.FIVE_YEARS_LESS)
                    .details("4명이서 랠리해요~")
                    .build();
        });
        assertThrows(InvalidNumberException.class, () -> {
            new Recruitment.Builder()
                    .id(1L)
                    .matchingId(1L)
                    .maximumLevel(new Level(4.0))
                    .minimumLevel(new Level(1.5))
                    .ageOfRecruitment(AgeGroupType.TWENTIES)
                    .sexOfRecruitment(GenderType.NONE)
                    .preferenceGame(PreferenceType.RALLY)
                    .numberOfRecruitment(0)
                    .costOfCourtPerPerson(2.0)
                    .annual(AnnualType.FIVE_YEARS_LESS)
                    .details("4명이서 랠리해요~")
                    .build();
        });
    }

    @Test
    @DisplayName("비용이 0원일 경우, InvalidCostException 발생한다.")
    void 모집_비용_테스트() {
        assertThrows(InvalidCostException.class, () -> {
            new Recruitment.Builder()
                    .id(1L)
                    .matchingId(1L)
                    .maximumLevel(new Level(4.0))
                    .minimumLevel(new Level(1.5))
                    .ageOfRecruitment(AgeGroupType.TWENTIES)
                    .sexOfRecruitment(GenderType.NONE)
                    .preferenceGame(PreferenceType.RALLY)
                    .numberOfRecruitment(4)
                    .costOfCourtPerPerson(0.0)
                    .annual(AnnualType.FIVE_YEARS_LESS)
                    .details("4명이서 랠리해요~")
                    .build();
        });
    }

    @Test
    @DisplayName("최소레벨이 최대레벨보다 클경우, LevelSettingException 발생한다.")
    void 모집_레벨설정_테스트() {
        assertThrows(LevelSettingException.class, () -> {
            new Recruitment.Builder()
                    .id(1L)
                    .matchingId(1L)
                    .maximumLevel(new Level(2.0))
                    .minimumLevel(new Level(5.5))
                    .ageOfRecruitment(AgeGroupType.TWENTIES)
                    .sexOfRecruitment(GenderType.NONE)
                    .preferenceGame(PreferenceType.RALLY)
                    .numberOfRecruitment(4)
                    .costOfCourtPerPerson(2.0)
                    .annual(AnnualType.FIVE_YEARS_LESS)
                    .details("4명이서 랠리해요~")
                    .build();
        });

    }

    @Test
    @DisplayName("레벨이 0~6사이의 0.5단위로 설정되지 않을경우, LevelSettingException 발생한다.")
    void 모집_레벨설정_테스트2() {
        assertThrows(LevelSettingException.class, () -> {
            new Recruitment.Builder()
                    .id(1L)
                    .matchingId(1L)
                    .maximumLevel(new Level(5.2))
                    .minimumLevel(new Level(1.0))
                    .ageOfRecruitment(AgeGroupType.TWENTIES)
                    .sexOfRecruitment(GenderType.NONE)
                    .preferenceGame(PreferenceType.RALLY)
                    .numberOfRecruitment(4)
                    .costOfCourtPerPerson(2.0)
                    .annual(AnnualType.FIVE_YEARS_LESS)
                    .details("4명이서 랠리해요~")
                    .build();
        });
    }

    @Test
    void chageClosed_Test() {
        Recruitment recruitment = new Recruitment.Builder()
                .id(1L)
                .matchingId(1L)
                .maximumLevel(new Level(5.0))
                .minimumLevel(new Level(1.0))
                .ageOfRecruitment(AgeGroupType.TWENTIES)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.RALLY)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.FIVE_YEARS_LESS)
                .status(RecruitmentStatusType.OPENING)
                .details("4명이서 랠리해요~")
                .build();

        assertAll(
                () -> assertDoesNotThrow(recruitment::changeClosed),
                () -> assertThat(recruitment.getStatus()).isEqualTo(RecruitmentStatusType.CLOSED)
        );
    }


    @ParameterizedTest
    @EnumSource(value = RecruitmentStatusType.class, names = {"CLOSED"})
    void changeClosed_throwException(RecruitmentStatusType statusType) {
        Recruitment recruitment = new Recruitment.Builder()
                .id(1L)
                .matchingId(1L)
                .maximumLevel(new Level(5.0))
                .minimumLevel(new Level(1.0))
                .ageOfRecruitment(AgeGroupType.TWENTIES)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.RALLY)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.FIVE_YEARS_LESS)
                .status(statusType)
                .details("4명이서 랠리해요~")
                .build();
        assertThrows(IllegalArgumentException.class, recruitment::changeClosed);
    }

    @Test
    void isClosed_Test() {
        Recruitment recruitment = new Recruitment.Builder()
                .id(1L)
                .matchingId(1L)
                .maximumLevel(new Level(5.0))
                .minimumLevel(new Level(1.0))
                .ageOfRecruitment(AgeGroupType.TWENTIES)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.RALLY)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.FIVE_YEARS_LESS)
                .status(RecruitmentStatusType.CLOSED)
                .details("4명이서 랠리해요~")
                .build();

        assertTrue(recruitment::isClosed);

    }
}
