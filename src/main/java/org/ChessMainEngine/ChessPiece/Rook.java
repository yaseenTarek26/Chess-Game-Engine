package org.ChessMainEngine.ChessPiece;

import org.ChessMainEngine.ChessBoard.ChessBoard;

import java.awt.*;

public class Rook extends ChessPiece {

    public Rook(ChessBoard chessBoard, int rows, int cols, boolean isWhite) {
        super(chessBoard);
        this.cols = cols;
        this.rows = rows;
        this.xPos = cols * chessBoard.getTileSize();
        this.yPos = rows * chessBoard.getTileSize();
        this.isWhite = isWhite;
        this.pieceName = "Rook";
        getChessPieceImage();
    }

    @Override
    protected Image getSubImage() {
        int pieceIndex = 4; // Rook index

        int row = isWhite ? 0 : 1;

        int x = pieceIndex * sheetScaleWidth;
        int y = row * sheetScaleHeight;

        return getBufferedImage().getSubimage(x, y, sheetScaleWidth, sheetScaleHeight);
    }
}
