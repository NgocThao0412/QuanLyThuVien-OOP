package Nguoi;

import DanhSach.DanhSachNhanVien;
import DanhSach.DanhSachTaiKhoan;
import KiemTra.KiemTra;

public class NhanVien extends Nguoi {
    private String maNhanvien;
    private String chucVu;
    private String ngayVaolam;
    private static int luongcoban = 4500000;
    private double hesoluong = 1.0;
    private double luong;
    private double thuong;
    private char hang;
    private int soNgayNghiTrongThang;

    public NhanVien() {
    }

    public NhanVien(String maNhanvien, String chucVu, String ngayVaolam, int Luongcoban, double heSoluong, double luong, double thuongg, 
            char hang, int soNgayNghiTrongThang) {
        this.maNhanvien = maNhanvien;
        this.chucVu = chucVu;
        this.ngayVaolam = ngayVaolam;
        NhanVien.luongcoban = Luongcoban;
        this.hesoluong = heSoluong;
        this.luong = luong;
        this.thuong = thuong;
        this.hang = hang;
        this.soNgayNghiTrongThang = soNgayNghiTrongThang;
    }

    public NhanVien(String maNhanvien, String ngayVaolam, int Luongcoban, double heSoluong, double luong, double thuong,
            char hang, int soNgayNghiTrongThang, String hoten, String ngaythangnamsinh, String CCCD, String gioitinh,
            String diachi, String sdt, String email) {
        super(hoten, ngaythangnamsinh, CCCD, gioitinh, diachi, sdt, email);
        this.maNhanvien = maNhanvien;
        this.ngayVaolam = ngayVaolam;
        NhanVien.luongcoban = Luongcoban;
        this.hesoluong = heSoluong;
        this.luong = luong;
        this.thuong = thuong;
        this.hang = hang;
        this.soNgayNghiTrongThang = soNgayNghiTrongThang;
    }

    public String getManhanvien() {
        return maNhanvien;
    }

    public void setManhanvien() {
        DanhSachNhanVien ttds = new DanhSachNhanVien();
        NhanVien[] dsnv = ttds.getDsNhanVien();
        int stt;
        if(dsnv.length != 0) stt = Integer.parseInt(dsnv[ttds.getSoLuong - 1].getManhanvien().substring(2)) + 1;
        else stt = 1;
        if (stt > 9) maNhanvien ="NV" + stt;
        else maNhanvien = "NV0" + stt;
        System.out.println("Ma nhan vien: " + maNhanvien);
    }

    public String getNgayvaolam() {
        return ngayVaolam;
    }

    public void setNgayvaolam(String a) {
        ngayVaolam = a;
    }

