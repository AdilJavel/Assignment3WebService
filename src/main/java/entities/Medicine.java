package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Medicine {
    private int id;
    private String name;
    private int price;
    private LocalDate date;
    private String manufacturer;
    private boolean byorder;

    public Medicine() {

    }

    public Medicine(String name, int price, LocalDate date, String manufacturer, boolean byorder) {
        setName(name);
        setPrice(price);
        setDate(date);
        setManufacturer(manufacturer);
        setByorder(byorder);
    }

    public Medicine(int id, String name, int price, LocalDate date, String manufacturer, boolean byorder) {
        setId(id);
        setName(name);
        setPrice(price);
        setDate(date);
        setManufacturer(manufacturer);
        setByorder(byorder);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public boolean isByorder() {
        return byorder;
    }
    public void setByorder(boolean byorder) {
        this.byorder = byorder;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", price='" + date + '\'' +
                ", price='" + manufacturer + '\'' +
                ", gender=" + byorder  +
                '}';
    }
}

