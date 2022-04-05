package cinema.entity;

import java.util.List;

public class SalleCinema {

    private int total_rows;
    private int total_columns;
    private List<Seat> seats;
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public void addOneToAvailableSeatAndMinusOnePurchase(Seat seat){
        this.number_of_available_seats++;
        this.number_of_purchased_tickets--;
        this.current_income -= seat.getPrice();
    }

    public void minusOneToAvailableSeatAndAddOnePurchase(Seat seat){
        this.number_of_available_seats--;
        this.number_of_purchased_tickets++;
        this.current_income += seat.getPrice();
    }
}
