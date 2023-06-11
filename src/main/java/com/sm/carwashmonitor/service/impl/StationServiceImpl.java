package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationRequestDto;
import com.sm.carwashmonitor.dto.StationResponseDto;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.StationService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public StationResponseDto createStation(StationRequestDto stationRequestDto) {
        Station station = stationMapper.toEntity(stationRequestDto);
        stationRepository.save(station);
        station.setUnits(new ArrayList<>());
        return stationMapper.toDto(station);
    }

    @Override
    public StationResponseDto getStation(Long stationId) {
        Optional<Station> station = stationRepository.findById(stationId);
        StationResponseDto response;
        if(station.isPresent()) {
            response = stationMapper.toDto(station.get());
        } else {
            throw new EntityNotFoundException("Station not found");
        }
        return response;
    }

    @Override
    public List<StationResponseDto> getAllStations() {
        List<Station> stations = stationRepository.findAll();
        List<StationResponseDto> stationDtos = new ArrayList<>();
        if(!stations.isEmpty()) {
            stations.forEach(station -> {
                stationDtos.add(stationMapper.toDto(station));
            });
        }
        return stationDtos;
    }
}
