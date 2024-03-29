package com.demo.bbq.business.payment.application.service;

import com.demo.bbq.business.payment.application.events.consumer.message.PaymentMessage;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentServiceImpl implements PaymentService {
  @Override
  public Observable<PaymentMessage> findAll() {
    return null;
  }
}
