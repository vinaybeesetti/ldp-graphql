package com.tri.freelancemp.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class GraphqlExceptionHandler extends DataFetcherExceptionResolverAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        logger.error("Error while resolving graphql exception", ex);
        if (ex instanceof ObjectNotFoundException) {
            return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).errorType(ErrorType.NOT_FOUND).build();
        } else if (ex instanceof UnauthorizedClientException) {
            return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).errorType(ErrorType.UNAUTHORIZED).build();
        }

        return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).build();
    }
}
