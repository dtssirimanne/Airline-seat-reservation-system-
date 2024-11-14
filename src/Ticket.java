
import java.io.IOException;    // Import Input Output Exception class to the code
import java.io.FileWriter;     // Import FileWriter class to the code



    public class Ticket {       //class to represent a ticket for a plane


        //Instance variables to store ticket information
        private  char row;
        private int seat;
        private int price;
        private Person person;

        public Ticket(char Row, int Seat, int price, Person person) {       //Constructor
            this.row = Row;
            this.seat = Seat;
            this.price = price;
            this.person = person;
        }

        public char getRow() {  //Getters

            return row;
        }

        public int getSeat() {
            return seat;
        }

        public int getprice() {
            return price;
        }

        public Person getperson() {
            return this.person;
        }


        public void setRow(String Row) { // setters
            this.row = row;
        }       //setters

        public void setSeat(int Seat) {
            this.seat = Seat;

        }

        public void setprice(int price) {
            this.price = price;
        }

        public void setperson(Person person) {
            this.person = person;
        }

        public static void saveTicketToFile (String ticketName, Person person){ // method that uses to save Ticket information to the text file
            try {
                FileWriter writer = new FileWriter(ticketName + ".txt");        //create FileWriter object to write to file
                writer.write("-------- Ticket Information: --------- \n");
                writer.write("Seat:" + ticketName + "\n");                      //write seat information
                writer.write("-------- Personal Information: ----------\n");
                writer.write("Name:" + person.getName() + "\n");            //write  the  name into file
                writer.write("Surname:" + person.getSurname() + "\n");
                writer.write("Email:" + person.getEmail() + "\n");
                writer.close();         //close the FileWriter object


            } catch (IOException e) {       //catch IOException if file operation fails
                System.out.println("An error occurred while saving ticket information to file.");
                e.printStackTrace();            //print stack trace for the exception

            }

        }

        // Method to print the information of a Ticket (including the information of the Person)
        public void printTicketsInfor() {
            System.out.println("Ticket Information:");
            System.out.println("Row: " + row);
            System.out.println("Seat: " + seat);
            System.out.println("Price: $" + price);
            System.out.println("Person Information:");
            person.printPersonInfor();
        }




        }





