package com.web.megamart.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {

    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;

}
