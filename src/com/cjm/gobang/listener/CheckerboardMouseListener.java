package com.cjm.gobang.listener;

import com.cjm.gobang.algorithm.CheckerboardAlgorithm;
import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.role.ChessPlayer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckerboardMouseListener extends MouseAdapter {
    private int i;
    private int j;
    private ChessPlayer player = LoginAlgorithm.getCurrentPlayer();

    public CheckerboardMouseListener() {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 35 && e.getX() <= 645 && e.getY() >= 35 && e.getY() <= 645 && Common.getCommon().getCurrent_page().equals("twoplayer")) {
            this.i = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getX());
            this.j = CheckerboardAlgorithm.calculationIndexByCoordinate(e.getY());
            if (Checkerboard.getCheckerboard("twoplayer").getCurrent_chess_piece()) {
                this.player.put(this.i, this.j);
            } else {
                this.player.put(this.i, this.j);
            }
        }

    }
}
