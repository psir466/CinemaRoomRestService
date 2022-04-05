package cinema.controller;

import cinema.DTO.AvailableSeatCinema;
import cinema.DTO.ConverterDTO;
import cinema.DTO.SeatDTO;
import cinema.DTO.SeatDTOWithoutPrice;
import cinema.entity.SalleCinema;
import cinema.entity.Seat;
import cinema.logic.ManagmtSalle;
import cinema.repository.SeatRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {

    //@Autowired   PAS OBLIGATOIRE !!!!
    private final ConverterDTO converterDTO;

    //@Autowired
    private final SalleCinema getInitSalle;

    //@Autowired
    private final ManagmtSalle managmntSalle;

    //@Autowired
    private final SeatRepository seatRepository;

    private Map<String, Integer> stats;

    // Il FAUT FAIRE UN CONSTRUCTEUR POUR LES TESTS
    // @Autowired n'est pas obligatoire si il y a un seul constructeur
    // mais si il y en a plusieurs alors il faut que au moins un ait @Autowired
    public Controller(ConverterDTO converterDTO, SalleCinema getInitSalle, ManagmtSalle managmntSalle, SeatRepository seatRepository, Map<String, Integer> stats) {
        this.converterDTO = converterDTO;
        this.getInitSalle = getInitSalle;
        this.managmntSalle = managmntSalle;
        this.seatRepository = seatRepository;
        this.stats = stats;
    }




    @GetMapping("/seats")
    public AvailableSeatCinema getAvailableSeatCinema(){

        return converterDTO.convertSalleCinemaToAvailableSeatCinema(getInitSalle);

    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeatCinema(@RequestBody SeatDTOWithoutPrice seatDTOWithoutPrice){



        // on force la salle à celle initialiser
        // car dans l'exercice il y a une seule salle
        Optional<Seat> s = seatRepository.findSeat(seatDTOWithoutPrice.getRow(),
                seatDTOWithoutPrice.getColumn(), getInitSalle);

        if(!s.isPresent()){


            System.out.println("not here  row" + seatDTOWithoutPrice.getRow());
            System.out.println("not here  col" + seatDTOWithoutPrice.getColumn());

            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }

        if(!managmntSalle.isSeatAvailable(s.get())){


            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }


        managmntSalle.bookSeat(s.get());

        Object o = converterDTO.convertSeatToTicketTockenDTO(s.get());

        return ResponseEntity.ok(o);

    }

    @PostMapping("/return")
    public ResponseEntity<Object> purchaseSeatCinema(@RequestBody Map<String, String> tockMap){

        String token = tockMap.get("token");

        System.out.println(token);

        Optional<Seat> seat = seatRepository.findSeatByToken(token);

        if(!seat.isPresent()){
            return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }

        managmntSalle.unBooked(seat.get());

        SeatDTO seatDTO = converterDTO.convertSeatToSeatDTO(seat.get());

        return new ResponseEntity(Map.of("returned_ticket", seatDTO), HttpStatus.OK);

    }

    @PostMapping("/stats")
    public ResponseEntity<Object> stats(@RequestParam Map<String,String> allParams){

        if(allParams.size() == 0 || allParams.size() > 1){

            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

        if(!allParams.containsKey("password")){
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

        if(!allParams.get("password").equals("super_secret")){

            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

        stats.put("current_income", getInitSalle.getCurrent_income());
        stats.put("number_of_available_seats", getInitSalle.getNumber_of_available_seats());
        stats.put("number_of_purchased_tickets", getInitSalle.getNumber_of_purchased_tickets());

        return new ResponseEntity(stats, HttpStatus.OK);
    }

}
