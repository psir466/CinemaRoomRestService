package cinema.DTO;

import cinema.entity.SalleCinema;
import cinema.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterDTO {

    @Autowired
    SalleCinema getInSalleCinema;

    public AvailableSeatCinema convertSalleCinemaToAvailableSeatCinema(SalleCinema salleCinema){

        AvailableSeatCinema availableSeatCinema = new AvailableSeatCinema();

        availableSeatCinema.setTotal_columns(salleCinema.getTotal_columns());

        availableSeatCinema.setTotal_rows(salleCinema.getTotal_rows());

        List<SeatDTO> ls = new ArrayList<>();

        for(Seat seat : salleCinema.getSeats()){

            SeatDTO seat1 = new SeatDTO();

            seat1.setColumn(seat.getColumn());
            seat1.setRow(seat.getRow());
            seat1.setPrice(seat.getPrice());

            ls.add(seat1);

        }

        availableSeatCinema.setAvailable_seats(ls);

        return availableSeatCinema;
    }

    public Seat convertSeatDTOWithoutPriceToSeat(SeatDTOWithoutPrice seatDTOWithoutPrice){

        // dans l''exercice il y a une seule salle mais on pourrait
        // dire qu'il y a plusieurs salle et un fauteil appartient à une salle

        Seat seat = new Seat();

        // on force la salle du fauteuil avec la salle initialisée
        seat.setSalleCinema(getInSalleCinema);

        seat.setRow(seatDTOWithoutPrice.getRow());
        seat.setColumn(seatDTOWithoutPrice.getColumn());

        return seat;
    }

    public SeatDTO convertSeatToSeatDTO(Seat seat){

        SeatDTO seatDTO = new SeatDTO();

        seatDTO.setColumn(seat.getColumn());
        seatDTO.setRow(seat.getRow());
        seatDTO.setPrice(seat.getPrice());

        return seatDTO;

    }

    public TicketTockenDTO convertSeatToTicketTockenDTO(Seat seat){

        TicketTockenDTO ticketTockenDTO = new TicketTockenDTO();

        SeatDTO seatDTO = convertSeatToSeatDTO(seat);

        ticketTockenDTO.setTicket(seatDTO);

        ticketTockenDTO.setToken(seat.getToken());

        return ticketTockenDTO;

    }


}
