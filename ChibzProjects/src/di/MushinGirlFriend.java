package di;

public class MushinGirlFriend implements Girlfriend{
    @Override
    public void cook() {
        System.out.println("Mushin Girl Friend Cook");
    }

    @Override
    public void cook(String what) {
        System.out.println("Mushin Girl Friend Cook" +  what);
    }
}
