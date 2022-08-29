// --== CS400 File Header Information ==--
// Name: Heewon Kim
// Email: hkim936@wisc.edu
// Notes to Grader: Thanks!

/*** JUnit imports ***/
// We will use the BeforeEach and Test annotation types to mark methods in
// our test class.
// The Assertions class that we import from here includes assertion methods like assertEquals()
// which we will used in test1000Inserts().
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
// More details on each of the imported elements can be found here:
// https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/package-summary.html
/*** JUnit imports end  ***/

        import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class MenuRecommendation<Ingredients, MenuName, MenuInfo> implements MapADT<Ingredients,
        MenuName, MenuInfo> {
    protected LinkedList<Node<Ingredients, MenuName, MenuInfo> >[] KeyVal;
    Node<Ingredients, MenuName, MenuInfo> node;
    int capacity;
    int size;

    /**
     * This is a constructor method
     *
     * @param capacity the value that initialize the capacity
     * @param menu
     */
    @SuppressWarnings("unchecked")
    public MenuRecommendation(int capacity, LinkedList<Ingredients> menu) {
        this.capacity = capacity;
        size = 0;
        this.KeyVal =
                (LinkedList<Node<Ingredients, MenuName, MenuInfo>>[]) new LinkedList<?>[capacity];
    } // with this capacity

    /**
     * This is the constructor method
     */
    @SuppressWarnings("unchecked")
    public MenuRecommendation() {
        this.capacity = 10;
        this.KeyVal = (LinkedList<Node<Ingredients, MenuName, MenuInfo>>[]) new LinkedList<?>[capacity];
    } // with default capacity = 10

    /**
     * This method help to rehash and update new list.
     */
    @SuppressWarnings("unchecked")
    public void rehashing() {
        this.capacity = capacity * 2;

        LinkedList<Node<Ingredients, MenuName, MenuInfo>>[] newKeyVal =
                (LinkedList<Node<Ingredients, MenuName, MenuInfo>>[]) new LinkedList<?>[capacity]; // Create
        // a new Linked list

        for (int i = 0; i < this.capacity; i++) {
            newKeyVal[i] = new LinkedList<>(); // initialize empty list which capacity is doubled
        }

        if (KeyVal != null) { // if KeyVal is not null
            for (LinkedList<Node<Ingredients, MenuName, MenuInfo>> temp : KeyVal) { // when temp and KeyVal
                // is matched
                if (temp != null) { // if temp is not null
                    for (Node<Ingredients, MenuName, MenuInfo> entry : temp) { // when entry is matched temp
                        int index = Math.abs(entry.getKey().hashCode() % this.capacity); // index
                        // to store
                        newKeyVal[index].add(entry); //add entry to new KeyVal in index
                    }
                }
                this.KeyVal = newKeyVal; // store entry to KeyVal
            }
        }
    }

    /**
     * This method helps to put to the list
     *
     * @param key   the key of entry
     * @param value the value of entry
     * @return true after successfully storing a new key-value pair, otherwise false when key
     * that is null or is already in your hash table
     */
    @SuppressWarnings("Unchecked")
    @Override
    public boolean put(Ingredients key, MenuName value, MenuInfo info) {
        node = new Node<>(key, value, info); // Generate new entry
        LinkedList<Node<Ingredients, MenuName, MenuInfo>> newKeyVal =
                new LinkedList<Node<Ingredients, MenuName, MenuInfo>>(); // Generate new linked list
        int index = Math.abs(key.hashCode()) % this.KeyVal.length; // hashcode

        if (key == null) { // if key is null return false
            return false;
        }

        if (KeyVal[index] == null) { // if KeyVal is null add entry and store new KeyVal to table
            newKeyVal.add(node);
            this.KeyVal[index] = newKeyVal;
            this.size++;
            if (((double) size) / ((double) capacity) >= 0.85) {// grow and rehash when its load
                // capacity becomes greater than or equal to 85%
                rehashing();
            }
            return true;
        }
//        System.out.println(node.toString());
        if (KeyVal[index].equals(node.getKey())) {
            return false;
        }
        if (((double) size) / ((double) capacity) >= 0.85) {// grow and rehash when its load
            // capacity becomes greater than or equal to 85%
            rehashing();
        }
        if (!node.hasNext()) { // if the entry has not next, store entry to next KeyVal
            node.setNext(node.next);
            KeyVal[index].add(node);
            size++;
            if (((double) size) / ((double) capacity) >= 0.85) {// grow and rehash when its load
                // capacity becomes greater than or equal to 85%
                rehashing();
            }
            return true;
        }
        if (((double) size) / ((double) capacity) >= 0.85) {// grow and rehash when its load
            // capacity becomes greater than or equal to 85%
            rehashing();
        }
        return true;
    }

    /**
     * This method helps to get the value of the key that was passed
     *
     * @param key key of the entry
     * @return Value of the Entry
     * @throws NoSuchElementException if key is null or when it is not found
     */
    @Override
    public MenuName getName(Ingredients key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException();
        }
        int index = Math.abs(key.hashCode()) % this.capacity; // hashcode

        // immediately move to hashcode

        if (KeyVal[index] != null) {
            for (int i = 0; i < KeyVal[index].size(); i++) {

                if (key.equals(KeyVal[index].get(i).getKey())) {
                    return KeyVal[index].get(i).getVal();
                } else if (node.hasNext()) {
                    if (key.equals(node.getNext())) {
                        return KeyVal[index].get(i).getVal();
                    }
                }

            }
        }
        throw new NoSuchElementException("Error: NoSuchElementException");
    }
    @Override
    public MenuInfo getInfo(Ingredients key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException();
        }
        int index = Math.abs(key.hashCode()) % this.capacity; // hashcode

        // immediately move to hashcode

        if (KeyVal[index] != null) {
            for (int i = 0; i < KeyVal[index].size(); i++) {

                if (key.equals(KeyVal[index].get(i).getKey())) {
                    return KeyVal[index].get(i).getInfo();
                } else if (node.hasNext()) {
                    if (key.equals(node.getNext())) {
                        return KeyVal[index].get(i).getInfo();
                    }
                }

            }
        }
        throw new NoSuchElementException("Error: NoSuchElementException");
    }
    @Override
    public int size() {
        return 0;
    }

    /**
     * This method checks whether the list contain the key
     *
     * @param key the key of entry
     * @return true if KeyVal contains entry key, otherwise false
     */
    @Override
    public boolean containsKey(Ingredients key) {
        for (int i = 0; i < KeyVal.length; i++) {
            if (KeyVal[i].element().getNext() != null) {
                if (KeyVal[i].element().getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method removes key and value
     *
     * @param key the key of the entry
     * @return null after removes given key
     */
    @Override
    public MenuName remove(Ingredients key) {
        int index = Math.abs(key.hashCode()) % this.capacity;

        for (Node<Ingredients, MenuName, MenuInfo> node : KeyVal[index]) {
            if (key.equals(node.getKey())) {
                this.KeyVal[index] = null;
                this.size--;
                return node.getVal();
            }
        }

        return null;
    }

    /**
     * clear all KeyVal
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.capacity; i++) {
            KeyVal[i] = null;
        }
        this.size = 0;
    }
//    protected MenuRecommendation<String, String> menu = null;
//
//    @BeforeEach
//    public void menu() {
//        this.menu = new MenuRecommendation<>();
//
//    }

//    @Test
//    public void test1() {
//        MenuRecommendation<String, String> menu = new MenuRecommendation<>();
//        try {
//            menu = menu.loadFile("/Users/heewonkim/IdeaProjects/CS400 Project/src/menuList" +
//                    ".txt");
//ass
////           menu.get(null);
////           menu.get("");
//            Throwable exception  = assertThrows(FileNotFoundException.class, () -> {
//                throw new IllegalArgumentException("a message");});
//            assertEquals(menu.get(""), exception.getMessage());
//        } catch (NoSuchElementException | NullPointerException | FileNotFoundException e) {
//            System.out.println(e.getMessage());
////            assertEquals(menu.get(""),e.getMessage());
//        }
//    }

//    public static boolean test2() {
//        MenuRecommendation<String, String> menu = new MenuRecommendation<>();
//        try {
//            menu = menu.loadFile("/Users/heewonkim/IdeaProjects/CS400 Project/src/menuList.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (!menu.get("orange").equals("Orange Revolution")
//                || !menu.get("strawberry").equals("Strawberry X-Treme")
//                || !menu.get("mango").equals("MangoFest")) {
//            return false;
//        }
//        return true;
//    }



//    public MenuRecommendation<String, String> loadFile(String csvFilePath) throws FileNotFoundException {
//        MenuRecommendation<String, String> menu = new MenuRecommendation<>();
//
//        try (Scanner inputFile = new Scanner(new File(csvFilePath))) {
//            inputFile.useDelimiter(",");
//            if (!inputFile.hasNextLine()) {
//                throw new FileNotFoundException();
//            } else {
//                while (inputFile.hasNextLine()) {
//                    String start = inputFile.next();
//                    String end = inputFile.next();
//                    menu.put(start, end);
//                    if (inputFile.hasNextLine()) {
//                        inputFile.nextLine();
//                    }
//                }
//                return menu;
//            }
//        } catch (Exception e) {
//            throw new FileNotFoundException("file is not found");
//        }
//    }

    public static void main(String[] args) {
        MenuRecommendation<String, String, String> menu;
        MenuLoader menuList = new MenuLoader();

        try {
            menu = menuList.loadFile("/Users/heewonkim/IdeaProjects/CS400 Project/src/menuList" +
                    ".txt");
//            menu = menu.loadFile("/Users/heewonkim/IdeaProjects/CS400 Project/src/menuList.txt");
            System.out.println(menu.capacity);
//            menu.put(menuList.node.getKey(),menuList.node.getVal());
            System.out.println(menu.getName("orange"));
//            System.out.println(menu.put(menuList., menuList.KeyVal));
            Scanner scnr = new Scanner(System.in);
            Scanner yN = new Scanner(System.in);
            String fruit;
            String yesNo;
            System.out.println("_____________________________");
            System.out.println("What is your favorite fruits?");
            System.out.println("Pick one among: ");
            System.out.println("Strawberry"
                    + "\n" + "Orange"
                    + "\n" + "Lemon"
                    + "\n" + "Blueberry"
                    + "\n" + "Banana"
                    + "\n" + "Chocolate"
                    + "\n" + "Pineapple"
                    + "\n" + "Cherry"
                    + "\n" + "Mango"
                    + "\n" + "Vegan"
                    + "\n" + "Kiwi"
                    + "\n" + "Coconut"
                    + "\n" + "Apple");

            try {
                while (scnr.hasNext()) {
                    fruit = scnr.nextLine().toLowerCase(Locale.ROOT);
                    if (menu.containsKey(fruit.toLowerCase(Locale.ROOT))){
                    System.out.println("@ " + menu.getName(fruit) + " is the best for you!");
                    System.out.println("Do you want to get menu's Information? : y or n");
                    }else if(!menu.containsKey(fruit)){
                        System.out.println("Not found! Please search another:");
                    }
                    yesNo = yN.nextLine().toLowerCase(Locale.ROOT);
                    if (yesNo.equalsIgnoreCase("exit") || fruit.equalsIgnoreCase("exit")){
                        break;
                    }
                    if (yesNo.equals("y")){
                        System.out.println("@ Info" + menu.getInfo(fruit));
                        System.out.println("You can sear more SMOOTHIE! Type:");
                    }else if (yesNo.equals("n")) {
                        System.out.println("_____________________________");
                        System.out.println("Have a healthy day!");
                        System.out.println("_____________________________");
                        System.out.println("If you don't know what do you want TYPE: I don't know! or Search more!");
                    }else {
                        System.out.println("_____________________________");
                        System.out.println("Have a healthy day!");
                        System.out.println("_____________________________");
                        System.out.println("If you don't know what do you want TYPE: I don't know! or Search more!");
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("You don't know what you want?");
                System.out.println("No worry! I will recommend best 3 Smoothies for ya!\n");
                System.out.println("Top 3!");
                System.out.println("No.1 " + menu.getName("strawberry"));
                System.out.println("No.2 " + menu.getName("orange"));
                System.out.println("No.3 " + menu.getName("banana"));
            }
            System.out.println("_____________________________");
            System.out.println("Have a healthy day!");
            System.out.println("_____________________________");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
