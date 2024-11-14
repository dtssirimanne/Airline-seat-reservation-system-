
import java.util.Scanner;                                    // Import Scanner class to the code



public class w2052088_PlaneManagement {                     //Create a new class called "w2052088_PlaneManagement"
    private static final int Rows = 4;                              // Number of rows in the plane
    private static final int[][] seats = new int[Rows][];               //2D array to represent seats, where each row may have a different number of seats
    private static final int[] seatsPerRow = {14, 12, 12, 14};              //Array storing the number of seats per row
    private static final int seats_Total = 52;                      //Total number of seats in the plane
    //private static final int seats_Available = 0;

   // private static final int seat_Sold = 1;
    private static int totalSales = 0;                  //Total sales made from ticket purchases
    private static final Ticket[] tickets = new Ticket[seats_Total];              //Array to store ticket objects for each seat


    static {                                            //static block to Initialize the seats  array with appropriate length for each row
        for (int i = 0; i < Rows; i++) {
            seats[i] = new int[seatsPerRow[i]];         //Initialize each inner array appropriate length
        }
    }

        public static void main (String[]args){             //Create the Main method

            System.out.println("\n \t Welcome to the Plane Management Application");            //Display the welcome message when the program starts

            Scanner scanner = new Scanner(System.in);                                           //create a new Scanner object to red user input

            while (true) {          //  Get while loop to repeat menu part
                System.out.println("\n***************************************************");
                System.out.println("\n *                 Menu                           *");
                System.out.println("\n***************************************************");
                System.out.println("1) Buy a seat");
                System.out.println("2) Cancel a seat");                                                 //Create the menu
                System.out.println("3) Find first available seat");
                System.out.println("4) Show seating plan");
                System.out.println("5) print tickets information and total sales");
                System.out.println("6) Search ticket");
                System.out.println("0) Quit");
                System.out.println("\n***************************************************");
                System.out.println("Please select an option:");         //  Display to user to "Enter option"

                if(scanner.hasNextInt()) {
                    int option = scanner.nextInt();    //Get user input to variable name "option"


                    switch (option) {               //Switch statement to handle different options
                        case 1:
                            buySeat(scanner);       // In case 1, call the buySeat method
                            break;
                        case 2:
                            cancelSeat(scanner);            // In case 2, call the cancelSeat method
                            break;
                        case 3:
                            findFirstAvailable();       // In case 3, call the findFirstAvailable  method
                            break;
                        case 4:
                            showSeatingPlan();          // In case 4, call the showSeatingPlan method
                            break;
                        case 5:
                            printTicketsInfo();         // In case 5, call the  printTicketsInfo method
                            break;
                        case 6:
                            searchTicket(scanner);      // In case 6, call the  searchTicket method
                            break;
                        case 0:
                            System.out.println("Exiting the program......");  // In case 0 , this case use to quit a program
                            return;


                    }
                }else{
                    scanner.next();
                    System.out.println("Invalid input please enter a valid option ");
                }


            }
        }

        public static void buySeat (Scanner scanner){      //Method to buy a seat
            System.out.println("Enter your name:");
            String name = scanner.next();
            System.out.println("Enter your surname:");          //Ask user to  enter the name , surname, email
            String surname = scanner.next();
            System.out.println("Enter your email:");
            String email = scanner.next();


            Person person = new Person(name, surname, email); //create a Person object

            System.out.println("Enter row letter (A-D):");
            char row = scanner.next().toUpperCase().charAt(0);
            System.out.println("Enter seat number : ");
            int seatNumber = scanner.nextInt();

            if (row < 'A' || row > 'D' || seatNumber < 1 || seatNumber > seatsPerRow[row - 'A']) {          //check if the seat selection is valid
                System.out.println("Invalid seat selection");
                return;
            }


            int price = calculatePrice(row, seatNumber);
            Ticket ticket = new Ticket(row, seatNumber, price, person);


            System.out.println("Name:" + ticket.getperson().getName() + "  " + ticket.getperson().getSurname());        //Display the users name, surname , email and seat number
            System.out.println("Seat Number:" + ticket.getRow() + ticket.getSeat());
            System.out.println("The seat purchased successfully");


            for (int i = 0; i < tickets.length; i++) {          //  Loop through tickets array to find an empty spot to store the ticket
                if (tickets[i] == null) {
                    tickets[i] = ticket;
                    totalSales += price;
                    ticket.saveTicketToFile(" Ticket_" + row + seatNumber, person);  // call the saveTicketToFile method
                    seats[row - 'A'][seatNumber - 1] = 1;
                    break;
                }

            }
        }


