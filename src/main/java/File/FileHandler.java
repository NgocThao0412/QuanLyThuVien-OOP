package File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import SanPham.Sach;

public class FileHandler {
    private static Scanner fr;

    public static void themNv(String manv, String hoten, String chucvu, String ntns, String gioitinh, String cccd, String diachi, String sdt, String email, String ngayVaoLam, double heSoLuong, int soNgaynghitrongthang) {
        String tmp = manv + "#" + hoten + "#" + chucvu + "#" + ntns + "#" + gioitinh + "#" + cccd + "#" + diachi + "#" + sdt + "#" + email + "#" + ngayVaoLam + "#" + heSoLuong + "#" + soNgaynghitrongthang;
        ghiFile(tmp, "dsnv.txt");
    }

    public static void themSach(String maSach, String tenSach, String tacGia, String theLoai, String nhaXuatBan, int soLuong, int gia) {
        String tmp = maSach + "#" + tenSach + "#" + tacGia + "#" + theLoai + "#" + nhaXuatBan + "#" + soLuong + "#" + gia;
        ghiFile(tmp, "dss.txt");
    }

    public static void themTheLoai(String maTheLoai, String tenTheLoai, int soLuongSach, String[] dsMaSach) {
        String tmp = maTheLoai + "#" + tenTheLoai + "#" + soLuongSach;
        for (int i = 0; i < dsMaSach.length; i++) tmp += "#" + dsMaSach[i];
        ghiFile(tmp, "dstl.txt");
    }

    public static void themNXB(String maNhaXB, String tenNhaXB, String diachi, String sdt) {
        String tmp = maNhaXB + "#" + tenNhaXB + "#" + diachi + "#" + sdt;
        ghiFile(tmp, "dsnxb.txt");
    }

    public static void themPN(int maPhieuNhap, String ngaynhap, String maNhaXB, String maNV, int tongTien, int SoluongNhap, Sach[] dsmspNhap) {
        String tmp = maPhieuNhap + "#" + ngaynhap + "#" + maNhaXB + "#" + maNV + "#" + tongTien + "#" + SoluongNhap;
        for (int i = 0; i < dsmspNhap.length; i++) {
            tmp += "#" + dsmspNhap[i].getmaSach() + "#" + dsmspNhap[i].getSoLuong();
        }
        ghiFile(tmp, "dspn.txt");
    }

    public static void themKH(String makh, String hoten, String ntns, String gioitinh, String cccd, String diachi, String sdt, String email,
                              String ngayLapThe, String[] dsMaSachDangMuon, int soSachDangMuon, int tongTienPhat) {
        String tmp = makh + "#" + hoten + "#" + ntns + "#" + gioitinh + "#" + cccd + "#" + diachi + "#" + sdt + "#" + email + "#" +
                      ngayLapThe + "#" + dsMaSachDangMuon.length + "#";

        for (int i = 0; i < dsMaSachDangMuon.length; i++) {
            tmp += dsMaSachDangMuon[i] + "#";
        }

        tmp += soSachDangMuon + "#" + tongTienPhat;

        ghiFile(tmp, "dskh.txt");
    }

    public static void themTK(String username, String password, String type) {
        String tmp = username + "#" + password + "#" + type;
        ghiFile(tmp, "dstk.txt");
    }

    public static void themPM(int soPhieuMuon, String ngayLapPhieu, String maNhanVien, String maKhachHang,
                              String ngayTraDuKien, String trangThai, int tongSoLuongSach, Sach[] dsSachMuon) {
        String tmp = soPhieuMuon + "#" + ngayLapPhieu + "#" + maNhanVien + "#" + maKhachHang + "#" +
                      ngayTraDuKien + "#" + trangThai + "#" + tongSoLuongSach;

        for (int i = 0; i < dsSachMuon.length; i++) {
            tmp += "#" + dsSachMuon[i].getmaSach() + "#" + dsSachMuon[i].getSoLuong();
        }
        ghiFile(tmp, "dspm.txt");
    }

