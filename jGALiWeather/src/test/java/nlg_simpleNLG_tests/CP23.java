package nlg_simpleNLG_tests;

import java.util.ArrayList;
import java.util.HashMap;
import jgaliweather.algorithm.ICA_operators.ICAConditionsOperator;
import jgaliweather.algorithm.ICA_operators.ICAOperator;
import jgaliweather.algorithm.ICA_operators.ICARainOperator;
import jgaliweather.algorithm.ICA_operators.ICASkyStateOperator;
import jgaliweather.algorithm.ICA_operators.ICAWindOperator;
import jgaliweather.configuration.partition_reader.Partition;
import jgaliweather.configuration.partition_reader.PartitionReader;
import jgaliweather.configuration.template_reader.TemplateReader;
import jgaliweather.configuration.variable_reader.VariableReader;
import jgaliweather.data.data_structures.Value;
import jgaliweather.data.data_structures.Variable;
import jgaliweather.nlg_simpleNLG.nlg_generators.ICAGenerator;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/* Tests method that generates sentences describing air condition*/
public class CP23 {

    public CP23() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {

        TemplateReader tr = new TemplateReader();
        tr.parseFile("Configuration/Languages/english.xml");

        VariableReader vr = new VariableReader();
        vr.parseFile("Configuration/variables.xml");

        PartitionReader pr = new PartitionReader();
        pr.parseFile("Configuration/partitions.xml");
        HashMap<String, Partition> partitions = pr.getPartitions();

        Variable wind_var = new Variable("Viento");

        wind_var.getValues().add(new Value(317, 0));
        wind_var.getValues().add(new Value(320, 1));
        wind_var.getValues().add(new Value(320, 2));
        wind_var.getValues().add(new Value(317, 3));
        wind_var.getValues().add(new Value(320, 4));
        wind_var.getValues().add(new Value(320, 5));
        wind_var.getValues().add(new Value(332, 6));
        wind_var.getValues().add(new Value(317, 7));
        wind_var.getValues().add(new Value(332, 8));

        Pair<Integer, Integer> WIND_INTERVAL = new Pair(309, 332);

        ICAWindOperator w_op = new ICAWindOperator(WIND_INTERVAL, wind_var, 9);

        ArrayList<Double> wind_salida = w_op.applyOperator();

        Variable sky_var = new Variable("Meteoro");

        sky_var.getValues().add(new Value(110, 0));
        sky_var.getValues().add(new Value(103, 1));
        sky_var.getValues().add(new Value(111, 2));
        sky_var.getValues().add(new Value(107, 3));
        sky_var.getValues().add(new Value(118, 4));
        sky_var.getValues().add(new Value(101, 5));
        sky_var.getValues().add(new Value(109, 6));
        sky_var.getValues().add(new Value(102, 7));
        sky_var.getValues().add(new Value(113, 8));

        ICASkyStateOperator ss_op = new ICASkyStateOperator(partitions.get("C"), sky_var, 9);

        ArrayList<HashMap<String, Double>> sky_salida = ss_op.applyOperator();

        Variable rain_var = new Variable("Meteoro");

        rain_var.getValues().add(new Value(110, 0));
        rain_var.getValues().add(new Value(103, 1));
        rain_var.getValues().add(new Value(111, 2));
        rain_var.getValues().add(new Value(107, 3));
        rain_var.getValues().add(new Value(118, 4));
        rain_var.getValues().add(new Value(101, 5));
        rain_var.getValues().add(new Value(109, 6));
        rain_var.getValues().add(new Value(102, 7));
        rain_var.getValues().add(new Value(113, 8));

        ICARainOperator r_op = new ICARainOperator(partitions.get("R"), rain_var, 9);

        ArrayList<Double> rain_salida = r_op.applyOperator();

        ICAConditionsOperator icac_op = new ICAConditionsOperator(wind_salida, rain_salida, sky_salida, 9);

        ArrayList<String> ica_cond_output = icac_op.defineConditions();

        Variable curr_var = new Variable("Meteoro");

        curr_var.getValues().add(new Value(5, 0));
        curr_var.getValues().add(new Value(1, 1));
        curr_var.getValues().add(new Value(3, 2));

        ICAOperator ica_op = new ICAOperator(partitions.get("ICA"), curr_var);

        String ica_output = ica_op.applyOperator();

        ICAGenerator nssg = new ICAGenerator(tr.getLabelsets().get("ICA"), ica_output, ica_cond_output);

        String salida = nssg.generate();

        System.out.println(salida);
        Assert.assertEquals("With respect to air quality state, it will be changeable although it is expected to change to admissible.", salida);
    }
}