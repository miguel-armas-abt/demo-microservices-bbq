package com.demo.bbq.utils.interceptor.logging.mdc;

import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.MDC_NAME_COMPONENT;
import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.HEADER_TRACE;
import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.MDC_TRACE_ID;
import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.HEADER_PARENT;
import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.MDC_PARENT_ID;
import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.HEADER_TRACEPARENT;
import static com.demo.bbq.utils.interceptor.logging.constants.LoggingConstant.MDC_TRACEPARENT;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpHeaders;

public class MDCUtil {

  public static void generateTracking(String applicationName, HttpHeaders httpHeaders) {

    ThreadContext.put(MDC_NAME_COMPONENT, applicationName);
    putHeader(httpHeaders, HEADER_TRACE, MDC_TRACE_ID);
    putHeader(httpHeaders, HEADER_PARENT, MDC_PARENT_ID);
    putHeader(httpHeaders, HEADER_TRACEPARENT, MDC_TRACEPARENT);
  }

  private static void putHeader(HttpHeaders httpHeaders, String headerName, String mdcKey) {
    String headerValue = httpHeaders.getFirst(headerName);
    ThreadContext.put(mdcKey, headerValue != null ? headerValue : StringUtils.EMPTY);
  }

}