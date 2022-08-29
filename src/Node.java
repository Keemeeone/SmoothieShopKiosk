// --== CS400 File Header Information ==--
// Name: Heewon Kim
// Email: hkim936@wisc.edu
// Notes to Grader: Thanks!

/**
 * Entry class.
 * @param <Ingredients> This represents key.
 * @param <MenuName> This represents value.
 * @author Heewon Kim
 */
public class Node<Ingredients, MenuName, MenuInfo>{
    private Ingredients key; // key
    private MenuName val;
    private MenuInfo info;// value
    Node<Ingredients, MenuName, MenuInfo> next;

    /**
     * This is a constructor method.
     * @param key the key for entry.
     * @param val the value for entry.
     */
    public Node(Ingredients key, MenuName val, MenuInfo info){ // constructor
        this.key = key; // This is the key
        this.val = val; // This is the value
        this.info = info;
        this.next = null;
    }

    /**
     * This method gets key.
     * @return KeyType the key
     */
    public Ingredients getKey(){
        return key;
    }

    /**
     * This method gets value.
     * @return ValueType the Value
     */
    public MenuName getVal() {
        return val;
    }

    public MenuInfo getInfo() {return  info;}

    /**
     * This method helps to check next index if next index exist.
     * @return true if next index exist, otherwise false.
     */
    public boolean hasNext() {
        if (this.next != null) {
            return true;
        }
        return false;
    }

    /**
     * This method help to set new entry.
     * @param next the entry that needs to be set as next entry.
     */
    public void setNext (Node<Ingredients, MenuName, MenuInfo> next) {
        this.next = next;
    }

    /**
     * This method helps to get next index.
     * @return next next entry
     */
    public Node<Ingredients, MenuName, MenuInfo> getNext() {
        return next;
    }
}
