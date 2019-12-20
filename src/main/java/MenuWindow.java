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
            setName("Filter - Smirnova Darya FIT 16208");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu filterMenu = new JMenu("Filters");
            JMenu helpMenu = new JMenu("Help");

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

