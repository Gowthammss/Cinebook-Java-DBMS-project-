interface Bookable {
    boolean bookSeats(String[] seatCodes);
    void cancelBooking(String[] seatCodes);
}