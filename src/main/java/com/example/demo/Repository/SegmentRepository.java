package com.example.demo.Repository;
import com.example.demo.Models.Segment;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface SegmentRepository extends CrudRepository<Segment, Integer> {

    @Query("""
            SELECT g.GIFTNAME
            FROM GIFT g
            JOIN SEGMENTGIFT sg ON g.GIFTID = sg.GIFTID
            WHERE sg.SEGMENTID = :segmentId
            """)

    List<String> getGiftNamesBySegmentId(@Param("segmentId") Integer segmentId);
}