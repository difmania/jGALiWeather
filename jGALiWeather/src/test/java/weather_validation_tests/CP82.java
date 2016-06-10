/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_validation_tests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import jgaliweather.PredictionSummarizer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Difma
 */
public class CP82 {
    
    public CP82() {
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

            String id = "27044";

            ArrayList<String> dates = new ArrayList();
            dates.add("2015-09-21");
            dates.add("2015-09-22");
            dates.add("2015-09-23");
            dates.add("2015-09-24");

            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdt.parse("2015-09-21");

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            
            PredictionSummarizer ps = new PredictionSummarizer("Configuration/configuration.xml", "");
            String salida = ps.generateTextualForecastsTest(id, dates, cal);

            /*
             *   Cielos parcialmente nubosos en general durante los próximos días, aunque ocasionalmente se encontrarán poco nubosos o despejados. Las temperaturas serán normales 
             *   para esta época del año, con mínimas en ascenso moderado y máximas sin cambios.
             */
            assertEquals(salida, "Partly cloudy skies in general for the next few days, although it will occasionally be clear. Temperature will be normal for this period of the year, with minimums in moderate increase and maximums without changes.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
