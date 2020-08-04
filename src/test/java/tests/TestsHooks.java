package tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import model.TriangleRequest;
import model.TriangleResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestsHooks {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    private static class ObjectMapperFactory implements Jackson2ObjectMapperFactory {
        @Override
        public ObjectMapper create(Type cls, String charset) {
            return new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }

    /**
     * Create the config with the custom object mapper before all tests.
     */
    static {
        ObjectMapperConfig config = new ObjectMapperConfig().jackson2ObjectMapperFactory(new ObjectMapperFactory() {
        });
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(config);
    }

    @Before
    public void cleanData() {
        List<TriangleResponse> triangles = RestAssured
                .given(Specs.getBasicNoFilterSpecification(Endpoints.TRIANGLES))
                .get()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", TriangleResponse.class);

        ArrayList<String> ids = new ArrayList<>();

        for (TriangleResponse triangle : triangles) {
            ids.add(triangle.getId());
        }

        for (String id : ids) {
            RestAssured.given(Specs.getBasicNoFilterSpecification(Endpoints.TRIANGLE_ID))
                    .pathParam("triangleId", id)
                    .delete();
        }
    }

    /**
     * Create a default triangle with sides (3,4,5) for pre-condition purposes where it is needed.
     */
    protected static TriangleResponse createBasicTriangle() {
        return RestAssured.with()
                .spec(Specs.getBasicNoFilterSpecification(Endpoints.TRIANGLE))
                .body(TriangleRequest.builder()
                        .input("3;4;5")
                        .build()
                )
                .when()
                .post()
                .then()
                .extract().body().as(TriangleResponse.class);
    }
}
