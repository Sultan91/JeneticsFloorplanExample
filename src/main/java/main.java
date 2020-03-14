import io.jenetics.engine.*;
import io.jenetics.util.Factory;
import javafx.util.Pair;
import io.jenetics.*;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.util.ArrayList;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import static io.jenetics.engine.Limits.bySteadyFitness;

public class main {
    public static void main(final String[] args)
    {
        int nRooms = 6;
        String[] roomNames = {"Entry", "Bath", "Kitchen", "LVRoom", "Bed 1", "Bed 2"};
        int[][] adjMatrix = {
                            {0, 1, 1, 1, 0, 0},
                            {1, 0, 1, 1, 0, 0},
                            {1, 1, 0, 1, 0, 0},
                            {1, 0, 1, 0, 1, 1},
                            {0, 0, 0, 1, 0, 1},
                            {0, 0, 0, 1, 1, 0}
        };
        double[] roomDimensions = {4, 5, 7, 8, 6, 6};
        FitnessModel fm = new FitnessModel(adjMatrix);
        Factory<Genotype<DoubleGene>> model = ModelFactory.of(nRooms, roomDimensions);
        // Creation of our genetic evolution engine
        Engine<DoubleGene, Double> engine = Engine.builder(fm.getFitness(), model)
                .populationSize(10000)
                .optimize(Optimize.MINIMUM)
                .alterers(
                        new Mutator<>(.05),
                        new MeanAlterer<>(.5)
                )
                .build();
        // Gather some stats about evolution
        final EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();
        final Phenotype<DoubleGene, Double> best = engine.stream()
                .limit(bySteadyFitness(40))
                .peek(statistics)
                .collect(toBestPhenotype());
        // converting from gene domain to rectangles
        ArrayList<Rectangle> rooms = ModelFactory.convert2floorplan(best.getGenotype());
        System.out.println(statistics);
        System.out.println(best);
        // Visualization
        JFrame f = new JFrame("Floor plan");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new FPViewer(rooms, roomNames));
        f.setSize(600,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
