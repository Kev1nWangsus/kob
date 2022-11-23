package com.botduel.backend.websocket.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer id;
    private Integer sx;
    private Integer sy;
    private Integer botId;
    private String botCode;

    private List<Integer> steps;

    private boolean checkTailIncreasing(int step) {
        // check if snake's length increases in this round
        if (step <= 10) {
            return true;
        }

        return step % 3 == 1;
    }

    public List<Cell> getCells() {
        List<Cell> result = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        result.add(new Cell(x, y));
        for (int d : steps) {
            x += dx[d];
            y += dy[d];
            result.add(new Cell(x, y));
            if (!checkTailIncreasing(++step)) {
                result.remove(0);
            }
        }
        return result;
    }

    public String getStepString() {
        StringBuilder res = new StringBuilder();
        for (int d : steps) {
            res.append(d);
        }
        return res.toString();
    }
}
