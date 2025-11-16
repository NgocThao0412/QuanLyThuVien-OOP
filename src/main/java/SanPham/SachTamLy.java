package SanPham;

import KiemTra.KiemTra;

public class SachTamLy extends Sach {
    private String nhaXuatBan;

    public SachTamLy() {
        super();
        setLoaiSach("Sach Tam Ly");
    }

    public SachTamLy(String maSach, String tenSach, String tacGia,
                      int soLuong, int price, String nhaXuatBan) {
        super(maSach, tenSach, tacGia, "Sach Tam Ly", soLuong, price);
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setNhaXuatBan() {
        System.out.print("Nhap nha xuat ban: ");
        boolean check;
        do {
            nhaXuatBan = sc.nextLine();
            check = KiemTra.KiemTraNhaXuatBan(nhaXuatBan);
        } while(!check);
    }

    @Override
    public String in() {
        return super.in() + "\t\tNXB: " + getNhaXuatBan() + "\n\n";
    }

    @Override
    public void nhap() {
        super.nhap();
        setNhaXuatBan();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Nha xuat ban: " + nhaXuatBan);
    }

    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin Sach Tam Ly ===");
            System.out.println("1. Sua thong tin Sach");
            System.out.println("2. Sua nha xuat ban");
            System.out.println("0. Quay ve menu truoc");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    break;
                case 1:
                    super.suaThongTin();
                    break;
                case 2:
                    System.out.println("NXB hien tai: " + getNhaXuatBan());
                    setNhaXuatBan();
                    break;
                default:
                    System.out.println("Hay chon dung so trong menu!!!");
                    break;
            }
        } while(chon != 0);
    }
}
