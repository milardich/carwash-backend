package com.sm.carwashmonitor.service.impl;

import com.sm.carwashmonitor.dto.StationDTO;
import com.sm.carwashmonitor.dto.StationRequestDTO;
import com.sm.carwashmonitor.mapper.StationMapper;
import com.sm.carwashmonitor.model.Station;
import com.sm.carwashmonitor.repository.StationRepository;
import com.sm.carwashmonitor.service.StationService;
import com.sm.carwashmonitor.validation.StationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final StationValidation stationValidation;

    @Override
    public StationDTO createStation(StationRequestDTO stationRequestDto) {
        stationValidation.validate(stationRequestDto);
        Station station = stationMapper.toEntity(stationRequestDto);
        Station savedStation = stationRepository.save(station);
        return stationMapper.toDto(savedStation);
    }

    @Override
    public StationDTO getStation(Long stationId) {
        Station station = stationRepository.findById(stationId).orElseThrow();
        return stationMapper.toDto(station);
    }

    @Override
    public List<StationDTO> getAllStations() {
        List<Station> stations = stationRepository.findAll();
        return toResponseDtos(stations);
    }

    private List<StationDTO> toResponseDtos(List<Station> stations) {
        List<StationDTO> stationDTOs = new ArrayList<>();
        stations.forEach(station -> {
            stationDTOs.add(stationMapper.toDto(station));
        });
        return stationDTOs;
    }
}
