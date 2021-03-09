package ru.dvkorol.simbirtest.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SimbirData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @NotEmpty
    String url;

    @Transient
    String directory;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordStat> wordStats = new ArrayList<>();

    public void addwordStats(List<WordStat> wordStats){
        for (WordStat wordStat : wordStats){
            this.wordStats.add(wordStat);
            wordStat.setSimbirData(this);
        }
    }
    public void removewordStat(List<WordStat> wordStats){
        for (WordStat wordStat : wordStats){
            this.wordStats.remove(wordStat);
            wordStat.setSimbirData(null);
        }

    }
}
