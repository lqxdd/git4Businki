
import java.awt.Color;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;


class BarChart3D extends ApplicationFrame
{
    private static final long serialVersionUID = 1L;

    public BarChart3D(final String title)
    {
        super(title);

        final CategoryDataset dataset    = Dataset.createDataset1();
        final JFreeChart      chart      = createChart(dataset);
        final ChartPanel      chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        setContentPane(chartPanel);

    }
    private JFreeChart createChart(final CategoryDataset dataset)
    {

        final JFreeChart chart = ChartFactory.createBarChart3D
                (
                "Студентики",   // chart title
                "Номер курса",                  // Надпись под осью Х
                "Количество",                  // Надпись ось У
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,                     // Описание и подсказки
                false
                );
        // Определение фона plot'a
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint (new Color(212, 212, 248));    // Определение фона plot'a

        CategoryAxis axis = plot.getDomainAxis();   // Настройка CategoryAxis


        // Скрытие осевых линий и меток делений
        axis.setAxisLineVisible (false);    // осевая линия
        axis.setTickMarksVisible(false);    // метки деления оси

        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        BarRenderer r = (BarRenderer) renderer;
        r.setMaximumBarWidth(0.05);

        return chart;
    }

    public static void main(final String[] args)
    {
        final BarChart3D demo = new BarChart3D("3D Bar Chart");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

