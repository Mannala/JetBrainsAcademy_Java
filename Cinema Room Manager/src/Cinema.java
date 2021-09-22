import java.util.*;

public class Cinema {
    static int rows = 0;
    static int seats = 0;
    static int ticketsSold = 0;
    static int income = 0;
    static int totalIncome = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        ticketsSold = 0;
        income = 0;
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            totalIncome = (rows / 2 * seats * 10) + ((rows - (rows / 2)) * seats * 8);
        }

        char[][] array = new char[rows][seats];
        for (int i = 0; i < array.length; i++) {
            Arrays.fill(array[i], 'S');
        }
        cinemaMenu(array);
    }

    public static void cinemaMenu(char[][] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit\n");
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                printCinema(array);
                break;
            case 2:
                buyTicket(array);
                break;
            case 3:
                statistics(array);
                break;
            case 0:
                break;
        }
    }

    public static void statistics(char[][] array) {
        System.out.println("Number of purchased tickets: " + ticketsSold);
        double percentageSeats = (double) ticketsSold / (rows * seats) * 100;
        System.out.printf("Percentage %.2f%% %n", percentageSeats);
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
        cinemaMenu(array);
    }

    public static void buyTicket(char[][] array) {
        Scanner scanner = new Scanner(System.in);
        int price;

        System.out.println("\nEnter a row number:");
        int rowNum = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();
        if (rowNum < 1 | rowNum > array.length | seatNum < 1 | seatNum > array[1].length) {
            System.out.println("Wrong input");
            buyTicket(array);
            return;
        }
        if (array[rowNum - 1][seatNum - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(array);
            return;
        }
        if (array.length * array[0].length <= 60) {
            price = 10;
        } else if (rowNum <= array.length / 2) {
            price = 10;
        } else {
            price = 8;
        }
        System.out.println("Ticket price: $" + price);
        array[rowNum - 1][seatNum - 1] = 'B';
        ticketsSold++;
        income += price;

        cinemaMenu(array);
    }

    public static void printCinema(char[][] array) {
        System.out.print("\nCinema:\n  ");
        for (int i = 1; i <= array[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(1 + i + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        cinemaMenu(array);
    }
}