package com.megamart.nofitication.email;

import lombok.Getter;

public enum EmailTemplate {

   PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
   ORDER_CONFIRMATION("order-confirmation.html", "Order successfully placed")
    ;
   @Getter
    private final String template;

   @Getter
    private final String subject;

   EmailTemplate(final String template, final String subject) {
        this.template = template;
        this.subject = subject;
   }

}
