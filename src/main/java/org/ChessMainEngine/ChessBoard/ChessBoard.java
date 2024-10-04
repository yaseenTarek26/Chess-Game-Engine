package org.ChessMainEngine.ChessBoard;

import org.ChessMainEngine.ChessPiece.*;
import org.ChessMainEngine.Colors.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessBoard extends JPanel {
    private int rows =8, cols=8;
    private final int tileSize = 85;//it's recommended not changes this value cuz it can lead to some problem after loading the chess pieces.
    private ArrayList<ChessPiece> pieceList = new ArrayList<>();
    private Color whiteBlock = Colors.createColor(227,198,181),darkBlock= Colors.createColor(157,105,53);

    public ChessBoard(){
       configureChessBoard();

    }

     private void configureChessBoard(){
         this.setPreferredSize(new Dimension((cols* tileSize) ,(rows* tileSize)));
     }


   @Override
    public String toString(){
        return null;
   }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        int tileSize = getDynamicTileSize();

        // Draw the chessboard tiles dynamically
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                initComponentColor(graphics2D, row, col);
                highLightChessBoardBorders(graphics2D, row, col, tileSize);
            }
        }
         initializeGame();
        // Paint chess pieces
        iterateThroughPieceList(graphics2D, tileSize);
    }
    private int getDynamicTileSize() {
        // Get the current window width and height
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();

        // Calculate dynamic tile size based on the available window size and number of rows/columns
        return Math.min(windowWidth / cols, windowHeight / rows);
    }

    private void highLightChessBoardBorders(Graphics2D graphics2D, int row, int col, int tileSize) {
        graphics2D.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
    }
    public void initializeGame() {
        // Adding white pieces to the board
        addPiece(new Rook(this, 0, 0, true));  // White Rook (left)
        addPiece(new Knight(this, 0, 1, true));  // White Knight
        addPiece(new Bishop(this, 0, 2, true));  // White Bishop
        addPiece(new Queen(this, 0, 3, true));  // White Queen
        addPiece(new King(this, 0, 4, true));  // White King
        addPiece(new Bishop(this, 0, 5, true));  // White Bishop
        addPiece(new Knight(this, 0, 6, true));  // White Knight
        addPiece(new Rook(this, 0, 7, true));  // White Rook (right)

        // Add white pawns
        for (int col = 0; col < getCols(); col++) {
            addPiece(new Pawn(this, 1, col, true));  // White Pawns
        }

        // Adding black pieces to the board
        addPiece(new Rook(this, 7, 0, false));  // Black Rook (left)
        addPiece(new Knight(this, 7, 1, false));  // Black Knight
        addPiece(new Bishop(this, 7, 2, false));  // Black Bishop
        addPiece(new Queen(this, 7, 3, false));  // Black Queen
        addPiece(new King(this, 7, 4, false));  // Black King
        addPiece(new Bishop(this, 7, 5, false));  // Black Bishop
        addPiece(new Knight(this, 7, 6, false));  // Black Knight
        addPiece(new Rook(this, 7, 7, false));  // Black Rook (right)

        // Add black pawns
        for (int col = 0; col < getCols(); col++) {
            addPiece(new Pawn(this, 6, col, false));  // Black Pawns
        }
    }
    private void addPiece(ChessPiece piece) {
        pieceList.add(piece);
    }

    public void iterateThroughPieceList(Graphics2D graphics2D, int tileSize) {
        for (ChessPiece chessPiece : pieceList) {
            chessPiece.PaintChessPiece(graphics2D, tileSize);
        }
    }
   private void highLightChessBoardBorders(Graphics2D graphics2D,int rows , int cols){
       graphics2D.fillRect(cols * getTileSize() , rows * getTileSize(),getTileSize(), getTileSize());
   }
   private void initComponentColor(Graphics2D graphics2D,int rows , int cols){
       graphics2D.setColor((cols + rows)%2== 0 ? this.whiteBlock : this.darkBlock);
   }
    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Color getWhiteBlock() {
        return whiteBlock;
    }

    public void setWhiteBlock(Color whiteBlock) {
        this.whiteBlock = whiteBlock;
    }

    public Color getDarkBlock() {
        return darkBlock;
    }

    public void setDarkBlock(Color darkBlock) {
        this.darkBlock = darkBlock;
    }
}
