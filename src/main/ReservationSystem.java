package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem {
	private final List<Hotel> hotels;

    public ReservationSystem(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Hotel findCheapestHotel() {
        return hotels.stream().sorted(Comparator.comparingDouble(Hotel::getRate)).findFirst().get();
    }
    
    public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();
		
		System.out.println("Please enter the FROM date in format: YYYY-MM-DD");
		LocalDate localFromDate = readDate(inp);
		
		if(localFromDate.isBefore(currentDate)) {
			System.out.println("Please enter a future FROM date.");
			return;
		}

		System.out.println("Please enter the TO date in format: YYYY-MM-DD");
		LocalDate localToDate = readDate(inp);
		
		if(localToDate.isBefore(localFromDate)) {
			System.out.println("TO Date cannot be before FROM Date. Please enter a date after FROM Date!!");
			return;
		}
		
		long numOfDays = ChronoUnit.DAYS.between(localFromDate, localToDate);
//		System.out.println(numOfDays);
		
		Hotel MiamiBeach = new Hotel(80, "Miami Beach");
        Hotel MiamiDowntown = new Hotel(120, "Miami Downtown");
        Hotel MiamiMidtown = new Hotel(100, "Miami Midtown");
        
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(MiamiDowntown);
        hotelList.add(MiamiBeach);
        hotelList.add(MiamiMidtown);
        
		ReservationSystem reservationSystem = new ReservationSystem(hotelList);
		System.out.println(reservationSystem.findCheapestHotel().getName());
		System.out.println("Total fare for stay of "+numOfDays + " days will be: USD " +numOfDays*reservationSystem.findCheapestHotel().getRate());
	}

	private static LocalDate readDate(Scanner scanner) {
		String input = scanner.next();
        try {
            return LocalDate.parse(input);
        } catch (Exception ex) {
            System.out.println("Invalid date format. Please enter date in format: YYYY-MM-DD");
            return null;
        }
	}
}
