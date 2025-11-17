package DanhSach;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import KiemTra.KiemTra;
import Nguoi.KhachHang;

public class DanhSachKhachHang {
    private KhachHang[] DsKhachHang;
    private final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String FILE_PATH = "KhachHang.txt";

    public DanhSachKhachHang() {
        this.DsKhachHang = new KhachHang[0];
        docFile();
    }

    public KhachHang[] GetDsKhachHang() {
        return DsKhachHang;
    }

    public void SetDsKhachHang(KhachHang[] DsKhachHang) {
        this.DsKhachHang = DsKhachHang;
    }

    public void them(KhachHang kh) {
        DsKhachHang = Arrays.copyOf(DsKhachHang, DsKhachHang.length + 1);
        DsKhachHang[DsKhachHang.length - 1] = kh;
        System.out.println("Them khach hang thanh cong!");
    }

    public void them() {
        KhachHang kh = new KhachHang();
        kh.nhap();
        them(kh);
        ghiFile();
    }

    public void xoa() {
        System.out.print("Nhap ma khach hang can xoa: ");
        String maKH = sc.nextLine();
        KhachHang khCanXoa = layPhanTuVoi(maKH);

        if (khCanXoa == null) {
            System.out.println("Khong tim thay khach hang voi ma: " + maKH);
            return;
        }

        if (khCanXoa.GetSoSachDangMuon() > 0) {
            System.out.println("Khong the xoa khach hang nay vi ho van dang muon " + khCanXoa.GetSoSachDangMuon() + " cuon sach.");
            return;
        }

        System.out.print("Ban co chac chan muon xoa khach hang " + maKH + " khong? (1-Co, 0-Khong): ");
        int xacNhan = KiemTra.CheckNumber();

        if (xacNhan == 1) {
            KhachHang[] dsMoi = new KhachHang[DsKhachHang.length - 1];
            int j = 0;
            for (KhachHang kh : DsKhachHang) {
                if (!kh.GetMaKhachHang().equalsIgnoreCase(maKH)) {
                    dsMoi[j++] = kh;
                }
            }
            DsKhachHang = dsMoi;
            System.out.println("Da xoa khach hang thanh cong.");
            ghiFile();
        } else {
            System.out.println("Huy thao tac xoa.");
        }
    }

    public void sua() {
        System.out.print("Nhap ma khach hang can sua thong tin: ");
        String maKH = sc.nextLine();
        KhachHang khCanSua = layPhanTuVoi(maKH);

        if (khCanSua == null) {
            System.out.println("Khong tim thay khach hang voi ma: " + maKH);
            return;
        }

        khCanSua.suaThongTin();
        ghiFile();
    }

    public void tim() {
        int Chon;
        do {
            System.out.println("\n=== Tim kiem khach hang (Doc gia) ===");
            System.out.println("1. Tim kiem theo Ma khach hang");
            System.out.println("2. Tim kiem theo Ho ten");
            System.out.println("0. Thoat tim kiem");
            System.out.print("Nhap lua chon: ");
            Chon = KiemTra.CheckNumber();

            List<KhachHang> ketQua = new ArrayList<>();
            String tuKhoa = "";

            if (Chon == 1 || Chon == 2) {
                System.out.print("Nhap tu khoa tim kiem: ");
                tuKhoa = sc.nextLine().toLowerCase();
            }

            switch (Chon) {
                case 1:
                    for (KhachHang kh : DsKhachHang) {
                        if (kh.GetMaKhachHang().toLowerCase().contains(tuKhoa)) {
                            ketQua.add(kh);
                        }
                    }
                    break;
                case 2:
                    for (KhachHang kh : DsKhachHang) {
                        if (kh.getHoten().toLowerCase().contains(tuKhoa)) {
                            ketQua.add(kh);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Thoat tim kiem.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }

            if (Chon == 1 || Chon == 2) {
                if (ketQua.isEmpty()) {
                    System.out.println("Khong tim thay khach hang nao voi tu khoa: " + tuKhoa);
                } else {
                    xuatDanhSach(ketQua.toArray(new KhachHang[0]));
                }
            }
        } while (Chon != 0);
    }

    public KhachHang layPhanTuVoi(String maKhachHang) {
        for (KhachHang kh : DsKhachHang) {
            if (kh.GetMaKhachHang().equalsIgnoreCase(maKhachHang)) {
                return kh;
            }
        }
        return null;
    }

    public void xuatDanhSach() {
        xuatDanhSach(DsKhachHang);
    }

    public void xuatDanhSach(KhachHang[] danhSach) {
        if (danhSach == null || danhSach.length == 0) {
            System.out.println("Danh sach khach hang hien tai dang trong.");
            return;
        }

        System.out.println("\n====================================================== DANH SACH KHAC HANG ======================================================");
        System.out.printf("%-10s | %-30s | %-12s | %-15s | %-12s | %-15s | %-10s\n",
                "Ma KH", "Ho Ten", "Ngay Lap The", "So DT", "So Sach Muon", "Tien Phat", "Email");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        for (KhachHang kh : danhSach) {
            String ngayLapThe = (kh.GetNgayLapThe() != null) ? kh.GetNgayLapThe().format(DATE_FORMATTER) : "N/A";
            System.out.printf("%-10s | %-30s | %-12s | %-15s | %-12d | %-15d | %-10s\n",
                    kh.GetMaKhachHang(),
                    kh.getHoten(),
                    ngayLapThe,
                    kh.getSdt(),
                    kh.GetSoSachDangMuon(),
                    kh.GetTongTienPhat(),
                    kh.getEmail());
        }
        System.out.println("=================================================================================================================================");
        System.out.println("Tong so khach hang: " + danhSach.length);
    }

    public void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (KhachHang kh : DsKhachHang) {
                String ngaySinh = (kh.getNgaythangnamsinh() != null) ? kh.getNgaythangnamsinh() : "";
                String ngayLapThe = (kh.GetNgayLapThe() != null) ? kh.GetNgayLapThe().format(DATE_FORMATTER) : "";
                String dsMaSach = "";
                if (kh.GetDsMaSachDangMuon() != null && kh.GetDsMaSachDangMuon().length > 0) {
                    dsMaSach = String.join(",", kh.GetDsMaSachDangMuon());
                }

                pw.printf("%s|%s|%s|%s|%s|%s|%s|%s|%s|%d|%s\n",
                        kh.GetMaKhachHang(),
                        kh.getHoten(),
                        ngaySinh,
                        kh.getCCCD(),
                        kh.getGioitinh(),
                        kh.getDiachi(),
                        kh.getSdt(),
                        kh.getEmail(),
                        ngayLapThe,
                        kh.GetTongTienPhat(),
                        dsMaSach);
            }
            System.out.println("Ghi danh sach khach hang vao file thanh cong.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi file KhachHang.txt: " + e.getMessage());
        }
    }

