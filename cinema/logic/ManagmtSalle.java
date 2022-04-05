package cinema.logic;

import cinema.entity.SalleCinema;
import cinema.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManagmtSalle {


    // dans l'exercice il y a une seule salle de cinéma.
    @Autowired
    SalleCinema getInitSalle;


    public boolean isSeatExist(Seat seat) {

        return getInitSalle.getSeats().contains(seat);

    }

    public boolean isSeatAvailable(Seat seat) {

        return seat.isAvailable();
    }

    public void bookSeat(Seat seat) {

        seat.setAvailable(false);

        seat.setToken(this.generateToken());

        getInitSalle.minusOneToAvailableSeatAndAddOnePurchase(seat);

    }

    public String generateToken() {

        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    public void unBooked(Seat seat) {

        seat.setAvailable(true);
        seat.setToken("");

        getInitSalle.addOneToAvailableSeatAndMinusOnePurchase(seat);

    }

}


