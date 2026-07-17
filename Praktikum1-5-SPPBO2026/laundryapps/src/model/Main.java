package model;

public class Main {

    public static void main(String[] args) {

        Costumer costumer = new Costumer();
        costumer.setId(1);
        costumer.setNama("Budi");
        costumer.setAlamat("Padang");
        costumer.setHp("08123456789");

        Service service = new Service();
        service.setId(1);
        service.setJenis("Cuci Kering");
        service.setHarga(12000);
        service.setStatus("Aktif");

        Order order = new Order();
        order.setId(1);
        order.setId_costumer(costumer.getId());
        order.setId_service(service.getId());
        order.setId_user(1);
        order.setTotal(service.getHarga());
        order.setTanggal("08-07-2026");
        order.setTanggal_selesai("09-07-2026");
        order.setStatus("Proses");
        order.setStatus_pembayaran("Belum Lunas");

        System.out.println("===== DATA COSTUMER =====");
        System.out.println("ID      : " + costumer.getId());
        System.out.println("Nama    : " + costumer.getNama());
        System.out.println("Alamat  : " + costumer.getAlamat());
        System.out.println("No HP   : " + costumer.getHp());

        System.out.println("\n===== DATA SERVICE =====");
        System.out.println("ID      : " + service.getId());
        System.out.println("Jenis   : " + service.getJenis());
        System.out.println("Harga   : " + service.getHarga());
        System.out.println("Status  : " + service.getStatus());

        System.out.println("\n===== DATA ORDER =====");
        System.out.println("ID Order             : " + order.getId());
        System.out.println("ID Costumer          : " + order.getId_costumer());
        System.out.println("ID Service           : " + order.getId_service());
        System.out.println("ID User              : " + order.getId_user());
        System.out.println("Total                : " + order.getTotal());
        System.out.println("Tanggal              : " + order.getTanggal());
        System.out.println("Tanggal Selesai      : " + order.getTanggal_selesai());
        System.out.println("Status               : " + order.getStatus());
        System.out.println("Status Pembayaran    : " + order.getStatus_pembayaran());

    }

}