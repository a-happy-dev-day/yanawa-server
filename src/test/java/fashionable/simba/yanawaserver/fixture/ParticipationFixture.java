package fashionable.simba.yanawaserver.fixture;


import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;

import java.time.LocalDateTime;

public class ParticipationFixture {
    public static final Participation fixtureParticipation1 = new Participation.Builder()
            .id(1L)
            .userId(1L)
            .matchingId(1L)
            .requestDateTime(LocalDateTime.now())
            .status(ParticipationStatusType.ACCEPTED)
            .build();
    public static final Participation fixtureParticipation2 = new Participation.Builder()
            .id(2L)
            .userId(2L)
            .matchingId(1L)
            .requestDateTime(LocalDateTime.now())
            .status(ParticipationStatusType.ACCEPTED)
            .build();
    public static final Participation fixtureParticipation3 = new Participation.Builder()
            .id(3L)
            .userId(3L)
            .matchingId(1L)
            .requestDateTime(LocalDateTime.now())
            .status(ParticipationStatusType.ACCEPTED)
            .build();


}
