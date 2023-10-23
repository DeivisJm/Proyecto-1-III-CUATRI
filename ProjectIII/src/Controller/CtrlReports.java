package Controller;

import Model.Nascent;
import Model.DAOEntity;
import Model.DAONascent;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 *
 * @author fabri
 */
public class CtrlReports {
    // This line defines a class named CtrlReports.

    DAONascent nacent = new DAONascent();
    DAOEntity dao = new DAOEntity();
    // These lines create instances of the DAONascent and DAOEntity classes.

    public void loadEnty(JComboBox<String> cbxEntity) {
        // This line defines a method named loadEnty that takes a JComboBox as a parameter.

        List<String> entity = this.dao.readEntitys();
        // This line reads a list of entities using the DAOEntity object.

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        // This line creates a DefaultComboBoxModel for String items.

        for (String enty : entity) {
            model.addElement(enty);
        }
        // This loop populates the model with entity names.

        cbxEntity.setModel(model);
        // This line sets the model for the JComboBox, populating it with entity names.
    }

    public void loadNascentSelected(JTable table, String entitySelected) {
        // This line defines a method named loadNascentSelected that takes a JTable and a String as parameters.

        DefaultTableModel model = new DefaultTableModel();
        // This line creates a DefaultTableModel for tabular data.

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Latitud");
        model.addColumn("Longitud");
        model.addColumn("Descripción");
        // These lines add columns to the table model.

        Map<String, Nascent> nascentsByEntity = nacent.getNascentsByEntity(entitySelected);
        // This line retrieves a map of Nascent objects by entity from the DAONascent object.

        for (Nascent nascent : nascentsByEntity.values()) {
            Object[] row = {nascent.getId(), nascent.getName(), nascent.getAddress(), nascent.getLatitude(), nascent.getLength(), nascent.getDescription()};
            model.addRow(row);
        }
        // This loop populates the table model with data from Nascent objects.

        table.setModel(model);
        // This line sets the model for the JTable, displaying the data.
    }

    public void generateBarChart(Map<String, Nascent> nascentsByEntity) {
        // This line defines a method named generateBarChart that takes a map as a parameter.

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // This line creates a dataset for a bar chart.

        for (Nascent nascent : nascentsByEntity.values()) {
            String nascentName = nascent.getName();
            double nascentLatitude = nascent.getLatitude();
            // These lines extract the name and latitude of each Nascent object.

            dataset.addValue(nascentLatitude, "Latitud", nascentName);
            // This line adds the Nascent's latitude data to the chart dataset.
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Latitud de Nascents por Entidad",
            "Nascents",
            "Latitud",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        // This line creates a bar chart using JFreeChart library with the provided dataset and settings
CategoryPlot plot = (CategoryPlot) chart.getPlot();
// In the chart, obtain the CategoryPlot, which represents the type of plot being used.

BarRenderer renderer = (BarRenderer) plot.getRenderer();
// Get the BarRenderer for the plot, which allows customization of bar chart properties.

renderer.setSeriesPaint(0, java.awt.Color.blue);
// Set the paint (color) for series 0 (the first series) to blue, changing the bar color.

ChartPanel chartPanel = new ChartPanel(chart);
// Create a panel to display the chart and pass the chart to it.

chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
// Set the preferred size of the chart panel to 800x600 pixels.

JFrame frame = new JFrame("Gráfica de Latitud de Nascents por Entidad");
// Create a new JFrame (window) with the specified title.

frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
// Set the default close operation for the JFrame, allowing it to be disposed of when closed.

frame.getContentPane().add(chartPanel);
// Add the chart panel to the content pane of the JFrame.

frame.pack();
// Pack the components within the JFrame, adjusting its size to fit its contents.

RefineryUtilities.centerFrameOnScreen(frame);
// Center the JFrame on the screen.

frame.setVisible(true);
// Make the JFrame and its content visible on the screen.

   }

}
