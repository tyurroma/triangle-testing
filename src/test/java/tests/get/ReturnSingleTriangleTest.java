package tests.get;

import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import model.Error;
import model.TriangleResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.TestsHooks;

import java.util.UUID;

public class ReturnSingleTriangleTest extends TestsHooks {

    private String id;
    private TriangleResponse triangle;

    @Before
    public void setUp() {
        triangle = TestsHooks.createBasicTriangle();
        id = triangle.getId();
    }

    @Test
    public void shouldReturnTriangle() {
        TriangleResponse actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().as(TriangleResponse.class);

        Assert.assertEquals(triangle, actualResult);
    }

    @Test
    public void shouldReturnNotFound() {
        String id = UUID.randomUUID().toString();

        Error actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .get()
                .then()
                .statusCode(404)
                .extract().body().as(Error.class)
                .toBuilder().timestamp(null).exception(null).build();

        Error expectedResult = Error.builder()
                .status("404")
                .error("Not Found")
                .message("Not Found")
                .path(String.format("/triangle/%s", id))
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnUnauthorized() {
        Error actualResult = RestAssured.with()
                .spec(Specs.getNoAuthSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .get()
                .then()
                .statusCode(401)
                .extract().body().as(Error.class)
                .toBuilder().timestamp(null).exception(null).build();

        Error expectedResult = Error.builder()
                .status("401")
                .error("Unauthorized")
                .message("No message available")
                .path(String.format("/triangle/%s", id))
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }

    // There is no 405 code in the Docs.
    @Test
    public void shouldReturnMethodNotAllowedIfEmptyId() {
        Error actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", "")
                .when()
                .get()
                .then()
                .statusCode(405)
                .extract().body().as(Error.class)
                .toBuilder().timestamp(null).exception(null).build();

        Error expectedResult = Error.builder()
                .status("405")
                .error("Method Not Allowed")
                .message("Request method 'GET' not supported")
                .path("/triangle/")
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
