package domain;

public class BankAccount {
    private final String bank;
    private final String name;
    private final String number;
    private final String currency;
    private final String type;

    public BankAccount(String bank, String name, String number,
            String currency, String type) {
        super();
        this.bank = bank;
        this.name = name;
        this.number = number;
        this.currency = currency;
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("BankAccount [bank=%s, accountName=%s, accountNumber=%s, currency=%s]",
                             bank, name, number, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BankAccount that = (BankAccount)obj;

        return that.bank.equals(bank) &&
               that.name.equals(name) &&
               that.number.equals(number) &&
               that.currency.equals(currency);
    }

}
