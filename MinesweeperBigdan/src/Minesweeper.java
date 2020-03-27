import javax.swing.*;

public class Minesweeper {
    private static Drawer initGame(int width, int height, int scale, float mineProbability) {
        Game game = new Game(width, height, mineProbability);
        Drawer drawer = new Drawer(game, scale);
        Controls controls = new Controls(game, drawer);
        drawer.addMouseListener(controls);
        drawer.addMouseMotionListener(controls);

        return drawer;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Minesweeper");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.add(initGame(9, 9, 40, 0.1f));
        jFrame.pack();

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
