package com.learning.dwivna.ekaasaa.exceptions.resolver;

import com.learning.dwivna.ekaasaa.exceptions.UserAddFailedException;
import com.learning.dwivna.ekaasaa.exceptions.UserNotDeletedException;
import com.learning.dwivna.ekaasaa.exceptions.UserNotFoundException;
import com.learning.dwivna.ekaasaa.exceptions.UserUpdateFailedException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphQLExceptionResolver implements DataFetcherExceptionResolver {
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        List<GraphQLError> graphQLErrors = new ArrayList<>();
        if (exception instanceof UserNotFoundException) {
            graphQLErrors.add(GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.NOT_FOUND)
                    .message(exception.getMessage())
                    .location(environment.getField().getSourceLocation())
                    .path(environment.getExecutionStepInfo().getPath())
                    .build());
        } else if (exception instanceof UserAddFailedException) {
            graphQLErrors.add(GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.BAD_REQUEST)
                    .message(exception.getMessage())
                    .location(environment.getField().getSourceLocation())
                    .path(environment.getExecutionStepInfo().getPath())
                    .build());
        } else if (exception instanceof UserNotDeletedException) {
            graphQLErrors.add(GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.NOT_FOUND)
                    .message(exception.getMessage())
                    .location(environment.getField().getSourceLocation())
                    .path(environment.getExecutionStepInfo().getPath())
                    .build());
        } else if (exception instanceof UserUpdateFailedException) {
            graphQLErrors.add(GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.NOT_FOUND)
                    .message(exception.getMessage())
                    .location(environment.getField().getSourceLocation())
                    .path(environment.getExecutionStepInfo().getPath())
                    .build());
        }
        return Mono.just(graphQLErrors);
    }
}
