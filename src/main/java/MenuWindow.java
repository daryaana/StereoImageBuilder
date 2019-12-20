import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

    ActionListener blackWhiteAction = e -> mainPanel.blackWhiteFilter();
    ActionListener rotateAction = e -> {
        mainPanel.rotateFilter();
    };

    public void createToolbarBlackWhiteF(JToolBar toolBar) {
        JButton blackWhiteButton = new JButton();
        blackWhiteButton.setIcon(new ImageIcon(MenuWindow.class.getResource("images/blackwhite.png")));
        blackWhiteButton.setToolTipText("Black & White");
        toolBar.add(blackWhiteButton);
        blackWhiteButton.addActionListener(blackWhiteAction);
    }
    public void createToolbarWaterColor(JToolBar toolBar) {
        JButton waterButton = new JButton();
        waterButton.setIcon(new ImageIcon(MenuWindow.class.getResource("images/water.png")));
        waterButton.setToolTipText("Watercolor");
        toolBar.add(waterButton);
        waterButton.addActionListener(waterAction);
    }
    ActionListener openAction = e -> {
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image formats", "png", "bmp", "jpg"));
        int f = fileChooser.showOpenDialog(null);
        if (f == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mainPanel.loadFile(file);
        }
    };
    ActionListener blurAction = e -> {
        mainPanel.blurFilter();
    };

    public void createToolbarRotate(JToolBar toolBar) {
        JButton rotateButton = new JButton();
        rotateButton.setIcon(new ImageIcon(MenuWindow.class.getResource("images/rotate.png")));
        rotateButton.setToolTipText("Rotate");
        toolBar.add(rotateButton);
    //    rotateButton.addActionListener(rotateAction);
    }
    public void createToolbarBlur(JToolBar toolBar) {
        JButton blurButton = new JButton();
        blurButton.setIcon(new ImageIcon(MenuWindow.class.getResource("images/blur.png")));
        blurButton.setToolTipText("Blur");
        toolBar.add(blurButton);
        blurButton.addActionListener(blurAction);
    }

    private void rotateSettings() {
        JDialog dialog = new JDialog(this, "Set angle", true);
        JPanel anglePanel = new JPanel();
        JSlider angleSlider = new JSlider(JSlider.HORIZONTAL);
        TextField angleField = new TextField("", 10);
        JButton save = new JButton("Save");
        int angle = 0;

        dialog.setLayout(new BorderLayout());
        anglePanel.setLayout(new GridLayout(2, 2));

        angleSlider.setMaximum(180);
        angleSlider.setMinimum(-180);

        save.addActionListener(d -> dialog.dispose());

        angleField.setText(String.valueOf(angle));
        angleSlider.setValue(angle);

        angleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                angleSlider.setValue(Integer.valueOf(angleField.getText()));
            }
        });

        angleSlider.addChangeListener(s -> {
            int a = angleSlider.getValue();
            angleField.setText(String.valueOf(a));
            mainPanel.setAngle(a);
            mainPanel.rotateFilter();
        });

        anglePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Angle"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        anglePanel.add(angleField);
        anglePanel.add(angleSlider);
        anglePanel.add(save);

        dialog.add(anglePanel);

        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setPreferredSize(new Dimension(300, 130));
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
        public void createMenu () {
            setSize(1090, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu filterMenu = new JMenu("Filters");
            JMenu helpMenu = new JMenu("Help");

            createToolbarRotate(toolBar);
            createToolbarBlackWhiteF(toolBar);
            createToolbarBlur(toolBar);
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

