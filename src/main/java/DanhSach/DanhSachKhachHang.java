package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.KhachHang;
import SanPham.PhanTu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; // Thêm Scanner

public class DanhSachKhachHang implements DanhSachChung {
    private Scanner sc = new Scanner(System.in); // Thêm Scanner
    
    private int soLuong; // Đã đổi: SoLuong -> soLuong
    private KhachHang[] dsKhachHang; // Đã đổi: DsKhachHang -> dsKhachHang
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DanhSachKhachHang(){
        dsKhachHang = getDsKhachHang(); // Đã đổi: GetDsKhachHang -> getDsKhachHang
    }
    
    public DanhSachKhachHang(int soLuong, KhachHang[] dsKhachHang){ // Đã đổi tên tham số
        this.soLuong = soLuong;
        this.dsKhachHang = dsKhachHang;
    }
    
    public int getSoLuong(){ // Đã đổi tên hàm
        return soLuong;
    }
    
    public void setSoLuong(int soLuong){ // Đã đổi tên hàm và tham số
        this.soLuong = soLuong;
    }
    
    public KhachHang[] getDsKhachHang(){ // Đã đổi tên hàm
        String data = FileHandler.docFile("dskh.txt"); // Đã đổi: FileHandler.DocFile -> FileHandler.docFile
        if (data == null || data.isEmpty()) { // Đã đổi: Data -> data
             setSoLuong(0);
             return new KhachHang[0]; 
        }
        
        String[] dArr = data.split("\n"); // Đã đổi: DArr -> dArr
        
        if (dArr[0].isEmpty()) setSoLuong(0);
        else {
            try {
                setSoLuong(Integer.parseInt(dArr[0]));
            } catch (NumberFormatException e) {
                System.out.println("Loi khi doc so luong tu file dskh.txt.");
                setSoLuong(0);
                return new KhachHang[0];
            }
        }
        
        dsKhachHang = new KhachHang[soLuong];
        KhachHang kh; // Đã đổi: Kh -> kh
        
        String[] lArr; // Đã đổi: LArr -> lArr
        int k = 0, m; // Đã đổi: K -> k, M -> m
        
        for (int i = 1; i < dArr.length; i++) { 
            if (dArr[i].isEmpty()) continue; 
            
            lArr = dArr[i].split("#");
            m = 0;

            try {
                kh = new KhachHang();
                // Gọi các hàm set/get camelCase của KhachHang và Nguoi
                kh.setMaKhachHang(lArr[m++]);
                kh.setHoten(lArr[m++]); 
                kh.setNgaythangnamsinh(lArr[m++]);
                kh.setGioitinh(lArr[m++]); 
                kh.setCCCD(lArr[m++]);
                kh.setDiachi(lArr[m++]);
                kh.setSdt(lArr[m++]);
                kh.setEmail(lArr[m++]);
                
                String ngayLapTheStr = lArr[m++]; // Đã đổi
                if (ngayLapTheStr != null && !ngayLapTheStr.trim().isEmpty()) {
                    kh.setNgayLapThe(LocalDate.parse(ngayLapTheStr, DATE_FORMATTER));
                }

                int n = Integer.parseInt(lArr[m++]); // Đã đổi: N -> n
                String[] tmp = new String[n]; // Đã đổi: Tmp -> tmp
                for(int j = 0; j < n; j++) {
                    if (lArr.length > m) { 
                        tmp[j] = lArr[m++];
                    }
                }
                kh.setDsMaSachDangMuon(tmp); 
                
                kh.setSoSachDangMuon(Integer.parseInt(lArr[m++])); 
                kh.setTongTienPhat(Integer.parseInt(lArr[m++])); 
                
                dsKhachHang[k++] = kh;
            } catch (Exception e) {
                System.out.println("Loi khi doc dong " + (i+1) + " trong file dskh.txt: " + e.getMessage());
            }
        }
        if (k < soLuong) {
             setSoLuong(k);
             KhachHang[] dsThucTe = new KhachHang[k]; // Đã đổi
             System.arraycopy(dsKhachHang, 0, dsThucTe, 0, k);
             return dsThucTe;
        }
        return dsKhachHang;
    }

