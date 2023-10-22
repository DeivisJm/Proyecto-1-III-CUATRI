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

    DAONascent nacent = new DAONascent();
    DAOEntity dao = new DAOEntity();

    public void loadEnty(JComboBox<String> cbxEntity) {
        List<String> entity = this.dao.readEntitys();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (String enty : entity) {
            model.addElement(enty);
        }

        cbxEntity.setModel(model);
    }

   public void loadNascentSelected(JTable table, String entitySelected) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID");
    model.addColumn("Nombre");
    model.addColumn("Dirección");
    model.addColumn("Latitud");
    model.addColumn("Longitud");
    model.addColumn("Descripción");

    Map<String, Nascent> nascentsByEntity = nacent.getNascentsByEntity(entitySelected);

    for (Nascent nascent : nascentsByEntity.values()) {
        Object[] row = {nascent.getId(), nascent.getName(), nascent.getAddress(), nascent.getLatitude(), nascent.getLength(), nascent.getDescription()};
        model.addRow(row);
    }

    table.setModel(model);
}
   public void generateBarChart(Map<String, Nascent> nascentsByEntity) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Nascent nascent : nascentsByEntity.values()) {
        String nascentName = nascent.getName();
        double nascentLatitude = nascent.getLatitude();
        // Agrega los datos de latitud a la gráfica
        dataset.addValue(nascentLatitude, "Latitud", nascentName);
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

    // Personaliza la apariencia de la gráfica (opcional)
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, java.awt.Color.blue);

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

    JFrame frame = new JFrame("Gráfica de Latitud de Nascents por Entidad");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add(chartPanel);
    frame.pack();
    RefineryUtilities.centerFrameOnScreen(frame);
    frame.setVisible(true);
   }

}
