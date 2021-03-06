package algorithm_tests.weather_operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jgaliweather.algorithm.weather_operators.RainOperator;
import jgaliweather.configuration.partition_reader.Partition;
import jgaliweather.configuration.partition_reader.PartitionReader;
import jgaliweather.data.data_structures.Value;
import jgaliweather.data.data_structures.Variable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/* Tests Rain operators */
public class CP09 {

    public CP09() {
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

        try {
            PartitionReader pr = new PartitionReader();
            pr.parseFile("Configuration/partitions.xml");
            HashMap<String, Partition> partitions = pr.getPartitions();
            
            Variable curr_var = new Variable("Meteoro");
            
            curr_var.getValues().add(new Value(110, 0));
            curr_var.getValues().add(new Value(117, 1));
            curr_var.getValues().add(new Value(111, 2));
            curr_var.getValues().add(new Value(107, 3));
            curr_var.getValues().add(new Value(118, 4));
            curr_var.getValues().add(new Value(103, 5));
            curr_var.getValues().add(new Value(109, 6));
            curr_var.getValues().add(new Value(104, 7));
            curr_var.getValues().add(new Value(113, 8));
            curr_var.getValues().add(new Value(119, 9));
            curr_var.getValues().add(new Value(105, 10));
            curr_var.getValues().add(new Value(108, 11));
            
            RainOperator r_op = new RainOperator(partitions.get("R"), curr_var);
            
            ArrayList<String> salida_esperada = new ArrayList();
            salida_esperada.add("0-4 I I P P I");
            salida_esperada.add("6 SN");
            salida_esperada.add("8-9 ST ST");
            salida_esperada.add("11 P");
            
            ArrayList<String> salida = r_op.applyOperator();
            
            for (int i = 0; i < salida.size(); i++) {
                Assert.assertEquals(salida.get(i), salida_esperada.get(i));
            }
        } catch (Exception ex) {
            Logger.getLogger(CP09.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
