package com.sm.carwashmonitor.service;

import com.sm.carwashmonitor.dto.StationStatisticsDTO;
import com.sm.carwashmonitor.dto.StatisticsHighlightsDTO;
import com.sm.carwashmonitor.dto.StatisticsSummaryDTO;
import com.sm.carwashmonitor.repository.StatisticsRepository;
import com.sm.carwashmonitor.service.impl.StatisticsServiceImpl;
import com.sm.carwashmonitor.util.CarwashResourceUtil;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTests {
    @Mock
    private StatisticsRepository statisticsRepository;

    @Spy
    private CarwashResourceUtil carwashResourceUtil;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    // Test obj
    StatisticsHighlightsDTO statisticsHighlightsDTO;
    StatisticsSummaryDTO statisticsSummaryDTO;
    StationStatisticsDTO stationStatisticsDTO;
    String dateTimeFrom;
    String dateTimeTo;
    String timezone;

    @BeforeEach
    public void init() {
        statisticsHighlightsDTO = new StatisticsHighlightsDTO();
        statisticsSummaryDTO = new StatisticsSummaryDTO();
        stationStatisticsDTO = new StationStatisticsDTO();
        dateTimeFrom = "2024-09-01T00:00:00";
        dateTimeTo = "2024-09-07T16:23:00";
        timezone = "Europe/Warsaw";
        statisticsHighlightsDTO.setTotalWaterConsumption(1.0F);
        statisticsHighlightsDTO.setTotalWaxConsumption(1.0F);
        statisticsHighlightsDTO.setTotalDetergentConsumption(1.0F);
        statisticsSummaryDTO.setTotalRevenue(1.0F);
        statisticsSummaryDTO.setAllStationStatistics(new ArrayList<>());
        stationStatisticsDTO.setRevenue(1.0f);
        stationStatisticsDTO.setWaterCost(1.0F);
        stationStatisticsDTO.setWaxCost(1.0F);
        stationStatisticsDTO.setDetergentCost(1.0F);
        statisticsSummaryDTO.getAllStationStatistics().add(this.stationStatisticsDTO);
    }

    @Test
    void getStatisticsHighlightsReturnDTO() throws Exception {
        Mockito.when(statisticsRepository.getStatisticsHighlights(
                Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(this.statisticsHighlightsDTO);

        try (MockedStatic<CarwashResourceUtil> mockedStatic = Mockito.mockStatic(CarwashResourceUtil.class)) {
            CarwashResourceUtil.setCosts(this.statisticsHighlightsDTO);
            mockedStatic.verify(() -> CarwashResourceUtil.setCosts(this.statisticsHighlightsDTO));
        }

        StatisticsHighlightsDTO actual = statisticsService.getStatisticsHighlights(
                this.dateTimeFrom,
                this.dateTimeTo,
                this.timezone
        );

        Assertions.assertEquals(this.statisticsHighlightsDTO.getTotalWaterCost(), actual.getTotalWaterCost());
    }

    @Test
    void getStatisticsSummaryReturnDTO() throws Exception {
        List<StationStatisticsDTO> allStationStatistics = new ArrayList<>();
        allStationStatistics.add(this.stationStatisticsDTO);
        Mockito.when(statisticsRepository.getStatisticsSummary(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(allStationStatistics);

        try (MockedStatic<CarwashResourceUtil> mockedStatic = Mockito.mockStatic(CarwashResourceUtil.class)) {
            CarwashResourceUtil.setCosts(this.statisticsSummaryDTO);
            mockedStatic.verify(() -> CarwashResourceUtil.setCosts(this.statisticsSummaryDTO));
        }

        StatisticsSummaryDTO actual = statisticsService.getStatisticsSummary(
                this.dateTimeFrom,
                this.dateTimeTo,
                this.timezone
        );

        Assertions.assertEquals(this.statisticsSummaryDTO.getTotalRevenue(), actual.getTotalRevenue());
    }

}

