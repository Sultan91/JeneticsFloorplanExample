public class Rectangle {
    private final double x0;
    private final double y0;
    private final double w;
    private final double h;

    public Rectangle(double x0, double y0, double w, double h)
    {
        this.x0 = x0;
        this.y0 = y0;
        this.w = w;
        this.h = h;
    }
    public double getArea(){return this.w * this.h;}
    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public double getXMax(){return this.x0+this.w;}

    public double getYMax(){return this.y0+this.h;}

    public double getXMin(){return this.x0;}

    public double getYMin(){return this.y0;}

    public double getYCenter(){return this.y0 + this.h/2;}

    public double getXCenter(){return this.x0 + this.w/2;}
}
