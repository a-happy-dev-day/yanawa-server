package fashionable.simba.yanawaserver.matching.domain;


import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.error.MatchingTimeException;
import fashionable.simba.yanawaserver.matching.error.NoCourtDataException;
import fashionable.simba.yanawaserver.matching.error.NoMatchingDataException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Matching {
    private Long id;
    private Long hostId;
    private Long courtId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private MatchingStatusType status;

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

    public Matching(Builder builder) {
        if (builder.id == null) {
            throw new NoMatchingDataException();
        }
        if (builder.courtId == null) {
            throw new NoCourtDataException();
        }
        if (builder.startTime.isAfter(builder.endTime)) {
            throw new MatchingTimeException();
        }

        this.id = builder.id;
        this.courtId = builder.courtId;
        this.hostId = builder.hostId;
        this.date = builder.date;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private Long courtId;
        private Long hostId;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private MatchingStatusType status;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder courtId(Long courtId) {
            this.courtId = courtId;
            return this;
        }

        public Builder hostId(Long hostId) {
            this.hostId = hostId;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder status(MatchingStatusType status) {
            this.status = status;
            return this;
        }

        public Matching build() {
            return new Matching(this);
        }
    }
}
