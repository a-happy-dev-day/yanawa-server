package fashionable.simba.yanawaserver.fixture;


import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;

import java.time.LocalDateTime;

public class ParticipationFixture {
    public static final Participation fixtureParticipation1 = new Participation.Builder(1L, 1L, 1L)
            .requestDateTime(LocalDateTime.now()).status(RequestStatusType.ACCEPTED).build();
    public static final Participation fixtureParticipation2 = new Participation.Builder(2L, 11L, 1L)
            .requestDateTime(LocalDateTime.now()).status(RequestStatusType.ACCEPTED).build();
    public static final Participation fixtureParticipation3 = new Participation.Builder(3L, 3L, 1L)
            .requestDateTime(LocalDateTime.now()).status(RequestStatusType.ACCEPTED).build();


}
