import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

interface MenuLoaderInterface {
    MenuRecommendation<String, String, String> loadFile(String csvFilePath) throws FileNotFoundException;
}

public class MenuLoader extends MenuRecommendation<String, String, String> implements MenuLoaderInterface {

    public MenuRecommendation<String, String, String> loadFile(String csvFilePath) throws FileNotFoundException {
        MenuRecommendation<String, String, String> menu = new MenuRecommendation<>();

        try (Scanner inputFile = new Scanner(new File(csvFilePath))) {
            inputFile.useDelimiter(",");
            if (!inputFile.hasNextLine()) {
                throw new FileNotFoundException();
            } else {
                while (inputFile.hasNextLine()) {
                    String start = inputFile.next();
                    String middle = inputFile.next();
                    String end = inputFile.next();
                    menu.put(start, middle, end);
                    if (inputFile.hasNextLine()) {
                        inputFile.nextLine();
                    }
                }
                return menu;
            }
        } catch (Exception e) {
            throw new FileNotFoundException("file is not found");
        }
    }
}
