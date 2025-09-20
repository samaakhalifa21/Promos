package com.example.demo.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("SEGMENT")
public class Segment {
    @Id
    @Column("SEGMENTID")
    private Integer segmentId;


    public Segment(Integer segmentId) {
        this.segmentId = segmentId;


    }

    public Integer getSegmentId() { return segmentId; }
}
