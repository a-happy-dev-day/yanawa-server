package fashionable.simba.yanawaserver.matching.domain;


import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.error.MatchingTimeException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hostId;
    private Long courtId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private MatchingStatusType status;

    protected Matching() {
        /*no-op*/
    }

    public Long getId() {
        return id;
    }

    public Long getHostId() {
        return hostId;
    }

    public Long getCourtId() {
        return courtId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public MatchingStatusType getStatus() {
        return status;
    }

    public void changeOngoing() {
        if (!this.status.equals(MatchingStatusType.WAITING)) {
            throw new IllegalArgumentException("기다리는 상황에서만 진행할 수 있습니다.");
        }
        this.status = MatchingStatusType.ONGOING;
    }

    public void changeFinished() {
        if (!this.status.equals(MatchingStatusType.ONGOING)) {
            throw new IllegalArgumentException("진행중인 상황에서만 종료할 수 있습니다.");
        }
        this.status = MatchingStatusType.FINISHED;
    }

    public Matching(Long id, Long hostId, Long courtId, LocalDate date, LocalTime startTime, LocalTime endTime, MatchingStatusType status) {
        if (startTime.isAfter(endTime)) {
            throw new MatchingTimeException("시작시간이 종료시간보다 늦을 수 없습니다.");
        }
        this.id = id;
        this.hostId = hostId;
        this.courtId = courtId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Matching(Long hostId, Long courtId, LocalDate date, LocalTime startTime, LocalTime endTime, MatchingStatusType status) {
        if (startTime.isAfter(endTime)) {
            throw new MatchingTimeException("시작시간이 종료시간보다 늦을 수 없습니다.");
        }
        this.hostId = hostId;
        this.courtId = courtId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
