import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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

    // TODO: additional entities added below
    private static final List<Country> countries;
    private static final List<Corporation> corporations;
    private static final Rectangle sandbox;
    private static List<Order> orders;
    private static final int countryTop;
    private static final List<String> states;
    private static final List<String> orderTypes;
    static {

        // init sandbox
        // Rectangle shows the area that corporations are not allowed to leave
        sandbox = new Rectangle(0, getHorizontalLineY(), windowWidth,
                600 - getHorizontalLineY());

        // lower y coordinate of rectangle
        countryTop = 600;

        // init countries with names and insert them to the proper places
        countries = Arrays.asList(
                new Country((int) (sandbox.width / 6) - 75, countryTop, "poland"),
                new Country((int) (sandbox.width / 6) * 2 - 75, countryTop, "chile"),
                new Country((int) (sandbox.width / 6) * 3 - 75, countryTop, "malaysia"),
                new Country((int) (sandbox.width / 6) * 4 - 75, countryTop, "mexico"),
                new Country((int) (sandbox.width / 6) * 5 - 75, countryTop, "nigeria"));

        // init corporations with names and insert them to the random places within the
        // sandbox
        Random rand = new Random();
        corporations = Arrays.asList(
                new Corporation(rand.nextInt(windowWidth),
                        rand.nextInt(countryTop) + getHorizontalLineY(),
                        "boeing", "Shake"),
                new Corporation(rand.nextInt(windowWidth),
                        rand.nextInt(countryTop) + getHorizontalLineY(),
                        "general_dynamics", "Rest"),
                new Corporation(rand.nextInt(windowWidth),
                        rand.nextInt(countryTop) + getHorizontalLineY(),
                        "lockheed_martin", "ChaseClosest"),
                new Corporation(rand.nextInt(windowWidth),
                        rand.nextInt(countryTop) + getHorizontalLineY(),
                        "northrop_grumman", "GotoXY"),
                new Corporation(rand.nextInt(windowWidth),
                        rand.nextInt(countryTop) + getHorizontalLineY(),
                        "raytheon", "GotoXY"));

        // orders will added to here
        orders = new ArrayList<>();
        orderTypes = Arrays.asList("FoodOrder", "ElectronicsOrder", "BuyGoldOrder", "SellGoldOrder");
        // possible states array
        states = Arrays.asList("Shake", "Rest", "ChaseClosest", "GotoXY");
    }

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
     * that covers the horizontal top line and
     * the top of the countries returns the sandbox rectangle
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
    public static boolean isInSandbox(Position p) {
        return sandbox.contains(new Point((int) p.getX(), (int) p.getY()));
    }

    /**
     * returns the list of countries
     * 
     * @see Common#countries
     * @return List<Country>
     */
    public static List<Country> getCountries() {
        return countries;
    }

    /**
     * returns the list of corporations
     * 
     * @see Common#corporations
     * @return List<Corporation>
     */
    public static List<Corporation> getCorporations() {
        return corporations;
    }

    /**
     * returns the list of orders
     * 
     * @see Common#orders
     * @return List<Order>
     */
    public static List<Order> getOrders() {
        return orders;
    }

    /**
     * returns a random state name from the states list
     * 
     * @see Common#states
     * @return String
     */
    public static String getStateClassName() {
        return states.get(randomGenerator.nextInt(states.size()));
    }

    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0)
            foodPrice.step();
        if (randomGenerator.nextInt(300) == 0)
            electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0)
            goldPrice.step();
        updateCorps(corporations);
        updateCountries(countries);
        updateOrders();
    }

    private static void updateOrders() {
        orders = orders.stream().filter(order -> {
            order.step();
            // if order is on the horizontal line
            if (order.getPosition().getY() < getHorizontalLineY()) {
                order.out();
                return false;
            }
            return true;

        }).collect(Collectors.toList());
    }

    private static void updateCountries(List<Country> countries) {
        // for each country
        countries.forEach(country -> {
            // create orders
            if (randomGenerator.nextInt(200) == 0) {

                // create a random order class name from the list orderTypes
                String className = orderTypes.get(randomGenerator.nextInt(orderTypes.size()));

                // create order with using the string class name
                // luckily, the country knows nothing about what type of order it creates
                Order order = country.createOrder(className);

                // if it is an import to raise the happiness happiness should
                // be less than 50 percent
                if (className.equals("FoodOrder") && className.equals("ElectronicsOrder")) {
                    if (country.happiness < 50) {
                        orders.add(order);
                    }
                } else {
                    // gold order
                    orders.add(order);
                }

            }
            country.step();
        });
    }

    private static void updateCorps(List<Corporation> corps) {
        // update each corporation based on the current and some random values
        corps.forEach(corp -> {
            // randomly change their states
            if (randomGenerator.nextInt(100) == 0)
                corp.setState(corp.createState(getStateClassName()));

            // if corporation is outside of the sandbox then
            // move it back to the sandbox and
            // its state is not GotoXY go to the x y which are inside sandbox
            if (!isInSandbox(corp.getPosition()) && corp.getState().getName() != "GotoXY")
                corp.setState(corp.createState("GotoXY"));

            // update the corporation entity
            corp.step();

        });
    }
}