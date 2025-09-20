package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("HISTORY")
public class History {
    @Id
    @Column("ID")
    private Integer id;
    @Column("MSISDN")
    private Long msisdn;
    @Column("GIFTID")
    private Integer giftId;
    @Column("USEDAT")
    private LocalDateTime usedAt;

    public History(Integer id, Long msisdn, Integer giftId, LocalDateTime usedAt) {
        this.id = id;
        this.msisdn = msisdn;
        this.giftId = giftId;
        this.usedAt = usedAt;
    }

    public Integer getId() { return id; }
    public Long getMsisdn() { return msisdn; }
    public Integer getGiftId() { return giftId; }
    public LocalDateTime getUsedAt() { return usedAt; }
}
