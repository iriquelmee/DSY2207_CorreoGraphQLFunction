package com.grupo8.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.InputStreamReader;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

public class GraphQLProvider {
    private static GraphQL graphQL;

    public static GraphQL getGraphQLInstance() {
        //constructor instancia graphql provider
        if (graphQL == null) {
            graphQL = buildGraphQL();
        }
        return graphQL;
    }

    private static GraphQL buildGraphQL() {
        // instanciando esquema graphql
        InputStream schemaStream = GraphQLProvider.class.getResourceAsStream("/schema.graphqls");
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(new InputStreamReader(schemaStream, StandardCharsets.UTF_8));

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring().
            type("Query", typeWiring -> typeWiring.dataFetcher("getCorreosByIdUsuario",CorreoResolver.getCorreosByIdUsuarioFetcher())).
            type("Mutation", typeWiring -> typeWiring.dataFetcher("insertCorreo", CorreoResolver.insertCorreoFetcher())).
            build();

        SchemaGenerator generator = new SchemaGenerator();
        GraphQLSchema schema = generator.makeExecutableSchema(typeRegistry, wiring);

        return GraphQL.newGraphQL(schema).build();
    }
}
