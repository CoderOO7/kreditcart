package com.kreditcart.paymentservice.PaymentGateway;

import com.kreditcart.paymentservice.Utils.PaymentMetadataBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.key.secret}")
    private String apiKey;
    @Autowired
    private PaymentMetadataBuilder paymentMetadataBuilder;

    @Override
    public String generatePaymentLink(UUID orderId, String email, String phoneNumber, double amount) {
        try {
            Stripe.apiKey = this.apiKey;

            Price price = getPrice(amount);

            Map<String, String> metadata = paymentMetadataBuilder.builder()
                    .withOrderId(orderId)
                    .withEmail(email)
                    .withPhone(phoneNumber)
                    .withAmount(amount)
                    .build();

            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .putAllMetadata(metadata)
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .setAfterCompletion(
                                    PaymentLinkCreateParams.AfterCompletion.builder()
                                            .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                            .setRedirect(
                                                    PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                            .setUrl("https://youtu.be/xdGO-qmEJio?t=157")
                                                            .build()
                                            )
                                            .build()
                            )
                            .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        }catch (StripeException stripeException) {
            throw new RuntimeException(stripeException);
        }
    }

    private Price getPrice(double amount) {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            // todo: stripe don't accept payment in float,
                            // how to handle that case, in order service or in payment service ?
                            .setUnitAmount((long)amount)
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();
            return Price.create(params);
        }catch (StripeException stripeException) {
            throw new RuntimeException(stripeException);
        }

    }
}
