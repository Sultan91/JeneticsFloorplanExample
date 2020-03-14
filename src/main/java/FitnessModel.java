import io.jenetics.Genotype;

import java.util.ArrayList;
import java.util.function.Function;

public class FitnessModel {
    public final int[][] adjMatrix;

    public FitnessModel(int[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
    }
    // Estimation of overlapping rectangles' areas
    private static double overlap(Rectangle r1, Rectangle r2)
    {
        double dx = Math.min(r1.getXMax(), r2.getXMax()) - Math.max(r1.getXMin(), r2.getXMin());
        double dy = Math.min(r1.getYMax(), r2.getYMax()) - Math.max(r1.getYMin(), r2.getYMin());
        if ((dx>=0) &&  (dy>=0)) {
            return dx * dy;
        }
        return 0;// Two rectangles don't overlap
    }

    /**
     * Return orthogonal distance
     * @param r1
     * @param r2
     * @return
     */
    private static double distance(Rectangle r1, Rectangle r2)
    {
        return Math.max(Math.abs(r1.getX0() - r2.getX0())-(r1.getW() - r2.getW())/2,
                Math.abs(r1.getY0() - r2.getY0())- (r1.getH() - r2.getH())/2);
    }
    // There are goes our general fitness function
    public double scalarFitness(final Genotype model)
    {
        ArrayList<Rectangle> rooms = ModelFactory.convert2floorplan(model);
        double penalty = 0;
        for (int i=0; i< rooms.size(); i++){
            for (int j=0; j< rooms.size(); j++){
                if(i!=j)
                    penalty += overlap(rooms.get(i), rooms.get(j));
                if(this.adjMatrix[i][j] == 1)
                    penalty += distance(rooms.get(i), rooms.get(j));
            }
        }
        return penalty;
    }

    public Function<Genotype, Double> getFitness()
    {
        return this::scalarFitness;
    }
}
