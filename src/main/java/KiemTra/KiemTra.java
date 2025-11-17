package KiemTra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KiemTra {

    // KIEM TRA MA
    public static boolean KiemTraMaNV(String ma) {
        if (ma == null || !ma.matches("^NV\\d{3,}$")) {
            System.out.println("Loi: Ma nhan vien phai co dang NV + it nhat 3 chu so. VD: NV001");
            return false;
        }
        return true;
    }

    public static boolean KiemTraMaKH(String ma) {
        if (ma == null || !ma.matches("^KH\\d{3,}$")) {
            System.out.println("Loi: Ma khach hang phai co dang KH + it nhat 3 chu so. VD: KH005");
            return false;
        }
        return true;
    }

    public static boolean KiemTraMaSachTN(String ma) {
        String regex = "^TN\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ma);
        return matcher.find();
    }
	public static boolean KiemTraMaSachVH(String ma) {
        String regex = "^VH\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ma);
        return matcher.find();
    }
	public static boolean KiemTraMaSachTL(String ma) {
        String regex = "^TL\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ma);
        return matcher.find();
    }

    public static boolean KiemTraMaPhieu(String ma) {
        if (ma == null || !ma.matches("^PM\\d{3,}$")) {
            System.out.println("Loi: Ma phieu muon phai co dang PM + it nhat 3 chu so. VD: PM001");
            return false;
        }
        return true;
    }

    //Kiem Tra cccd
    public static boolean KiemTraCCCD(String cccd)
	{
		String reg = "^\\d{12}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(cccd);
		if(!matcher.matches() || cccd == "")
		{
			System.out.print("So can cuoc cong dan khong dung!!! Moi nhap lai:");
			return false;
		}
		return true;
	}

    // KIEM TRA CHUOI
    public static boolean KiemTraHoTen(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            System.out.println("Loi: Ho ten khong duoc de trong!");
            return false;
        }
        if (!ten.matches("^[A-Za-z ]+$")) {
            System.out.println("Loi: Ho ten chi duoc chua chu cai va khoang trang!");
            return false;
        }
        return true;
    }

    public static boolean KiemTraGioiTinh(String gt) {
        if (gt == null || gt.trim().isEmpty()) {
            System.out.println("Loi: Gioi tinh khong duoc de trong!");
            return false;
        }
        if (!gt.matches("(?i)(nam|nu)")) {
            System.out.println("Loi: Gioi tinh chi duoc nhap 'Nam' hoac 'Nu'.");
            return false;
        }
        return true;
    }

    public static boolean KiemTraDiaChi(String dc) {
        if (dc == null || dc.trim().isEmpty()) {
            System.out.println("Loi: Dia chi khong duoc de trong!");
            return false;
        }
        if (dc.trim().length() < 5) {
            System.out.println("Loi: Dia chi qua ngan, phai tu 5 ky tu tro len!");
            return false;
        }
        return true;
    }

    // KIEM TRA LIEN LAC
    public static boolean KiemTraSDT(String sdt) {
        if (sdt == null || sdt.trim().isEmpty()) {
            System.out.println("Loi: So dien thoai khong duoc de trong!");
            return false;
        }
        if (!sdt.matches("^0\\d{9}$")) {
            System.out.println("Loi: So dien thoai phai co 10 so va bat dau bang 0!");
            return false;
        }
        return true;
    }

    public static boolean KiemTraEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Loi: Email khong duoc de trong!");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            System.out.println("Loi: Email khong hop le! VD: ten@gmail.com");
            return false;
        }
        return true;
    }

    // KIEM TRA NGAY
    public static boolean KiemTraNgay(String ngay) {
        if (ngay == null || ngay.trim().isEmpty()) {
            System.out.println("Loi: Ngay khong duoc de trong!");
            return false;
        }
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(ngay, fmt);
            LocalDate now = LocalDate.now();

            if (date.isAfter(now)) {
                System.out.println("Loi: Ngay khong duoc lon hon ngay hien tai!");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Loi: Ngay khong hop le! Dinh dang dung la dd/MM/yyyy.");
            return false;
        }
    }

    public static boolean KiemTraNgayMuonTra(String ngayMuon, String ngayTra) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate muon = LocalDate.parse(ngayMuon, fmt);
            LocalDate tra = LocalDate.parse(ngayTra, fmt);
            if (tra.isBefore(muon)) {
                System.out.println("Loi: Ngay tra khong duoc truoc ngay muon!");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Loi: Dinh dang ngay muon/tra sai! Phai la dd/MM/yyyy.");
            return false;
        }
    }

    // KIEM TRA SO
    public static boolean KiemTraSoLuong(int sl) {
        if (sl <= 0) {
            System.out.println("Loi: So luong phai lon hon 0!");
            return false;
        }
        return true;
    }

    public static boolean KiemTraGia(double gia) {
        if (gia <= 0) {
            System.out.println("Loi: Gia phai lon hon 0!");
            return false;
        }
        return true;
    }

    // KIEM TRA TAI KHOAN
    public static boolean KiemTraTenTK(String tenTK) {
        if (tenTK == null || tenTK.trim().isEmpty()) {
            System.out.println("Loi: Ten tai khoan khong duoc de trong!");
            return false;
        }
        if (!tenTK.matches("^[A-Za-z0-9_]{5,20}$")) {
            System.out.println("Loi: Ten tai khoan phai dai 5–20 ky tu va chi gom chu, so, dau gach duoi!");
            return false;
        }
        return true;
    }

    public static boolean KiemTraMatKhau(String mk) {
        if (mk == null || mk.trim().isEmpty()) {
            System.out.println("Loi: Mat khau khong duoc de trong!");
            return false;
        }
        if (!mk.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            System.out.println("Loi: Mat khau phai it nhat 6 ky tu va co ca chu va so!");
            return false;
        }
        return true;
    }

    // KIEM TRA TINH TRANG
    public static boolean KiemTraTinhTrang(String tt) {
        if (tt == null || tt.trim().isEmpty()) {
            System.out.println("Loi: Tinh trang khong duoc de trong!");
            return false;
        }
        if (!tt.matches("(?i)(dang muon|da tra)")) {
            System.out.println("Loi: Tinh trang chi duoc la 'Dang muon' hoac 'Da tra'.");
            return false;
        }
        return true;
    }


    // KIEM TRA THONG TIN SACH
    public static boolean KiemTraTenSach(String tenSach) {
    if (tenSach == null || tenSach.trim().isEmpty()) {
        System.out.println("Loi: Ten sach khong duoc de trong!");
        return false;
    }
    if (tenSach.trim().length() < 2) {
        System.out.println("Loi: Ten sach qua ngan!");
        return false;
    }
    return true;
}

    public static boolean KiemTraTacGia(String tacGia) {
    if (tacGia == null || tacGia.trim().isEmpty()) {
        System.out.println("Loi: Tac gia khong duoc de trong!");
        return false;
    }
    if (!tacGia.matches("^[A-Za-z ]+$")) {
        System.out.println("Loi: Ten tac gia chi duoc chua chu cai va khoang trang!");
        return false;
    }
    return true;
}

    public static boolean KiemTraTheLoai(String theLoai) {
    if (theLoai == null || theLoai.trim().isEmpty()) {
        System.out.println("Loi: The loai khong duoc de trong!");
        return false;
    }
    return true;
}
    public static boolean KiemTraNamXuatBan(int nam) {
    int namHienTai = LocalDate.now().getYear();
    if (nam < 1900 || nam > namHienTai) {
        System.out.println("Loi: Nam xuat ban khong hop le (1900 - " + namHienTai + ")!");
        return false;
    }
    return true;
}
 public static boolean KiemTraNhaXuatBan(String nxb) {
    if (nxb == null || nxb.trim().isEmpty()) {
        System.out.println("Loi: Nha xuat ban khong duoc de trong!");
        return false;
    }

    // Không cho phép ký tự đặc biệt
    if (!nxb.matches("[a-zA-Z0-9\\s\\p{L}]+")) {
        System.out.println("Loi: Ten nha xuat ban khong duoc chua ky tu dac biet!");
        return false;
    }

    // Kiểm tra độ dài hợp lý
    if (nxb.length() < 3 || nxb.length() > 50) {
        System.out.println("Loi: Ten nha xuat ban phai tu 3 - 50 ky tu!");
        return false;
    }

    return true;
}
  //KIEM TRA LUA CHON
    public static int CheckNumber() {
        boolean check = false;
		String str;
		do {
			str = sc.nextLine();
			check = true;
			if(!isNumber(str)) {
				check = false;
				System.out.print("Nhap sai so!!! Moi nhap lai: ");
			}
		} while (!check);
		return Integer.parseInt(str);
	}
	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
    //KIEM TRA CHUOI
    public static boolean isDecimal(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	//manh them
 public static String checkTheLoaiSach() {
    boolean check = false;
    int choose;
    System.out.println("==============================");
    System.out.println("1. Sach Trinh Tham");
    System.out.println("2. Sach Thieu Nhi");
    System.out.println("3. Sach Tam Ly");
    System.out.println("==============================");
    do {
        System.out.print("Moi chon: ");
        try {
            choose = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nhap sai! Vui long nhap so tu 1-3.");
            continue;
        }
        switch (choose) {
            case 1:
                return "Sach Trinh Tham";
            case 2:
                return "Sach Thieu Nhi";
            case 3:
                return "Sach Tam Ly";
            default:
                System.out.println("Hay chon so co trong menu!!!");
                break;
        }
    } while (true);
}

}

