package fashionable.simba.yanawaserver.members.domain;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "MEMBER_ROLE",
        joinColumns = @JoinColumn(name = "id", referencedColumnName = "id")
    )
    @Column(name = "role")
    private List<String> roles;

    @Embedded
    private MemberAccessToken memberAccessToken;

    protected Member() {/*no-op*/}

    public Member(Long id, String email, List<String> roles, MemberAccessToken memberAccessToken) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.memberAccessToken = memberAccessToken;
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

    public MemberAccessToken getMemberAccessToken() {
        return memberAccessToken;
    }

    public void updateAccessToken(MemberAccessToken memberAccessToken) {
        this.memberAccessToken = memberAccessToken;
    }
}
