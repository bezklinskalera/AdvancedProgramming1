import data.Data;
import data.DataException;

import java.util.Arrays;

public class MainData2 {
    public static void main(String[] args) {

        if(args.length<3){
            throw new DataException("Error, there are not enough values");
        }

        try{
            double min = Double.parseDouble(args[0]);
            double max = Double.parseDouble(args[1]);

            String[] values = Arrays.copyOfRange(args, 2, args.length);
            Data data = new Data(values, min, max);

            System.out.println(data);

            data.setRange("0;4");
            System.out.println(data);

            data.setRange("15 25");
            System.out.println(data);

        }catch (NumberFormatException e) {
            System.out.println("Error, when converting a value to a real number (" + e.getMessage() + ")");
        } catch (DataException e) {
            System.out.println(e.getMessage());
        }


    }
}
