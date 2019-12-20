import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuWindow extends JFrame {
    private JToolBar toolBar = new JToolBar();
    private MainPanel mainPanel = new MainPanel();
    private JFileChooser fileChooser = null;
    private int redInit = 2, greenInit = 2, blueInit = 2;
    MenuWindow() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        mainPanel.setRGB(redInit, greenInit, blueInit);
    }
    public void createToolbarBlackWhiteF(JToolBar toolBar) {
        JButton blackWhiteButton = new JButton();
        blackWhiteButton.setIcon(new ImageIcon(MenuWindow.class.getResource("images/blackwhite.png")));
        blackWhiteButton.setToolTipText("Black & White");
        toolBar.add(blackWhiteButton);
      //  blackWhiteButton.addActionListener(blackWhiteAction);
    }
    ActionListener openAction = e -> {
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image formats", "png", "bmp", "jpg"));
        int f = fileChooser.showOpenDialog(null);
        if (f == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mainPanel.loadFile(file);
        }
    };


        public void createMenu () {
            setSize(1090, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu filterMenu = new JMenu("Filters");
            JMenu helpMenu = new JMenu("Help");

            createToolbarBlackWhiteF(toolBar);
            toolBar.setFloatable(false);

            menuBar.add(fileMenu);
            menuBar.add(filterMenu);
            menuBar.add(helpMenu);
            setJMenuBar(menuBar);


            add(toolBar, BorderLayout.PAGE_START);


            getContentPane().add(mainPanel);


/**
 * подпункты в меню*/
            JMenuItem openMenuItem = new JMenuItem("Open");
            JMenuItem exitMenuItem = new JMenuItem("Exit");

            fileMenu.addSeparator();//зделитель при нью и опен
            fileMenu.add(openMenuItem);
            fileMenu.add(exitMenuItem);

            openMenuItem.addActionListener(openAction);


            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);


        }

}

