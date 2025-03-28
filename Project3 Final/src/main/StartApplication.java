package main;

/** This java file exists because I (Andrew Lightfoot) was running into an issue with the error:
 * Error: JavaFX runtime components are missing, and are required to run this application
 *  when I tried to run the program from the main class. I spent a while trying to debug it, but the only solution I
 *  could find was to create a "fake" (in this case named StartApplication.java - this file) that literally only calls
 *  the Main java class. This fixed the issue for me. Valerie and Jing did not run into this issue and could run the
 *  program from Main.java just fine.
 */
public class StartApplication {
    public static void main(String[] args) {
        Main.main(args);
    }
}
