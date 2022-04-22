import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Common {
    private static final String title = "Arms Race";
    private static final int windowWidth = 1650;
    private static final int windowHeight = 1000;

    private static final int firstVerticalLineX = 500;
    private static final int secondVerticalLineX = 1250;
    private static final int horizontalLineY = 100;

    private static final Random randomGenerator = new Random(1234);

    private static final LivePrice foodPrice = new LivePrice(30, 65, "Food Products", 5, 1, 1, 10);
    private static final LivePrice electronicsPrice = new LivePrice(580, 65, "Consumer Electronics", 30, 2, 10, 50);
    private static final LivePrice goldPrice = new LivePrice(1300, 65, "Gold", 75, 3, 50, 100);
    private static final List<Country> countries = Arrays.asList(
            new Country((int) (windowWidth / 6) - 75, 600, "poland"),
            new Country((int) (windowWidth / 6) * 2 - 75, 600, "chile"),
            new Country((int) (windowWidth / 6) * 3 - 75, 600, "malaysia"),
            new Country((int) (windowWidth / 6) * 4 - 75, 600, "mexico"),
            new Country((int) (windowWidth / 6) * 5 - 75, 600, "nigeria"));
    private static final List<Corporation> corporations = Arrays.asList(
            new Corporation(randomGenerator.nextInt(windowWidth), randomGenerator.nextInt(getHorizontalLineY(), 600),
                    "boeing"),
            new Corporation(randomGenerator.nextInt(windowWidth), randomGenerator.nextInt(getHorizontalLineY(), 600),
                    "general_dynamics"),
            new Corporation(randomGenerator.nextInt(windowWidth), randomGenerator.nextInt(getHorizontalLineY(), 600),
                    "lockheed_martin"),
            new Corporation(randomGenerator.nextInt(windowWidth), randomGenerator.nextInt(getHorizontalLineY(), 600),
                    "northrop_grumman"),
            new Corporation(randomGenerator.nextInt(windowWidth), randomGenerator.nextInt(getHorizontalLineY(), 600),
                    "raytheon"));

    // getters
    public static String getTitle() {
        return title;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getFirstVerticalLineX() {
        return firstVerticalLineX;
    }

    public static int getSecondVerticalLineX() {
        return secondVerticalLineX;
    }

    public static int getHorizontalLineY() {
        return horizontalLineY;
    }

    public static Random getRandomGenerator() {
        return randomGenerator;
    }

    public static LivePrice getFoodPrice() {
        return foodPrice;
    }

    public static LivePrice getElectronicsPrice() {
        return electronicsPrice;
    }

    public static LivePrice getGoldPrice() {
        return goldPrice;
    }

    public static Country getCountry(String name) {
        return countries.stream().filter(country -> country.getName().equals(name)).findFirst().get();
    }

    public static List<Country> getCountries() {
        return countries;
    }

    public static List<Corporation> getCorporations() {
        return corporations;
    }

    public static State generateRandomState() {
        int type = randomGenerator.nextInt(4);
        switch (type) {
            case 0:
                return new Shake();
            case 1:
                return new GotoXY();
            case 2:
                return new Rest();
            default:
                return new ChaseClosest();
        }
    }

    static {

    }

    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0)
            foodPrice.step();
        if (randomGenerator.nextInt(300) == 0)
            electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0)
            goldPrice.step();
        corporations.forEach(Corporation::step);
        if (randomGenerator.nextInt(50) == 0)
            corporations.forEach(Corporation::update);
        // TODO: call other entities' step()
    }
}