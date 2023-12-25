package lastpencil;
import java.util.*;
public class PencilBot {
    public String name;
    public int moves;

    public PencilBot(String name) {
        this.name = name;
    }

    public int winGame() {
        if (moves % 4 == 0) {
            return 3;
        } else if (moves % 4 == 3) {
            return 2;
        } else if (moves % 4 == 2) {
            return 1;
        }
        return 0;
    }

    public int loseGame() {
        Random random = new Random();
        int choose = random.nextInt(1, 4);
        if (moves == 1) {
            return 1;
        } else if (moves % 4 == 1) {
            return choose;
        }
        return 0;
    }

    public int getMoves() {
        return this.moves;
    }

    public void setMoves(int plays) {
        this.moves = plays;
    }

    public int botPlays() {
        int potentialMoves = moves % 4;
        if (potentialMoves == 1) {
            return this.loseGame();
        } else {
            return this.winGame();
        }
    }
}