    public void docFile() {
        List<KhachHang> tempDs = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 10) {
                    try {
                        String maKH = parts[0];
                        String hoTen = parts[1];
                        String ngaySinh = parts[2];
                        String cccd = parts[3];
                        String gioiTinh = parts[4];
                        String diaChi = parts[5];
                        String sdt = parts[6];
                        String email = parts[7];
                        String ngayLapTheStr = parts[8];
                        int tongTienPhat = Integer.parseInt(parts[9]);
                        String dsMaSachStr = (parts.length > 10) ? parts[10] : "";

                        LocalDate ngayLapThe = null;
                        if (!ngayLapTheStr.isEmpty()) {
                            ngayLapThe = LocalDate.parse(ngayLapTheStr, DATE_FORMATTER);
                        }

                        KhachHang kh = new KhachHang(0, tongTienPhat, maKH, ngayLapThe, hoTen, ngaySinh, cccd, gioiTinh, diaChi, sdt, email);

                        if (!dsMaSachStr.isEmpty()) {
                            kh.SetDsMaSachDangMuon(dsMaSachStr.split(","));
                        } else {
                            kh.SetDsMaSachDangMuon(new String[0]);
                        }

                        tempDs.add(kh);
                    } catch (Exception e) {
                        System.err.println("Loi du lieu tren mot dong trong file KhachHang.txt: " + line);
                    }
                }
            }
            DsKhachHang = tempDs.toArray(new KhachHang[0]);
            System.out.println("Doc " + DsKhachHang.length + " khach hang tu file thanh cong.");
        } catch (IOException e) {
            System.err.println("Loi khi doc file KhachHang.txt: " + e.getMessage());
        }
    }

    public void menu() {
        int Chon;
        do {
            System.out.println("\n======== QUAN LY DANH SACH KHAC HANG ========");
            System.out.println("1. Them khach hang moi");
            System.out.println("2. Xoa khach hang");
            System.out.println("3. Sua thong tin khach hang");
            System.out.println("4. Tim kiem khach hang");
            System.out.println("5. Xuat danh sach khach hang");
            System.out.println("0. Thoat va luu du lieu");
            System.out.print("Nhap lua chon: ");

            try {
                Chon = KiemTra.CheckNumber();
            } catch (Exception e) {
                Chon = -1;
            }

            switch (Chon) {
                case 1: them(); break;
                case 2: xoa(); break;
                case 3: sua(); break;
                case 4: tim(); break;
                case 5: xuatDanhSach(); break;
                case 0: ghiFile(); System.out.println("Thoat chuong trinh quan ly khach hang."); break;
                default: System.out.println("Lua chon khong hop le. Vui long nhap lai.");
            }
        } while (Chon != 0);
    }
}
