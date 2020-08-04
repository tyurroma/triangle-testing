package tests.delete;

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

public class DeleteTriangleTest extends TestsHooks {

    private String id;
    private TriangleResponse triangle;

    @Before
    public void setUp() {
        triangle = TestsHooks.createBasicTriangle();
        id = triangle.getId();
    }

    @Test
    public void shouldDeleteTriangle() {
        RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .delete()
                .then()
                .statusCode(200);

        RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .get()
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldNotReturnErrorIfDoesNotExist() {
        String id = UUID.randomUUID().toString();

        RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .delete()
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnUnauthorized() {
        Error actualResult = RestAssured.with()
                .spec(Specs.getNoAuthSpecification(Endpoints.TRIANGLE_ID))
                .pathParam("triangleId", id)
                .when()
                .delete()
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
                .delete()
                .then()
                .statusCode(405)
                .extract().body().as(Error.class)
                .toBuilder().timestamp(null).exception(null).build();

        Error expectedResult = Error.builder()
                .status("405")
                .error("Method Not Allowed")
                .message("Request method 'DELETE' not supported")
                .path(String.format("/triangle/"))
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
