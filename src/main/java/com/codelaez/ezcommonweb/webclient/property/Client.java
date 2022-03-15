package com.codelaez.ezcommonweb.webclient.property;

import lombok.Data;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author khanhspring
 */
@Data
@Validated
public class Client {
    @NotBlank
    private String id;
    @NotBlank
    private String baseUrl;

    private MultiValueMap<String, String> headers;
}
