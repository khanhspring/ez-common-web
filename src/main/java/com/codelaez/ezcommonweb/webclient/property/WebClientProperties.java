package com.codelaez.ezcommonweb.webclient.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author khanhspring
 */
@Data
@Validated
@ConfigurationProperties(prefix = WebClientProperties.PREFIX)
public class WebClientProperties {
    public static final String PREFIX = "ez.web-client";

    private List<Client> clients;
}
