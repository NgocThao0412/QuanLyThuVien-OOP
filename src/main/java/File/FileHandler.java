//Minh nhật //Handler là người xử lý
package File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import SanPham.Sach; 

public class FileHandler {
    private static Scanner fr; // Đã đổi: Fr -> fr

    public static void themNv(String manv, String hoten, String chucvu, String ntns, String gioitinh, String cccd, String diachi, String sdt, String email, String ngayVaoLam, double heSoLuong, int soNgaynghitrongthang) { // Đã đổi tên hàm
        String tmp = manv+"#"+hoten+"#"+chucvu+"#"+ntns+"#"+gioitinh+"#"+cccd+"#"+diachi+"#"+sdt+"#"+email+"#"+ngayVaoLam+"#"+heSoLuong+"#"+soNgaynghitrongthang; // Đã đổi: Tmp -> tmp
        ghiFile(tmp, "dsnv.txt"); // Đã đổi: GhiFile -> ghiFile
    }

    public static void themSach(String maSach, String tenSach, String tacGia, String theLoai, String nhaXuatBan, int soLuong, int gia) { // Đã đổi tên hàm
        String tmp = maSach + "#" + tenSach + "#" + tacGia + "#" + theLoai + "#" + nhaXuatBan + "#" + soLuong + "#" + gia ; // Đã đổi
        ghiFile(tmp, "dssach.txt"); // Đã đổi
    }

    public static void themTheLoai(String maTheLoai, String tenTheLoai, int soLuongSach, String[] dsMaSach) { // Đã đổi tên hàm
        String tmp = maTheLoai + "#" + tenTheLoai + "#" + dsMaSach.length; // Đã đổi
        for(int i=0;i<dsMaSach.length;i++) tmp += "#" + dsMaSach[i];
        ghiFile(tmp, "dstheloai.txt"); // Đã đổi
    }

    public static void themNXB(String maNhaCC , String tenNhaCC , String diachi , String sdt) { // Đã đổi tên hàm
        String tmp = maNhaCC + "#" + tenNhaCC + "#" + diachi + "#" + sdt; // Đã đổi
        ghiFile(tmp, "dsnxb.txt"); // Đã đổi
    }

    public static void themPN(int maPhieuNhap, String ngaynhap, String maNhaCC , String maNV, int tongTien, int SoluongNhap, Sach[] dsmspNhap) { // Đã đổi tên hàm
        String tmp = maPhieuNhap + "#" + ngaynhap + "#" + maNhaCC + "#" + maNV + "#" + tongTien + "#" + SoluongNhap; // Đã đổi
        for(int i=0;i<dsmspNhap.length;i++)
        {
            tmp+="#" + dsmspNhap[i].getmaSach() + "#" + dsmspNhap[i].getSoLuong(); // Đã đổi: GetMaSach -> getMaSach
        }
        ghiFile(tmp, "dspn.txt"); // Đã đổi
    }

    public static void themKH(String makh, String hoten, String ntns, String gioitinh, String cccd, String diachi, String sdt, String email, 
                              String ngayLapThe, String[] dsMaSachDangMuon, int soSachDangMuon, int tongTienPhat) { // Đã đổi tên hàm
        String tmp = makh + "#" + hoten + "#" + ntns + "#" + gioitinh + "#" + cccd + "#" + diachi + "#" + sdt + "#" + email + "#" + 
                     ngayLapThe + "#" + dsMaSachDangMuon.length + "#"; // Đã đổi

        for(int i=0; i < dsMaSachDangMuon.length; i++) {
            tmp += dsMaSachDangMuon[i] + "#";
        }
        
        tmp += soSachDangMuon + "#" + tongTienPhat; 

        ghiFile(tmp, "dskh.txt"); // Đã đổi
    }

    public static void themTK(String username, String password, String type) { // Đã đổi tên hàm
        String tmp = username + "#" + password + "#" + type; // Đã đổi
        ghiFile(tmp, "dstk.txt"); // Đã đổi
    }

    public static void themPM(int soPhieuMuon, String ngayLapPhieu, String maNhanVien, String maKhachHang, 
                              String ngayTraDuKien, String trangThai, int tongSoLuongSach, Sach[] dsSachMuon) { // Đã đổi tên hàm
        String tmp = soPhieuMuon + "#" + ngayLapPhieu + "#" + maNhanVien + "#" + maKhachHang + "#" + 
                     ngayTraDuKien + "#" + trangThai + "#" + tongSoLuongSach; // Đã đổi
        
        for(int i=0; i < dsSachMuon.length; i++)
        {
            tmp += "#" + dsSachMuon[i].getmaSach() + "#" + dsSachMuon[i].getSoLuong(); // Đã đổi
        }
        ghiFile(tmp, "dspm.txt"); // Đã đổi
    }

