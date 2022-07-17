package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.RequestStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MatchingRequestTest {
    @Test
    @DisplayName("매칭요청 생성 테스트")
    void 매칭요청_생성_Test() {
        //
        UUID userId = UUID.randomUUID();
        UUID hostId = UUID.randomUUID();

        //
        MatchingRequest matchingRequest = new MatchingRequest.MatchingRequestBuilder(
            userId, hostId)
        .setRequestDateTime(LocalDateTime.now())
        .setStatus(RequestStatusType.ACCEPTED)
        .build();
        //
        Assertions.assertEquals(userId,matchingRequest.getUserId());
        Assertions.assertEquals(hostId,matchingRequest.getHostId());
        Assertions.assertEquals(LocalDateTime.now(),matchingRequest.getRequestDateTime());
        Assertions.assertEquals(RequestStatusType.ACCEPTED,matchingRequest.getStatus());
    }

    @Test
    @DisplayName("매칭 요청 상태는 대기중, 수락, 거절, 만료 중 하나의 정보를 가진다")
    void 매칭요청_상태_Test() {
        //
        Set<RequestStatusType> requestStatusTypes = new HashSet<>(Arrays.asList(
                RequestStatusType.ACCEPTED, RequestStatusType.WAITING, RequestStatusType.REJECTED, RequestStatusType.EXPIRED
        ));
        //
        MatchingRequest matchingRequest = new MatchingRequest.MatchingRequestBuilder(
                UUID.randomUUID(), UUID.randomUUID())
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.ACCEPTED)
                .build();
        //
        Assertions.assertTrue(requestStatusTypes.contains(matchingRequest.getStatus()));
    }
}
