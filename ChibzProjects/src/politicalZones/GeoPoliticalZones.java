package politicalZones;

public enum GeoPoliticalZones {
    NORTH_CENTRAL("Benue", "FCT", "Kogi", "Kwara", "Nasarawa", "Niger", "Plateu"),
    NORTH_EAST("Adamawa", "Bauchi", "Borno", "Gambia", "Taraba", "Yobe"),
    NORTH_WEST("Kaduna", "Katsina", "Kano", "Kebbi", "Sokoto", "Jigawa", "Zamfara"),
    SOUTH_EAST("Abia", "Anambra", "Ebonyi", "Enugu", "Imo"),
    SOUTH_SOUTH("Akwa-ibom", "Bayelsa", "Cross-river", "Delta", "Edo", "Rivers"),
    SOUTH_WEST("Ekiti", "Lagos", "Osun", "Ondo", "Ogun", "Oyo");

    private final String [] states;

    GeoPoliticalZones(String... states){
        this. states = states;
    }

    public boolean containsState(String state){
        for(String s : states){
            if(s.equalsIgnoreCase(state)){
                return true;
            }
        }
        return false;
    }

    public static GeoPoliticalZones findZone(String state){
        for(var zone : GeoPoliticalZones.values()){
            if(zone.containsState(state)){
                return zone;
            }
        }
        return null;
    }
}
