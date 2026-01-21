import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayDeque;

public class Connect {
    private String[] board;

    private static record Coordinate(int x, int y) {

        static final List<Coordinate> offsets = List.of(
            new Coordinate(-1, -1),
            new Coordinate(1, -1),
            new Coordinate(2, 0),
            new Coordinate(-2, 0),
            new Coordinate(1, 1),
            new Coordinate(-1, 1)
        );

        public Coordinate add(Coordinate c) {
            return new Coordinate(x + c.x, y + c.y);
        }

        public List<Coordinate> getNeighbours(String[] board) {
            return offsets.stream()
                .map(c -> this.add(c))
                .filter(c ->
                        c.y >= 0 &&
                        c.y < board.length &&
                        c.x >= 0 &&
                        c.x < board[c.y].length())
                .toList();
        }
    }

    public Connect(String[] board) {
        this.board = board;
    }

    public Winner computeWinner() {
        if (board.length == 0) {
            return Winner.NONE;
        }

        var playerO = new ArrayList<Coordinate>();
        var playerX = new ArrayList<Coordinate>();

        for (int x = 0; x < this.board[0].length(); x++) {
            if (board[0].charAt(x) == 'O')
                playerO.add(new Coordinate(x, 0));
        }

        for (int y = 0; y < board.length; y++) {
            inner: for (int x = 0; x < board[y].length(); x++) {
                char ch = board[y].charAt(x);
                if (ch == 'X') {
                    playerX.add(new Coordinate(x, y));
                    break inner;
                } else if (ch == 'O')
                    break inner;
            }
        }

        var visitedByO = dfs(playerO, Winner.PLAYER_O);
        var visitedByX = dfs(playerX, Winner.PLAYER_X);

        for (int x = 0; x < board[board.length - 1].length(); x++) {
            if (board[board.length - 1].charAt(x) == 'O' &&
                visitedByO.contains(new Coordinate(x, board.length - 1))) {
                return Winner.PLAYER_O;
            }
        }

        for (int y = 0; y < board.length; y++) {
            if (board[y].charAt(board[y].length() - 1) == 'X' &&
                visitedByX.contains(new Coordinate(board[y].length() - 1, y))) {
                return Winner.PLAYER_X;
            }
        }

        return Winner.NONE;
    }

    private Set<Coordinate> dfs(List<Coordinate> start, Winner player) {
        if (player == Winner.NONE) {
            throw new IllegalArgumentException("DFS should be performed on PLAYER_X or PLAYER_O");
        }

        var playerChar = player == Winner.PLAYER_X ? 'X' : 'O';
        var visited = new HashSet<Coordinate>(start);
        var stack = new ArrayDeque<Coordinate>(start);

        while(!stack.isEmpty()) {
            var coord = stack.removeLast();
            for (var neighbour : coord.getNeighbours(board)) {
                if (!visited.contains(neighbour) &&
                    board[coord.y()].charAt(coord.x()) == playerChar) {
                    stack.addLast(neighbour);
                    visited.add(neighbour);
                }
            }
        }

        return visited;
    }
}
