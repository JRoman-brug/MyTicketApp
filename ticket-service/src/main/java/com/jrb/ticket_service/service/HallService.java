package com.jrb.ticket_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.dtos.SeatDTOs;
import com.jrb.ticket_service.entity.Hall;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.exception.ErrorCode;
import com.jrb.ticket_service.exception.HallNotFound;
import com.jrb.ticket_service.mapper.HallMapper;
import com.jrb.ticket_service.mapper.SeatMapper;
import com.jrb.ticket_service.repository.HallRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HallService {
    private HallRepository hallRepository;
    private HallMapper hallMapper;
    private SeatMapper seatMapper;

    public HallService(HallRepository hallRepository, HallMapper hallMapper, SeatMapper seatMapper) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
        this.seatMapper = seatMapper;
    }

    // [
    // ["X"," ","X"]
    // ["X"," ","X"]
    // ]
    public HallDTOs.Response createHall(HallDTOs.CreateRequest dto) {
        Hall newHall = hallMapper.toEntity(dto);
        List<Seat> seats = seatResolver(dto.schema(), dto.rowLabels(), dto.columnLabels());
        newHall.addSeats(seats);
        Hall savedHall = hallRepository.save(newHall);
        return hallMapper.toResponse(savedHall);
    }

    private List<Seat> seatResolver(List<List<String>> schema, List<String> rowLabels, List<String> columnsLabels) {
        return IntStream.range(0, schema.size()).boxed()
                .flatMap(rowIndex -> IntStream.range(0, schema.get(rowIndex).size())
                        .mapToObj(columnIndex -> createSeatIfPresent(rowIndex, columnIndex, schema, rowLabels,
                                columnsLabels)))
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<Seat> createSeatIfPresent(int row, int column, List<List<String>> schema, List<String> rowLabel,
            List<String> columnLabel) {
        String value = schema.get(row).get(column);
        boolean noExist = !"X".equalsIgnoreCase(value);
        if (noExist)
            return Optional.empty();

        String label = getLabel(rowLabel.get(row), columnLabel.get(column));
        Seat seat = Seat.builder()
                .row(row)
                .column(column)
                .label(label)
                .build();
        return Optional.of(seat);
    }

    private String getLabel(String rowLabel, String columnLabel) {
        log.debug("Seat label created: {}{}", columnLabel, rowLabel);
        return columnLabel + rowLabel;
    }

    public HallDTOs.Response getHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new HallNotFound(ErrorCode.HALL_NOT_FOUND));
        return hallMapper.toResponse(hall);
    }

    public List<SeatDTOs.Summary> mapper(List<Seat> seats) {
        return seats.stream().map(seatMapper::toSummary)
                .toList();
    }

    public void deleteHall(Long id) {
        boolean exist = hallRepository.existsById(id);
        if (!exist)
            throw new HallNotFound(ErrorCode.HALL_NOT_FOUND);
        hallRepository.deleteById(id);
    }
}
