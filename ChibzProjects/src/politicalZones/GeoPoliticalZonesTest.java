package politicalZones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoPoliticalZonesTest {


    @Test
    void testNorthWest() {
        GeoPoliticalZones zone = GeoPoliticalZones.NORTH_WEST;
        assertTrue(zone.containsState("kaduna"));
    }

    @Test
    void testNorthEast(){
        GeoPoliticalZones zone = GeoPoliticalZones.NORTH_EAST;
        assertTrue(zone.containsState("adamawa"));
    }
}