package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import SanPham.PhanTu;
import SanPham.Sach;
import SanPham.SachTamLy;
import SanPham.SachThieuNhi;
import SanPham.SachTrinhTham;

public class DanhSachSach implements DanhSachChung {
    private int soLuong;
    private Sach[] dsSach;

    public DanhSachSach() {
        dsSach = getDsSach();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

   public Sach[] getDsSach() {
    String data = FileHandler.docFile("dss.txt");
    if (data == null || data.isEmpty()) {  // kiểm tra file rỗng
        setSoLuong(0);
        return new Sach[0];
    }

    String[] dArr = data.split("\n");

    // dòng đầu tiên là số lượng
    try {
        setSoLuong(Integer.parseInt(dArr[0].trim()));
    } catch (NumberFormatException e) {
        setSoLuong(0);
    }

    dsSach = new Sach[soLuong];
    int k = 0;

    for (int i = 1; i < dArr.length; i++) {
        String[] lArr = dArr[i].split("#");
        if (lArr.length < 7) continue;  // đảm bảo đủ cột

        Sach sp = null;

        switch (lArr[3]) {  // chọn loại sách
            case "Sach Trinh Tham": sp = new SachTrinhTham(); break;
            case "Sach Thieu Nhi": sp = new SachThieuNhi(); break;
            case "Sach Tam Ly": sp = new SachTamLy(); break;
            default: continue;  // nếu loại không hợp lệ, bỏ qua
        }

        int m = 0;
        sp.setmaSach(lArr[m++]);
        sp.settenSach(lArr[m++]);
        sp.setTacGia(lArr[m++]);
        sp.setLoaiSach(lArr[m++]);

        String nxb = lArr[m++];
        sp.setSoLuong(Integer.parseInt(lArr[m++]));
        sp.setPrice(Integer.parseInt(lArr[m++]));

        // gán NXB theo loại
        if (sp instanceof SachTrinhTham) ((SachTrinhTham) sp).setNhaXuatBan(nxb);
        else if (sp instanceof SachThieuNhi) ((SachThieuNhi) sp).setNhaXuatBan(nxb);
        else if (sp instanceof SachTamLy) ((SachTamLy) sp).setNhaXuatBan(nxb);

        dsSach[k++] = sp;
    }

    // nếu có dòng bị bỏ qua, chỉnh lại mảng cho đủ số lượng thực
    if (k < soLuong) {
        Sach[] tmp = new Sach[k];
        System.arraycopy(dsSach, 0, tmp, 0, k);
        dsSach = tmp;
        setSoLuong(k);
    }

    return dsSach;
}


    public void setDsSach(Sach[] dsSach) {
        String tenFile = "dss.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong + "\n", tenFile);

        for (int i = 0; i < soLuong; i++) {
            Sach sp = dsSach[i];
            String nhaXuatBan = "";
            if (sp instanceof SachTrinhTham)
                nhaXuatBan = ((SachTrinhTham) sp).getNhaXuatBan();
            else if (sp instanceof SachThieuNhi)
                nhaXuatBan = ((SachThieuNhi) sp).getNhaXuatBan();
            else if (sp instanceof SachTamLy)
                nhaXuatBan = ((SachTamLy) sp).getNhaXuatBan();

            FileHandler.themSach(
                sp.getmaSach(), sp.gettenSach(), sp.getTacGia(), sp.getLoaiSach(),nhaXuatBan,
                sp.getSoLuong(), sp.getPrice()
            );
        }

        this.dsSach = dsSach;
    }

    // ======== NHẬP/XUẤT DANH SÁCH =========
    public void nhapDanhSach() {
        FileHandler.resetFile("dss.txt");
        System.out.print("Moi nhap so luong Sach: ");

        soLuong = KiemTra.CheckNumber();
        dsSach = new Sach[soLuong];

        int soLuongTemp=0, soLuongCurrent = soLuong;
        String tmp;

        for(int i=0;i<soLuongCurrent;i++){
            System.out.println("Them Sach thu " + (i+1) + ": ");
            System.out.println("Chon loai sach: ");
            tmp = KiemTra.checkTheLoaiSach();
            if(tmp.equals("Sach Trinh Tham")) {
                dsSach[i] = new SachTrinhTham();
                dsSach[i].nhap();
            }
            else if(tmp.equals("Sach Thieu Nhi")) {
                dsSach[i] = new SachThieuNhi();
                dsSach[i].nhap();
            }
            else if(tmp.equals("Sach Tam Ly")) {
                dsSach[i] = new SachTamLy();
                dsSach[i].nhap();
            }
            soLuong = ++soLuongTemp;
            setDsSach(dsSach);
        }
        DanhSachTheLoai dstl = new DanhSachTheLoai();
        dstl.resetDstl();
    }

    public void xuatDanhSach() {
        if (soLuong == 0) {
            System.out.println("Chua co Sach nao!!");
            return;
        }
        System.out.println("=== Danh sach Sach ===");
        System.out.printf("%-15s %-35s %-30s %-20s %-10s %-10s\n",
                "Ma Sach", "Ten Sach", "Tac Gia", "Loai Sach", "So luong", "Gia");
        for (int i = 0; i < soLuong; i++) {
      System.out.println("-----------------------------------------------------------");
            dsSach[i].xuat();
        }
        System.out.println();
    }

    // ======== THEM/XOA/SUA =========
    public void themVaoDanhSach(PhanTu pt) {
         Sach[] dsSachTemp = new Sach[soLuong+1];
        for(int i=0;i<soLuong;i++){
            dsSachTemp[i] = getDsSach()[i];
        }
        dsSachTemp[soLuong] = (Sach) pt;
        soLuong++;
        setDsSach(dsSachTemp);
    }

    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong sach can them vao danh sach: ");
        int sl;
        boolean check = false;
        do {
            sl = KiemTra.CheckNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
        PhanTu pt;
        String tmp;
        for(int i=0;i<sl;i++)
        {
            System.out.println("Them sach thu " + (i+1) + ": ");
            System.out.println("Chon loai Sach: ");
            tmp = KiemTra.checkTheLoaiSach();
            if(tmp.equals("Sach Trinh Tham")) {
                pt = new SachTrinhTham();
                pt.nhap();
                themVaoDanhSach(pt);
            }
            else if(tmp.equals("Sach Thieu Nhi")) {
                pt = new SachThieuNhi();
                pt.nhap();
                themVaoDanhSach(pt);
            }
            else if(tmp.equals("Sach Tam Ly")) {
                pt = new SachTamLy();
                pt.nhap();
                themVaoDanhSach(pt);
            }
        }
        DanhSachTheLoai dstl = new DanhSachTheLoai();
        dstl.resetDstl();
    }
     public void themPhanTuVaoDanhSach() {
        PhanTu pt;
        String tmp;
        System.out.println("Chon loai Sach: ");
        tmp = KiemTra.checkTheLoaiSach();
        if(tmp.equals("Sach Trinh Tham")) {
            pt = new SachTrinhTham();
            pt.nhap();
            themVaoDanhSach(pt);
        }
        else if(tmp.equals("Sach Thieu Nhi")) {
            pt = new SachThieuNhi();
            pt.nhap();
            themVaoDanhSach(pt);
        }
        else if(tmp.equals("Sach Tam Ly")) {
            pt = new SachTamLy();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    public void xoaPhanTu() {
        System.out.println("Tim sach can xoa: ");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            Sach[] temp = new Sach[soLuong - 1];
            for (int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri) continue;
                temp[k++] = dsSach[i];
            }

            // Xoa khoi danh sach the loai
            DanhSachTheLoai dstl = new DanhSachTheLoai();
            dstl.xoaPhanTuMaSach(getDsSach()[viTri].getmaSach(), getDsSach()[viTri].getLoaiSach());

            soLuong--;
            setDsSach(temp);
            System.out.println("Xoa thanh cong!");
        } else System.out.println("Khong tim thay Sach!");
    }

    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim Sach can chinh sua: ");
        int viTri = timViTriPhanTu();
        if (viTri != -1) {
            dsSach[viTri].suaThongTin();
            setDsSach(dsSach);
        } else System.out.println("Khong tim thay Sach!");
    }

