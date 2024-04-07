package digital.softwareshinobi.napkinexchange.security.repository;

import digital.softwareshinobi.napkinexchange.security.model.StockPriceHistory;
import digital.softwareshinobi.napkinexchange.security.model.StockPriceHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, StockPriceHistoryId> {

    @Modifying
    @Query(value = "truncate table stock_history", nativeQuery = true)
    void truncateTable();
}
