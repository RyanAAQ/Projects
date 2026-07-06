package foodSchedule;

public enum FoodSchedule {
    MONDAY("Rice", "Soup"),
    TUESDAY("Bread", "Stew");

    private final String morningFood;
    private final String eveningFood;

    FoodSchedule(String morningFood, String eveningFood) {
        this.morningFood = morningFood;
        this.eveningFood = eveningFood;
    }

    public String getMorningFood() { return morningFood; }

    public String getEveningFood() { return eveningFood; }
}
