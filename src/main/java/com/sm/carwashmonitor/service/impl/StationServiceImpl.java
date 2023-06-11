package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    @Override
    public StationResponseDto createStation(StationRequestDto stationRequestDto) {
        Station station = stationMapper.toEntity(stationRequestDto);
        stationRepository.save(station);
        station.setUnits(new ArrayList<>());
        return stationMapper.toDto(station);
    }
}