    public void setDsKhachHang(PhanTu[] dsKhachhang){ // Đã đổi tên hàm và tham số
        KhachHang kh; // Đã đổi
        String tenFile = "dskh.txt"; // Đã đổi
        
        FileHandler.resetFile(tenFile); // Đã đổi: ResetFile -> resetFile
        FileHandler.ghiFile(soLuong+"", tenFile); // Đã đổi: GhiFile -> ghiFile
        
        for(int i=0; i < soLuong; i++) {
            kh = (KhachHang) dsKhachhang[i];
            
            String ngayLapTheStr = ""; // Đã đổi
            if (kh.getNgayLapThe() != null) { // Đã đổi: GetNgayLapThe -> getNgayLapThe
                ngayLapTheStr = kh.getNgayLapThe().format(DATE_FORMATTER);
            }

            FileHandler.themKH( // Đã đổi: ThemKH -> themKH
                kh.getMaKhachHang(), // Đã đổi: GetMaKhachHang -> getMaKhachHang
                kh.getHoten(), 
                kh.getNgaythangnamsinh(), 
                kh.getGioitinh(), 
                kh.getCCCD(), 
                kh.getDiachi(), 
                kh.getSdt(), 
                kh.getEmail(),
                ngayLapTheStr, 
                kh.getDsMaSachDangMuon(), 
                kh.getSoSachDangMuon(),   
                kh.getTongTienPhat()      
            );
        }
        this.dsKhachHang = (KhachHang[]) dsKhachhang;
    }
    
    public void nhapDanhSach(){ // Đã đổi tên hàm
        System.out.print("Nhap so luong doc gia: "); 
        
        soLuong = KiemTra.checkNumber(); // Đã đổi: CheckNumber -> checkNumber
        
        dsKhachHang = new KhachHang[soLuong];
        
        int stt, soLuongTemp=0, soLuongCurrent = soLuong; // Đã đổi
        
        for (int i = 0; i < soLuongCurrent; i++){
            dsKhachHang[i] = new KhachHang();
            stt = i+1;
            System.out.println("** Doc gia thu "+stt+" **"); 
            
            dsKhachHang[i].nhap(); // Đã đổi: Nhap -> nhap
            soLuong = ++soLuongTemp;
            
            setDsKhach(dsKhachHang);
            
        }
    } 

    public void xuatDanhSach(){ // Đã đổi tên hàm
        if(soLuong == 0) {
            System.out.println("Chua co doc gia nao!!"); 
            return;
        }
        System.out.println("=== Danh sach doc gia ==="); 
        for (int i = 0; i<soLuong; i++){
            if(getDsKhachHang()[i] != null) { 
                getDsKhachHang()[i].xuat(); // Đã đổi: Xuat -> xuat
            }
        }
        System.out.println();
    } 

    public void themVaoDanhSach(PhanTu pt){ // Đã đổi tên hàm và tham số
        KhachHang[] dsKhachHangTmp = new KhachHang[soLuong+1]; // Đã đổi
        
        for(int i=0;i<soLuong;i++)
            dsKhachHangTmp[i] = getDsKhachHang()[i];
        
        dsKhachHangTmp[soLuong] = (KhachHang) pt;
        
        soLuong++;
        setDsKhachHang(dsKhachHangTmp);
    }

