package model;

public class CustomerBuilder {

    private String idCustomer;
    private String nama;
    private String email;
    private String alamat;
    private String noHp;

    public CustomerBuilder setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public CustomerBuilder setNama(String nama) {
        this.nama = nama;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public CustomerBuilder setNoHp(String noHp) {
        this.noHp = noHp;
        return this;
    }

    public Customer build() {

        return new Customer(
                idCustomer,
                nama,
                email,
                alamat,
                noHp);

    }

}