package practice.two;

import java.util.ArrayList;
import java.util.List;

public class GoodsManager {
    private final List<Goods> goodsList = new ArrayList<Goods>();

    public boolean add(Goods g) {
        boolean exists = goodsList.stream()
                .anyMatch(item -> item.getCode().equalsIgnoreCase(g.getCode()));

        if (exists) {
            IO.println("Error: " + g.getCode() + " already exists");
            return false;
        }

        return goodsList.add(g);
    }

    public void displayAll() {
        if (goodsList.isEmpty()) {
            IO.println("Goods list is empty");
        } else {
            goodsList.forEach(item -> IO.println(item));
        }
    }
}
