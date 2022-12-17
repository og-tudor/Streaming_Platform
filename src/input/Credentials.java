package input;

public class Credentials {
    /** Enum Type for accounts */
    public enum AccountType {
        standard,
        premium
    }

    private String name;
    private String password;
    private AccountType accountType;
    private String country;
    private String balance;

    public Credentials(final String name, final String password, final AccountType accountType,
                       final String country, final String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public Credentials() {
    }

    public Credentials(final Credentials credentials) {
        this(credentials.getName(), credentials.getPassword(), credentials.getAccountType(),
             credentials.getCountry(), credentials.getBalance());
    }
    /** Getter */
    public String getName() {
        return name;
    }
    /** Setter */
    public void setName(final String name) {
        this.name = name;
    }
    /** Getter */
    public String getPassword() {
        return password;
    }
    /** Setter */
    public void setPassword(final String password) {
        this.password = password;
    }
    /** Getter */
    public AccountType getAccountType() {
        return accountType;
    }
    /** Setter */
    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }
    /** Getter */
    public String getCountry() {
        return country;
    }
    /** Setter */
    public void setCountry(final String country) {
        this.country = country;
    }
    /** Getter */
    public String getBalance() {
        return balance;
    }
    /** Setter */
    public void setBalance(final String balance) {
        this.balance = balance;
    }
    /** Print Credentials */
    @Override
    public String toString() {
        return "Input.User{"
                +
                "name='" + name + '\''
                +
                ", password='" + password + '\''
                +
                ", accountType=" + accountType
                +
                ", country='" + country + '\''
                +
                ", balance=" + balance
                +
                '}';
    }
}

