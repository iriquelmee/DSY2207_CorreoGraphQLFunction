package com.grupo8;

import com.grupo8.graphql.GraphQLProvider;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;
import java.util.Map;

public class Function {

    @FunctionName("graphqlCorreo")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Map<String, Object>>> request, final ExecutionContext context) {

            Map<String, Object> body = request.getBody().orElseThrow(() -> new RuntimeException("Body vacío"));

            String query = (String) body.get("query");

            Map<String, Object> result = GraphQLProvider.getGraphQLInstance().execute(query).toSpecification();

            return request.createResponseBuilder(HttpStatus.OK).body(result).build();
    }
}
