package domain;


public class BankAccountBuilder {

    private String bank;
    private String name;
    private String number;
    private String currency = "NZD";
    private String type = "Other";

    private BankAccountBuilder() {}

    public static BankAccountBuilder any() {
        return new BankAccountBuilder();
    }


    public BankAccountBuilder withBank(String bank) {
        this.bank = bank;
        return this;
    }

    public BankAccountBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BankAccountBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public BankAccountBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(bank, name, number, currency, type);
    }
}
