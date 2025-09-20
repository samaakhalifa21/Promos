package com.example.demo.Repository;

import com.example.demo.Models.History;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HistoryRepository extends CrudRepository<History, Integer> {

    @Query("""
            SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END
            FROM HISTORY
            WHERE MSISDN = :msisdn
              AND GIFTID = :giftId
            """)
    boolean existsByMsisdnAndGiftId(@Param("msisdn") Long msisdn,
                                    @Param("giftId") Integer giftId);
}
