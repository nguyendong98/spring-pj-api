package com.springboot.training.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCreateRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;

    @Email
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String description;

    private Integer price;
}
