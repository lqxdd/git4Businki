import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controls implements MouseListener, MouseMotionListener {
    private Game game;
    private Drawer drawer;

    public Controls(Game game, Drawer drawer) {
        this.game = game;
        this.drawer = drawer;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            Game.RevelationResult result = drawer.reveal(mouseEvent.getX(), mouseEvent.getY());
            if (result == Game.RevelationResult.Explosion) {
                drawer.explode(mouseEvent.getX(), mouseEvent.getY());
                game.over();
            }
        }
        if (mouseEvent.getButton() == MouseEvent.BUTTON3)
            drawer.switchFlag(mouseEvent.getX(), mouseEvent.getY());
        drawer.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        drawer.unfocus();
        drawer.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        drawer.focus(mouseEvent.getX(), mouseEvent.getY());
        drawer.repaint();
    }
}
