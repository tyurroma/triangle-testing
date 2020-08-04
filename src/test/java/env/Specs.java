package env;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Class defines a specification for RestAssured requests.
 */
public class Specs {

    public static RequestSpecification getBasicSpecification(String basePath) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(Env.BASE_PATH)
                .setBasePath(basePath)
                .addHeader("X-User", Env.TOKEN)
                .addFilter(new Filter())
                .build();
    }

    public static RequestSpecification getNoAuthSpecification(String basePath) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(Env.BASE_PATH)
                .setBasePath(basePath)
                .addFilter(new Filter())
                .build();
    }

    public static RequestSpecification getBasicNoFilterSpecification(String basePath) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(Env.BASE_PATH)
                .setBasePath(basePath)
                .addHeader("X-User", Env.TOKEN)
                .build();
    }
}
