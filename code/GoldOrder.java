public abstract class GoldOrder extends Order {

    public GoldOrder(double x, double y, Country country) {
        super(x, y, country);
    }

    /**
     * this function is called if order is inside of a corporation object
     * 
     * @param corp which the order inside
     */
    public abstract void inside(Corporation corp);
}