import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Dataset
{
    private static final double[][] data = new double[][] {};

    public static CategoryDataset createDataset1()
    {
        DefaultCategoryDataset dataset;

        // Нижний ключ
        final String series1 = "Студенты";


        // Колонки
        final String category1 = "1 курс" ;
        final String category2 = "2 курс";
        final String category3 = "3 курс"   ;


        dataset = new DefaultCategoryDataset();

        dataset.addValue(44, series1, category1);
        dataset.addValue(40, series1, category2);
        dataset.addValue(26, series1, category3);

        return dataset;
    }

}