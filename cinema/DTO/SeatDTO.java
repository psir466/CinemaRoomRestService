package cinema.DTO;

import cinema.entity.SalleCinema;

public class SeatDTO {

    private int row;
    private int column;
    private int price;

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


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
