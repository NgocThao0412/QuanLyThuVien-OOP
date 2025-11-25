package Phieu;

import DanhSach.DanhSachSach;
import KiemTra.KiemTra;
import SanPham.PhanTu;
import SanPham.Sach;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhieuMuon extends PhanTu {
    private String maPM;
    private String maDG;
    private String maTL;
    private Date ngayMuon;
    private Date ngayTra;
    private String tinhTrang;
    private int soLuongMuon;


    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FILE_NAME = "dspm.txt";

    public PhieuMuon() {}

    public PhieuMuon(String maPM, String maDG, String maTL, Date ngayMuon, Date ngayTra, String tinhTrang, int soLuongMuon) {
    this.maPM = maPM;
    this.maDG = maDG;
    this.maTL = maTL;
    this.ngayMuon = ngayMuon;
    this.ngayTra = ngayTra;
    this.tinhTrang = tinhTrang;
    this.soLuongMuon = soLuongMuon;
}


    // ===== Getter - Setter =====
    public String getMaPM() { return maPM; }
    public void setMaPM(String maPM) { this.maPM = maPM; }

    public String getMaDG() { return maDG; }
    public void setMaDG(String maDG) { this.maDG = maDG; }

    public String getMaTL() { return maTL; }
    public void setMaTL(String maTL) { this.maTL = maTL; }

    public Date getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(Date ngayMuon) { this.ngayMuon = ngayMuon; }

    public Date getNgayTra() { return ngayTra; }
    public void setNgayTra(Date ngayTra) { this.ngayTra = ngayTra; }

    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    public String getNgayMuonString() { return sdf.format(ngayMuon); }
    public String getNgayTraString() { return (ngayTra != null ? sdf.format(ngayTra) : "");}

    public int getSoLuongMuon() { return soLuongMuon; }
    public void setSoLuongMuon(int soLuongMuon) { this.soLuongMuon = soLuongMuon; }



    // ===== Nhập =====
    @Override
    public void nhap() {
        System.out.println("\n=== NHAP THONG TIN PHIEU MUON ===");

        do {
            System.out.print("Nhap ma phieu muon: ");
            maPM = sc.nextLine();
        } while (!KiemTra.KiemTraMaPhieu(maPM) || !KiemTra.KiemTraMaPhieuTrung(maPM)); ;

        do {
            System.out.print("Nhap ma doc gia: ");
            maDG = sc.nextLine();
        } while (!KiemTra.KiemTraMaKH(maDG));

        do {
    System.out.print("Nhap ma sach: ");
    maTL = sc.nextLine().trim();

    if (maTL.startsWith("TN")) {
        if (KiemTra.KiemTraMaSachTN(maTL)) 
            break;
    } else if (maTL.startsWith("TT")) {
        if (KiemTra.KiemTraMaSachTT(maTL)) 
            break;
    } else if (maTL.startsWith("TL")) {
        if (KiemTra.KiemTraMaSachTL(maTL)) 
            break;
    } else {
        System.out.println("Loi: Ma sach phai bat dau bang TN, TT hoac TL va co it nhat 2 chu so. VD: TN01, TT02, TL03");
    }

     } while (true);
        
        do {
            System.out.print("Nhap so luong muon: ");
         try {
            soLuongMuon = Integer.parseInt(sc.nextLine());
         } catch (Exception e) { soLuongMuon = -1; }
        } while (soLuongMuon <= 0);

       // ===== Nhập ngày mượn =====
    String strNgayMuon, strNgayTra;
     Date now = new Date();

// --- Nhập ngày mượn ---
    while (true) {
    System.out.println("\nChon cach nhap ngay muon:");
    System.out.println("1. Lay ngay hien tai");
    System.out.println("2. Tu nhap ngay");
    System.out.print("Chon: ");

    String chon = sc.nextLine();

    if (chon.equals("1")) {
        ngayMuon = now;
        strNgayMuon = sdf.format(now);
        System.out.println("Ngay muon duoc chon: " + strNgayMuon);
        break;
    } 
    else if (chon.equals("2")) {
        System.out.print("Nhap ngay muon (dd/MM/yyyy): ");
        strNgayMuon = sc.nextLine();
        if (KiemTra.KiemTraNgay(strNgayMuon)) {
            try { ngayMuon = sdf.parse(strNgayMuon); } catch (Exception e) {}
            break;
        } else {
            System.out.println("Ngay khong hop le!");
        }
    } 
    else {
        System.out.println("Lua chon khong hop le!");
    }
}

// --- Nhập ngày trả ---
    while (true) {
    System.out.println("\nChon cach nhap ngay tra:");
    System.out.println("1. Lay ngay hien tai");
    System.out.println("2. Tu nhap ngay");
    System.out.print("Chon: ");

    String chon = sc.nextLine();

    if (chon.equals("1")) {
        ngayTra = now;
        strNgayTra = sdf.format(now);

        if (KiemTra.KiemTraNgayMuonTra(strNgayMuon, strNgayTra))
            break;
        else {
            System.out.println("Ngay tra phai sau ngay muon!");
        }
    } 
    else if (chon.equals("2")) {
        System.out.print("Nhap ngay tra (dd/MM/yyyy): ");
        strNgayTra = sc.nextLine();

        if (KiemTra.KiemTraNgay(strNgayTra) &&
            KiemTra.KiemTraNgayMuonTra(strNgayMuon, strNgayTra)) {

            try { ngayTra = sdf.parse(strNgayTra); } catch (Exception e) {}
            break;
        } else {
            System.out.println("Ngay tra khong hop le!");
        }
    } 
    else {
        System.out.println("Lua chon khong hop le!");
    }
    }

        try {
            ngayMuon = sdf.parse(strNgayMuon);
            ngayTra = sdf.parse(strNgayTra);
        } catch (Exception e) {
            System.out.println("Loi dinh dang ngay!");
        }

        do {
            System.out.print("Nhap tinh trang (Dang muon / Da tra): ");
            tinhTrang = sc.nextLine();
        } while (!KiemTra.KiemTraTinhTrang(tinhTrang));
    }

    @Override
    public void xuat() {
    System.out.println("============================================================ Thong tin phieu muon ================================================");

    System.out.println("Ma phieu muon: " + maPM);
    System.out.println("Ma doc gia: " + maDG);
    System.out.println("Ma tai lieu: " + maTL);
    System.out.println("So luong muon: " + soLuongMuon);
    System.out.println("Ngay muon: " + sdf.format(ngayMuon));
    
    if (ngayTra != null)
        System.out.println("Ngay tra: " + sdf.format(ngayTra));
    else
        System.out.println("Ngay tra: ");
    

    System.out.println("Tinh trang: " + tinhTrang);

   DanhSachSach dss = new DanhSachSach();    
   Sach[] ds = dss.getDsSach();
   int soLuongSach = dss.getSoLuong();

   System.out.println("============================================================ Danh sach sach muon =================================================");

   for (int i = 0; i < soLuongSach; i++) {
    Sach s = ds[i];

    if (s.getmaSach().equalsIgnoreCase(maTL)) {
        
        String nxb = "";
        try {
            nxb = (String) s.getClass().getMethod("getNhaXuatBan").invoke(s);
        } catch (Exception e) {
            nxb = "";
        }

        System.out.printf(
            "Ma sach:%-12s  Ten sach:%-28s  Tac gia:%-20s  Loai sach:%-15s  Nha xuat ban:%-20s\n",
            s.getmaSach(),
            s.gettenSach(),
            s.getTacGia(),
            s.getLoaiSach(),
            nxb
        );

        System.out.printf(
            "So luong muon:%-10d  Gia:%-10d\n",
            soLuongMuon,
            s.getPrice()
        );
    }
}
}

    // ===== Sửa =====
    @Override
    public void suaThongTin() {
        int chon;

        do {
            System.out.println("\n--- CHINH SUA PHIEU MUON ---");
            System.out.println("1. Sua ma doc gia");
            System.out.println("2. Sua ma tai lieu");
            System.out.println("3. Sua ngay muon");
            System.out.println("4. Sua ngay tra");
            System.out.println("5. Sua tinh trang");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhap so khong hop le!");
                chon = -1;
                continue;
            }

            try {
                switch (chon) {
                    case 1:
                        do {
                            System.out.print("Nhap ma doc gia moi: ");
                            maDG = sc.nextLine();
                           } while (!KiemTra.KiemTraMaKH(maDG));
                            break;

                    case 2:
                        do {
                            System.out.print("Nhap ma tai lieu moi: ");
                            maTL = sc.nextLine();
                        } while (!KiemTra.KiemTraMaSachTL(maTL));
                         break;

                    case 3:
                        boolean validNgayMuon = false;
                        do {
                        try {
                           System.out.print("Nhap ngay muon moi: ");
                           ngayMuon = sdf.parse(sc.nextLine());
                           validNgayMuon = true;
                    } catch (ParseException e) {
                           System.out.println("Loi: Dinh dang ngay muon sai! Phai la dd/MM/yyyy.");
                    }
                        } while (!validNgayMuon);
                         break;

                    case 4:
                        boolean validNgayTra = false;
                        do {
                        try {
                          System.out.print("Nhap ngay tra moi: ");
                          ngayTra = sdf.parse(sc.nextLine());
                          if (KiemTra.KiemTraNgayMuonTra(sdf.format(ngayMuon), sdf.format(ngayTra))) {
                         validNgayTra = true;
                        }
                    }  catch (ParseException e) {
                        System.out.println("Loi: Dinh dang ngay tra sai! Phai la dd/MM/yyyy.");
                    }
                        } while (!validNgayTra);
                        break;

                    case 5:
                          do {
                              System.out.print("Nhap tinh trang moi: ");
                              tinhTrang = sc.nextLine();
                              } while (!KiemTra.KiemTraTinhTrang(tinhTrang));
                        break;
                    case 0:
                        System.out.println("Thoat sua!");
                        break;

                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (Exception e) {
                System.out.println("Loi nhap lieu: " + e.getMessage());
            }

        } while (chon != 0);
    }

    // ===== Đọc file =====
    public static PhieuMuon[] docFile() {
        PhieuMuon[] ds = new PhieuMuon[200];
        int n = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("#");

               if (p.length == 7) {
                   Date ngayMuon = sdf.parse(p[3]);
                   Date ngayTra = sdf.parse(p[4]);
                   int sl = Integer.parseInt(p[6]);

                 ds[n++] = new PhieuMuon(p[0], p[1], p[2], ngayMuon, ngayTra, p[5], sl);
                }

            }

        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }

        return Arrays.copyOf(ds, n);
    }

    // ===== Ghi file =====
    public static void ghiFile(PhieuMuon[] ds) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (PhieuMuon pm : ds) {
                if (pm == null) continue;

                bw.write(pm.maPM + "#" + pm.maDG + "#" + pm.maTL + "#" +
                         sdf.format(pm.ngayMuon) + "#" +
                         sdf.format(pm.ngayTra) + "#" +
                         pm.tinhTrang + "#" +
                         pm.soLuongMuon);
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
}
