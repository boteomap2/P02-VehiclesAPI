package com.udacity.pricing.init;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DbInit {

    private final PriceRepository priceRepository;

    public DbInit(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @PostConstruct
    private void postConstruct() {
        for (long i = 1; i <= 20; i++) {
            priceRepository.save(new Price("USD", randomPrice(), i));
        }
    }

    private BigDecimal randomPrice() {
        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal(5000d)).setScale(2, RoundingMode.HALF_UP);
    }
}
