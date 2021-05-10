import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RevolutionSoupTest {
    /**
     * Calculate the volume of the region bounded by x^3 and x^2 that is rotated about the line y=3.
     */
    @Test
    void xCubedXSquaredAboutYEquals3Rotation() {
        RevolutionSoup rs = new RevolutionSoup(new XCubed(), new XSquared(), 10000000);
        assertEquals(rs.revolution(0, 1, 3), 1.3912767465945097);
    }
}