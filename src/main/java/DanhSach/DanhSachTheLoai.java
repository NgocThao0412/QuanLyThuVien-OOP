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

    public void themVaoDanhSach(PhanTu pt) {
        if (pt == null) return;

        LoaiSach[] current = (dsTheLoai == null) ? new LoaiSach[0] : dsTheLoai;
        LoaiSach[] newArr = new LoaiSach[current.length + 1];

        System.arraycopy(current, 0, newArr, 0, current.length);
        newArr[current.length] = (LoaiSach) pt;

        soLuong = newArr.length;
        setDsTheLoai(newArr);
    }

    // ==============================
    // 4. THÊM K THỂ LOẠI
    // ==============================
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong the loai can them: ");
        int sl;
        do {
            sl = KiemTra.CheckNumber();
            if (sl <= 0) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while (sl <= 0);

        PhanTu pt;
        for (int i = 0; i < sl; i++) {
            pt = new LoaiSach();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    // ==============================
    // 5. CHỈNH SỬA
    // ==============================
    public void chinhSuaThongTinPhanTu() {

        int viTri = timViTriPhanTu();

        LoaiSach[] dsDmSp = getDsTheLoai();

        if (viTri != -1 && viTri < dsDmSp.length) {
            dsDmSp[viTri].suaThongTin();
            setDsTheLoai(dsDmSp);
        } else System.out.println("Khong tim thay!");
    }

    // ==============================
    // 6. XÓA THỂ LOẠI
    // ==============================
    public void xoaPhanTu() {
        System.out.println("Tim dong the loai can xoa: ");

        int viTri = timViTriPhanTu();

        // Nếu tìm thấy
        if (viTri != -1 && dsTheLoai != null && viTri < dsTheLoai.length) {
            LoaiSach[] dsDm = new LoaiSach[soLuong - 1];

            for (int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri) continue; // bỏ phần tử
                dsDm[k++] = dsTheLoai[i];
            }

            soLuong = dsDm.length;
            setDsTheLoai(dsDm);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay the loai!");
    }

    // ==============================
    // 7. LẤY TÊN THỂ LOẠI TỪ MÃ SÁCH
    // ==============================
    public String getTheLoai(String maSach) {
        if (maSach == null || dsTheLoai == null) return null;

        for (LoaiSach tl : dsTheLoai) {
            String[] dsMa = tl.getDsMaSach();
            if (dsMa == null) continue;
            for (String ms : dsMa) {
                if (ms != null && ms.equalsIgnoreCase(maSach))
                    return tl.gettenLoaiSach();
            }
        }
        return null; // Không tìm thấy
    }

    // ==============================
    // 8. THÊM SÁCH VÀO THỂ LOẠI
    // ==============================
    public void themSachVaoTheLoai(String maSach, String tenTL) {
        if (maSach == null || tenTL == null || dsTheLoai == null) return;

        for (LoaiSach tl : dsTheLoai) {
            if (tl.gettenLoaiSach() != null && tl.gettenLoaiSach().equalsIgnoreCase(tenTL)) {

                String[] old = tl.getDsMaSach();
                if (old == null) old = new String[0];

                String[] newArr = new String[old.length + 1];
                System.arraycopy(old, 0, newArr, 0, old.length);
                newArr[old.length] = maSach;

                tl.setDsMaSach(newArr);
                tl.setSoLuong(newArr.length);
                break;
            }
        }
        setDsTheLoai(dsTheLoai);
    }

    // ==============================
    // 9. XÓA SÁCH TỪ THỂ LOẠI
    // ==============================
    public void xoaPhanTuMaSach(String maSach, String tenTL) {
        if (maSach == null || tenTL == null || dsTheLoai == null) return;

        for (LoaiSach tl : dsTheLoai) {

            if (tl.gettenLoaiSach() != null && tl.gettenLoaiSach().equalsIgnoreCase(tenTL)) {

                String[] ds = tl.getDsMaSach();
                if (ds == null || ds.length == 0) return;

                int vt = -1;

                for (int i = 0; i < ds.length; i++)
                    if (ds[i] != null && ds[i].equalsIgnoreCase(maSach)) {
                        vt = i;
                        break;
                    }

                if (vt == -1) return;

                String[] newArr = new String[ds.length - 1];

                for (int i = 0, k = 0; i < ds.length; i++) {
                    if (i == vt) continue;
                    newArr[k++] = ds[i];
                }

                tl.setDsMaSach(newArr);
                tl.setSoLuong(newArr.length);
            }
        }
        setDsTheLoai(dsTheLoai);
    }

    // ==============================
    // 10. TÌM VỊ TRÍ THỂ LOẠI
    // ==============================
    public int timViTriPhanTu() {

        System.out.print("Tim theo ten (1) hay ma (2): ");
        int loai = KiemTra.CheckNumber();
        loai = (loai != 2) ? 1 : 2;

        // clear buffer nếu cần
        try {
            sc.nextLine();
        } catch (Exception e) {
            // nếu sc không tồn tại hoặc lỗi, bỏ qua (PhanTu của bạn nên có sc)
        }

        System.out.print("Nhap gia tri can tim: ");
        String key = sc.nextLine();

        LoaiSach[] ds = (dsTheLoai == null) ? new LoaiSach[0] : dsTheLoai;

        for (int i = 0; i < ds.length; i++) {
            if (ds[i] == null) continue;
            if (loai == 1 && ds[i].gettenLoaiSach() != null && ds[i].gettenLoaiSach().equalsIgnoreCase(key))
                return i;

            if (loai == 2 && ds[i].getmaLoaiSach() != null && ds[i].getmaLoaiSach().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    // ==============================
    // 11. TÌM PHẦN TỬ GIỐNG
    // ==============================
    public PhanTu layPhanTuVoi(String ma) {
        if (ma == null || dsTheLoai == null) return null;
        for (LoaiSach tl : dsTheLoai) {
            if (tl.getmaLoaiSach() != null && tl.getmaLoaiSach().equalsIgnoreCase(ma))
                return tl;
        }
        return null;
    }

    // ==============================
    // 12. XUẤT
    // ==============================
    public void xuatDanhSach() {
        if (soLuong == 0 || dsTheLoai == null || dsTheLoai.length == 0) {
            System.out.println("Chua co the loai nao!");
            return;
        }
        System.out.println("=== Danh sach the loai ===");
        for (LoaiSach tl : dsTheLoai) {
            if (tl != null) tl.xuat();
        }
    }

    // ==============================
    // 13. RESET
    // ==============================
    public void resetDsTheLoai() {
        if (dsTheLoai == null) dsTheLoai = new LoaiSach[0];

        for (LoaiSach tl : dsTheLoai) {
            if (tl == null) continue;
            tl.setSoLuong(0);
            tl.setDsMaSach(new String[0]);
        }
        setDsTheLoai(dsTheLoai);
    }

    // ==============================
    // 14. THỐNG KÊ
    // ==============================
    public void thongKe() {
        int chon;

        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc the loai co so luong >= n");
            System.out.println("0. Thoat");
            System.out.print("Moi chon: ");

            chon = KiemTra.CheckNumber();

            if (chon == 1) {
                System.out.print("Nhap n: ");
                int n = KiemTra.CheckNumber();

                if (dsTheLoai == null) break;
                for (LoaiSach tl : dsTheLoai) {
                    if (tl != null && tl.getSoLuong() >= n)
                        tl.xuat();
                }
            }

        } while (chon != 0);
    }

    @Override
    public void nhapDanhSach() {
        themKPhanTuVaoDanhSach();
    }

    @Override
    public PhanTu timPhanTu() {
        String ma = "";
        System.out.print("Nhap ma the loai: ");
        try {
            // reuse existing scanner 'sc' to avoid creating an unclosed Scanner on System.in
            ma = sc.nextLine();
        } catch (Exception e) {
            // handle exception
        }
        return layPhanTuVoi(ma);
    }
}
