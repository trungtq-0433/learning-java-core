import practice.two.Crockery;
import practice.two.Electrical;
import practice.two.Food;
import practice.two.Goods;
import practice.two.GoodsManager;

record BaseInfo(String code, String name, int inventoryQuantity, double unitPrice) {}

final Scanner sc = new Scanner(System.in);
final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);
final Clock clock = Clock.systemDefaultZone();
final GoodsManager goodsManager = new GoodsManager();

void main() {
    while (true) {
        try {
            IO.println("1: Food | 2: Electrical | 3: Crockery | 4: View | 0: Exit");
            switch (inputInt("Type of goods")) {
                case 0 -> { return; }
                case 1 -> add(readFood());
                case 2 -> add(readElectrical());
                case 3 -> add(readCrockery());
                case 4 -> showAll();
                default -> IO.println("Please choose the correct goods type");
            }
        } catch (NoSuchElementException e) {
            return; // stdin closed (Ctrl+D or end of piped input)
        } catch (IllegalArgumentException e) {
            IO.println("Error: " + e.getMessage());
        }
    }
}

Goods readFood() {
    BaseInfo base = readBaseInfo();
    return new Food(base.code(), base.name(), base.inventoryQuantity(), base.unitPrice(),
            inputDate("Mfg date"), inputDate("Exp date"), input("Supplier"), clock);
}

Goods readElectrical() {
    BaseInfo base = readBaseInfo();
    return new Electrical(base.code(), base.name(), base.inventoryQuantity(), base.unitPrice(),
            inputInt("Warranty months"), inputDouble("Capacity KW"));
}

Goods readCrockery() {
    BaseInfo base = readBaseInfo();
    return new Crockery(base.code(), base.name(), base.inventoryQuantity(), base.unitPrice(),
            input("Manufacturer"), inputDate("Date received"), clock);
}

BaseInfo readBaseInfo() {
    String code = input("Code");
    if (goodsManager.exists(code)) {
        throw new IllegalArgumentException("Code " + code + " already exists");
    }
    return new BaseInfo(code, input("Name"), inputInt("Inventory Quantity"), inputDouble("Unit Price"));
}

void add(Goods goods) {
    goodsManager.add(goods);
    IO.println("Add succeed!");
}

void showAll() {
    List<Goods> all = goodsManager.getAll();
    if (all.isEmpty()) {
        IO.println("Goods list is empty");
    } else {
        all.forEach(IO::println);
    }
}

String input(String label) {
    IO.print(label + ": ");
    return sc.nextLine();
}

int inputInt(String label) {
    return parse(label, Integer::parseInt, "an integer");
}

double inputDouble(String label) {
    return parse(label, Double::parseDouble, "a number");
}

LocalDate inputDate(String label) {
    return parse(label + " (d/M/yyyy)", text -> LocalDate.parse(text, fmt), "a valid date");
}

<T> T parse(String label, Function<String, T> parser, String expected) {
    String text = input(label);
    try {
        return parser.apply(text.trim());
    } catch (NumberFormatException | DateTimeParseException e) {
        throw new IllegalArgumentException("%s must be %s, got \"%s\"".formatted(label, expected, text));
    }
}
