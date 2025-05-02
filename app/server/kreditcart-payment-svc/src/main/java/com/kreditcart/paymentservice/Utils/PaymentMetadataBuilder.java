package com.kreditcart.paymentservice.Utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PaymentMetadataBuilder {

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, String> metadata = new HashMap<>();

        public Builder withOrderId(UUID orderId) {
            if (orderId != null) {
                metadata.put("order_id", orderId.toString());
            }
            return this;
        }

        public Builder withEmail(String email) {
            if (email != null && !email.isBlank()) {
                metadata.put("email", email);
            }
            return this;
        }

        public Builder withPhone(String phone) {
            if (phone != null && !phone.isBlank()) {
                metadata.put("phone_number", phone);
            }
            return this;
        }

        public Builder withAmount(double amount) {
            metadata.put("amount", String.valueOf(amount));
            return this;
        }

        public Map<String, String> build() {
            return metadata;
        }
    }
}