    public void setNgayvaolam() {
        System.out.print("Su dung ngay thang nam hien tai de lam ngay vao lam? (1 - co, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if(chon == 1) ngayVaolam = ngayhientai + "/" + thanghientai + "/" + namhientai;
        else {
            System.out.println("Nhap ngay vao lam (dd/mm/yy): ");
            boolean check;
            do {
                check = true;
                ngayVaolam = sc.nextLine();
                check = KiemTra.check_date(ngayVaolam);
                if(check) {
                    check = KiemTra.checkDate(ngayVaolam);
                    if(!check) System.out.print("Moi nhap lai: ");
                }
            } while (!check);
        }
    }

    public double getHesoluong() {
        return hesoluong;
    }
    public void setHesoluong(double heSoluong) {
        this.hesoluong = heSoluong;
    }
    public void setHesoluong() {
        System.out.print("Nhap he so luong: ");
        boolean check = false;
        String ch;
        do {
            ch = sc.nextLine();
            check = KiemTra.isDecimal();
        } while(!check);
        hesoluong = Double.parseDouble(ch);
    }

    public double getLuong() {
        return luong;
    }
    public void setLuong() {
        this.luong = luong;
        double luong = 0;
        luong = luongcoban * hesoluong + thuong;
    }
    
    public double getThuong() {
        return thuong;
    }
    public void setThuong(double thuong) {
        this.thuong = thuong;
    }

    public char getHang() {
        return hang;
    }

    public void setHang(char hang) { //Tính hạng và thưởng dựa theo ngày vào làm
        this.hang = hang;
        double thuong = 0;

        String ngayVaolam = getNgayvaolam();
        String[] ngayThangNam = ngayVaolam.split("/"); // Tách thành mảng

        if(2025 - Integer.parseInt(ngayThangNam[2]) > 1) { // nếu số năm làm lớn hơn 1
            if(hang == 'A')
                thuong = luongcoban * hesoluong * 0.05;
            else if (hang == 'B')
                thuong = luongcoban * hesoluong *0.02;
        }
        setThuong(thuong);
    }

    public static int getLuongcoban() {
        return luongcoban;
    }

    public static void setLuongcoban(int LuongCoBan) {
        NhanVien.luongcoban = LuongCoBan;
    }

    public int getSongaynghitrongthang() {
        return soNgayNghiTrongThang;
    }

    public void setSongaynghitrongthang() {
        System.out.print("Nhap so ngay nghi trong thang: ");
        do {
            soNgayNghiTrongThang = KiemTra.checkNumber();;
            if (soNgayNghiTrongThang < 0 || soNgayNghiTrongThang > 31)
                System.out.print("Moi nhap lai: ");
        } while (soNgayNghiTrongThang < 0 || soNgayNghiTrongThang > 31);

        if (soNgayNghiTrongThang < 2)
            setHang('A');
        else if (soNgayNghiTrongThang < 5)
            setHang('B');
        else
            setHang('C');

        setLuong();
    }

    public void setSongaynghitrongthang(int soNgayNghiTrongThang) {
        this.soNgayNghiTrongThang = soNgayNghiTrongThang;
        if (soNgayNghiTrongThang < 2)
            setHang('A');
        else if (soNgayNghiTrongThang < 5)
            setHang('B');
        else
            setHang('C');
        setLuong();
    }
   
    public void setTaikhoan() {
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
        dstk.themPhanTuVaoDanhSach(maNhanvien, chucVu);
    }

    public String getChucvu() {
        return chucVu;
    }

    public void setChucvu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setChucvu() {
        System.out.print("nhap chuc vu nhan vien (nhan vien/quanly): ");
        boolean checkchucvu = false;
        if(chucVu != null) {
            checkchucvu = true;
        }
        boolean check = false;
        do {
            check = true;
            chucVu = sc.nextLine();
            if (chucVu.equals("quan ly") || chucVu.equals("nhan vien")) check = true;
            else check = false;
        }
        while (!check);
        if(chucVu.equals("quan ly")) setHesoluong(2.0);
        if(checkchucvu) {
            DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
            dstk.chinhSuaThongTinPhanTu(maNhanvien, chucVu);
        }
    }
    
    @Override 
    public void nhap() {
        setManhanvien();
        setChucvu();
        setNgayvaolam();
        setSongaynghitrongthang(0);
        super.nhap();
        System.out.print("Ban co muon tao tai khoan cho nhan vien hay khong? (1 - co, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        chon = (chon == 0) ? 0 : 1;
        if (chon == 1) setTaikhoan();
    }
    @Override

    public void xuat() {
        System.out.println("Ma nhan vien: " + getManhanvien());
        super.xuat();
        System.out.printf("\n%-15s %-25s %-25s %-30s %10s %-10s %-10s\n", "Chuc vu", "Ngay vao lam", "He so luong",
            "So ngay nghi trong thang", "Luong", "Thuong", "Hang");
        System.out.printf("%-15s %25s %-25s %-30s %-10s %-10s %-10s\n", getChucvu(), getNgayvaolam(), getHesoluong(), getSongaynghitrongthang(),
            getLuong(), getThuong(), getHang());
        System.out.println("**************************************");
    }
    
    public static void xuat(NhanVien nv) {
        nv.xuat();
    }
    @Override
    public void suaThongtin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin nhan vien ====");
            System.out.println("1. Sua thong tin ca nhan");
            System.out.println("2. Sua chuc vu");
            System.out.println("3. Sua ngay vao lam");
            System.out.println("4. Sua he so luong");
            System.out.println("5. Sua so ngay nghi trong thang");
            System.out.println("0. Thoat");
            System.out.println("==================================");
            System.out.printf("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoa sua thong tin nhan vien!!");
                    break;
                case 1:
                    super.suaThongtin();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: " + getChucvu());
                    setChucvu();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: " + getNgayvaolam());
                    setNgayvaolam();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: " + getHesoluong());
                    setHesoluong();
                    break; 
                case 5:
                    System.out.println("Thong tin hien tai: " + getSongaynghitrongthang());
                    setSongaynghitrongthang();
                    break;
                default:
                    System.out.println("Hay nhao so co trong menu");
                    break;
            }
        } while(chon != 0);
    }
}




