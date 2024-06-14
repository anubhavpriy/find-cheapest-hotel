package main;

import java.util.Objects;

public class Hotel {
    private final double rate;
    private final String name;

    public Hotel(double rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    public double getRate() {
        return rate;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return Double.compare(hotel.getRate(), getRate()) == 0 &&
                Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRate(), name);
    }
}
