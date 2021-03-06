package space.space.services.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import space.space.data.models.Log;
import space.space.data.repositories.LogRepository;
import space.space.services.models.LogServiceModel;
import space.space.services.services.LogService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    @Override
    public LogServiceModel saveLog(LogServiceModel logServiceModel) {
        Log log = this.modelMapper.map(logServiceModel, Log.class);
        return this.modelMapper.map(this.logRepository.saveAndFlush(log),LogServiceModel.class);
    }

    @Override
    public void clearLogBefore6m() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.minus(6, ChronoUnit.MONTHS);
        logRepository.deleteAllByTimeBefore(localDateTime);
    }
}
