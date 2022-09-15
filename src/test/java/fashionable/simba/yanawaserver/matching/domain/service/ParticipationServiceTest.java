package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipationServiceTest {
//    ParticipationService participationService = new ParticipationService(participationRepository);
//
//    @BeforeEach
//    void setUp() {
//        Recruitment recruitment = new Recruitment(1L, new Level(4.0), new Level(1.5), AgeGroupType.TWENTIES, GenderType.NONE,
//                PreferenceType.RALLY, 3, 2.0, AnnualType.FIVE_YEARS_LESS, "4명이서 랠리해요~", RecruitmentStatusType.OPENING);
//
//    }

    @DisplayName("사용자가 참여를 요청한다.")
    @Test
    void createParticipationTest() {

    }

    @DisplayName("진행자가 참여를 수락한다.")
    @Test
    void acceptParticipationTest() {

    }

    @DisplayName("진행자가 참여를 거절한다.")
    @Test
    void rejectParticipationTest() {

    }
}
