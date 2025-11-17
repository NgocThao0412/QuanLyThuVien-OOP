package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.KhachHang;
import SanPham.PhanTu;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DanhSachKhachHang implements DanhSachChung {
    private int soLuong;
    private KhachHang[] dsKhachHang;

    public DanhSachKhachHang(){
        dsKhachHang = getDsKhachHang();
    }

    public DanhSachKhachHang(int soLuong, KhachHang[] dsKhachHang){
        this.soLuong = soLuong;
        this.dsKhachHang = dsKhachHang;
    }

    public int getSoLuong(){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public KhachHang[] getDsKhachHang(){
        String data = FileHandler.docFile("dskh.txt");
        String[] dArr = data.split("\n");

        if (dArr.length == 0 || dArr[0].length() == 0) setSoLuong(0);
        else {
            try {
                setSoLuong(Integer.parseInt(dArr[0]));
            } catch (NumberFormatException e) {
                setSoLuong(0);
            }
        }

        dsKhachHang = new KhachHang[soLuong];
        KhachHang kh;

        String[] lArr;
        int k = 0, m;

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");
            m = 0;

            if (lArr.length < 11) continue;

            kh = new KhachHang();
            kh.setMaKhachHang(lArr[m++]);
            kh.setHoten(lArr[m++]);
            kh.setNgaythangnamsinh(lArr[m++]);
            kh.setGioitinh(lArr[m++]);
            kh.setCCCD(lArr[m++]);
            kh.setDiachi(lArr[m++]);
            kh.setSdt(lArr[m++]);
            kh.setEmail(lArr[m++]);

            try {
                kh.setNgayLapThe(LocalDate.parse(lArr[m++], KhachHang.DATE_FORMATTER));
            } catch (DateTimeParseException e) {
                kh.setNgayLapThe(null);
            }

            try {
                kh.setTongTienPhat(Integer.parseInt(lArr[m++]));
            } catch (NumberFormatException e) {
                kh.setTongTienPhat(0);
            }

            int soLuongSachMuon = 0;
            try {
                soLuongSachMuon = Integer.parseInt(lArr[m++]);
            } catch (NumberFormatException e) {
                soLuongSachMuon = 0;
            }

            String[] dsMaSach = new String[soLuongSachMuon];
            for(int j = 0; j < soLuongSachMuon; j++) {
                if (m < lArr.length) dsMaSach[j] = lArr[m++];
                else dsMaSach[j] = "";
            }
            kh.setDsMaSachDangMuon(dsMaSach);

            dsKhachHang[k++] = kh;
        }
        return dsKhachHang;
    }

    public void setDsKhachHang(PhanTu[] dsKhachhang){
        KhachHang kh;
        String tenFile = "dskh.txt";

        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i = 0; i < soLuong; i++) {
            kh = (KhachHang) dsKhachhang[i];

            String ngayLapTheStr = (kh.getNgayLapThe() != null) ? kh.getNgayLapThe().format(KhachHang.DATE_FORMATTER) : "null";

            FileHandler.ghiFile(
                kh.getMaKhachHang() + "#" +
                kh.getHoten() + "#" +
                kh.getNgaythangnamsinh() + "#" +
                kh.getGioitinh() + "#" +
                kh.getCCCD() + "#" +
                kh.getDiachi() + "#" +
                kh.getSdt() + "#" +
                kh.getEmail() + "#" +
                ngayLapTheStr + "#" + 
                kh.getTongTienPhat() + "#" +
                kh.getSoSachDangMuon(),
                tenFile
            );

            String[] dsMaSach = kh.getDsMaSachDangMuon();
            if (dsMaSach != null && dsMaSach.length > 0) {
                for (String maSach : dsMaSach) {
                    FileHandler.ghiFile("#" + maSach, tenFile);
                }
            }
            FileHandler.ghiFile("\n", tenFile);
        }
        this.dsKhachHang = (KhachHang[]) dsKhachhang;
    }

    public void nhapDanhSach(){
        System.out.print("Nhap so luong doc gia: ");

        int soLuongMoi = KiemTra.CheckNumber();
        int soLuongBanDau = getSoLuong();
        
        KhachHang[] dsKhachHangMoi = new KhachHang[soLuongBanDau + soLuongMoi];

        if (dsKhachHang != null) {
            System.arraycopy(dsKhachHang, 0, dsKhachHangMoi, 0, soLuongBanDau);
        }

        for (int i = 0; i < soLuongMoi; i++){
            int stt = soLuongBanDau + i + 1;
            dsKhachHangMoi[soLuongBanDau + i] = new KhachHang();
            System.out.println("** Doc gia thu "+stt+" **");

            dsKhachHangMoi[soLuongBanDau + i].nhap();
        }
        
        setSoLuong(soLuongBanDau + soLuongMoi);
        setDsKhachHang(dsKhachHangMoi);
    }
    
    public void xuatDanhSach(){
        if(soLuong == 0) {
            System.out.println("Chua co doc gia nao!!");
            return;
        }
        System.out.println("=== Danh sach doc gia ===");
        KhachHang[] dskh = getDsKhachHang();
        for (int i = 0; i < soLuong; i++){
            dskh[i].xuat();
        }
        System.out.println();
    }

    public void themVaoDanhSach(PhanTu pt){
        KhachHang[] dsKhachHangCu = getDsKhachHang();
        KhachHang[] dsKhachHangTmp = new KhachHang[soLuong+1];

        for(int i = 0; i < soLuong; i++)
            dsKhachHangTmp[i] = dsKhachHangCu[i];

        dsKhachHangTmp[soLuong] = (KhachHang) pt;

        soLuong++;
        setDsKhachHang(dsKhachHangTmp);
    }

    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong doc gia can them vao danh sach: ");
        int sl;
        boolean check;
        do {
            sl = KiemTra.CheckNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);

        PhanTu pt;
        for(int i = 0; i < sl; i++)
        {
            pt = new KhachHang();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    public void chinhSuaThongTinPhanTu(){
        System.out.println("Tim doc gia can chinh sua: ");
        int viTri = timViTriPhanTu();
        KhachHang[] dskh = getDsKhachHang();
        if (viTri != -1) {
            dskh[viTri].suaThongTin();
            setDsKhachHang(dskh);
        }
        else System.out.println("Khong tim thay!");
    }

    public void xoaPhanTu(){
        System.out.println("Tim doc gia can xoa: ");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            KhachHang[] dsKhachHangTmp = new KhachHang[soLuong-1];

            for(int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri) continue;
                dsKhachHangTmp[k++] = getDsKhachHang()[i];
            }

            soLuong--;
            setDsKhachHang(dsKhachHangTmp);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay doc gia!");
    }

    public PhanTu timPhanTu(){

        int loai;
        System.out.print("Tim doc gia theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten doc gia can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma doc gia can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        KhachHang[] dsKhachHangTmp = getDsKhachHang();

        for(int i = 0; i < soLuong; i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return dsKhachHangTmp[i];
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().toLowerCase().contains(giaTriCanTim.toLowerCase()))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().toLowerCase().contains(giaTriCanTim.toLowerCase()))
                        return dsKhachHangTmp[i];
            }
        }
        return null;
    }

    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim doc gia theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten doc gia can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma doc gia can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        KhachHang[] dsKhachHangTmp = getDsKhachHang();

        for(int i = 0; i < soLuong; i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().equals(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().toLowerCase().contains(giaTriCanTim.toLowerCase()))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang().toLowerCase().contains(giaTriCanTim.toLowerCase()))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriKhachHang(String maKhachHang) {
        KhachHang[] dskh = getDsKhachHang();
        for(int i = 0; i < soLuong; i++) {
            if (dskh[i].getMaKhachHang().equals(maKhachHang))
                return i;
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String thamSo) {
        KhachHang[] dskh = getDsKhachHang();
        for(int i = 0; i < soLuong; i++) {
            if (dskh[i].getMaKhachHang().equals(thamSo))
                return dskh[i];
        }
        return null;
    }

    public void thongKe() {
        int chon, n;
        KhachHang[] dskh = getDsKhachHang();
        do {
            System.out.println("=== Thong ke Doc Gia ===");
            System.out.println("1. Loc doc gia co so sach dang muon >= n");
            System.out.println("2. Loc doc gia voi tong tien phat >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.CheckNumber();

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong sach dang muon can tim: ");
                    n = KiemTra.CheckNumber();
                    for (KhachHang khachHang: dskh) {
                        if (khachHang.getSoSachDangMuon() >= n) khachHang.xuat();
                    }
                    break;
                case 2:
                    System.out.print("Nhap tong tien phat can tim: ");
                    n = KiemTra.CheckNumber();
                    for (KhachHang khachHang: dskh) {
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
