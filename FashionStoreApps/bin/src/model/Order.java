package model;

public class Order {

    private String idOrder;
    private String tanggal;
    private String idCustomer;
    private String idProduct;
    private int qty;
    private double total;

    public Order(String idOrder,
                 String tanggal,
                 String idCustomer,
                 String idProduct,
                 int qty,
                 double total) {

        this.idOrder = idOrder;
        this.tanggal = tanggal;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.qty = qty;
        this.total = total;

    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public int getQty() {
        return qty;
    }

    public double getTotal() {
        return total;
    }

}