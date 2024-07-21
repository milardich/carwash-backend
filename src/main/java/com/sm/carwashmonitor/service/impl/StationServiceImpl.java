package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.StationService;
import com.sm.carwashmonitor.validation.StationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final StationValidation stationValidation;

    @Override
    public StationResponseDto createStation(StationRequestDto stationRequestDto) {
        stationValidation.validate(stationRequestDto);
        Station station = stationMapper.toEntity(stationRequestDto);
        stationRepository.save(station);
        station.setUnits(new ArrayList<>());
        return stationMapper.toDto(station);
    }

    @Override
    public StationResponseDto getStation(Long stationId) {
        stationValidation.exists(stationId);
        Optional<Station> optionalStation = stationRepository.findById(stationId);
        Station station = optionalStation.orElseThrow();
        return stationMapper.toDto(station);
    }

    @Override
    public List<StationResponseDto> getAllStations() {
        List<Station> stations = stationRepository.findAll();
        stationValidation.isEmpty(stations);
        return toResponseDtos(stations);
    }

    private List<StationResponseDto> toResponseDtos(List<Station> stations) {
        List<StationResponseDto> stationResponseDtos = new ArrayList<>();
        stations.forEach(station -> {
            stationResponseDtos.add(stationMapper.toDto(station));
        });
        return stationResponseDtos;
    }
}
