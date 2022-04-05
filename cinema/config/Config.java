package cinema.config;

import cinema.entity.SalleCinema;
import cinema.entity.Seat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public SalleCinema getInitSalle(){

        SalleCinema salleCinema = new SalleCinema();

        List<Seat> ls = new ArrayList<>();

        salleCinema.setTotal_columns(9);
        salleCinema.setTotal_rows(9);
        salleCinema.setSeats(ls);

        for(int i = 1; i <= salleCinema.getTotal_rows(); i++){

            for(int j = 1; j <= salleCinema.getTotal_columns(); j++){

                Seat s = new Seat();
                s.setColumn(j);
                s.setRow(i);
                s.setSalleCinema(salleCinema);
                s.setAvailable(true);
                s.setToken("");

                if(i <= 4){
                    s.setPrice(10);
                }else{
                    s.setPrice(8);
                }

                salleCinema.getSeats().add(s);
            }


        }

        salleCinema.setNumber_of_available_seats(salleCinema.getSeats().size());

        return salleCinema;
    }

}
