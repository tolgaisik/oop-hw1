import java.util.Random;

public abstract class Order extends Entity {
    int amount;
    double speed;
    Position path;
    Country country;

    public Order(double x, double y, Country country) {
        super(x, y);
        this.country = country;
        init();
    }

    private void init() {
        Random rand = new Random();
        amount = rand.nextInt(5) + 1; // [1,2,3,4,5]
        speed = rand.nextDouble(3) + 3; // [3,4,5]
        // actually, it is a random point from the sandbox
        // in order to cover various angles in the sandbox
        // between atan(10/27) and atan(19/8) angles
        // we adjust the values so that orders go to
        // opposite(verticalHorizonLine) wall more often
        int x = rand.nextInt(10) + 10; // xcoord
        int y = rand.nextInt(20) + 8; // ycoord
        double length = Math.sqrt(x * x + y * y);
        // randomly directed to left or right
        path = new Position(rand.nextBoolean() ? x / length : -(x / length), -(y / length));
    }

    /**
     * called when order goes out of the horizon
     * 
     * @return none
     */
    abstract void out();
    // TODO
    // Order is 24 x 24
}