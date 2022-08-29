// --== CS400 File Header Information ==--
// Name: Heewon Kim
// Email: hkim936@wisc.edu
// Notes to Grader: Thanks!

import java.util.NoSuchElementException;

/**
 * This is interface
 * @param <ingredients>
 * @param <MenuName>
 */
public interface MapADT <ingredients, MenuName, MenuInfo> {

    public boolean put(ingredients key, MenuName value, MenuInfo info);
    public MenuName getName(ingredients key) throws NoSuchElementException;
    public MenuInfo getInfo(ingredients key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(ingredients key);
    public MenuName remove(ingredients key);
    public void clear();

}
