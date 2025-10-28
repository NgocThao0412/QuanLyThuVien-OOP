public class PhieuMuon {
    private String maphieumuon;
    private String masach;
    private String maKH;

    // --- Constructor ---
    public PhieuMuon(String maphieumuon, String masach, String maKH) {
        this.maphieumuon = maphieumuon;
        this.masach = masach;
        this.maKH = maKH;
    }

    // --- Getter & Setter ---
    public String getMaphieumuon() {
        return maphieumuon;
    }

    public void setMaphieumuon(String maphieumuon) {
        this.maphieumuon = maphieumuon;
    }
    
    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }


    @Override
    public String toString() {
        return "PhieuMuon{" +
                "maphieumuon:'" + maphieumuon + '\'' +
                ", masach:'" + masach + '\'' +
                ", maKH:'" + maKH + '\'' +
                '}';
    }
}
