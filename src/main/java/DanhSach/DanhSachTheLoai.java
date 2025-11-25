package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import SanPham.LoaiSach;
import SanPham.PhanTu;

public class DanhSachTheLoai implements DanhSachChung {

    private int soLuong;
    private LoaiSach[] dsTheLoai;

    public DanhSachTheLoai() {
        dsTheLoai = docFile();
        if (dsTheLoai == null) {
            dsTheLoai = new LoaiSach[0];
            soLuong = 0;
        } else {
            // đảm bảo soLuong đồng bộ với mảng (docFile đã cố gắng set soLuong nhưng an toàn hơn khi sync lại)
            soLuong = dsTheLoai.length;
        }
    }

    public DanhSachTheLoai(int soLuong, LoaiSach[] dsTheLoai) {
        this.soLuong = soLuong;
        this.dsTheLoai = (dsTheLoai == null) ? new LoaiSach[0] : dsTheLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LoaiSach[] getDsTheLoai() {
        // trả về mảng hiện tại (không đọc file mỗi lần gọi để tránh I/O thừa)
        if (dsTheLoai == null) return new LoaiSach[0];
        return dsTheLoai;
    }

    private LoaiSach[] docFile() {
        String data = FileHandler.docFile("dstl.txt");
        if (data == null || data.trim().isEmpty()) {
            soLuong = 0;
            return new LoaiSach[0];
        }

        String[] lines = data.split("\n");

        if (lines.length == 0 || lines[0].trim().isEmpty()) {
            soLuong = 0;
            return new LoaiSach[0];
        }

        // try-catch để tránh NumberFormatException khi file hỏng
        int fileCount = 0;
        try {
            fileCount = Integer.parseInt(lines[0].trim());
        } catch (Exception e) {
            fileCount = 0;
        }

        if (fileCount <= 0) {
            soLuong = 0;
            return new LoaiSach[0];
        }

        LoaiSach[] ds = new LoaiSach[fileCount];
        int actualLoaded = 0;

        for (int i = 0; i < fileCount && (i + 1) < lines.length; i++) {

            String line = lines[i + 1].trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("#");

            // Cần ít nhất mã, tên, số lượng
            if (parts.length < 3) continue;

            LoaiSach tl = new LoaiSach();
            tl.setmaLoaiSach(parts[0].trim());
            tl.settenLoaiSach(parts[1].trim());

            int sl = 0;
            try {
                sl = Integer.parseInt(parts[2].trim());
            } catch (Exception e) {
                sl = 0;
            }

            String[] dsMa = new String[sl];
            for (int j = 0; j < sl; j++) {
                int idx = 3 + j;
                if (idx < parts.length) dsMa[j] = parts[idx].trim();
                else dsMa[j] = ""; // tránh null nếu file thiếu mã
            }

            tl.setDsMaSach(dsMa);
            tl.setSoLuong(sl);

            ds[actualLoaded++] = tl;
        }

        // nếu actualLoaded < fileCount thì co rút mảng
        if (actualLoaded != fileCount) {
            LoaiSach[] trimmed = new LoaiSach[actualLoaded];
            System.arraycopy(ds, 0, trimmed, 0, actualLoaded);
            ds = trimmed;
        }

        soLuong = ds.length;
        return ds;
    }
    public void setDsTheLoai(LoaiSach[] ds) {
        if (ds == null) ds = new LoaiSach[0];
        FileHandler.resetFile("dstl.txt");
        FileHandler.ghiFile(ds.length + "\n", "dstl.txt");

        for (LoaiSach tl : ds) {
            // đảm bảo không bị NullPointer nếu getDsMaSach() là null
            String[] dsm = tl.getDsMaSach();
            if (dsm == null) dsm = new String[0];

            FileHandler.themTheLoai(
                    tl.getmaLoaiSach(),
                    tl.gettenLoaiSach(),
                    tl.getSoLuong(),
                    dsm
            );
        }
        this.dsTheLoai = ds;
        this.soLuong = ds.length;
    }
    public boolean CheckmaTheLoai(String thamSo) {
        LoaiSach[] dsDm = getDsTheLoai();
        for(int i=0;i<soLuong;i++) {
            if (thamSo.contains(dsDm[i].getmaLoaiSach()))
                return true;
        }
        return false;
    }

    public void ThemSachvaoTheLoai(String masach) {
        LoaiSach[] dstl = getDsTheLoai();
        int vitri = -1;
        for(int i = 0; i < dstl.length; i++) {
            if(masach.contains(dstl[i].getmaLoaiSach())) {
                vitri = i;
                break;
            }
        }
        dstl[vitri].themMaSachVaoDS(masach);
        setDsTheLoai(dstl);
    }
    public String getTheloai(String maTheLoai) {
        LoaiSach[] dstl = getDsTheLoai();
        int vitri = -1;
        for(int i = 0; i < dstl.length; i++) {
            if(maTheLoai.contains(dstl[i].getmaLoaiSach())) {
                vitri = i;
                break;
            }
        }
        return dstl[vitri].gettenLoaiSach();
    }
    public void resetDstl() {
        LoaiSach[] dstl = getDsTheLoai();
        for(int i = 0; i < dstl.length; i++) {
            dstl[i].setSoLuong();
            dstl[i].setDsMaSach();
        }
        setDsTheLoai(dstl);
    }
    public void nhapDanhSach() {
        System.out.println("Nhap so luong The Loai: ");
        soLuong = KiemTra.CheckNumber();
        dsTheLoai = new LoaiSach[soLuong];
        int stt, soLuongTemp=0, soLuongCurrent = soLuong;
        for (int i = 0; i < soLuongCurrent; i++){
            dsTheLoai[i] = new LoaiSach();
            stt = i+1;
            System.out.println("** Loai Sach thu "+stt+" **");

            dsTheLoai[i].nhap();
            soLuong = ++soLuongTemp;
            // mỗi lần đọc phần tử từ mảng sẽ ghi trực tiếp vào file kèm số lượng phần tử đã đọc
            setDsTheLoai(dsTheLoai);
        }
    }
    // Chỉ xuất thể loại
    public void xuatDanhSachTheLoai() {
        System.out.println("=== Danh sach Loai Sach ===");
        for (int i = 0; i < soLuong; i++){
            dsTheLoai[i].xuatLoaiSach();
        }
        System.out.println();
    }
    
    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co loai Sach nao!!");
            return;
        }
        System.out.println("=== Danh sach Loai Sach ===");
        for (int i = 0; i < soLuong; i++){
            dsTheLoai[i].xuat();
        }
        System.out.println();
    }

    public void themVaoDanhSach(PhanTu pt) {
        LoaiSach[] dsDm = new LoaiSach[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsDm[i] = getDsTheLoai()[i];
        dsDm[soLuong] = (LoaiSach) pt;
        soLuong++;
        setDsTheLoai(dsDm);
    }

    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong dong Sach can them vao danh sach: \n");
        int sl;
        boolean check = false;
        do {
            sl = KiemTra.CheckNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new LoaiSach();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim loai sach can chinh sua: ");

        int viTri = timViTriPhanTu();

        LoaiSach[] dsDmSp = getDsTheLoai();

        if (viTri != -1) {
            dsDmSp[viTri].suaThongTin();
            setDsTheLoai(dsDmSp);
        } else System.out.println("Khong tim thay!");
    }
    public void xoaPhanTuMaSach(String ms, String loaisach) {
        LoaiSach[] dstl = getDsTheLoai();
        int viTri1 = -1, viTri2 = -1;
        for (int i = 0; i < dstl.length; i++) if(dstl[i].gettenLoaiSach().equals(loaisach)) {
            viTri1 = i;
            break;
        }
        String[] dstlx = dstl[viTri1].getDsMaSach();
        for (int i = 0; i < dstlx.length; i++) if(dstlx[i].equals(ms)) {
            viTri2 = i;
            break;
        }
        // Nếu tìm thấy
        String[] dstlxtmp = new String[dstlx.length - 1];
        for (int i = 0, k = 0; i < dstlx.length; i++) {
            if (i == viTri2) continue; // bỏ phần tử
            dstlxtmp[k++] = dstlx[i];
        }
        dstl[viTri1].setDsMaSach(dstlxtmp);
        setDsTheLoai(dstl);
    }
    public void xoaPhanTu() {
        System.out.println("Tim Loai Sach can xoa: ");

        int viTri = timViTriPhanTu();

        // Nếu tìm thấy
        if (viTri != -1) {
            LoaiSach[] dsDm = new LoaiSach[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue; // bỏ phần tử
                dsDm[k++] = getDsTheLoai()[i];
            }

            soLuong--;
            setDsTheLoai(dsDm);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay loai Sach!");
    }

    public PhanTu timPhanTu() { // tìm danh mục sản phẩm dựa theo tên hoặc khoá (tương đối || tuyệt đối)

        int loai;
        System.out.print("Tim loai sach theo ten (1) hay theo ma (2), vui long chon: ");
        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten loai sach can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma loai sach can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;
        LoaiSach[] dsDm = getDsTheLoai();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác

                if (loai == 1)
                    if (dsDm[i].gettenLoaiSach().equalsIgnoreCase(giaTriCanTim))
                        return dsDm[i];
                if (loai == 2)
                    if (dsDm[i].getmaLoaiSach().equalsIgnoreCase(giaTriCanTim))
                        return dsDm[i];

            } else {

                if (loai == 1)
                    if (dsDm[i].gettenLoaiSach().contains(giaTriCanTim))
                        return dsDm[i];
                if (loai == 2)
                    if (dsDm[i].getmaLoaiSach().contains(giaTriCanTim))
                        return dsDm[i];

            }
        }
        return null;
    }

    public int timViTriPhanTu() { // trả về vị trí phần tử trong mảng
        int loai;
        System.out.print("Tim loai sach theo ten (1) hay theo ma (2), vui long chon: ");
        loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;
        if (loai == 1)
            System.out.print("Nhap ten loai sach can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma loai sach can tim: ");
        String giaTriCanTim = sc.nextLine();
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.CheckNumber();
        chon = (chon != 2) ? 1 : 2;
        LoaiSach[] dsDm = getDsTheLoai();
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsDm[i].gettenLoaiSach().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsDm[i].getmaLoaiSach().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsDm[i].gettenLoaiSach().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsDm[i].getmaLoaiSach().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String thamSo) {
        LoaiSach[] dsDm = getDsTheLoai();
        for(int i=0;i<soLuong;i++) {
            if (dsDm[i].getmaLoaiSach().equalsIgnoreCase(thamSo))
                return dsDm[i];
        }
        return null;
    }

    public void thongKe() {
        int chon, n;
        dsTheLoai = getDsTheLoai();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc loai sach co so luong >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.CheckNumber();

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = KiemTra.CheckNumber();
                    for (LoaiSach dmSP: dsTheLoai) {
                        if (dmSP.getSoLuong() >= n) dmSP.xuat();
                    }
                    break;
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}

