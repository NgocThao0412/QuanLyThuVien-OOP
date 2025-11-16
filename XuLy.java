import DanhSach.*;
import Nguoi.NhanVien;
import Nguoi.TaiKhoan;
import SanPham.PhanTu;
import KiemTra.KiemTra;

import java.util.Scanner;

public class XuLy {
    private static Scanner sc = new Scanner(System.in);
    public static String username;
    
    /**
     * Phương thức xử lý trung tâm, dùng để đăng nhập.
     */
    public static void xuLyTrungTam() {
        int chon;
        DanhSachTaiKhoan danhSachTaiKhoan = new DanhSachTaiKhoan(); 
        
        System.out.println("************************************************************\n" +
                            "*** Chao mung ban den voi chuong trinh                    ***\n" +
                            "*** QUAN LY THU VIEN                                    ***\n" +
                            "*** --------------------------------------------------- ***\n" +
                            "************************************************************");
        
        do {
            System.out.println("============================================================");
            System.out.println("*** Moi ban dang nhap vao chuong trinh!!! Ban la...    ***");
            System.out.println("*** 1. Nhan vien (Thu thu)                             ***");
            System.out.println("*** 2. Quan ly                                         ***");
            System.out.println("*** 0. Thoat chuong trinh                              ***");
            System.out.println("============================================================");
            System.out.print("Moi chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    System.out.print("Tai khoan: ");
                    String user1 = sc.nextLine();
                    System.out.print("Mat khau:  ");
                    String pass1 = sc.nextLine();
                    TaiKhoan taiKhoan1 = danhSachTaiKhoan.checkLogin(user1, pass1);
                    if(taiKhoan1 != null) {
                        if(taiKhoan1.getType().equals("nhan vien")) {
                            System.out.println("Dang nhap thanh cong!!!");
                            username = user1;
                            MenuNV();
                            chon = 0;
                        }
                        else {
                            System.out.println("Ban dang dang nhap voi vai tro la nhan vien!!! Vui long su dung tai khoan nhan vien.");
                        }
                    } else {
                        System.out.println("Sai tai khoan hoac mat khau!");
                    }
                    break;
                case 2:
                    System.out.print("Tai khoan: ");
                    String user = sc.nextLine();
                    System.out.print("Mat khau:  ");
                    String pass = sc.nextLine();
                    TaiKhoan taiKhoan = danhSachTaiKhoan.checkLogin(user, pass);
                    if(taiKhoan != null) {
                        if(taiKhoan.getType().equals("quan ly")) {
                            System.out.println("Dang nhap thanh cong!!!");
                            username = user;
                            MenuQL();
                            chon = 0;
                        }
                        else {
                            System.out.println("Ban dang dang nhap voi vai tro la quan ly!!! Vui long su dung tai khoan quan ly.");
                        }
                    } else {
                        System.out.println("Sai tai khoan hoac mat khau!");
                    }
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
                }
        } while (chon!=0);
    }

    // --- Menu Nhân Viên (Thu thủ) ---

    /**
     * Menu chức năng dành cho Nhân viên (Thu thủ).
     */
    public static void MenuNV() {
        // Khởi tạo các lớp Danh Sách
        DanhSachSach danhSachSach = new DanhSachSach(); 
        DanhSachKhachHang danhSachKhachHang = new DanhSachKhachHang();
        DanhSachPhieuMuon danhSachPhieuMuon = new DanhSachPhieuMuon(); 
        DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
        DanhSachTaiKhoan danhSachTaiKhoan = new DanhSachTaiKhoan();
        
        NhanVien nhanVien = (NhanVien) danhSachNhanVien.layPhanTuVoi(username); 
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Thu Vien - Nhan Vien *****"); 
        do {
            System.out.println("==============================");
            System.out.println("Xin chao " + nhanVien.getHoten()); 
            System.out.println("1. Xem thong tin ca nhan");
            System.out.println("2. Sua thong tin ca nhan");
            System.out.println("3. Tao phieu muon moi");         
            System.out.println("4. In danh sach phieu muon");  
            System.out.println("5. In file phieu muon");          
            System.out.println("6. Them doc gia moi");
            System.out.println("7. In danh sach doc gia");
            System.out.println("8. In danh sach sach");
            System.out.println("9. Kiem tra han tra (theo Ma Doc Gia)");
            System.out.println("10. Thay doi mat khau tai khoan");
            System.out.println("0.  Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    nhanVien.xuat();
                    break;
                case 2:
                    danhSachNhanVien.chinhSuaThongTinPhanTu(username);
                    nhanVien = (NhanVien) danhSachNhanVien.layPhanTuVoi(username);
                    break;
                case 3:
                    danhSachPhieuMuon.themPhieuMuon(); 
                    break;
                case 4:
                    danhSachPhieuMuon.xuatDanhSach();
                    break;
                case 5:
                    System.out.println("Chuc nang In file phieu muon dang phat trien!");
                    break;
                case 6:
                    danhSachKhachHang.themKPhanTuVaoDanhSach();
                    break;
                case 7:
                    danhSachKhachHang.xuatDanhSach();
                    break;
                case 8:
                    danhSachSach.xuatDanhSach();
                    break;
                case 9:
                    System.out.println("Chuc nang Kiem tra han tra dang phat trien!");
                    break;
                case 10:
                    danhSachTaiKhoan.changePassword(username);
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
                }
            }while (chon!=0);
    }
    
