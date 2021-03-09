package ru.dvkorol.simbirtest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dvkorol.simbirtest.models.SimbirData;

public interface SimbirDataRepository extends CrudRepository<SimbirData, Long> {
}
