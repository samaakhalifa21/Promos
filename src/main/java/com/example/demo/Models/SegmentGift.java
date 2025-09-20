package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("SEGMENTGIFT")
public class SegmentGift {
    @Id
    @Column("SEGMENTID")
    private Integer segmentId;
    @Column("GIFTID")
    private Integer giftId;

    public SegmentGift(Integer segmentId,  Integer giftId) {
        this.segmentId = segmentId;

        this.giftId = giftId;
    }


    public Integer getGiftId() { return giftId; }
    public Integer getSegmentId() { return segmentId; }

}
