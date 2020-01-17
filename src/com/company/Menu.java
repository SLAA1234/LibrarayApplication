package com.company;

import java.util.Scanner;

public class Menu {

    public void chooseUserMenu() {
        System.out.println("Please Choose from below: \n 1. I am a user. \n 2. I am a librarian.\n 3. exit.");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        userLogIn();
                        break;

                    case 2:
                        adminLogIn();
                        break;

                    case 3:
                        System.exit(0);
                }
            }
                catch(Exception e){// can select number out of 1-3. fix later
                    System.out.println("You must select a number between 1-3.");

            }
        }
    }

    private void adminLogIn() {
    }

    private void userLogIn() {
    }

    }




