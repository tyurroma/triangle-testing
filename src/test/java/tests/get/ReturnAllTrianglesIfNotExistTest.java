package tests.get;

import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import model.TriangleResponse;
import org.junit.Assert;
import org.junit.Test;
import tests.TestsHooks;

import java.util.List;

public class ReturnAllTrianglesIfNotExistTest extends TestsHooks {

    @Test
    public void shouldReturnAnEmptyArray() {
        List<TriangleResponse> actualResult = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLES))
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", TriangleResponse.class);

        Assert.assertTrue(actualResult.isEmpty());
    }
}
