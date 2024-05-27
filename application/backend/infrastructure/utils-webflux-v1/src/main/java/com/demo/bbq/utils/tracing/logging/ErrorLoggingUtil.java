package com.demo.bbq.utils.tracing.logging;

import static com.demo.bbq.utils.tracing.logging.constants.LoggingMessage.EXCEPTION_WITHOUT_MESSAGE;
import static com.demo.bbq.utils.tracing.logging.constants.ThreadContextConstant.REQ_METHOD;
import static com.demo.bbq.utils.tracing.logging.constants.ThreadContextConstant.REQ_URI;

import com.demo.bbq.utils.tracing.logging.constants.LoggingExceptionMessage;
import com.demo.bbq.utils.tracing.logging.injector.ThreadContextInjectorUtil;
import com.demo.bbq.utils.tracing.logging.util.HeaderMapperUtil;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
public class ErrorLoggingUtil {

  public static void generateTrace(Throwable ex, ServerWebExchange exchange) {
    ThreadContextInjectorUtil.populateFromHeaders(HeaderMapperUtil.recoverTraceHeaders(exchange.getRequest().getHeaders()));
    String message = Optional.ofNullable(ex.getMessage()).orElse(EXCEPTION_WITHOUT_MESSAGE);

    if (ex instanceof WebClientRequestException webClientRequestException) {
      ThreadContext.put(REQ_METHOD, webClientRequestException.getMethod().toString());
      ThreadContext.put(REQ_URI, webClientRequestException.getUri().toString());

      Throwable cause = webClientRequestException.getCause();
      message = LoggingExceptionMessage
          .getExceptionMessages()
          .getOrDefault(cause.getClass(), Optional.ofNullable(cause.getMessage()).orElse("WebClientRequestException with an unspecified cause"));
    }

    log.error(message, ex);
    ThreadContext.clearAll();
  }
}