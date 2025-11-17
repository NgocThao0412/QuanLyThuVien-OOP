package Nguoi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DanhSach.DanhSachKhachHang;
import DanhSach.DanhSachSach;
import KiemTra.KiemTra;
import SanPham.Sach;

public class KhachHang extends Nguoi {
    private int SoSachDangMuon;
    private int TongTienPhat;
    private String[] DsMaSachDangMuon;
    private String MaKhachHang;
    private LocalDate NgayLapThe;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public KhachHang() {}

    public KhachHang(int SoSachDangMuon, int TongTienPhat, String MaKhachHang, LocalDate NgayLapThe) {
        this.SoSachDangMuon = SoSachDangMuon;
        this.TongTienPhat = TongTienPhat;
        this.MaKhachHang = MaKhachHang;
        this.NgayLapThe = NgayLapThe;
    }

    public KhachHang(int SoSachDangMuon, int TongTienPhat, String MaKhachHang, LocalDate NgayLapThe, String hoten,
            String ngaythangnamsinh, String CCCD, String gioitinh, String diachi, String sdt, String email) {
        super(hoten, ngaythangnamsinh, CCCD, gioitinh, diachi, sdt, email);
        this.SoSachDangMuon = SoSachDangMuon;
        this.TongTienPhat = TongTienPhat;
        this.MaKhachHang = MaKhachHang;
        this.NgayLapThe = NgayLapThe;
    }

    public int getSoSachDangMuon() {
        return SoSachDangMuon;
    }

    public void setSoSachDangMuon(int SoSachDangMuon) {
        this.SoSachDangMuon = SoSachDangMuon;
    }

    public int getTongTienPhat() {
        return TongTienPhat;
    }

