package digital.softwareshinobi.napkinexchange.market.service;

import digital.softwareshinobi.napkinexchange.market.model.Market;
import digital.softwareshinobi.napkinexchange.market.enums.MarketTrajectory;
import digital.softwareshinobi.napkinexchange.market.repository.MarketRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarketService {

    @Autowired
    private final MarketRepository marketRepository;

    public ZonedDateTime systemTime() {

        return this.getMarket().getDate();

    }

    public Market getMarket() {

        Market market = marketRepository.findById(1).orElse(null);

        if (market == null) {

            LocalDate localDate = LocalDate.now();

            LocalTime localTime = LocalTime.now();

            ZoneId zoneId = ZoneId.of("GMT-05:00");

            var startingTime = ZonedDateTime.of(localDate, localTime, zoneId);

            market = new Market(startingTime, 41.0, MarketTrajectory.NORMAL);

            saveMarket(market);

        }

        return market;

    }

    public void saveMarket(final Market market) {

        if (market == null) {

            return;

        }

        marketRepository.save(market);

    }
}
