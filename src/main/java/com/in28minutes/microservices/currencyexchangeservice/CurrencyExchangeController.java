package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Environment environment;

    private ExchangeValueRepository exchangeValueRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CurrencyExchangeController(Environment environment, ExchangeValueRepository exchangeValueRepository) {
        this.environment = environment;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    // Create simple controller method
    // http://localhost:8000/currency-exchange/from/USD/to/INR
    // http://localhost:8000/currency-exchange/from/EUR/to/INR
    // http://localhost:8000/currency-exchange/from/AUD/to/INR
    // Change the port no. to run other instances (See application.properties file)
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port"))); // Get the port of the server itself

        logger.info("{}", exchangeValue); // Add this to put empty value for Spring Cloud Sleuth to set an ID for any requests that comes in.

        return exchangeValue;
    }
}
