package cinema.entity;

import java.util.Objects;

public class Seat {

    private int row;
    private int column;
    private SalleCinema salleCinema;
    private int price;
    private boolean available;
    private String token;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int columns) {
        this.column = columns;
    }

    public SalleCinema getSalleCinema() {
        return salleCinema;
    }

    public void setSalleCinema(SalleCinema salleCinema) {
        this.salleCinema = salleCinema;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column && Objects.equals(salleCinema, seat.salleCinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, salleCinema);
    }
}
