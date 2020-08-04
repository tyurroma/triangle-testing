package tests.post;

import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import model.Error;
import model.TriangleRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.TestsHooks;

public class CreateTriangleLimitTest extends TestsHooks {

    @Before
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            TestsHooks.createBasicTriangle();
        }
    }

    // There is no limit for creating 11 triangles.
    @Test
    public void shouldReturnLimitExceeded() {
        TriangleRequest triangle = TriangleRequest.builder()
                .input("3;4;5")
                .build();

        Error actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE))
                .body(triangle)
                .when()
                .post()
                .then()
                .statusCode(422)
                .extract().body().as(Error.class);

        Error expectedResult = Error.builder()
                .status("422")
                .error("Unprocessable Entity")
                .message("Limit exceeded")
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
