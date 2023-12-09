import java.util.Scanner;
import java.util.StringTokenizer;

import db.HashMapDB;
import db.InMemoryDB;

public class InMemoryDBDemo {
    public static void main(String[] args) {
        boolean running = true;
        InMemoryDB database = new HashMapDB();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the In-Memory Database! Type \"help\" to see a list of all the commands.");
        
        while(running) {
            System.out.println("Input you command below: ");
            String input = scanner.nextLine();
            
            StringTokenizer tokenizer = new StringTokenizer(input);
            switch (tokenizer.nextToken().toLowerCase()) {
                case "get":
                    String key = tokenizer.nextToken();
                    Integer val = database.get(key);
                    System.out.println("The value of " + key + " is " + val + ".");
                    break;
                case "put":
                    String key1 = tokenizer.nextToken();
                    Integer val1 = Integer.parseInt(tokenizer.nextToken());
                    database.put(key1, val1);
                    System.out.println("The key/pair value of " + key1 + "/" + val1 + " will be placed into the database once you commit your changes.");
                    break;
                case "commit":
                    database.commit();
                    System.out.println("Your transaction have been commited to the database.");
                    break;
                case "rollback":
                    database.rollback();
                    System.out.println("Your transaction has been rolled back and will not be commited to the database.");
                    break;
                case "exit":
                    running = false;
                    break;
                case "begin":
                    database.begin_transaction();
                    System.out.println("A new transaction has begun.");
                    break;
                case "help":
                    System.out.println("List of Commands:\n"
                        + "\"begin\": Begins a new transaction to the database.\n"
                        + "\"get [key]\": Returns the value currently being stored at the specified key.\n"
                        + "\"put [key] [value]\": Places the value specified at the specified key. Note: puts will not be saved until the transaction is committed\n"
                        + "\"commit\": Apply your transaction to the database\n"
                        + "\"rollback\": Clear and close your transaction, keeping the database in its state before the transaction\n"
                        + "\"exit\": Exits the program"
                        + "\"help\": Lists all the possible commands");
                    break;
                default:
                    System.out.println("Invalid command! Please type \"help\" to get a list of all the commands");
                    break;
            }
        }

        System.out.println("Thank you for using the In-Memory Database. Have a nice day!");
        scanner.close();
    }
}