package ru.dvkorol.simbirtest.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WordStat {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String word;
    Integer count;

    @ManyToOne (fetch = FetchType.LAZY)
    SimbirData simbirData;

}
