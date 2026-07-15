package di;

public class KetuGirlFriend implements Girlfriend{
    @Override
    public void cook() {
        System.out.println("Ketu Girl Friend Cook");
    }

    @Override
    public void cook(String what) {
        System.out.println("Ask Musa");
    }
}
