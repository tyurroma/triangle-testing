package tests.get;

import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import model.TriangleResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.TestsHooks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReturnAllTrianglesIfExistTest extends TestsHooks {

    private List<TriangleResponse> trianglesList = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i <= 10; i++) {
            trianglesList.add(TestsHooks.createBasicTriangle());
        }
        trianglesList.sort(Comparator.comparing(TriangleResponse::getId));
    }

    @Test
    public void shouldReturnAllTriangles() {
        List<TriangleResponse> triangles = RestAssured.with()
                .spec(Specs.getBasicSpecification(Endpoints.TRIANGLES))
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", TriangleResponse.class);

        List<TriangleResponse> actualResult = new ArrayList<>();
        for (TriangleResponse triangle : triangles) {
            actualResult.add(triangle);
        }
        actualResult.sort(Comparator.comparing(TriangleResponse::getId));

        Assert.assertEquals(trianglesList, actualResult);
    }
}