    public static void taoCacFile() { // Đã đổi tên hàm
        File[] f = new File[8]; // Đã đổi: F -> f
        try {
            f[0] = new File("dssach.txt"); 
            f[1] = new File("dsnv.txt");
            f[2] = new File("dskh.txt");
            f[3] = new File("dstk.txt");
            f[4] = new File("dstheloai.txt"); 
            f[5] = new File("dspm.txt"); 
            f[6] = new File("dsncc.txt");
            f[7] = new File("dspn.txt");
            
            String tenFile = ""; // Đã đổi: TenFile -> tenFile
            for (int i = 0; i < f.length; i++) {
                if (f[i].createNewFile()) {
                    switch (i) {
                        case 0: 
                            tenFile = "dssach.txt";
                            ghiFile("5", tenFile);
                            themSach("TT01", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài", "Thiếu Nhi", "Kim Đồng", 10, 50000);
                            themSach("VH01", "Nhà Giả Kim", "Paulo Coelho", "Văn Học", "Nhã Nam", 15, 80000);
                            themSach("KT01", "Lược Sử Loài Người", "Yuval Noah Harari", "Khoa Học", "Omega Plus", 5, 250000);
                            themSach("TN01", "Đắc Nhân Tâm", "Dale Carnegie", "Tâm Lý", "First News", 20, 75000);
                            themSach("VH02", "Hai Số Phận", "Jeffrey Archer", "Văn Học", "NXB Trẻ", 8, 120000);
                            break;
                        case 1: 
                            tenFile = "dsnv.txt";
                            ghiFile("4", tenFile);
                            themNv("NV01", "Tran Van A", "nhan vien", "17/07/2004", "nam", "052204016288", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "tranvana@gmail.com", "17/07/2023", 0.5, 0);
                            themNv("NV02", "Tran Van B", "nhan vien","30/07/2000", "nu", "054524226300", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "tranvanb@gmail.com", "21/09/2023", 1.2, 0);
                            themNv("NV03", "Tran Thi C", "quan ly","01/01/1950", "nam", "022201236288", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "quanly1@gmail.com", "12/09/2023", 0.4, 1);
                            themNv("NV04", "Tran Bui D", "quan ly","02/02/2000", "nu", "054504012328", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "quanly2@gmail.com", "25/12/2023", 2, 1);
                            break;
                        case 2: 
                            tenFile = "dskh.txt";
                            String[] rong = new String[0]; 
                            ghiFile("3", tenFile);
                            themKH("KH01", "Doan Van A", "20/12/1950", "nu", "320873941", "273 An Duong Vuong, P3, Q5, TP.HCM", "0894172635", "doanvana@gmail.com", "10/01/2023", rong, 0, 0);
                            themKH("KH02", "Nguyen Van B", "28/11/2002", "nam","320142913", "273 An Duong Vuong, P3, Q5, TP.HCM", "0913716241", "hahah@gmail.com", "15/05/2024", rong, 0, 0);
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
                            String[] dsMaSp1 = new String[]{"TT01"};
                            themTheLoai("THIEUNHI", "Sách Thiếu Nhi", 1, dsMaSp1); 
                            String[] dsMaSp2 = new String[]{"VH01", "VH02"};
                            themTheLoai("VANHOC", "Sách Văn Học", 2, dsMaSp2);
                            String[] dsMaSp3 = new String[]{"KT01", "TN01"};
                            themTheLoai("KYNANG", "Sách Kỹ Năng - Khoa Học", 2, dsMaSp3);
                            break;
                        case 5: 
                            tenFile = "dspm.txt";
                            ghiFile("0", tenFile); 
                            break;
                        case 6: 
                            tenFile = "dsnxb.txt";
                            ghiFile("3", tenFile);
                            themNXB("NXB_KIMDONG","NXB Kim Đồng","55 Quang Trung, Hà Nội","02439434730");
                            themNXB("NHA_NAM","Nhã Nam","59 Đỗ Quang, Cầu Giấy, Hà Nội","02435146876");
                            themNXB("FIRST_NEWS","First News","11H Nguyễn Thị Minh Khai, Q1, TP.HCM","02838227979");
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

    public static void ghiFile (String giaTri, String tenFile){ // Đã đổi tên hàm và tham số
        try {
            File fo = new File(tenFile); // Đã đổi: Fo -> fo
            
            String data = ""; // Đã đổi: Data -> data
            if (fo.exists()) {
                fr = new Scanner(fo, "utf-8");
                while (fr.hasNextLine())
                    data += fr.nextLine() + "\n";
                fr.close();
            }
            FileWriter fw = new FileWriter(tenFile); // Đã đổi: Fw -> fw
            fw.write(data + giaTri);
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the ghi file!");
        }
    }

    public static void inFile (String giaTri, String tenFile){ // Đã đổi tên hàm và tham số
        try {
            FileWriter fw = new FileWriter(tenFile); // Đã đổi
            fw.write(giaTri);
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the ghi file!");
        }
    }

    public static void resetFile(String tenFile) { // Đã đổi tên hàm và tham số
        try {
            FileWriter fw = new FileWriter(tenFile); // Đã đổi
            fw.write("");
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the reset file!");
        }
    }

    public static String docFile (String tenFile){ // Đã đổi tên hàm và tham số
            try {
                File fo = new File(tenFile); // Đã đổi
                if (!fo.exists()) {
                    System.out.println("File khong ton tai: " + tenFile);
                    return null;
                }
                fr = new Scanner(fo, "utf-8"); 
                String data = ""; // Đã đổi
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
