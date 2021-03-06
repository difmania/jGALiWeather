package jgaliweather.configuration.variable_reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/* Defines an input variable, loaded from a
    XML variable configuration file.
 */
public class XMLVariable {

    private String name;
    private int start;
    private int end;
    private ArrayList<Integer> valid_lengths;
    private int actual_data_length;

    /*  Initializes a new XMLVariable object

        :param name: Name of the variable
        :param start: Initial numeric code which defines valid values for the variable
        :param end: Ending numeric code which defines valid values for the variable
        :param valid_lengths: List with valid data lengths for the variable

        :return: A new XMLVariable object
     */
    public XMLVariable(String name, int start, int end, String valid_lengths) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.valid_lengths = new ArrayList();

        StringTokenizer st = new StringTokenizer(valid_lengths);
        while (st.hasMoreTokens()) {
            this.valid_lengths.add(Integer.parseInt(st.nextToken()));
        }

        this.actual_data_length = Collections.max(this.valid_lengths);
    }

    public ArrayList<Integer> getValid_lengths() {
        return valid_lengths;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setValid_lengths(ArrayList<Integer> valid_lengths) {
        this.valid_lengths = valid_lengths;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setActual_data_length(int actual_data_length) {
        this.actual_data_length = actual_data_length;
    }

    public int getActual_data_length() {
        return actual_data_length;
    }

    @Override
    public String toString() {
        return "Variable " + name + ": " + start + " to " + end;
    }

}
