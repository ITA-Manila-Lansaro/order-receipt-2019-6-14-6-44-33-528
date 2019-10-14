package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {

    public static final String printHeader = "======Printing Orders======\n";
    private Order order;
    private StringBuilder output = new StringBuilder();
    private double totalSalesTx = 0d;
    private double total = 0d;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        output.append(printHeader);
        output.append(getCustomerInfo());
        printLineItems();
        printSaleTax("Sales Tax", totalSalesTx);
        printTotalAmount("Total Amount", total);
        return output.toString();
    }

    private void printTotalAmount(String totalAmountTxt, double total) {
        output.append(totalAmountTxt).append('\t').append(total);
    }

    private void printSaleTax(String salesTaxTxt, double totalSalesTx) {
        printTotalAmount(salesTaxTxt, totalSalesTx);
    }

    private void printLineItems() {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription()).append('\t');
            output.append(lineItem.getPrice()).append('\t');
            output.append(lineItem.getQuantity()).append('\t');
            output.append(lineItem.totalAmount()).append('\n');

            double salesTax = calculateSalesTax(lineItem);
            totalSalesTx += salesTax;
            total += calculateTotalAmount(lineItem, salesTax);
        }
    }

    private double calculateSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * .10;
    }

    private double calculateTotalAmount(LineItem lineItem, double salesTax) {
        return lineItem.totalAmount() + salesTax;
    }

    private String getCustomerInfo() {
        return order.getCustomerName() + order.getCustomerAddress();
    }
}