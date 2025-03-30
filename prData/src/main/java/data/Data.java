package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Data {

    private List<Double> data;
    private List<String> errors;

    private double min;

    private double max;

    public Data(String[] values, double min, double max) {
        this.data = new ArrayList<>();
        this.errors = new ArrayList<>();
        this.min = min;
        this.max = max;

        for (String s : values){
            try {
                data.add(Double.parseDouble(s));
            }catch (NumberFormatException e){
                errors.add(s);
            }
        }
    }

    public double calcAverage (){

        if (data == null || data.size() == 0) {
            throw new DataException("There is no data in the given range");
        }

        int count = 0;
        double sum = 0;

        for (double num : data){
            if (num >= min && num <= max) {
                sum += num;
                count++;
            }
        }
        if (count == 0){
            throw new  DataException("There is no data in the given range");
        }
        double resultAvg = sum/count;

      return resultAvg;
    }

    public double calcStandardDeviation(){

        double resultAvg = calcAverage();
        double resultSquare = 0;
        int count = 0;
        double sum = 0;

        for (double num : data){
            if (num >= min && num <= max) {
                double result = num - resultAvg;
                resultSquare = Math.pow(result,2);
                sum += resultSquare;
                count++;
            }
        }
        double resultDev=Math.sqrt(sum/count);

        return resultDev;
    }

    public void setRange(String newRange){

        if( newRange == null || !newRange.contains(";")){
            throw new DataException("Data error setting range");
        }
        String[] values = newRange.split(";");

        if (values.length != 2) {
            throw new DataException("Data error setting range");
        }

        try {
            min = Double.parseDouble(values[0].trim());
            max = Double.parseDouble(values[1].trim());
        } catch (NumberFormatException e) {
            throw new DataException("Data error setting range");
        }

    }

    public List<Double> getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        String avgStr = "ERROR";
        String stdDevStr = "ERROR";

        try {
            double resultAvg = calcAverage();
            double resultDev = calcStandardDeviation();
            avgStr = String.format(Locale.US, "%.1f", resultAvg);
            stdDevStr = String.format(Locale.US, "%f", resultDev);
        } catch (DataException e) {
        }

        return String.format(Locale.US,
                "Min: %.1f, Max: %.1f, %s, %s, Average: %s, StandardDeviation: %s",
                min, max, data.toString(), errors.toString(), avgStr, stdDevStr);
    }
}
