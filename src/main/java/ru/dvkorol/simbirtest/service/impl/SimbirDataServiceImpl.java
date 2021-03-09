package ru.dvkorol.simbirtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvkorol.simbirtest.SimbDownloader;
import ru.dvkorol.simbirtest.models.SimbirData;
import ru.dvkorol.simbirtest.models.WordStat;
import ru.dvkorol.simbirtest.repository.SimbirDataRepository;
import ru.dvkorol.simbirtest.service.SimbirDataService;

import java.util.List;

@Service
public class SimbirDataServiceImpl implements SimbirDataService {

    @Autowired
    private SimbirDataRepository simbirDataRepository;

    @Override
    @Transactional
    public SimbirData addData(SimbirData simbirData) {

        List<WordStat> wordStats = SimbDownloader.getStatData(simbirData.getUrl(), simbirData.getDirectory());
        simbirData.addwordStats(wordStats);
        SimbirData savedData = simbirDataRepository.save(simbirData);

        return savedData;

    }
}
