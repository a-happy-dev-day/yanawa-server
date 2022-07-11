package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.*;
import fashionable.simba.yanawaserver.error.InvaildCostException;
import fashionable.simba.yanawaserver.error.InvaildNumberException;
import fashionable.simba.yanawaserver.error.LevelSettingException;
import fashionable.simba.yanawaserver.error.MatchingTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Matching {
    private final UUID matchingId;
    private final Court courtId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private AnnualType annual;
    private Double minimumLevel;
    private Double maximumLevel;
    private AgeGroupType ageOfRecruitment;
    private GenderType sexOfRecruitment;
    private PreferenceType preferenceGame;
    private Integer numberOfRecruitment;
    private Double costOfCourtPerPerson;
    private String details;
    private StatusType status;
    private final UUID hostId;

    public Matching(UUID matchingId, Court courtId, LocalDate date, LocalTime startTime, LocalTime endTime, AnnualType annual, Double minimumLevel, Double maximumLevel, AgeGroupType ageOfRecruitment, GenderType sexOfRecruitment, PreferenceType preferenceGame, Integer numberOfRecruitment, Double costOfCourtPerPerson, String details, StatusType status, UUID hostId) {
        if (startTime.isAfter(endTime)) {
            throw new MatchingTimeException();
        }
        if (minimumLevel > maximumLevel){
            throw new LevelSettingException();
        }
        if (numberOfRecruitment < 1 || numberOfRecruitment > 8) {
            throw new InvaildNumberException();
        }
        if (costOfCourtPerPerson <= 0) {
            throw new InvaildCostException();
        }

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
        this.details = details;
        this.status = status;
        this.hostId = hostId;
    }

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

    public StatusType getStatus() {
        return status;
    }

    public UUID getHostId() {
        return hostId;
    }
}