    public void setTongTienPhat(int TongTienPhat) {
        this.TongTienPhat = TongTienPhat;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    // Tạo mã khách hàng tự động
    public void setMaKhachHang() {
        DanhSachKhachHang ttds = new DanhSachKhachHang();
        KhachHang[] dskh = ttds.getDsKhachHang();
        int Stt;
        if (dskh != null && dskh.length != 0) {
            try {
                Stt = Integer.parseInt(dskh[dskh.length - 1].getMaKhachHang().substring(2)) + 1;
            } catch (NumberFormatException e) {
                Stt = 1;
            }
        } else {
            Stt = 1;
        }

        if (Stt > 9)
            MaKhachHang = "KH" + Stt;
        else
            MaKhachHang = "KH0" + Stt;

        System.out.println("Ma khach hang (doc gia) da duoc tao: " + MaKhachHang);
    }

    public LocalDate getNgayLapThe() {
        return NgayLapThe;
    }

    public void setNgayLapThe(LocalDate NgayLapThe) {
        this.NgayLapThe = NgayLapThe;
    }

    public void setNgayLapThe() {
        System.out.print("Nhap ngay lap the (dd/MM/yyyy): ");
        boolean check = false;
        do {
            try {
                String InputDate = sc.nextLine();
                this.NgayLapThe = LocalDate.parse(InputDate, DATE_FORMATTER);
                check = true;
            } catch (Exception e) {
                System.out.print("Ngay khong hop le. Vui long nhap lai (dd/MM/yyyy): ");
            }
        } while (!check);
    }

    public String[] getDsMaSachDangMuon() {
        return DsMaSachDangMuon;
    }

    public void setDsMaSachDangMuon(String[] DsMaSachDangMuon) {
        this.DsMaSachDangMuon = DsMaSachDangMuon;
        if (DsMaSachDangMuon != null) {
            this.SoSachDangMuon = DsMaSachDangMuon.length;
        } else {
            this.SoSachDangMuon = 0;
        }
    }

    public void setDsMaSachDangMuon_Nhap() {
        System.out.print("Ban co muon xuat ra man hinh danh sach sach khong? (1 - in, 0 - khong): ");
        int Chon = KiemTra.CheckNumber();
        DanhSachSach DsSach = new DanhSachSach();

        if (Chon == 1) {
            DsSach.xuatDanhSach();
        }

        System.out.print("Nhap so luong sach dang muon: ");
        int SoLuong = KiemTra.CheckNumber();

        String[] DsMaSachArr = new String[SoLuong];
        boolean Check;
        for (int i = 0; i < SoLuong; i++) {
            System.out.print("Nhap ma sach thu " + (i + 1) + ": ");
            do {
                Check = true;
                DsMaSachArr[i] = sc.nextLine();
                if (DsSach.layPhanTuVoi(DsMaSachArr[i]) == null) {
                    Check = false;
                    System.out.print("Khong tim thay ma sach!!! Hay nhap lai: ");
                }
            } while (!Check);
        }
        setDsMaSachDangMuon(DsMaSachArr);
    }

    public void XoaMaSachKhoiDsMuon() {
        if (DsMaSachDangMuon == null || DsMaSachDangMuon.length == 0) {
            System.out.println("Khach hang chua muon sach nao!");
            return;
        }

        System.out.print("Nhap ma sach can xoa khoi danh sach muon: ");
        String GiaTriCanXoa = sc.nextLine();

        boolean Check = false;
        String[] DsMaSachMoi = new String[DsMaSachDangMuon.length - 1];
        int K = 0;
        for (int i = 0; i < DsMaSachDangMuon.length; i++) {
            if (DsMaSachDangMuon[i].equalsIgnoreCase(GiaTriCanXoa)) {
                Check = true;
                continue;
            }
            if (K < DsMaSachMoi.length) {
                DsMaSachMoi[K++] = DsMaSachDangMuon[i];
            }
        }

        if (Check) {
            setDsMaSachDangMuon(DsMaSachMoi);
            System.out.println("Da xoa ma sach thanh cong!");
        } else {
            System.out.println("Khong tim thay ma sach trong danh sach dang muon!");
        }
    }

    public void ThemMaSachVaoDsMuon() {
        int OldLength = (DsMaSachDangMuon != null) ? DsMaSachDangMuon.length : 0;
        String[] DsMaSachMoi = new String[OldLength + 1];

        for (int i = 0; i < OldLength; i++) {
            DsMaSachMoi[i] = DsMaSachDangMuon[i];
        }

        DanhSachSach DsSach = new DanhSachSach();
        String MaSachMoi;
        boolean Check;

        System.out.print("Ban co muon xuat ra man hinh danh sach sach khong? (1 - in, 0 - khong): ");
        int Chon = KiemTra.CheckNumber();
        if (Chon == 1) {
            DsSach.xuatDanhSach();
        }

        do {
            System.out.print("Nhap ma sach muon them: ");
            MaSachMoi = sc.nextLine();
            if (DsSach.layPhanTuVoi(MaSachMoi) == null) {
                Check = false;
                System.out.println("Khong tim thay ma sach nay!");
            } else {
                Check = true;
            }
        } while (!Check);

        DsMaSachMoi[OldLength] = MaSachMoi;
        setDsMaSachDangMuon(DsMaSachMoi);
        System.out.println("Da them ma sach thanh cong!");
    }

    public void ThemKMaSachVaoDsMuon() {
        System.out.print("Nhap so ma sach can them vao danh sach: ");
        int K = KiemTra.CheckNumber();
        for (int i = 0; i < K; i++)
            ThemMaSachVaoDsMuon();
    }

    @Override
    public void nhap() {
        setMaKhachHang();
        super.nhap();
        setNgayLapThe();
        System.out.print("Nhap tong tien phat (nhap 0 neu khong co): ");
        setTongTienPhat(KiemTra.CheckNumber());

        System.out.print("Cap nhat danh sach sach dang muon? (1 - co, 0 - khong): ");
        int Chon = KiemTra.CheckNumber();
        if (Chon == 1) {
            setDsMaSachDangMuon_Nhap();
        } else {
            setDsMaSachDangMuon(new String[0]);
        }
    }

    @Override
    public void xuat() {
        System.out.println("****************************");
        System.out.println("Ma khach hang: " + getMaKhachHang());
        super.xuat();

        if (NgayLapThe != null) {
            System.out.println("Ngay lap the: " + NgayLapThe.format(DATE_FORMATTER));
        }

        System.out.println("Tong tien phat: " + getTongTienPhat());
        System.out.println("So sach dang muon: " + getSoSachDangMuon());
        System.out.println();

        if (DsMaSachDangMuon != null && DsMaSachDangMuon.length != 0) {
            System.out.println("--- Danh sach sach dang muon ---");
            System.out.printf("%-15s %-30s %-25s %-20s \n", "Ma Sach", "Ten Sach", "Tac Gia", "The Loai");

            Sach Pt;
            DanhSachSach DsSach = new DanhSachSach();

            for (String maSach : DsMaSachDangMuon) {
                Pt = (Sach) DsSach.layPhanTuVoi(maSach);
                if (Pt != null) {
                    System.out.printf("%-15s %-30s %-25s %-20s \n",
                            maSach, Pt.gettenSach(), Pt.getTacGia(), Pt.getLoaiSach());
                }
            }
        } else {
            System.out.println("Khach hang nay chua muon cuon sach nao.");
        }

        System.out.println("****************************");
    }

    public static void Xuat(KhachHang Kh) {
        Kh.xuat();
    }

    @Override
    public void suaThongTin() {
        int Chon;
        do {
            System.out.println("=== Sua thong tin khach hang (Doc gia) ===");
            System.out.println("1. Sua thong tin ca nhan");
            System.out.println("2. Cap nhat tong tien phat");
            System.out.println("3. Them 1 ma sach vao danh sach muon");
            System.out.println("4. Xoa ma sach khoi danh sach muon");
            System.out.println("5. Nhap moi lai danh sach muon");
            System.out.println("6. Them K ma sach vao danh sach muon");
            System.out.println("7. Cap nhat ngay lap the");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            Chon = KiemTra.CheckNumber();

            switch (Chon) {
                case 0:
                    System.out.println("Thoat sua thong tin khach hang!!");
                    break;
                case 1:
                    super.suaThongTin();
                    break;
                case 2:
                    System.out.println("Tong tien phat hien tai: " + getTongTienPhat());
                    System.out.print("Nhap tong tien phat moi: ");
                    setTongTienPhat(KiemTra.CheckNumber());
                    break;
                case 3:
                    System.out.println("Them 1 ma sach vao danh sach:");
                    ThemMaSachVaoDsMuon();
                    break;
                case 4:
                    System.out.println("Xoa ma sach khoi danh sach:");
                    XoaMaSachKhoiDsMuon();
                    break;
                case 5:
                    System.out.println("Nhap moi lai toan bo danh sach sach muon:");
                    setDsMaSachDangMuon_Nhap();
                    break;
                case 6:
                    System.out.println("Them K ma sach vao danh sach:");
                    ThemKMaSachVaoDsMuon();
                    break;
                case 7:
                    System.out.println("Ngay lap the hien tai: " +
                            (NgayLapThe != null ? NgayLapThe.format(DATE_FORMATTER) : "Chua co"));
                    setNgayLapThe();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!");
                    break;
            }
        } while (Chon != 0);
    }
}
