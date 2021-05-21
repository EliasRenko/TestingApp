package Rest;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestApiTest {

    public final String url = "https://swapi.dev/api/films";

    @Test
    @DisplayName("Rest test")
    public void TestSWAPI() {

        Logger logger = LoggerFactory.getLogger(RestApiTest.class);

        ReadContext body = JsonPath.parse(extract(url));

        List<String> filmTitles = body.read("$.results[*].title");

        logger.info("Фильмы:");
        for (String title : filmTitles) {
            logger.info(title);
        }

        logger.info("\n\nПланеты:");
        List<String> planets = body.read("$.results[0].planets");
        for (String planet : planets) {
            ReadContext planetContext = JsonPath.parse(extract(planet));
            String name = planetContext.read("$.name");
            logger.info(name);
        }

        logger.info("\n\nРасы:");
        List<String> species = body.read("$.results[0].species");
        for (String specimen : species) {
            ReadContext specimenContext = JsonPath.parse(extract(specimen));
            String name = specimenContext.read("$.name");
            logger.info(name);
        }

        logger.info("\n\nПерсонажи:");
        List<String> characters = body.read("$.results[0].characters");
        for (String character : characters) {
            ReadContext characterContext = JsonPath.parse(extract(character));
            String name = characterContext.read("$.name");
            logger.info(name);
        }
    }

    private String extract(String url) {
        return given().when().get(url).then().assertThat().statusCode(200).extract().body().asString();
    }
}
