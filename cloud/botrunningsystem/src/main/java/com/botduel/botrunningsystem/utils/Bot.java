package com.botduel.botrunningsystem.utils;

import java.util.ArrayList;
import java.util.List;

public class Bot implements com.botduel.botrunningsystem.utils.BotInterface {
    private boolean checkTailIncreasing(int step) {
        if (step <= 10) {
            return true;
        }
        return step % 3 == 1;
    }

    public List<Cell> getCells(int sx, int sy, String steps) {
        steps = steps.substring(1, steps.length() - 1);
        List<Cell> res = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for (int i = 0; i < steps.length(); i++) {
            int d = steps.charAt(i) - '0';
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            if (!checkTailIncreasing(++step)) {
                res.remove(0);
            }
        }
        return res;
    }

    @Override
    public Integer nextMove(String input) {
        String[] strings = input.split("#");
        int[][] gameMap = new int[13][14];
        for (int i = 0, k = 0; i < 13; i++) {
            for (int j = 0; j < 14; j++, k++) {
                if (strings[0].charAt(k) == '1') {
                    gameMap[i][j] = 1;
                }
            }
        }

        int aSx = Integer.parseInt(strings[1]), aSy = Integer.parseInt(strings[2]);
        int bSx = Integer.parseInt(strings[4]), bSy = Integer.parseInt(strings[5]);

        // take out our body and opponent's body
        List<Cell> aCells = getCells(aSx, aSy, strings[3]);
        List<Cell> bCells = getCells(bSx, bSy, strings[6]);

        for (Cell c : aCells) {
            gameMap[c.x][c.y] = 1;
        }
        for (Cell c : bCells) {
            gameMap[c.x][c.y] = 1;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int x = aCells.get(aCells.size() - 1).x + dx[i];
            int y = aCells.get(aCells.size() - 1).y + dy[i];
            if (x >= 0 && x < 13 && y >= 0 && y < 14 && gameMap[x][y] == 0) {
                return i;
            }
        }

        return 0;
    }

    static class Cell {
        public int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