    public void themKPhanTuVaoDanhSach() { // Đã đổi tên hàm
        System.out.print("Nhap so luong doc gia can them vao danh sach: "); 
        int sl; // Đã đổi: Sl -> sl
        boolean check = false; // Đã đổi: Check -> check
        do {
            sl = KiemTra.checkNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
        PhanTu pt; // Đã đổi: Pt -> pt
        for(int i=0;i<sl;i++)
        {
            pt = new KhachHang();
            pt.nhap(); // Đã đổi: Nhap -> nhap
            themVaoDanhSach(pt);
        }
    } 

    public void chinhSuaThongTinPhanTu(){ // Đã đổi tên hàm
        System.out.println("Tim doc gia can chinh sua: "); 
        int viTri = timViTriPhanTu(); // Đã đổi: TimViTriPhanTu -> timViTriPhanTu
        KhachHang[] dskh = getDsKhachHang(); // Đã đổi: Dskh -> dskh
        if (viTri != -1) {
            dskh[viTri].suaThongTin(); // Đã đổi: SuaThongTin -> suaThongTin
            setDsKhachHang(dskh);
        }
        else System.out.println("Khong tim thay!");
    } 

    public void xoaPhanTu(){ // Đã đổi tên hàm
        System.out.println("Tim doc gia can xoa: "); 
        int viTri = timViTriPhanTu(); // Đã đổi
        if (viTri != -1) {
            KhachHang[] dsKhachHangTmp = new KhachHang[soLuong-1]; // Đã đổi
            
            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue; 
                dsKhachHangTmp[k++] = getDsKhachHang()[i];
            }
            
            soLuong--;
            setDsKhachHang(dsKhachHangTmp);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay doc gia!"); 
    }
    
    public PhanTu timPhanTu(){ // Đã đổi tên hàm
        int loai; // Đã đổi
        System.out.print("Tim doc gia theo ten (1) hay theo ma (2), vui long chon: "); 
        
        loai = KiemTra.checkNumber();;
        loai = (loai != 2) ? 1 : 2;
        
        if (loai == 1)
            System.out.print("Nhap ten doc gia can tim: "); 
        if (loai == 2)
            System.out.print("Nhap ma doc gia can tim: "); 
        
        String giaTriCanTim = sc.nextLine(); // Đã đổi
        
        int chon; // Đã đổi
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        
        chon = KiemTra.checkNumber();;
        chon = (chon != 2) ? 1 : 2;
        
        KhachHang[] dsKhachHangTmp = getDsKhachHang(); // Đã đổi
        
        for(int i=0;i<soLuong;i++) {
            if (dsKhachHangTmp[i] == null) continue; 
            if (chon == 1) { 
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return dsKhachHangTmp[i];
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().contains(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return dsKhachHangTmp[i];
            }
        }
        return null;
    }

    public int timViTriPhanTu() { // Đã đổi tên hàm
        int loai; // Đã đổi
        System.out.print("Tim doc gia theo ten (1) hay theo ma (2), vui long chon: "); 
        
        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;
        
        if (loai == 1)
            System.out.print("Nhap ten doc gia can tim: "); 
        if (loai == 2)
            System.out.print("Nhap ma doc gia can tim: "); 
        
        String giaTriCanTim = sc.nextLine(); // Đã đổi
        
        int chon; // Đã đổi
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;
        
        KhachHang[] dsKhachHangTmp = getDsKhachHang(); // Đã đổi
        
        for(int i=0;i<soLuong;i++) {
            if (dsKhachHangTmp[i] == null) continue; 
            if (chon == 1) { 
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriKhachHang(String maKhachHang) { // Đã đổi tên hàm và tham số
        KhachHang[] dskh = getDsKhachHang(); // Đã đổi
        for(int i=0;i<soLuong;i++) {
            if (dskh[i] != null && dskh[i].getMaKhachHang().equals(maKhachHang)) 
                return i;
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String thamSo) { // Đã đổi tên hàm và tham số
        KhachHang[] dskh = getDsKhachHang(); // Đã đổi
        for(int i=0;i<soLuong;i++) {
            if (dskh[i] != null && dskh[i].getMaKhachHang().equals(thamSo)) 
                return dskh[i];
        }
        return null;
    }

    public void thongKe() { // Đã đổi tên hàm
        int chon, n; // Đã đổi
        dsKhachHang = getDsKhachHang();
        do {
            System.out.println("=== Thong ke Doc Gia ===");
            System.out.println("1. Loc doc gia voi so sach dang muon >= n");
            System.out.println("2. Loc doc gia co tong tien phat >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.checkNumber();

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong sach muon can tim: ");
                    n = KiemTra.checkNumber();
                    for (KhachHang khachHang: dsKhachHang) {
                        if (khachHang == null) continue;
                        if (khachHang.getSoSachDangMuon() >= n) khachHang.xuat(); 
                    }
                    break;
                case 2:
                    System.out.print("Nhap so luong tong tien phat can tim: ");
                    n = KiemTra.checkNumber();
                    for (KhachHang khachHang: dsKhachHang) {
                        if (khachHang == null) continue;
                        if (khachHang.getTongTienPhat() >= n) khachHang.xuat(); 
                    }
                    break;
                
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
