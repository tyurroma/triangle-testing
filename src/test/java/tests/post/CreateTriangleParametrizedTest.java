package tests.post;

import env.Endpoints;
import env.Specs;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Error;
import model.TriangleRequest;
import model.TriangleResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.TestsHooks;

import java.util.Arrays;
import java.util.List;

@RunWith(value = Parameterized.class)
public class CreateTriangleParametrizedTest extends TestsHooks {

    @Getter
    @AllArgsConstructor
    public static class TestCase {
        private String name;
        private Object body;
        private int expectedStatusCode;
        private Object expectedResponseBody;

        @Override
        public String toString() {
            return name;
        }
    }

    @Parameterized.Parameter
    public static TestCase testCase;

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<TestCase> testCases() {
        return Arrays.asList(
                new TestCase(
                        "Correct Triangle With Separator ;",
                        TriangleRequest.builder()
                                .separator(";")
                                .input("3;4;5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Additional Numbers In Input Object",
                        TriangleRequest.builder()
                                .separator(";")
                                .input("3;4;5;3;4;5")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With No Separator Field",
                        TriangleRequest.builder()
                                .input("3;4;5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Additional Numbers In Input",
                        "{\"input\": \"3;4;5;3;4;5\"}",
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Different Separator In Input",
                        TriangleRequest.builder()
                                .separator(",")
                                .input("3;4;5")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Zero Triangle",
                        TriangleRequest.builder()
                                .input("0;0;0")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Empty Triangle",
                        TriangleRequest.builder()
                                .input(";;")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator ,",
                        TriangleRequest.builder()
                                .separator(",")
                                .input("3,4,5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator /",
                        TriangleRequest.builder()
                                .separator("/")
                                .input("3/4/5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator +",
                        TriangleRequest.builder()
                                .separator("+")
                                .input("3+4+5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator !",
                        TriangleRequest.builder()
                                .separator("!")
                                .input("3!4!5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator @",
                        TriangleRequest.builder()
                                .separator("@")
                                .input("3@4@5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator #",
                        TriangleRequest.builder()
                                .separator("#")
                                .input("3#4#5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator %",
                        TriangleRequest.builder()
                                .separator("%")
                                .input("3%4%5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator ^",
                        TriangleRequest.builder()
                                .separator("^")
                                .input("3^4^5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator &",
                        TriangleRequest.builder()
                                .separator("&")
                                .input("3&4&5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator ?",
                        TriangleRequest.builder()
                                .separator("?")
                                .input("3?4?5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator *",
                        TriangleRequest.builder()
                                .separator("*")
                                .input("3*4*5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Separator |",
                        TriangleRequest.builder()
                                .separator("|")
                                .input("3|4|5")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.0f)
                                .secondSide(4.0f)
                                .thirdSide(5.0f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Float Sides",
                        TriangleRequest.builder()
                                .input("3.2;4.2;5.2")
                                .separator(";")
                                .build(),
                        200,
                        TriangleResponse.builder()
                                .firstSide(3.2f)
                                .secondSide(4.2f)
                                .thirdSide(5.2f)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Negative Side",
                        TriangleRequest.builder()
                                .input("-3;4;5")
                                .separator(";")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Zero Side",
                        TriangleRequest.builder()
                                .input("0;4;5")
                                .separator(";")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Line",
                        TriangleRequest.builder()
                                .input("2;3;5")
                                .separator(";")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Incorrect Triangle",
                        TriangleRequest.builder()
                                .input("3;4;10")
                                .separator(";")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Without Mandatory Field",
                        TriangleRequest.builder()
                                .separator(";")
                                .build(),
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Without Body",
                        null,
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Empty Body",
                        "{}",
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "No Auth",
                        TriangleRequest.builder()
                                .input("3;4;5")
                                .separator(";")
                                .build(),
                        401,
                        Error.builder()
                                .status("401")
                                .error("Unauthorized")
                                .message("No message available")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Invalid Json",
                        "{\"separator\": \";\", \"insert\": \"3;4;5\"}",
                        422,
                        Error.builder()
                                .status("422")
                                .error("Unprocessable Entity")
                                .message("Cannot process input")
                                .path(Endpoints.TRIANGLE)
                                .build()
                ),
                new TestCase(
                        "Correct Triangle With Not Allowed Method",
                        TriangleRequest.builder()
                                .input("3;4;5")
                                .separator(";")
                                .build(),
                        405,
                        Error.builder()
                                .status("405")
                                .error("Method Not Allowed")
                                .message("Request method 'PATCH' not supported")
                                .path(Endpoints.TRIANGLE)
                                .build()
                )
        );
    }

    @Test
    public void create() {
        switch (testCase.expectedStatusCode) {
            case 200: {
                TriangleResponse response = RestAssured.with()
                        .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE))
                        .body(testCase.body)
                        .when()
                        .post()
                        .then()
                        .statusCode(testCase.expectedStatusCode)
                        .extract().body().as(TriangleResponse.class);

                Assert.assertFalse(response.getId().isEmpty());

                TriangleResponse actualResult = response.toBuilder().id(null).build();

                Assert.assertEquals(testCase.expectedResponseBody, actualResult);

                break;
            }
            case 422: {
                RequestSpecification request = RestAssured.with()
                        .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE));

                if (testCase.body != null) {
                    request.body(testCase.body);
                }

                Error actualResult = request.when()
                        .post()
                        .then()
                        .statusCode(testCase.expectedStatusCode)
                        .extract().body().as(Error.class)
                        .toBuilder().timestamp(null).exception(null).build();

                Assert.assertEquals(testCase.expectedResponseBody, actualResult);

                break;
            }
            case 401: {
                Error actualResult = RestAssured.with()
                        .spec(Specs.getNoAuthSpecification(Endpoints.TRIANGLE))
                        .body(testCase.body)
                        .when()
                        .post()
                        .then()
                        .statusCode(testCase.expectedStatusCode)
                        .extract().body().as(Error.class)
                        .toBuilder().timestamp(null).exception(null).build();

                Assert.assertEquals(testCase.expectedResponseBody, actualResult);

                break;
            }
            // There is no 405 code in the Docs.
            case 405: {
                Error actualResult = RestAssured.with()
                        .spec(Specs.getBasicSpecification(Endpoints.TRIANGLE))
                        .body(testCase.body)
                        .when()
                        .patch()
                        .then()
                        .statusCode(testCase.expectedStatusCode)
                        .extract().body().as(Error.class)
                        .toBuilder().timestamp(null).exception(null).build();

                Assert.assertEquals(testCase.expectedResponseBody, actualResult);

                break;
            }
            default: {
                throw new RuntimeException("Unexpected status code: " + testCase.expectedStatusCode);
            }
        }
    }
}
