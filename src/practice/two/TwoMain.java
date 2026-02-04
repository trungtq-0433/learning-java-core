import practice.two.*;

Scanner sc = new Scanner(System.in);
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy");

String input(String label) {
    IO.println(label + ": ");
    return sc.nextLine();
}

int inputInt(String label) {
    return Integer.parseInt(input(label));
}

double inputDouble(String label) {
    return Double.parseDouble(input(label));
}

LocalDate inputDate(String label) {
    return LocalDate.parse(input(label + " (d/M/yyyy)"), fmt);
}

void main() {
    GoodsManager goodsManager = new GoodsManager();
    while (true) {
        try {
            IO.println("1: Food | 2: Electrical | 3: Crockery | 4: View | 0: Exit");
            int type = inputInt("Type of goods");
            if (type == 0) break;
            if (type == 4) goodsManager.displayAll();

            if (type == 1 || type == 2 || type == 3) {
                String code = input("Code"), name = input("Name");
                int inventoryQuantity = inputInt("Inventory Quantity");
                double unitPrice = inputDouble("Unit Price");

                Goods g = switch (type) {
                    case 1 ->
                            new Food(code, name, inventoryQuantity, unitPrice, inputDate("mfgDate"), inputDate("expDate"), input("Supplier"));
                    case 2 ->
                            new Electrical(code, name, inventoryQuantity, unitPrice, inputInt("Warranty months"), inputInt("Capacity KW"));
                    case 3 ->
                            new Crockery(code, name, inventoryQuantity, unitPrice, input("Manufacturer"), inputDate("Date received"));
                    default -> null;
                };

                if (g != null && goodsManager.add(g)) IO.println("Add succeed!");
            } else {
                IO.println("Please choose the correct goods type");
            }

        } catch (Exception e) {
            IO.println(e.getMessage());
        }
    }
}
