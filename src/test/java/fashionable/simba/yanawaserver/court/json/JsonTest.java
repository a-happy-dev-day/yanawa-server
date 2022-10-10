package fashionable.simba.yanawaserver.court.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    private static final String LIST_PUBLIC_RESERVATION_SPORT = "ListPublicReservationSport";
    private static final String JSON_TEST_FILE_PATH = "src/test/java/fashionable/simba/yanawaserver/court/json/test.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("응답 값 중 Court 리스트를 가져와 AREANM, PLACENM, IMGURL의 정보만 추출한다.")
    void test1() throws IOException, JSONException {
        String jsonString = new String(Files.readAllBytes(Paths.get(JSON_TEST_FILE_PATH)));
        JSONObject jsonObject = new JSONObject(jsonString).getJSONObject(LIST_PUBLIC_RESERVATION_SPORT);
        List<Court> list = objectMapper.readValue(
            jsonObject.getJSONArray("row").toString(),
            new TypeReference<>() {
            }
        );

        assertThat(list.size()).isEqualTo(5);
    }
}
