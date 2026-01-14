package com.jrb.ticket_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.CreateHallDTO;
import com.jrb.ticket_service.dtos.DeleteHallRequestDTO;
import com.jrb.ticket_service.dtos.GetHallResponseDTO;
import com.jrb.ticket_service.dtos.SeatDTO;
import com.jrb.ticket_service.entity.Hall;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.entity.enums.TicketStatus;
import com.jrb.ticket_service.exception.ErrorCode;
import com.jrb.ticket_service.exception.HallNotFound;
import com.jrb.ticket_service.repository.HallRepository;

@Service
public class HallService {
    private HallRepository hallRepository;
    private Logger logger = LoggerFactory.getLogger(HallService.class);

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    // [
    // ["X"," ","X"]
    // ["X"," ","X"]
    // ]
    public void createHall(CreateHallDTO dto) {
        String label = dto.label();
        int columns = dto.columns();
        int rows = dto.rows();
        List<Seat> seats = seatResolver(dto.schema(), dto.rowLabels(), dto.columnLabels());
        Hall newHall = Hall.builder()
                .name(label)
                .totalRows(rows)
                .totalColumns(columns)
                .build();
        newHall.addSeats(seats);
        hallRepository.save(newHall);
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
                .status(TicketStatus.LIBRE)
                .build();
        return Optional.of(seat);
    }

    private String getLabel(String rowLabel, String columnLabel) {
        logger.debug("Seat label created: {}{}", columnLabel, rowLabel);
        return columnLabel + rowLabel;
    }

    public GetHallResponseDTO getHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new HallNotFound(ErrorCode.HALL_NOT_FOUND));
        String label = hall.getName();
        int rows = hall.getTotalRows();
        int columns = hall.getTotalColumns();
        List<SeatDTO> seats = mapper(hall.getSeats());
        return new GetHallResponseDTO(label, rows, columns, seats);
    }

    public List<SeatDTO> mapper(List<Seat> seats) {
        return seats.stream().map(seat -> new SeatDTO(
                seat.getRow(),
                seat.getColumn(),
                seat.getLabel(),
                seat.getStatus()))
                .toList();
    }

    public void deleteHall(DeleteHallRequestDTO dto) {
        boolean exist = hallRepository.existsById(dto.id());
        if (!exist)
            throw new HallNotFound(ErrorCode.HALL_NOT_FOUND);
        hallRepository.deleteById(dto.id());
    }
}
