package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("GIFT")
public class Gift {
    @Id
    @Column("GIFTID")
    private Integer giftId;
    @Column("GIFTNAME")
    private String giftName;

    public Gift(Integer giftId, String giftName) {
        this.giftId = giftId;
        this.giftName = giftName;
    }

    public Integer getGiftId() { return giftId; }
    public String getGiftName() { return giftName; }
}
