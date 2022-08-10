package fashionable.simba.member.acceptance;


import fashionable.simba.utils.DatabaseCleanup;
import fashionable.simba.yanawaserver.members.DataLoader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
    @LocalServerPort
    int port;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    private DataLoader dataLoader;

    String 관리자;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();
        dataLoader.loadData();
    }
}
