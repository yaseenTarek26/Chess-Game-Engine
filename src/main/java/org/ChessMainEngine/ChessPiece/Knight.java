package org.ChessMainEngine.ChessPiece;

import org.ChessMainEngine.ChessBoard.ChessBoard;

import java.awt.*;

public class Knight extends ChessPiece{

    public Knight(ChessBoard chessBoard , int rows , int cols ,boolean isWhite){
        super(chessBoard);
        this.cols = cols;
        this.rows = rows;
        this.xPos =cols * chessBoard.getTileSize();
        this.yPos = rows * chessBoard.getTileSize();
        this.isWhite =isWhite;
        this.pieceName = "Knight";
        getChessPieceImage();
    }

    @Override
    protected Image getSubImage() {
        int pieceIndex = switch (this.pieceName.toLowerCase()) {
            case "king" -> 0;
            case "queen" -> 1;
            case "bishop" -> 2;
            case "knight" -> 3;
            case "rook" -> 4;
            case "pawn" -> 5;
            default -> throw new IllegalArgumentException("Invalid piece name: " + this.pieceName);
        };

        // Determine row (0 for white, 1 for black)
        int row = isWhite ? 0 : 1;

        // Calculate x and y coordinates
        int x = pieceIndex * sheetScaleWidth;
        int y = row * sheetScaleHeight;

        // Return the subimage from the sprite sheet
        return getBufferedImage().getSubimage(x, y, sheetScaleWidth, sheetScaleHeight);
    }



}
