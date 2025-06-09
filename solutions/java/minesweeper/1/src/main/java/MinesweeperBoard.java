import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/

public class MinesweeperBoard {

    private ArrayList<String> board = new ArrayList<>();

    public MinesweeperBoard(List<String> input) {
        for (var s : input) {
            board.add(s);
        }
    }

    public List<String> withNumbers() {
        ArrayList<String> withNumbers = new ArrayList<>(board);
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(0).length(); j++) {
                if (board.get(i).charAt(j) == ' ') {
                    updateBoardAt(withNumbers, i, j);
                }
            }
        }
        return withNumbers;
    }

    private void updateBoardAt(ArrayList<String> board, int x, int y) {
        int count = 0;
        for (int[] neighbor : getNeighbors(x, y)) {
            if (board.get(neighbor[0]).charAt(neighbor[1]) == '*') {
                count++;
            }
        }
        if (count > 0) {
            String row = board.get(x);
            String rest = row.length() >= y + 1 ? row.substring(y + 1) : "";
            row = row.substring(0, y) + count + rest;
            board.set(x, row);
        }

    }

    private List<int[]> getNeighbors(int x, int y) {
        List<int[]> neighbors = new LinkedList<int[]>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int xNeighbor = x + i;
                int yNeighbor = y + j;
                if (xNeighbor >= 0 && yNeighbor >= 0 && xNeighbor < board.size() && yNeighbor < board.get(0).length()) {
                    neighbors.add(new int[] { xNeighbor, yNeighbor });
                }
            }
        }
        return neighbors;
    }

}
