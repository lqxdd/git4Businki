import javax.swing.*;
import java.awt.*;

public class Drawer extends JComponent {
    private Game game;
    private int scale;

    private final Color[] colorMap = new Color[]{Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED};

    private int focusedX = -1, focusedY = -1;

    public Drawer(Game game, int scale) {
        this.game = game;
        this.scale = scale;
        setPreferredSize(new Dimension(game.width * scale + 1, game.height * scale + 1));
    }

    public void focus(int x, int y) {
        focusedX = x / scale;
        focusedY = y / scale;
    }

    public Game.RevelationResult reveal(int x, int y) {
        return game.reveal(x / scale, y / scale);
    }

    public void switchFlag(int x, int y) {
        game.switchFlag(x / scale, y / scale);
    }

    public void explode(int x, int y) {
        game.explode(x / scale, y / scale);
    }

    public void unfocus() {
        focusedX = -1;
        focusedY = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, scale));
        FontMetrics fm = g.getFontMetrics(g.getFont());

        for (int i = 0; i < game.width; i++)
            for (int j = 0; j < game.height; j++) {
                if (game.cells[i][j].isRevealed)
                    g.setColor(Color.WHITE);
                else
                    if (i == focusedX && j == focusedY)
                        g.setColor(Color.LIGHT_GRAY);
                    else
                        g.setColor(Color.GRAY);
                g.fillRect(i * scale, j * scale, scale, scale);

                if (game.cells[i][j].isRevealed) {
                    if (game.cells[i][j].isMine) {
                        if (game.cells[i][j].isExploded)
                            g.setColor(Color.RED);
                        else
                            g.setColor(Color.BLACK);
                        g.fillOval((int) (i * scale + scale * 0.1), (int) (j * scale + scale * 0.1), (int) (scale * 0.8), (int) (scale * 0.8));
                    }
                    else
                    if (game.cells[i][j].count > 0) {
                        g.setColor(colorMap[game.cells[i][j].count]);
                        String s = Integer.toString(game.cells[i][j].count);
                        g.drawString(s, i * scale + (scale - fm.stringWidth(s)) / 2, j * scale + (scale - fm.getHeight()) / 2 + scale);
                    }
                }
//                else // comment to flag revealed cells
                    if (game.cells[i][j].isFlagged && (game.cells[i][j].isMine || !game.cells[i][j].isRevealed)) {
                        g.setColor(Color.ORANGE);
                        g.fillRect((int) (i * scale + scale * 0.2), (int) (j * scale + scale * 0.2), (int) (scale * 0.6 + 1), (int) (scale * 0.6 + 1));
                    }

                g.setColor(Color.BLACK);
                g.drawRect(i * scale, j * scale, scale, scale);
            }

        if (game.isOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, getWidth() / 10));
            fm = g.getFontMetrics(g.getFont());
            String s;
            if (game.isWin)
                s = "YOU WON";
            else
                s = "YOU LOST";
            g.drawString(s, (getWidth() - fm.stringWidth(s)) / 2, getHeight() / 2);
        }
    }
}
