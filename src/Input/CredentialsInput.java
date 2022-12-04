package Input;

public class CredentialsInput {
    public enum AccountType {
        standard,
        premium
    }

    private String name;
    private String password;
    private AccountType accountType;
    private String country;
    private int balance;

    public CredentialsInput(String name, String password, AccountType accountType, String country, int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public CredentialsInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Input.User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                ", country='" + country + '\'' +
                ", balance=" + balance +
                '}';
    }
}

