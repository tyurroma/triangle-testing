package tests.get;

import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import model.Error;
import model.PerimeterResponse;
import model.TriangleResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.TestsHooks;
import utils.TriangleUtils;

import java.util.UUID;

public class ReturnPerimeterTest extends TestsHooks {

    private String id;
    private TriangleResponse triangle;

    @Before
    public void setUp() {
        triangle = TestsHooks.createBasicTriangle();
        id = triangle.getId();
    }

    @Test
    public void shouldReturnCorrectTrianglePerimeter() {
        PerimeterResponse perimeter = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.PERIMETER))
                .pathParam("triangleId", id)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().as(PerimeterResponse.class);

        Double actualResult = perimeter.getResult();
        Double expectedResult = TriangleUtils.getPerimeter(3.0, 4.0, 5.0);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnNotFound() {
        String id = UUID.randomUUID().toString();

        Error actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.PERIMETER))
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
                .path(String.format("/triangle/%s/perimeter", id))
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnUnauthorized() {
        Error actualResult = RestAssured.with()
                .spec(Specs.getNoAuthSpecification(Endpoints.PERIMETER))
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
                .path(String.format("/triangle/%s/perimeter", id))
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnNotFoundIfEmptyId() {
        Error actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.PERIMETER))
                .pathParam("triangleId", "")
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
                .path("/triangle//perimeter")
                .build();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