    public static void taoCacFile() {
        File[] f = new File[8];
        try {
            f[0] = new File("dss.txt");
            f[1] = new File("dsnv.txt");
            f[2] = new File("dskh.txt");
            f[3] = new File("dstk.txt");
            f[4] = new File("dstl.txt");
            f[5] = new File("dspm.txt");
            f[6] = new File("dsnxb.txt");
            f[7] = new File("dspn.txt");

            String tenFile = "";
            for (int i = 0; i < f.length; i++) {
                if (f[i].createNewFile()) {
                    switch (i) {
                        case 0:
                            tenFile = "dss.txt";
                            ghiFile("15", tenFile);
                            // Sách Tâm Lý
                            themSach("TL01", "Dac Nhan Tam", "Dale Carnegie", "Sach Tam Ly", "First News", 20, 75000);
                            themSach("TL02", "Nhung Dieu Ap Luc", "Mark Manson", "Sach Tam Ly", "Thai Ha", 12, 85000);
                            themSach("TL03", "Tu Duy Nhanh Va Cham", "Daniel Kahneman", "Sach Tam Ly",  "First News", 15, 100000);
                            themSach("TL04", "Suc Manh Cua Thoi Quen", "Charles Duhigg", "Sach Tam Ly", "Thai Ha", 18, 90000);
                            themSach("TL05", "Nghich Ly Cua Lua Chon", "Barry Schwartz", "Sach Tam Ly", "Nha Nam", 10, 80000);
                            // Sách Thiếu Nhi
                            themSach("TN01", "Hoang Tu Be", "Antoine de Saint-Exupery", "Sach Thieu Nhi", "Kim Dong", 15, 60000);
                            themSach("TN02", "Toi Thay Hoa Vang Tren Co Xanh", "Nguyen Nhat Anh", "Sach Thieu Nhi", "Kim Dong", 25, 70000);
                            themSach("TN03", "Doraemon Tap 1", "Fujiko F. Fujio", "Sach Thieu Nhi", "Kim Dong", 30, 20000);
                            themSach("TN04", "Pippi Tat Dai", "Astrid Lindgren", "Sach Thieu Nhi", "Kim Dong", 10, 55000);
                            themSach("TN05", "Chuyen Con Meo Day Hai Au Bay", "Luis Sepulveda", "Sach Thieu Nhi", "Kim Dong", 12, 65000);
                            //Sách Trinh Thám 
                            themSach("TT01", "Vu An Tren Tau Toc Hanh", "Agatha Christie", "Sach Trinh Tham", "Nha Nam", 10, 95000);
                            themSach("TT02", "Sherlock Holmes", "Arthur Conan Doyle", "Sach Trinh Tham", "Nha Nam", 8, 120000);
                            themSach("TT03", "Ma Code Da Vinci", "Dan Brown", "Sach Trinh Tham", "Nha Nam", 15, 110000);
                            themSach("TT04", "Co Gai Mat Tich", "Gillian Flynn", "Sach Trinh Tham", "Nha Nam", 7, 105000);
                            themSach("TT05", "Toi Giet Nguoi", "Haruki Murakami", "Sach Trinh Tham", "Nha Nam", 5, 130000);
                            break;

                        case 1:
                            tenFile = "dsnv.txt";
                            ghiFile("4", tenFile);
                            themNv("NV01", "Tran Van A", "nhan vien", "17/07/2004", "nam", "052204016288", "273 An Duong Vuong, Phuong Cho Quan, TP.HCM", "0938412413", "tranvana@gmail.com", "17/07/2025", 0.5, 0);
                            themNv("NV02", "Tran Van B", "nhan vien", "30/07/2000", "nu", "054524226300", "273 An Duong Vuong, Phuong Cho Quan, TP.HCM", "0938412413", "tranvanb@gmail.com", "21/09/2025", 1.2, 0);
                            themNv("NV03", "Tran Thi C", "quan ly", "01/01/1950", "nam", "022201236288", "273 An Duong Vuong, Phuong Cho Quan, TP.HCM", "0938412413", "quanly1@gmail.com", "12/09/2025", 0.4, 1);
                            themNv("NV04", "Tran Bui D", "quan ly", "02/02/2000", "nu", "054504012328", "273 An Duong Vuong, Phuong Cho Quan, TP.HCM", "0938412413", "quanly2@gmail.com", "25/12/2025", 2.0, 1);
                            break;

                        case 2:
                            tenFile = "dskh.txt";
                            String[] rong = new String[0];
                            ghiFile("3", tenFile);
                            themKH("KH01", "Doan Van A", "20/12/1950", "nu", "320873941", "273 An Duong Vuong, P3, Q5, TP.HCM", "0894172635", "doanvana@gmail.com", "10/01/2023", rong, 0, 0);
                            themKH("KH02", "Nguyen Van B", "28/11/2002", "nam", "320142913", "273 An Duong Vuong, P3, Q5, TP.HCM", "0913716241", "hahah@gmail.com", "15/05/2024", rong, 0, 0);
                            themKH("KH03", "Tran Van C", "10/10/1969", "nam", "320638711", "273 An Duong Vuong, P3, Q5, TP.HCM", "0907412663", "tranvanc@gmail.com", "01/09/2022", rong, 0, 0);
                            break;

                        case 3:
                            tenFile = "dstk.txt";
                            ghiFile("4", tenFile);
                            themTK("NV01", "123", "nhan vien");
                            themTK("NV02", "123", "nhan vien");
                            themTK("NV03", "123", "quan ly");
                            themTK("NV04", "123", "quan ly");
                            break;

                        case 4:
                            tenFile = "dstl.txt";
                            ghiFile("3", tenFile);
                            String[] dsMaSp1 = new String[]{"TL01", "TL02", "TL03", "TL04", "TL05"};
                            themTheLoai("TL", "Sach Tam Ly", 5, dsMaSp1);
                            String[] dsMaSp2 = new String[]{"TN01", "TN02", "TN03", "TN04", "TN05"};
                            themTheLoai("TN", "Sach Thieu Nhi", 5, dsMaSp2);
                            String[] dsMaSp3 = new String[]{"TT01", "TT02", "TT03", "TT04", "TT05"};
                            themTheLoai("TT", "Sach Trinh Tham", 5, dsMaSp3);
                            break;

                        case 5:
                            tenFile = "dspm.txt";
                            ghiFile("0", tenFile);
                            break;

                        case 6:
                            tenFile = "dsnxb.txt";
                            ghiFile("4", tenFile);
                            themNXB("NXB_KIMDONG", "NXB Kim Dong", "55 Quang Trung, Ha Noi", "02439434730");
                            themNXB("NHA_NAM", "Nha Nam", "59 Do Quang, Cau Giay, Ha Noi", "02435146876");
                            themNXB("FIRST_NEWS", "First News", "11H Nguyen Thi Minh Khai, Q1, TP.HCM", "02838227979");
                            themNXB("THAI_HA", "Thai Ha Books", "26 Hoang Cau, Dong Da, Ha Noi", "02438563810");
                            break;

                        case 7:
                            tenFile = "dspn.txt";
                            ghiFile("0", tenFile);
                            break;

                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the tao file!");
        }
    }

    public static void ghiFile(String giaTri, String tenFile) {
        try {
            File fo = new File(tenFile);
            String data = "";
            if (fo.exists()) {
                fr = new Scanner(fo, "utf-8");
                while (fr.hasNextLine())
                    data += fr.nextLine() + "\n";
                fr.close();
            }

            FileWriter fw = new FileWriter(tenFile);
            fw.write(data + giaTri);
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the ghi file!");
        }
    }

    public static void inFile(String giaTri, String tenFile) {
        try {
            FileWriter fw = new FileWriter(tenFile);
            fw.write(giaTri);
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the ghi file!");
        }
    }

    public static void resetFile(String tenFile) {
        try {
            FileWriter fw = new FileWriter(tenFile);
            fw.write("");
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the reset file!");
        }
    }

    public static String docFile(String tenFile) {
        try {
            File fo = new File(tenFile);
            if (!fo.exists()) {
                System.out.println("File khong ton tai: " + tenFile);
                return null;
            }
            fr = new Scanner(fo, "utf-8");
            String data = "";
            while (fr.hasNextLine())
                data += fr.nextLine() + "\n";
            fr.close();
            return data;
        } catch (Exception e) {
            System.out.println("Khong the doc file!");
            return null;
        }
    }
}
