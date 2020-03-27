import java.util.Random;

public class Game {
    public class Cell {
        public boolean isRevealed;
        public boolean isMine;
        public boolean isFlagged;
        public boolean isExploded;
        public int count;
    }

    public enum RevelationResult {
        OK,
        Explosion,
        NotRevealed,
    }

    public Cell[][] cells;
    public int width, height;
    public boolean isOver = false, isWin = false;

    public Game(int width, int height, float mineProbability) {
        this.width = width;
        this.height = height;

        Random random = new Random();
        cells = new Cell[width][height];

        // generate mines
        for (int i=0; i < width; i++)
            for (int j=0; j < height; j++) {
                cells[i][j] = new Cell();
                cells[i][j].isMine = random.nextFloat() < mineProbability;
            }

        // set numbers
        for (int i=0; i < width; i++)
            for (int j=0; j < height; j++)
                if (cells[i][j].isMine)
                    for (int k = -1; k <= 1; k++)
                        for (int l = -1; l <= 1; l++) {
                            if (k == 0 && l == 0)
                                continue;

                            int x = i + k;
                            int y = j + l;

                            if (x < 0 || x >= width)
                                continue;
                            if (y < 0 || y >= height)
                                continue;

                            cells[x][y].count++;
                        }
    }

    private void checkWin() {
        isWin = true;

        for (int i=0; i < width; i++)
            for (int j=0; j < height; j++)
                if (!cells[i][j].isMine && !cells[i][j].isRevealed)
                    isWin = false;

        if (isWin)
            over();
    }

    public RevelationResult reveal(int x, int y) {
        if (isOver)
            return RevelationResult.NotRevealed;

        if (cells[x][y].isFlagged)
            return RevelationResult.NotRevealed;

        cells[x][y].isRevealed = true;
        if (cells[x][y].isMine)
            return RevelationResult.Explosion;

        // if there's no adjacent mines, reveal neighbors
        if (cells[x][y].count == 0)
            for (int k = -1; k <= 1; k++)
                for (int l = -1; l <= 1; l++) {
                    if (k == 0 && l == 0)
                        continue;

                    int nx = x + k;
                    int ny = y + l;

                    if (nx < 0 || nx >= width)
                        continue;
                    if (ny < 0 || ny >= height)
                        continue;

                    if (!cells[nx][ny].isRevealed)
                        reveal(nx, ny);
                }

        checkWin();
        return RevelationResult.OK;
    }

    public void revealAll() {
        for (int i=0; i < width; i++)
            for (int j=0; j < height; j++)
                cells[i][j].isRevealed = true;
    }

    public void over() {
        revealAll();
        isOver = true;
    }

    public void switchFlag(int x, int y) {
        if (isOver)
            return;

        if (cells[x][y].isRevealed) {
            if (cells[x][y].isFlagged)
                cells[x][y].isFlagged = false;
        }

        cells[x][y].isFlagged = !cells[x][y].isFlagged;
    }

    public void explode(int x, int y) {
        cells[x][y].isExploded = true;
    }
}
