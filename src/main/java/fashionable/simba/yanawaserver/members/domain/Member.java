package fashionable.simba.yanawaserver.members.domain;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public abstract class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nickname;
    @Enumerated(value = EnumType.STRING)
    private MemberSex sex;

    @Embedded
    private MemberBirthDate birthDate;

    @Embedded
    private MemberLevel level;

    private boolean isFirst;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "MEMBER_ROLE",
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "id")
    )
    @Column(name = "role")
    private List<String> roles;

    protected Member() {/*no-op*/}

    protected Member(String nickname, Long id, String email, List<String> roles, boolean isFirst) {
        this.nickname = nickname;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.isFirst = isFirst;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void updateInformation(String nickname, MemberSex sex, MemberBirthDate birthDate, MemberLevel level) {
        this.nickname = nickname;
        this.sex = sex;
        this.birthDate = birthDate;
        this.level = level;
        this.isFirst = true;
    }

}
