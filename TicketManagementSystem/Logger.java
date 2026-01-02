package TicketManagementSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
public class Logger {
private static final String LOG_FILE = "resources/logs.txt";
public static void log(String message) {
String timeStampedMessage = LocalDateTime.now() + ": " + message;
System.out.println(timeStampedMessage);
try (Bu`eredWriter writer = new Bu`eredWriter(new FileWriter(LOG_FILE, true))) {
writer.write(timeStampedMessage);
writer.newLine();
} catch (IOException e) {
e.printStackTrace();
}
}
}