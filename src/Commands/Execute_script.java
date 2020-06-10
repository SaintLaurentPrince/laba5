/**
 * Класс команды Execute_script
 * @author SaintLaurentPrince and Mariec
 * @version 1.0
 */

package Commands;

import Controller.Commandable;
import Controller.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Execute_script implements Commandable {

    static ArrayList<String> exScrHistory = new ArrayList<>();
    /**
     * @param arg название файла
     */

    @Override
    public void execute(Object arg) {
        try {
            File file = new File((String) arg);
            Scanner in = new Scanner(file);
            Commands commands = new Commands();

            if (in!= null) {
                while (in.hasNextLine()) {
                    String command=in.nextLine();
                    String[] exArg = command.split(" ");
                    String exFileName= exArg[1];
                    if (!(command.equals(""))) {
                        if (!(command.equals("execute_script " + exFileName))) {
                            System.out.println("Команда \"" + command + "\":");
                            commands.executeCommand(command);
                            System.out.println();
                        }
                        else {
                            if (exScrHistory.contains("execute_script " + exFileName)) {
                                System.out.println("Команда \"" + command + "\": невыполнима во избежание бесконечной рекурсии .");
                            }
                            else {
                                exScrHistory.add("execute_script " + exFileName);
                                System.out.println("Команда \"" + command + "\":");
                                commands.executeCommand(command);
                                System.out.println();
                            }
                        }
                    }
                } exScrHistory.clear();
            } else System.out.println("Указанный файл не найден.");
        } catch (NullPointerException | FileNotFoundException e) {
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
