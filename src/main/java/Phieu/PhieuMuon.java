package Phieu;

import java.util.Date;

import KiemTra.KiemTra.CheckLoi;

public class PhieuMuon extends PhanTu {
    private String maPM;
    private Date ngayMuon;
    private Date ngayTra;
    private String tinhTrang;

    public PhieuMuon() {}

    public PhieuMuon(String maPM, Date ngayMuon, Date ngayTra, String tinhTrang) {
        this.maPM = maPM;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
    }

    // ===== Getter & Setter =====
    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }

    public Date getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(Date ngayMuon) { this.ngayMuon = ngayMuon; }

    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }

    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    // ===== Override =====
   @Override
    public void nhap() {
    System.out.println("\n=== NHAP THONG TIN PHIEU MUON ===");
    do {
        System.out.print("Nhap ma phieu muon: ");
        maPM = sc.nextLine();
    } while (!CheckLoi.KiemTraMaPhieu(maPM));

    String strNgayMuon, strNgayTra;
    do {
        System.out.print("Nhap ngay muon (dd/MM/yyyy): ");
        strNgayMuon = sc.nextLine();
    } while (!CheckLoi.KiemTraNgay(strNgayMuon));

    do {
        System.out.print("Nhap ngay tra (dd/MM/yyyy): ");
        strNgayTra = sc.nextLine();
    } while (!CheckLoi.KiemTraNgay(strNgayTra) ||
             !CheckLoi.KiemTraNgayMuonTra(strNgayMuon, strNgayTra));

    try {
        ngayMuon = sdf.parse(strNgayMuon);
        ngayTra = sdf.parse(strNgayTra);
    } catch (Exception e) {
        System.out.println("Loi dinh dang ngay: " + e.getMessage());
    }

    do {
        System.out.print("Nhap tinh trang (Dang muon / Da tra): ");
        tinhTrang = sc.nextLine();
    } while (!CheckLoi.KiemTraTinhTrang(tinhTrang));
}

    @Override
    public void xuat() {
        System.out.printf("%-10s %-15s %-15s %-15s\n",
                maPM,
                sdf.format(ngayMuon),
                sdf.format(ngayTra),
                tinhTrang);
    }

   @Override
    public void sua() {
    int chon;
    do {
        System.out.println("\n--- CHINH SUA PHIEU MUON ---");
        System.out.println("1. Sua ma phieu");
        System.out.println("2. Sua ngay muon");
        System.out.println("3. Sua ngay tra");
        System.out.println("4. Sua tinh trang");
        System.out.println("0. Thoat");
        System.out.print("Chon: ");
        
        try {
            chon = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Vui long nhap so!");
            chon = -1;
            continue;
        }

        try {
            switch (chon) {
                case 1:
                    do {
                        System.out.print("Nhap ma phieu moi: ");
                        maPM = sc.nextLine();
                    } while (!CheckLoi.KiemTraMaPhieu(maPM));
                    break;

                case 2:
                    String strNgayMuon;
                    do {
                        System.out.print("Nhap ngay muon moi (dd/MM/yyyy): ");
                        strNgayMuon = sc.nextLine();
                    } while (!CheckLoi.KiemTraNgay(strNgayMuon));
                    ngayMuon = sdf.parse(strNgayMuon);
                    break;

                case 3:
                    String strNgayTra;
                    do {
                        System.out.print("Nhap ngay tra moi (dd/MM/yyyy): ");
                        strNgayTra = sc.nextLine();
                    } while (!CheckLoi.KiemTraNgay(strNgayTra) ||
                             !CheckLoi.KiemTraNgayMuonTra(sdf.format(ngayMuon), strNgayTra));
                    ngayTra = sdf.parse(strNgayTra);
                    break;

                case 4:
                    do {
                        System.out.print("Nhap tinh trang moi (Dang muon / Da tra): ");
                        tinhTrang = sc.nextLine();
                    } while (!CheckLoi.KiemTraTinhTrang(tinhTrang));
                    break;

                case 0:
                    System.out.println("Thoat chinh sua!");
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } catch (Exception e) {
            System.out.println("Loi nhap lieu: " + e.getMessage());
        }

    } while (chon != 0);
}
