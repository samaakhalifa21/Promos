package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CUSTOMER")
public class Customer {
    // segmentation
    @Id
    @Column("ID")
    private Integer id;
    @Column("MSISDN")
    private long msisdn;

    @Column("SEGMENTID")
    private Integer segmentId;


    public Customer(Integer id, long msisdn,Integer segmentId) {
        this.id = id;
        this.msisdn = msisdn;
        this.segmentId= segmentId;

    }

    public Integer getId() { return id; }
    public long getMsisdn() { return msisdn; }
    public Integer getSegmentId() { return segmentId; }


}

