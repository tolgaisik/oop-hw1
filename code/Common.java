import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.awt.Point;
import java.awt.Rectangle;

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

    static {
        // init sandbox
        sandbox = new Rectangle(0, getHorizontalLineY(), windowWidth,
                500 - getHorizontalLineY());

        // init countries with names and insert them to the proper places
        countries = Arrays.asList(
                new Country((int) (windowWidth / 6) - 75, 600, "poland"),
                new Country((int) (windowWidth / 6) * 2 - 75, 600, "chile"),
                new Country((int) (windowWidth / 6) * 3 - 75, 600, "malaysia"),
                new Country((int) (windowWidth / 6) * 4 - 75, 600, "mexico"),
                new Country((int) (windowWidth / 6) * 5 - 75, 600, "nigeria"));

        // init corporations with names and insert them to the random places within the
        // sandbox
        corporations = Arrays.asList(
                new Corporation(randomGenerator.nextInt(windowWidth),
                        randomGenerator.nextInt(getHorizontalLineY(), 600),
                        "boeing", "Shake"),
                new Corporation(randomGenerator.nextInt(windowWidth),
                        randomGenerator.nextInt(getHorizontalLineY(), 600),
                        "general_dynamics", "Rest"),
                new Corporation(randomGenerator.nextInt(windowWidth),
                        randomGenerator.nextInt(getHorizontalLineY(), 600),
                        "lockheed_martin", "ChaseClosest"),
                new Corporation(randomGenerator.nextInt(windowWidth),
                        randomGenerator.nextInt(getHorizontalLineY(), 600),
                        "northrop_grumman", "GotoXY"),
                new Corporation(randomGenerator.nextInt(windowWidth),
                        randomGenerator.nextInt(getHorizontalLineY(), 600),
                        "raytheon", "GotoXY"));

        // orders will added to here
        orders = new ArrayList<>();
    }

    // additional entities added below
    private static final List<Country> countries;
    private static final List<Corporation> corporations;
    private static final Rectangle sandbox;
    private static final List<Order> orders;

    // additional methods added below

    /**
     * 
     * @param name
     * @return Country
     */
    public static Country getCountry(String name) {
        return countries.stream().filter(country -> country.getName().equals(name)).findFirst().get();
    }

    /**
     * sandbox is a rectangle object
     * that covers the horizontal top line the top of the countries
     * returns the sandbox rectangle
     * 
     * @return sandbox rectangle
     */
    public static Rectangle getSandBox() {
        return sandbox;
    }

    /**
     * checks whether entity object is in the sandbox rectangle
     * 
     * @see Common#getSandBox()
     * @param entity
     * @return boolean
     */
    public static boolean isInSandbox(Entity entity) {
        Position position = entity.getPosition();
        return sandbox.contains(new Point((int) position.getX(), (int) position.getY()));
    }

    public static List<Country> getCountries() {
        return countries;
    }

    public static List<Corporation> getCorporations() {
        return corporations;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0)
            foodPrice.step();
        if (randomGenerator.nextInt(300) == 0)
            electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0)
            goldPrice.step();
        // for each corporation
        corporations.forEach(Corporation::step);
        // for each country
    }
}