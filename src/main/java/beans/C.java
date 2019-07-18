package beans;

public class C {
    private String tip;
    private Integer size;

    public C(String tip, Integer size) {
        this.tip = tip;
        this.size = size;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
