public class PhieuNhap {
    private String maNCC;

    // --- Constructor ---
    public PhieuNhap(String maNCC) {
        this.maNCC = maNCC;
    }

    // --- Getter & Setter ---
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

   
    @Override
    public String toString() {
        return "PhieuNhap{" +
                "maNCC:'" + maNCC + '\'' +
                '}';
    }
}
