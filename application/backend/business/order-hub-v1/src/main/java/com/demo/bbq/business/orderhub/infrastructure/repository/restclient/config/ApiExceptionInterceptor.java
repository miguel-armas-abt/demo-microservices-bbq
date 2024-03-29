package com.demo.bbq.business.orderhub.infrastructure.repository.restclient.config;

import com.demo.bbq.business.orderhub.domain.exception.OrderHubException;
import com.demo.bbq.support.httpclient.retrofit.util.RetrofitInterceptorUtil;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.stereotype.Component;
@Component
public class ApiExceptionInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) {
    return RetrofitInterceptorUtil.validateErrorResponse.apply(chain, OrderHubException.ERROR0002.buildException());
  }

}
