package di;

public class Guy {
    private Girlfriend girlfriend;

    public Guy(){}

    public Guy(Girlfriend girlfriend){
        this.girlfriend = girlfriend;
    }
    public void setGirlfriend(Girlfriend girlfriend) {
        this.girlfriend = girlfriend;
    }

    public void cook(){
        girlfriend.cook();
    }

    public void cook(String what) {}
}
