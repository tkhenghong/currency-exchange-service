package com.in28minutes.microservices.currencyexchangeservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ExchangeValue {

    @Id
    private Long id;

    // from is a preserved keyword in SQL, so we need to call the following column with different name in the database
    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private BigDecimal conversionMultiple; // conversion factor, currency A * converionMultiple = currency B
    private int port;

    public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
        super(); // super() is written in Eclipse IDE, interesting!
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    // Make empty constructors to make JPA happy
    public ExchangeValue() {
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    // Port needs both getters and setters
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
