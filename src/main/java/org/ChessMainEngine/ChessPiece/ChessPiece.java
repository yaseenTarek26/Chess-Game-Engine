package org.ChessMainEngine.ChessPiece;

import org.ChessMainEngine.ChessBoard.ChessBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ChessPiece {
    private String spriteSheetPathFolder ="src/main/java/org/ChessMainEngine/Assets/StandardChessPiecesFamily/Chess_Pieces_Sprite.svg.png";
     int rows , cols ;
     int xPos, yPos;
     boolean isWhite;
     String pieceName;
     int value;

  private  BufferedImage bufferedImage;
    {
        try {
            // Load from the resources folder using ClassLoader
            bufferedImage = ImageIO.read(new File(spriteSheetPathFolder));
            if (bufferedImage == null) {
                throw new IOException("Failed to load image: " + spriteSheetPathFolder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    Image sprite;
    ChessBoard chessBoard;
      public ChessPiece(ChessBoard chessBoard){
          this.chessBoard = chessBoard;
      }

    protected final int sheetScaleWidth = bufferedImage.getWidth()/6;
    protected final int sheetScaleHeight = bufferedImage.getHeight()/2;
   protected  abstract Image getSubImage();
    protected Image getScaledImage(Image image) {

        return image.getScaledInstance(sheetScaleWidth, sheetScaleHeight, Image.SCALE_SMOOTH);
    }


    public  BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    protected void getChessPieceImage(){
        setSprite(getScaledImage(getSubImage()));
    }

    public void PaintChessPiece(Graphics2D graphics2D, int tileSize) {
        // Update positions based on tile size
        xPos = cols * tileSize;
        yPos = rows * tileSize;

        // Calculate centered positions
        int centeredX = xPos + (tileSize - sprite.getWidth(null)) / 2;
        int centeredY = yPos + (tileSize - sprite.getHeight(null)) / 2;

        // Draw the piece at the centered position
        graphics2D.drawImage(sprite, centeredX, centeredY, null);
    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public float getSheetScaleWidth() {
        return sheetScaleWidth;
    }
}
