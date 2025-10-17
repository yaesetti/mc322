package items;

import java.io.Serializable;

public interface Item extends Serializable {

    public String getName();

    public void printStatus();
}