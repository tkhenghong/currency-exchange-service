package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Environment environment;

    @Autowired
    CurrencyExchangeController(Environment environment) {
        this.environment = environment;
    }

    // Create simple controller method
    // http://localhost:8000/currency-exchange/from/USD/to/INR
    // Change the port no. to run other instances (See application.properties file)
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
//        exchangeValue.setPort(8000);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port"))); // Get the port of the server itself
        return exchangeValue;
    }
}
