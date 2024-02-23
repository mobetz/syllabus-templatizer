import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Controller {

    @FXML
    LineChart<Number, Number> chart;
    @FXML
    NumberAxis x_axis;
    @FXML
    NumberAxis y_axis;

    @FXML
    public void initialize() {


        /*
        A Chart in JavaFX is a graph that has one or more "series" of data. For a line chart,
        each series is one line. We can get the list of all the lines that currently exist in the
        chart with getData():
         */
        ObservableList<XYChart.Series<Number, Number>> data_lines = chart.getData();
        x_axis.setLabel("Date of Sample");
        y_axis.setLabel("Amount Collected");


        /*
        If I wanted to add a new line to the chart, I can create a new Series object, and
        add it to this observable list (remember, since it's observable, the chart will update
        automatically!):
         */
        XYChart.Series<Number, Number> first_line = new XYChart.Series();
        first_line.setName("Quadratic Growth Example");
        data_lines.add(0, first_line);

        /*
        This "series"/line we created also has a getData() method. However, on a Series, each
        element of data is one single datapoint, represented with a Data object:
         */

        ObservableList<XYChart.Data<Number, Number>> first_line_datapoints = first_line.getData();

        for ( int i=1; i<10; i++ ) {
            /*
            I can create data point objects and add them to my line as expected:
             */
            first_line_datapoints.add(new XYChart.Data<>(i, Math.pow(2, i)));
        }

        /*
        JavaFX charts only like working with String "Category" axes or numeric "Number" axes.
        But what if we have something like a Date?

        We need to figure out how to convert dates to and from numbers. We would load our Data points
        with a numeric representation of our dates (like maybe a number of days since the start),
        and then we would give the x_axis a "tick label formatter" to tell it how to turn those numbers
        into the representation we want to see.

        TickFormatters are a subclass of StringConverter. We could write a new StringConverter
        subclass in a separate file, or create an ad-hoc subclass here with {curly braces} (the way
        we did during the events lecture).

        StringFormatter just needs a toString(), and a fromString() (and in fact, we only *really*
        care about the toString unless our code is going to be turning the visual chart data back into
        dates).

        For us, we take that date number, and turn it into a date string, and then return that in toString():
         */

        x_axis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number day_as_a_number) {
                LocalDate today = LocalDate.now();
                return today.plusDays(day_as_a_number.longValue()).toString();
            }

            @Override
            public Number fromString(String string) {
                return DAYS.between(LocalDate.parse(string), LocalDate.now());
            }
        });


        /*
        Now when we display this chart, we will see dates along the bottom/X axis!
         */
    }
}
