
public class Bank {
    private Customer[] customers;
    private int numberOfCustomers;

    public Bank() {
        this.customers = new Customer[10];   // ukuran maksimum > 5
        this.numberOfCustomers = 0;
    }

    public void addCustomer(String f, String l) {
        if (this.numberOfCustomers < this.customers.length) {
            this.customers[this.numberOfCustomers] = new Customer(f, l);
            this.numberOfCustomers++;
        }
    }

    public int getNumOfCustomers() {
        return this.numberOfCustomers;
    }

    public Customer getCustomer(int index) {
        if (index >= 0 && index < this.numberOfCustomers) {
            return this.customers[index];
        }
        return null;
    }
}