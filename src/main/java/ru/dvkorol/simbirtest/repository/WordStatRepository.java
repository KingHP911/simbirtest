package ru.dvkorol.simbirtest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dvkorol.simbirtest.models.WordStat;

public interface WordStatRepository extends CrudRepository<WordStat, Long> {
}