    // --- Menu Quản Lý ---

    /**
     * Menu chức năng dành cho Quản lý.
     */
    public static void MenuQL() {
        DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
        NhanVien nhanVien = (NhanVien) danhSachNhanVien.layPhanTuVoi(username);
        
        // Khởi tạo các lớp cần thiết cho chức năng Quản lý
        DanhSachPhieuMuon danhSachPhieuMuon = new DanhSachPhieuMuon(); 
        DanhSachTaiKhoan danhSachTaiKhoan = new DanhSachTaiKhoan();
        DanhSachSach danhSachSach = new DanhSachSach();
        DanhSachKhachHang danhSachKhachHang = new DanhSachKhachHang();
        DanhSachNhaXuatBan danhSachNXB = new DanhSachNhaXuatBan();
        
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Thu Vien - Quan Ly *****"); 
        do {
            System.out.println("==============================");
            System.out.println("Xin chao " + nhanVien.getHoten());
            System.out.println("1.  Xem thong tin tai khoan");
            System.out.println("2.  Sua thong tin tai khoan");
            System.out.println("3.  Quan ly danh sach sach");           
            System.out.println("4.  Quan ly danh sach Nha Xuat Ban");   
            System.out.println("5.  Quan ly danh sach nhan vien");
            System.out.println("6.  Quan ly danh sach doc gia");
            System.out.println("7.  Quan ly danh sach tai khoan");
            System.out.println("8.  Quan ly danh sach phieu muon");
            System.out.println("9. Kiem tra han tra (theo Ma Doc Gia)");
            System.out.println("10. Thay doi mat khau tai khoan");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    nhanVien.xuat();
                    break;
                case 2:
                    danhSachNhanVien.chinhSuaThongTinPhanTu(username);
                    nhanVien = (NhanVien) danhSachNhanVien.layPhanTuVoi(username);
                    break;
                case 3:
                    quanLyDSS();
                    break;
                case 4:
                    quanLyDSNXB();
                    break;
                case 5:
                    quanLyDSNV();
                    break;
                case 6:
                    quanLyDSDG();
                    break;
                case 7:
                    quanLyDSTK();
                    break;
                case 8:
                    quanLyDSPM();
                    break;
                case 9:
                    System.out.println("Chuc nang Kiem tra han tra dang phat trien!");
                    break;
                case 10:
                    danhSachTaiKhoan.changePassword(username);
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }
    
    // --- Các hàm Quản lý Chi tiết ---

    /**
     * Phương thức in menu chung cho các danh sách.
     * @param ten Tên của đối tượng (ví dụ: "sach", "nhan vien")
     */
    private static void inMenu(String ten) {
        System.out.println("==============================");
        System.out.println("*** QUAN LY DANH SACH "+ten.toUpperCase()+" ***");
        System.out.println("1. Nhap moi danh sach");
        System.out.println("2. Xuat danh sach");
        System.out.println("3. Them "+ten+" vao danh sach");
        System.out.println("4. Chinh sua thong tin "+ten);
        System.out.println("5. Xoa "+ten);
        System.out.println("6. Tim "+ten);
        System.out.println("7. Thong ke");
        System.out.println("8. Tong so luong "+ten);
        System.out.println("0. Quay lai menu quan ly");
        System.out.println("==============================");
        System.out.print("Moi chon: ");
    }

    /**
     * Quản lý Danh sách Sách.
     */
    public static void quanLyDSS() {
        DanhSachSach danhSachSach = new DanhSachSach();
        int chon;
        
        do {
            inMenu("sach");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 1:
                    danhSachSach.nhapDanhSach();
                    break;
                case 2:
                    danhSachSach.xuatDanhSach();
                    break;
                case 3:
                    danhSachSach.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    danhSachSach.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    danhSachSach.xoaPhanTu();
                    break;
                case 6:
                    PhanTu phanTu = danhSachSach.timPhanTu();
                    if (phanTu != null) {
                        System.out.println("** Thong tin tim thay **");
                        phanTu.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    danhSachSach.thongKe();
                    break;
                case 8:
                    System.out.println("So luong: " + danhSachSach.getSoLuong()); 
                    break;
                case 0:
                    MenuQL();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }

    /**
     * Quản lý Danh sách Nhân viên.
     */
    public static void quanLyDSNV() {
        DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
        int chon;
        
        do {
            inMenu("nhan vien");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 1:
                    danhSachNhanVien.nhapDanhSach();
                    break;
                case 2:
                    danhSachNhanVien.xuatDanhSach();
                    break;
                case 3:
                    danhSachNhanVien.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    danhSachNhanVien.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    danhSachNhanVien.xoaPhanTu();
                    break;
                case 6:
                    PhanTu phanTu = danhSachNhanVien.timPhanTu();
                    if (phanTu != null) {
                        System.out.println("** Thong tin tim thay **");
                        phanTu.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    danhSachNhanVien.thongKe();
                    break;
                case 8:
                    System.out.println("So luong: " + danhSachNhanVien.getSoluong());
                    break;
                case 0:
                    MenuQL();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }

    /**
     * Quản lý Danh sách Độc giả.
     */
    public static void quanLyDSDG() {
        DanhSachKhachHang danhSachKhachHang = new DanhSachKhachHang();
        int chon;
        
        do {
            inMenu("doc gia");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 1:
                    danhSachKhachHang.nhapDanhSach();
                    break;
                case 2:
                    danhSachKhachHang.xuatDanhSach();
                    break;
                case 3:
                    danhSachKhachHang.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    danhSachKhachHang.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    danhSachKhachHang.xoaPhanTu();
                    break;
                case 6:
                    PhanTu phanTu = danhSachKhachHang.timPhanTu();
                    if (phanTu != null) {
                        System.out.println("** Thong tin tim thay **");
                        phanTu.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    danhSachKhachHang.thongKe();
                    break;
                case 8:
                    System.out.println("So luong: " + danhSachKhachHang.getSoLuong());
                    break;
                case 0:
                    MenuQL();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }

    /**
     * Quản lý Danh sách Tài khoản.
     */
    public static void quanLyDSTK() {
        DanhSachTaiKhoan danhSachTaiKhoan = new DanhSachTaiKhoan();
        int chon;
        
        do {
            inMenu("tai khoan");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 1:
                    danhSachTaiKhoan.nhapDanhSach();
                    break;
                case 2:
                    danhSachTaiKhoan.xuatDanhSach();
                    break;
                case 3:
                    danhSachTaiKhoan.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    danhSachTaiKhoan.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    danhSachTaiKhoan.xoaPhanTu();
                    break;
                case 6:
                    PhanTu phanTu = danhSachTaiKhoan.timPhanTu();
                    if (phanTu != null) {
                        System.out.println("** Thong tin tim thay **");
                        phanTu.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    danhSachTaiKhoan.thongKe();
                    break;
                case 8:
                    System.out.println("So luong: " + danhSachTaiKhoan.getSoLuong());
                    break;
                case 0:
                    MenuQL();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }

    /**
     * Quản lý Danh sách Nhà Xuất Bản.
     */
    public static void quanLyDSNXB() {
        DanhSachNhaXuatBan danhSachNXB = new DanhSachNhaXuatBan();
        int chon;
        
        do {
            inMenu("nha xuat ban");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 1:
                    danhSachNXB.nhapDanhSach();
                    break;
                case 2:
                    danhSachNXB.xuatDanhSach();
                    break;
                case 3:
                    danhSachNXB.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    danhSachNXB.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    danhSachNXB.xoaPhanTu();
                    break;
                case 6:
                    PhanTu phanTu = danhSachNXB.timPhanTu();
                    if (phanTu != null) {
                        System.out.println("** Thong tin tim thay **");
                        phanTu.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    danhSachNXB.thongKe();
                    break;
                case 8:
                    System.out.println("So luong: " + danhSachNXB.getSoLuong()); 
                    break;
                case 0:
                    MenuQL();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }

    /**
     * Quản lý Danh sách Phiếu Mượn.
     */
    public static void quanLyDSPM() {
        DanhSachPhieuMuon danhSachPhieuMuon = new DanhSachPhieuMuon();
        DanhSachSach danhSachSach = new DanhSachSach();
        int chon;
        
        do {
            inMenu("phieu muon");
            chon = KiemTra.CheckNumber();
            // Kiểm tra điều kiện cần có danh sách sách
            if (danhSachSach.getSoLuong() == 0 && chon != 0) {
                 System.out.println("Vui long nhap danh sach sach truoc khi quan ly phieu muon!"); 
                 chon = 0; // Thoát khỏi vòng lặp và quay lại menu QL
                 break; // Thoát switch
            }
            
            switch (chon) {
                case 1:
                    danhSachPhieuMuon.nhapDanhSach();
                    break;
                case 2:
                    danhSachPhieuMuon.xuatDanhSach();
                    break;
                case 3:
                    danhSachPhieuMuon.themPhieuMuon();
                    break;
                case 4:
                    danhSachPhieuMuon.suaPhieuMuon();
                    break;
                case 5:
                    danhSachPhieuMuon.xoaPhieuMuon();
                    break;
                case 6:
                    System.out.println("** Thong tin tim kiem **");
                    danhSachPhieuMuon.timPhieuMuon(); 
                    break;
                case 7:
                    danhSachPhieuMuon.thongKe();
                    break;
                case 8:
                    danhSachPhieuMuon.tongSoLuong();
                    break;
                case 0:
                    MenuQL();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
            }
        } while(chon!=0);
    }
}