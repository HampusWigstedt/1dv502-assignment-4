package assignment4;
import java.util.Random;

public class Member {
    private String name;
    private String email;
    private int memberID;
    

    public Member(String name, String email) {
        this.setName(name);
        this.setEmail(email);
        this.setMemberID();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getMemberID() {
        return memberID;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setMemberID() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int memberID = random.nextInt((max - min) + 1) + min;
        this.memberID = memberID;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", memberID=" + memberID +
                '}';
    }
}
