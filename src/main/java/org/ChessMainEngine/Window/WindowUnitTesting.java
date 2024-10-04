package org.ChessMainEngine.Window;

import org.ChessMainEngine.ChessBoard.ChessBoard;

public class WindowUnitTesting {

    public static void main(String[] args) {
       Window window = new Window();
        ChessBoard chessBoard = new ChessBoard();
        window.setDarkMode(true);
        window.addWindowComponent(chessBoard);



    }

}
