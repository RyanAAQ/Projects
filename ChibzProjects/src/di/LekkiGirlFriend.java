package di;

public class LekkiGirlFriend implements Girlfriend{
    @Override
    public void cook() {
        System.out.println("Lekki Girl Friend Cook");
    }

    @Override
    public void cook(String what) {
        System.out.println("Ask Adeola");
    }
}
