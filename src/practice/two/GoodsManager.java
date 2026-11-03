package practice.two;

import java.util.ArrayList;
import java.util.List;

public class GoodsManager {
    private final List<Goods> goodsList = new ArrayList<>();

    public void add(Goods goods) {
        if (exists(goods.getCode())) {
            throw new IllegalArgumentException("Code " + goods.getCode() + " already exists");
        }
        goodsList.add(goods);
    }

    public boolean exists(String code) {
        return goodsList.stream().anyMatch(item -> item.getCode().equalsIgnoreCase(code));
    }

    public List<Goods> getAll() {
        return List.copyOf(goodsList);
    }
}
