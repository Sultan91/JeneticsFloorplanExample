import io.jenetics.Chromosome;
import io.jenetics.DoubleChromosome;
import io.jenetics.DoubleGene;
import io.jenetics.Genotype;
import io.jenetics.util.Factory;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    public static Factory<Genotype<DoubleGene>> of (int nRooms, double[] roomDims)
    {
        List<DoubleChromosome> arr = new ArrayList<DoubleChromosome>();
        int currRoomIdx = 0;
        double delta = 2;
        for(int i=0; i<nRooms*4; i+=4)
        {
            double side = roomDims[currRoomIdx];
            arr.add(DoubleChromosome.of(1, 50));
            arr.add(DoubleChromosome.of(1, 50));
            arr.add(DoubleChromosome.of(side - delta, side + delta));
            arr.add(DoubleChromosome.of(side - delta, side + delta));
            currRoomIdx ++;
        }
        return Genotype.of(arr);
    }

    public static ArrayList<Rectangle> convert2floorplan(Genotype<DoubleGene> solution)
    {
        ArrayList<Rectangle> rooms = new ArrayList<Rectangle>();
        for(int i=0; i < solution.length(); i+= 4)
        {
            rooms.add(new Rectangle(
                    (double) solution.get(i).gene().allele(), // X0
                    (double) solution.get(i+1).gene().allele(),// y0
                    (double) solution.get(i+2).gene().allele(),// width
                    (double) solution.get(i+3).gene().allele() //height
                    ));
        }
        return rooms;
    }

}