   public PhanTu timPhanTu() { // tìm sản phẩm theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim Sach theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten sach can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma sach can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        Sach[] dsSachTmp = getDsSach();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSachTmp[i].gettenSach().equalsIgnoreCase(giaTriCanTim))
                        return dsSachTmp[i];
                if (loai == 2)
                    if (dsSachTmp[i].getmaSach().equalsIgnoreCase(giaTriCanTim))
                        return dsSachTmp[i];
            } else {
                if (loai == 1)
                    if (dsSachTmp[i].gettenSach().contains(giaTriCanTim))
                        return dsSachTmp[i];
                if (loai == 2)
                    if (dsSachTmp[i].getmaSach().contains(giaTriCanTim))
                        return dsSachTmp[i];
            }
        }
        return null;
    }
    public int timViTriPhanTu() {
      int loai;
        System.out.print("Tim sach theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;
        if (loai == 1)
            System.out.print("Nhap ten sach can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma sach can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;

        Sach[] dsSachTmp = getDsSach();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSachTmp[i].gettenSach().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSachTmp[i].getmaSach().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsSachTmp[i].gettenSach().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSachTmp[i].getmaSach().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriSach(String maSach) {
        Sach[] dsSach = getDsSach();
        for(int i=0;i<soLuong;i++) {
            if(dsSach[i].getmaSach().equalsIgnoreCase(maSach))
                return i;
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String thamso) {
         Sach[] dss = getDsSach();
        for(int i=0;i<soLuong;i++) {
            if(dss[i].getmaSach().equalsIgnoreCase(thamso))
                return dss[i];
        }
        return null;
    }

    // ======== THONG KE =========
     public void thongKe() {
        int chon,n;
        dsSach = getDsSach();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In sach co so luong lon hon n");
            System.out.println("2. In sach co gia ban lon hon n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = KiemTra.CheckNumber();
            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = KiemTra.CheckNumber();
                    for(int i=0;i<soLuong;i++) {
                        if(dsSach[i].getSoLuong() > n){
                            dsSach[i].xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap gia ban can tim: ");
                    n = KiemTra.CheckNumber();
                    for(int i=0;i<soLuong;i++) {
                        if(dsSach[i].getPrice() > n){
                            dsSach[i].xuat();
                        }
                    }
                    break;
                default:
                    chon=0;
                    break;
            }
        } while(chon!=0);
    }
}
