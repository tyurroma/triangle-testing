package env;

import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Filter implements io.restassured.filter.Filter {

    private static final Logger logger = Logger.getLogger(Filter.class.getName());

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        logger.log(Level.INFO,
                "\nRequest: " +
                        "=> " + requestSpec.getMethod() +
                        " " + requestSpec.getBody() +
                        " " + requestSpec.getURI() + "\n" +
                        "Response: " +
                        "<= " + response.getStatusCode() +
                        " " + response.getStatusLine() +
                        " " + response.getBody().prettyPrint() + "\n");
        return response;
    }
}