        public static int calculatePrice ( char row, int seatNumber){               //Method to calculate the price based on row and seat number
            int price = 0;
            if ((row == 'A' || row == 'B' || row == 'C' || row == 'D') && seatNumber >= 1 && seatNumber <= 5) {
                price = 200;
            } else if ((row == 'A' || row == 'B' || row == 'C' || row == 'D') && seatNumber >= 6 && seatNumber <= 9) {
                price = 150;

            } else if ((row == 'A' || row == 'B' || row == 'C' || row == 'D') && seatNumber >= 10 && seatNumber <= 14) {
                price = 180;
            }
            return price;

        }


        public static void cancelSeat (Scanner scanner){        //Method to cancel a seat
            System.out.println("Enter the row letter (A-D):");
            char row = scanner.next().charAt(0);            //read the row letter from user input
            System.out.println("Enter the seat number:");
            int seatNumber = scanner.nextInt();
            if (row < 'A' || row > 'D' || seatNumber < 1 || seatNumber > 14) {                  //check if the row and seat number are valid
                System.out.println("Invalid seat selection. Please try again.");
                return;
            }

            if (seats[row - 'A'][seatNumber - 1] == 0) {                    //check if the selected seat is already available
                System.out.println("Seat is already available.");
            } else {
                int price = calculatePrice(row, seatNumber);        //calculate the price of the canceled seat
                seats[row - 'A'][seatNumber - 1] = 0;                   //Mark the seat as available
                totalSales -= price;                                //Deduct the price of the canceled seat from total sales
                System.out.println("Seat was successfully canceled.");
            }

        }


        public static void findFirstAvailable () {                  // method to find and print the first available seat        // Method to find the first available seat
            for (int i = 0; i < seats.length; i++) {                //Iterate over each row of seats
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j] == 0) {                         //check if the current seat is available
                        char row = (char) ('A' + i);                 //convert row index to row letter(A,B,C,D)
                        int seatNumber = j + 1;                     //calculate seat number
                        System.out.println("First available seat:" + row + seatNumber);
                        return;
                    }
                }
            }
            System.out.println("unavailable seats,");
        }


        public static void showSeatingPlan () {         //Method to show the seating plan
            System.out.println("Seating Plan:");
            for (int i = 0; i < seats.length; i++) {            //update seat plan and confirm purchase
                char Row = (char) ('A' + i);
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j] == 0) {
                        System.out.print("O\t"); // Display "O" for available seats
                    } else {
                        System.out.print("X\t"); // Display "X" for booked seats
                    }
                }
                System.out.println();
            }
        }


        public static void printTicketsInfo () {    // method to print information about tickets and calculate total sales
            int totalSales = 0;     // Initialize total sales to counter

            System.out.println("Tickets information:");

            for (Ticket ticket : tickets) {             //iterate through the array of tickets
                if (ticket != null) {                       // check if the ticket object is not null
                    int price = calculatePrice(ticket.getRow(), ticket.getSeat());          //calculate the price of the ticket
                    System.out.println("\nSeat:" + ticket.getRow() + ticket.getSeat());
                    System.out.println(" \tPassenger Name:" + ticket.getperson().getName() + " " + ticket.getperson().getSurname());
                    System.out.println("\tPassenger Email:" + ticket.getperson().getEmail());
                    System.out.println("\tPrice: £" + price);
                    totalSales += price;        //Add ticket price to total sales
                }

            }
            System.out.println("\nTotal sales: £" + totalSales);        //print total sales


        }


        public static void searchTicket (Scanner scanner){      // Create a method to search the Tickets
            System.out.println("Enter row letter (A-D):");
            char row = scanner.next().toUpperCase().charAt(0);          //Read the user input
            if (row < 'A' || row > 'D') { // Check if the row input is valid
                System.out.println("Invalid row letter. Please enter a row letter between A and D.");
                return;
            }

            System.out.println("Enter seat number : ");
            int seatNumber = scanner.nextInt();                     //Read the user input
            if (seatNumber < 1 || seatNumber > 14) { // Check if the seat number input is valid
                System.out.println("Invalid seat number. Please enter a seat number between 1 and 14.");
                return;
            }
            boolean found = false;


            for (Ticket ticket : tickets) {                                                                  //Iterate through the tickets array to find a matching ticket
                if (ticket != null && ticket.getRow() == row && ticket.getSeat() == seatNumber){            // Check if the ticket is not null and matches the input row
                    System.out.println("Ticket found:");                                                     //Print message indicating the ticket is found
                    System.out.println("Seat:" + ticket.getRow()+ ticket.getSeat());                            //Display the seat information
                    System.out.println("Passenger Name:" + ticket.getperson().getName()+" "+ ticket.getperson().getSurname());      //Display the Passenger Name
                    System.out.println("Passenger Email:" + ticket.getperson().getEmail());                                             // Display passenger email
                    found = true;                                                                   // Set found flag to true
                    break;                                                                      // Exit the loop since the ticket is found
                }

            }
            if(!found){                                                                          //If ticket is found , print message indicating the seat is available
                System.out.println("This seat is available.");
            }


        }


    }