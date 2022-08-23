package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MatchingApplyTest {
    @Test
    @DisplayName("매칭신청 생성 테스트")
    void 매칭신청_생성_Test() {
        //
        Long id = 1L;
        Long userId = 1L;
        Long matchingId = 1L;
        LocalDateTime now = LocalDateTime.now();

        //
        MatchingApply matchingRequest = new MatchingApply.MatchingRequestBuilder(
                id, userId, matchingId)
                .setRequestDateTime(now)
                .setStatus(RequestStatusType.ACCEPTED)
                .build();
        //
        Assertions.assertEquals(userId, matchingRequest.getUserId());
        Assertions.assertEquals(matchingId, matchingRequest.getMatchingId());
        Assertions.assertEquals(now, matchingRequest.getRequestDateTime());
        Assertions.assertEquals(RequestStatusType.ACCEPTED, matchingRequest.getStatus());
    }

    @Test
    @DisplayName("매칭 요청 상태는 대기중, 수락, 거절, 만료 중 하나의 정보를 가진다")
    void 매칭신청_상태_Test() {
        //
        Set<RequestStatusType> requestStatusTypes = new HashSet<>(Arrays.asList(
                RequestStatusType.ACCEPTED, RequestStatusType.WAITING, RequestStatusType.REJECTED, RequestStatusType.EXPIRED
        ));
        //
        MatchingApply matchingRequest = new MatchingApply.MatchingRequestBuilder(
                1L, 1L, 1L)
                .setRequestDateTime(LocalDateTime.now())
                .setStatus(RequestStatusType.ACCEPTED)
                .build();
        //
        Assertions.assertTrue(requestStatusTypes.contains(matchingRequest.getStatus()));
    }
}
