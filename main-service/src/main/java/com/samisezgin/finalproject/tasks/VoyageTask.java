package com.samisezgin.finalproject.tasks;

import com.samisezgin.finalproject.model.enums.VoyageStatus;
import com.samisezgin.finalproject.repository.VoyageRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class VoyageTask {

    private final VoyageRepository voyageRepository;


    public VoyageTask(VoyageRepository voyageRepository) {

        this.voyageRepository = voyageRepository;

    }

    @PostConstruct
    public void checkVoyageDateTime() {
        var timer = new Timer();
        long period = 60 * 60 * 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("Voyage task running");
                var list = voyageRepository
                        .findAll().stream()
                        .filter(voyage -> voyage.getVoyageStatus()
                                .equals(VoyageStatus.ACTIVE) && voyage.getVoyageDateTime().isBefore(LocalDateTime.now()))
                        .toList();
                list.forEach(voyage -> voyage.setVoyageStatus(VoyageStatus.PASSIVE));
                voyageRepository.saveAll(list);

            }
        }, 1000, period);
    }
}
