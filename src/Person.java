
    public class Person {       //Create a new class called Person


            //Instance variables to store name , surname , and email
        private String name;
        private String surname;
        private String email;

        public Person(String name,String surname,String email){ //constructor to initialize a person object with provided name , surname, and email
            this.name = name;
            this.surname = surname;
            this.email = email;

        }
        public String getName(){   //Getter method to retrieve the name of the person
            return name;
        }
        public String getSurname(){         //Getter method to retrieve the surname of the person

            return surname;
        }
        public String  getEmail(){              ////Getter method to retrieve the email of the person

            return email;
        }

        public void setName (String Name){ //setters

            this.name=name;
        }

        public void setSurname(String Surname)
        {
            this.surname=surname;
        }
        public void setEmail(String Email){ this.email=email;

        }
        public void  printPersonInfor(){                // Method to print the information of the Person
            System.out.println("Name:" +name);
            System.out.println("Surname:" + surname);
            System.out.println("Email:" + email);
        }


    }



