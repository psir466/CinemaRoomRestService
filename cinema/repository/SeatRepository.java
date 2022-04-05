package cinema.repository;

import cinema.entity.SalleCinema;
import cinema.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class SeatRepository {

    @Autowired
    SalleCinema getInitSalle;

    public Optional<Seat> findSeat(int row, int column, SalleCinema salleCinema) {

        Seat seat = new Seat();
        seat.setRow(row);
        seat.setColumn(column);
        seat.setSalleCinema(salleCinema);

        return salleCinema.getSeats().stream().filter(s -> s.equals(seat)).findFirst();

    }

    public Optional<Seat> findSeatByToken(String token){


        return getInitSalle.getSeats().stream().filter(s -> s.getToken().equals(token)).findFirst();
    }
}
