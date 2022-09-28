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

class RecruitmentTest {
    Level maximumLevel = new Level(4.0);
    Level minimumLevel = new Level(1.5);

    @Test
    @DisplayName("참가 도메인 생성 테스트")
    void 모집_생성_테스트() {
        Recruitment recruitment = assertDoesNotThrow(() ->
            new Recruitment(
                1L,
                1L,
                maximumLevel,
                minimumLevel,
                AgeGroupType.TWENTIES,
                GenderType.NONE,
                PreferenceType.RALLY,
                3,
                2.0,
                AnnualType.FIVE_YEARS_LESS,
                "4명이서 랠리해요~",
                RecruitmentStatusType.OPENING
            )
        );

        assertAll(
            () -> assertThat(recruitment.getId()).isEqualTo(1L),
            () -> assertThat(recruitment.getMatchingId()).isEqualTo(1L),
            () -> assertThat(recruitment.getMaximumLevel().getLevel()).isEqualTo(4.0),
            () -> assertThat(recruitment.getMinimumLevel().getLevel()).isEqualTo(1.5),
            () -> assertThat(recruitment.getAgeOfRecruitment()).isEqualTo(AgeGroupType.TWENTIES),
            () -> assertThat(recruitment.getSexOfRecruitment()).isEqualTo(GenderType.NONE),
            () -> assertThat(recruitment.getPreferenceGame()).isEqualTo(PreferenceType.RALLY),
            () -> assertThat(recruitment.getNumberOfRecruitment()).isEqualTo(3),
            () -> assertThat(recruitment.getCostOfCourtPerPerson()).isEqualTo(2.0),
            () -> assertThat(recruitment.getAnnual()).isEqualTo(AnnualType.FIVE_YEARS_LESS),
            () -> assertThat(recruitment.getStatus()).isEqualTo(RecruitmentStatusType.OPENING),
            () -> assertThat(recruitment.getDetails()).isEqualTo("4명이서 랠리해요~")
        );
    }

    @Test
    @DisplayName("인원이 1~8이 아닐 경우, InvalidNumberException 밸생한다.")
    void 모집_인원_테스트() {
        assertAll(
            () -> assertThrows(InvalidNumberException.class, () -> {
                new Recruitment(
                    1L,
                    1L,
                    maximumLevel,
                    minimumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    10,
                    2.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            }),
            () -> assertThrows(InvalidNumberException.class, () -> {
                new Recruitment(
                    1L,
                    1L,
                    maximumLevel,
                    minimumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    0,
                    2.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            }),
            () -> assertThrows(InvalidNumberException.class, () -> {
                new Recruitment(
                    1L,
                    maximumLevel,
                    minimumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    10,
                    2.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            }),
            () -> assertThrows(InvalidNumberException.class, () -> {
                new Recruitment(
                    1L,
                    maximumLevel,
                    minimumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    0,
                    2.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            })
        );
    }

    @Test
    @DisplayName("비용이 0원일 경우, InvalidCostException 발생한다.")
    void 모집_비용_테스트() {
        assertAll(
            () -> assertThrows(InvalidCostException.class, () -> {
                new Recruitment(
                    1L,
                    1L,
                    maximumLevel,
                    minimumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    3,
                    0.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            }),
            () -> assertThrows(InvalidCostException.class, () -> {
                new Recruitment(
                    1L,
                    maximumLevel,
                    minimumLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    3,
                    0.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            })
        );
    }

    @Test
    @DisplayName("최소레벨이 최대레벨보다 클경우, LevelSettingException 발생한다.")
    void 모집_레벨설정_테스트() {
        Level maxLevel = new Level(2.0);
        Level miniLevel = new Level(4.5);
        assertAll(
            () -> assertThrows(LevelSettingException.class, () -> {
                new Recruitment(
                    1L,
                    1L,
                    maxLevel,
                    miniLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    3,
                    2.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            }),
            () -> assertThrows(LevelSettingException.class, () -> {
                new Recruitment(
                    1L,
                    maxLevel,
                    miniLevel,
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    3,
                    2.0,
                    AnnualType.FIVE_YEARS_LESS,
                    "4명이서 랠리해요~",
                    RecruitmentStatusType.OPENING
                );
            })
        );
    }


    @Test
    void chageClosed_Test() {
        Recruitment recruitment = new Recruitment(
            1L,
            1L,
            new Level(4.0),
            new Level(1.5),
            AgeGroupType.TWENTIES,
            GenderType.NONE,
            PreferenceType.RALLY,
            3,
            2.0,
            AnnualType.FIVE_YEARS_LESS,
            "4명이서 랠리해요~",
            RecruitmentStatusType.OPENING
        );

        assertAll(
            () -> assertDoesNotThrow(recruitment::changeClosed),
            () -> assertThat(recruitment.getStatus()).isEqualTo(RecruitmentStatusType.CLOSED)
        );
    }


    @ParameterizedTest
    @EnumSource(value = RecruitmentStatusType.class, names = {"CLOSED"})
    void changeClosed_throwException(RecruitmentStatusType statusType) {
        Recruitment recruitment = new Recruitment(
            1L,
            1L,
            new Level(4.0),
            new Level(1.5),
            AgeGroupType.TWENTIES,
            GenderType.NONE,
            PreferenceType.RALLY,
            3,
            2.0,
            AnnualType.FIVE_YEARS_LESS,
            "4명이서 랠리해요~",
            statusType
        );
        assertThrows(IllegalArgumentException.class, recruitment::changeClosed);
    }

    @Test
    void isClosed_Test() {
        Recruitment recruitment = new Recruitment(
            1L,
            1L,
            new Level(4.0),
            new Level(1.5),
            AgeGroupType.TWENTIES,
            GenderType.NONE,
            PreferenceType.RALLY,
            3,
            2.0,
            AnnualType.FIVE_YEARS_LESS,
            "4명이서 랠리해요~",
            RecruitmentStatusType.CLOSED
        );

        assertTrue(recruitment::isClosed);

    }
}
