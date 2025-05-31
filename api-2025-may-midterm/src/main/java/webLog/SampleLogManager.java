package webLog;

import java.io.FileNotFoundException;
import java.util.Random;

public class SampleLogManager extends LogManager {

    @Override
    public int readLodFile(String fileName) throws FileNotFoundException {
        Random random = new Random(0);
        int allEntries = super.readLodFile(fileName);
        int partList = list.size() / 3;

        for (int i = 0; i < partList; i++) {
            int index = random.nextInt(list.size());
            list.remove(index);
        }

        return list.size();
    }


}
