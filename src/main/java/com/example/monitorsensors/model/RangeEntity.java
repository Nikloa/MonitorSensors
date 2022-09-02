package com.example.monitorsensors.model;

import com.example.monitorsensors.validation.FromToCheck;

import javax.persistence.*;

@Entity
@Table(name = "ranges")
@FromToCheck
public class RangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "range_from")
    private int from;

    @Column(name = "range_to")
    private  int to;

    public RangeEntity() {}

    public RangeEntity(long id, int from, int to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
