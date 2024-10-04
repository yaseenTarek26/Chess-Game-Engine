package org.ChessMainEngine.Window;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame windowJFrame;
    private String windowTitle = "Chess";
    private int width = 1000, height = 1000;
    private boolean setGridBagLayOut =true;
    private boolean isVisible = true;
    private boolean isFullScreen = false;
    private boolean isAlwaysOnTop = false;
    private  boolean enableDarkMode= false;
    private float opacity = 1.0f;// Fully opaque

    private Image windowIcon;

    // Constructor to initialize the window
// Constructor to initialize the window
    public Window() {
        initWindow(windowTitle, width, height);
        configureWindow(); // Ensure attributes are applied here
        System.out.println(toString());
    }

    // Constructor with custom title
    public Window(String title) {
        initWindow(title, width, height);
        configureWindow();
    }

    // Constructor with custom title and dimensions
    public Window(String title, int width, int height) {
        initWindow(title, width, height);
        configureWindow();
    }


    // Initialization method
    private void initWindow(String title, int width, int height) {
        this.windowJFrame = new JFrame(title);
        this.windowTitle = title;
        this.width = width;
        this.height = height;

        configureWindow();
    }

    public void addWindowComponent(JPanel component){
        getWindowJFrame().add(component);
    }
    public void addWindowComponent(JPanel component,boolean refreshWindow){
        getWindowJFrame().add(component);
        if(refreshWindow)getWindowJFrame().repaint();
    }
    public void updateWindow(){getWindowJFrame().repaint();}
    // Configure window properties in one place
    private void configureWindow() {
        windowJFrame.setSize(width, height);
        windowJFrame.setMinimumSize(new Dimension(width, height));
        windowJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowJFrame.setVisible(isVisible);
        windowJFrame.setOpacity(opacity); // Set opacity
        windowJFrame.setAlwaysOnTop(isAlwaysOnTop);// Set always on top
        setGridLayOut();
        if (windowIcon != null) {
            windowJFrame.setIconImage(windowIcon); // Set window icon if available
        }
    }
    private void setGridLayOut(){
        if(setGridBagLayOut)
        windowJFrame.setLayout(new GridBagLayout());
    }

    // Public method to access the window instance
    public JFrame getWindowJFrame() {
        return windowJFrame;
    }

    // Getter and Setter for window title
    public String getWindowTitle() {
        return windowJFrame.getTitle();
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
        windowJFrame.setTitle(windowTitle);
    }

    // Getter and Setter for window width
    public int getWidth() {
        return windowJFrame.getWidth();
    }

    public void setWidth(int width) {
        this.width = width;
        windowJFrame.setSize(width, this.height);
    }

    // Getter and Setter for window height
    public int getHeight() {
        return windowJFrame.getHeight();
    }

    public void setHeight(int height) {
        this.height = height;
        windowJFrame.setSize(this.width, height);
    }

    // Set the window to always be on top
    public void setAlwaysOnTop(boolean isAlwaysOnTop) {
        this.isAlwaysOnTop = isAlwaysOnTop;
        windowJFrame.setAlwaysOnTop(isAlwaysOnTop);
    }

    // Set window position on screen
    public void setWindowPosition(int x, int y) {
        windowJFrame.setLocation(x, y);
    }

    // Maximize the window
    public void maximizeWindow() {
        windowJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Minimize the window
    public void minimizeWindow() {
        windowJFrame.setExtendedState(JFrame.ICONIFIED);
    }

    // Toggle full-screen mode
    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        if (isFullScreen) {
            windowJFrame.dispose();
            windowJFrame.setUndecorated(true);
            windowJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            windowJFrame.setVisible(true);
        } else {
            windowJFrame.dispose();
            windowJFrame.setUndecorated(false);
            windowJFrame.setVisible(true);
        }
    }

    public boolean isSetGridBagLayOut() {
        return setGridBagLayOut;
    }

    public void setSetGridBagLayOut(boolean setGridBagLayOut) {
        this.setGridBagLayOut = setGridBagLayOut;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public boolean isAlwaysOnTop() {
        return isAlwaysOnTop;
    }

    public boolean isEnableDarkMode() {
        return enableDarkMode;
    }

    public void setEnableDarkMode(boolean enableDarkMode) {
        this.enableDarkMode = enableDarkMode;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public Image getWindowIcon() {
        return windowIcon;
    }

    public void setWindowIcon(Image windowIcon) {
        this.windowIcon = windowIcon;
    }

    public void setWindowOpacity(float opacity) {//this is method doesn't recommend to be used currently its just under develop and it's not probobly tested.
        if (opacity < 0.0f || opacity > 1.0f) {
            throw new IllegalArgumentException("Opacity must be between 0.0 and 1.0");
        }
        this.opacity = opacity;

        if (windowJFrame != null) {
            // Make sure the window is undecorated before setting opacity
            if (windowJFrame.isDisplayable()) {
                windowJFrame.dispose();  // Dispose the window if it's already visible
            }
            windowJFrame.setUndecorated(true);  // Required for opacity to work
            windowJFrame.setOpacity(opacity);
            windowJFrame.setVisible(isVisible);  // Re-show the window
        }
    }



    // Overriding toString to give a detailed description of the window

    // Method to enable or disable dark mode
// Method to enable or disable dark mode
    public void setDarkMode(boolean enableDarkMode) {
        this.enableDarkMode = enableDarkMode; // Update the actual state based on parameter

        Color backgroundColor = enableDarkMode ? Color.DARK_GRAY : Color.WHITE;
        Color foregroundColor = enableDarkMode ? Color.WHITE : Color.BLACK;

        windowJFrame.getContentPane().setBackground(backgroundColor);
        windowJFrame.getContentPane().setForeground(foregroundColor);

        // Adjust all components inside the window
        adjustComponentColors(windowJFrame.getContentPane(), backgroundColor, foregroundColor);

        // Repaint to reflect changes
        windowJFrame.repaint();
    }

    // Utility method to recursively set background and foreground colors
    private void adjustComponentColors(Container container, Color backgroundColor, Color foregroundColor) {
        for (Component component : container.getComponents()) {
            if (component instanceof JPanel) {
                component.setBackground(backgroundColor);
            } else if (component instanceof JLabel || component instanceof JButton) {
                component.setForeground(foregroundColor);
                component.setBackground(backgroundColor.darker()); // Darker shade for buttons
            }
            if (component instanceof Container) {
                adjustComponentColors((Container) component, backgroundColor, foregroundColor);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Window Details:\n")
                .append("-------------------------\n")
                .append("Title: ").append(windowJFrame.getTitle()).append("\n")
                .append("Size: ").append(getWidth()).append(" (width) x ").append(getHeight()).append(" (height)\n")
                .append("Visible: ").append(windowJFrame.isVisible() ? "Yes" : "No").append("\n")
                .append("Resizable: ").append(windowJFrame.isResizable() ? "Yes" : "No").append("\n")
                .append("Maximized: ").append((windowJFrame.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0 ? "Yes" : "No").append("\n")
                .append("Undecorated: ").append(windowJFrame.isUndecorated() ? "Yes" : "No").append("\n")
                .append("Opacity: ").append(opacity).append("\n")
                .append("Always on Top: ").append(isAlwaysOnTop ? "Yes" : "No").append("\n")
                .append("Full Screen: ").append(isFullScreen ? "Yes" : "No").append("\n") // Added full screen status
                .append("Dark Mode: ").append(enableDarkMode ? "Enabled" : "Disabled").append("\n") // Added dark mode status
                .append("Component Count: ").append(windowJFrame.getContentPane().getComponentCount()).append("\n")
                .append("Layout Manager: ")
                .append(windowJFrame.getContentPane().getLayout() != null
                        ? windowJFrame.getContentPane().getLayout().getClass().getSimpleName()
                        : "None")
                .append("\n-------------------------\n");

        return sb.toString();
    }

}
