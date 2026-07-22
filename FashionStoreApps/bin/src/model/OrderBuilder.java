package model;

public class OrderBuilder {

    private String idOrder;
    private String tanggal;
    private String idCustomer;
    private String idProduct;
    private int qty;
    private double total;

    public OrderBuilder setIdOrder(String idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public OrderBuilder setTanggal(String tanggal) {
        this.tanggal = tanggal;
        return this;
    }

    public OrderBuilder setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public OrderBuilder setIdProduct(String idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public OrderBuilder setQty(int qty) {
        this.qty = qty;
        return this;
    }

    public OrderBuilder setTotal(double total) {
        this.total = total;
        return this;
    }

    public Order build() {
        return new Order(
                idOrder,
                tanggal,
                idCustomer,
                idProduct,
                qty,
                total
        );
    }

}