package com.nnk.springboot.domain;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "bidlist")
@Data
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidlistid")
    private int bidlistid;
    @NotBlank(message = "Account is mandatory")
    private String account;
    @NotBlank(message = "type is mandatory")
    private String type;
    @PositiveOrZero
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Date bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private Date creationDate;
    private String revisionName;
    private Date revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;
    public BidList() { }
    public BidList(String account,String type,double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
