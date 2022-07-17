package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.*;
import fashionable.simba.yanawaserver.error.InvaildCostException;
import fashionable.simba.yanawaserver.error.InvaildNumberException;
import fashionable.simba.yanawaserver.error.LevelSettingException;
import fashionable.simba.yanawaserver.error.MatchingTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Matching {
    // required parameters
    private final UUID matchingId;
    private final Court courtId;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final AnnualType annual;
    private final Double minimumLevel;
    private final Double maximumLevel;
    private final AgeGroupType ageOfRecruitment;
    private final GenderType sexOfRecruitment;
    private final PreferenceType preferenceGame;
    private final Integer numberOfRecruitment;
    private final Double costOfCourtPerPerson;
    private final MatchingStatusType status;
    private final UUID hostId;
    // optional parameters
    private final String details;


    public UUID getMatchingId() {
        return matchingId;
    }

    public Court getCourtId() {
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

    public AnnualType getAnnual() {
        return annual;
    }

    public Double getMinimumLevel() {
        return minimumLevel;
    }

    public Double getMaximumLevel() {
        return maximumLevel;
    }

    public AgeGroupType getAgeOfRecruitment() {
        return ageOfRecruitment;
    }

    public GenderType getSexOfRecruitment() {
        return sexOfRecruitment;
    }

    public PreferenceType getPreferenceGame() {
        return preferenceGame;
    }

    public Integer getNumberOfRecruitment() {
        return numberOfRecruitment;
    }

    public Double getCostOfCourtPerPerson() {
        return costOfCourtPerPerson;
    }

    public String getDetails() {
        return details;
    }

    public MatchingStatusType getStatus() {
        return status;
    }

    public UUID getHostId() {
        return hostId;
    }

    public Matching(MatchingBuilder builder) {
        if (builder.startTime.isAfter(builder.endTime)) {
            throw new MatchingTimeException();
        }
        Set<Double> levels = new HashSet<>(
                Arrays.asList(0.0,0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,5.5,6.0));
        if (builder.minimumLevel > builder.maximumLevel
                || !levels.contains(builder.minimumLevel)
                || !levels.contains(builder.maximumLevel)) {
            throw new LevelSettingException();
        }
        if (builder.numberOfRecruitment < 1 || builder.numberOfRecruitment > 8) {
            throw new InvaildNumberException();
        }
        if (builder.costOfCourtPerPerson <= 0) {
            throw new InvaildCostException();
        }

        this.matchingId           = builder.matchingId;
        this.courtId              = builder.courtId;
        this.date                 = builder.date;
        this.startTime            = builder.startTime;
        this.endTime              = builder.endTime;
        this.annual               = builder.annual;
        this.minimumLevel         = builder.minimumLevel;
        this.maximumLevel         = builder.maximumLevel;
        this.ageOfRecruitment     = builder.ageOfRecruitment;
        this.sexOfRecruitment     = builder.sexOfRecruitment;
        this.preferenceGame       = builder.preferenceGame;
        this.numberOfRecruitment  = builder.numberOfRecruitment;
        this.costOfCourtPerPerson = builder.costOfCourtPerPerson;
        this.details              = builder.details;
        this.status               = builder.status;
        this.hostId               = builder.hostId;
    }

    public static class MatchingBuilder {
        private final UUID matchingId;
        private final Court courtId;
        private final LocalDate date;
        private final LocalTime startTime;
        private final LocalTime endTime;
        private final AnnualType annual;
        private final Double minimumLevel;
        private final Double maximumLevel;
        private final AgeGroupType ageOfRecruitment;
        private final GenderType sexOfRecruitment;
        private final PreferenceType preferenceGame;
        private final Integer numberOfRecruitment;
        private final Double costOfCourtPerPerson;
        private String details;
        private final MatchingStatusType status;
        private final UUID hostId;

        public MatchingBuilder(UUID matchingId, Court courtId, LocalDate date, LocalTime startTime, LocalTime endTime, AnnualType annual, Double minimumLevel, Double maximumLevel, AgeGroupType ageOfRecruitment, GenderType sexOfRecruitment, PreferenceType preferenceGame, Integer numberOfRecruitment, Double costOfCourtPerPerson, MatchingStatusType status, UUID hostId) {
            this.matchingId = matchingId;
            this.courtId = courtId;
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
            this.annual = annual;
            this.minimumLevel = minimumLevel;
            this.maximumLevel = maximumLevel;
            this.ageOfRecruitment = ageOfRecruitment;
            this.sexOfRecruitment = sexOfRecruitment;
            this.preferenceGame = preferenceGame;
            this.numberOfRecruitment = numberOfRecruitment;
            this.costOfCourtPerPerson = costOfCourtPerPerson;
            this.status = status;
            this.hostId = hostId;
        }

        public MatchingBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public Matching build(){
            return new Matching(this);
        }
    }
}
