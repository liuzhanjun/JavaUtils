package beans;

import com.yun.hai.In.ComeRequestIn;

public class Address implements ComeRequestIn.RequestModel {


    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
