package com.cjm.gobang.algorithm;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.UniversalBoard;
import java.util.ArrayList;

public class CheckerboardAlgorithm {
    public static final UniversalBoard universalBoard = new UniversalBoard();

    public CheckerboardAlgorithm() {
    }

    public static int calculationPositionByCoordinate(int calculation) {
        int width = universalBoard.getCHECKERBOARD_BORDER_WIDTH();
        return calculation % width > width / 2 ? calculation - calculation % width + width / 2 : calculation - calculation % width - width / 2;
    }

    public static int calculationCoordinateByIndex(int index) {
        int width = universalBoard.getCHECKERBOARD_BORDER_WIDTH();
        return width * index + width - width / 2;
    }

    public static int calculationIndexByCoordinate(int calculation) {
        int width = universalBoard.getCHECKERBOARD_BORDER_WIDTH();
        return calculation % width > width / 2 ? (calculation - calculation % width + width) / width - 1 : (calculation - calculation % width) / width - 1;
    }

    public static boolean judge(int i, int j) {
        boolean flag = false;
        if (checkConnectedCount(i, j, 1, 0) >= 5) {
            flag = true;
        } else if (checkConnectedCount(i, j, 0, 1) >= 5) {
            flag = true;
        } else if (checkConnectedCount(i, j, 1, -1) >= 5) {
            flag = true;
        } else if (checkConnectedCount(i, j, 1, 1) >= 5) {
            flag = true;
        }

        return flag;
    }

    public static int checkConnectedCount(int i, int j, int iChange, int jChange) {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        int[][] boardCondition = checkerboard.getCheckerboardSituation();
        int color = boardCondition[i][j];
        int count = 1;
        int tempI = iChange;
        int tempJ = jChange;
        checkerboard.setChessConnectedRecord(new ArrayList());
        int[] chessPosition = new int[]{i, j};
        checkerboard.getChessConnectedRecord().add(chessPosition);

        while(i + tempI >= 0 && i + tempI < boardCondition.length && j + tempJ >= 0 && j + tempJ < boardCondition.length && boardCondition[i + tempI][j + tempJ] == color) {
            ++count;
            chessPosition = new int[]{i + tempI, j + tempJ};
            checkerboard.getChessConnectedRecord().add(chessPosition);
            if (tempI != 0) {
                ++tempI;
            }

            if (tempJ != 0) {
                if (tempJ > 0) {
                    ++tempJ;
                } else {
                    --tempJ;
                }
            }
        }

        tempI = iChange;
        tempJ = jChange;

        while(i - tempI >= 0 && i - tempI < boardCondition.length && j - tempJ >= 0 && j - tempJ < boardCondition.length && boardCondition[i - tempI][j - tempJ] == color) {
            ++count;
            chessPosition = new int[]{i - tempI, j - tempJ};
            checkerboard.getChessConnectedRecord().add(chessPosition);
            if (tempI != 0) {
                ++tempI;
            }

            if (tempJ != 0) {
                if (tempJ > 0) {
                    ++tempJ;
                } else {
                    --tempJ;
                }
            }
        }

        return count;
    }
}
