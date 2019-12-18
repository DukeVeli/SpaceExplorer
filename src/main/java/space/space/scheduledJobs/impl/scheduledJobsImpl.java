package space.space.scheduledJobs.impl;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.space.scheduledJobs.ScheduledJob;
import space.space.services.services.CreditAccountService;
import space.space.services.services.LogService;

@Component
@AllArgsConstructor
public class scheduledJobsImpl implements ScheduledJob {
    private final CreditAccountService creditAccountService;
    private final LogService logService;


    @Override
    @Scheduled(cron =  "*/15 * * * *") //every 15 min
    public void addCredits() {
        creditAccountService.addDailyCredit();

    }

    @Override
    @Scheduled(cron =  "0 0 1 */6 *") //six months
    public void clearLog() {
                logService.clearLogBefore6m();
    }
}
