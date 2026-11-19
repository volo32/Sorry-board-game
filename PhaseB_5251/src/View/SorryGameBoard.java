package View;

import Model.Card;
import Model.CardSorry;
import Model.Pawn;
import Model.Square;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Random;

public class SorryGameBoard extends Component {
public boolean timeToChooseAgain;

    JLabel[][] gridLabels = new JLabel[16][16];
    private static final int Yellow = 0;
    private static final int Red = 1;
    private static Pawn yellowPawn1, yellowPawn2, redPawn1, redPawn2;
    private static JTextField moveInputField;
    public static int currentPlayer = 1;
    private static final int gridSize = 40;
    private static JFrame frame;
    private static JLabel backgroundLabel;
    private JLabel playedCardLabel = new JLabel();

    public void createAndShowGUI() {

        frame = new JFrame("Sorry! Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);

        ImageIcon backgroundImage = new ImageIcon("src/images/lewforos.png");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null);

        // Set the bounds to cover the entire frame
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        initializeGrid();


        yellowPawn1 = initializePawn("src/images/yellowPawn1.png");
        yellowPawn2 = initializePawn("src/images/yellowPawn2.png");
        redPawn1 = initializePawn("src/images/redPawn1.png");
        redPawn2 = initializePawn("src/images/redPawn2.png");

        setPawnLocation(yellowPawn1, gridSize*11, gridSize*14);
        setPawnLocation(yellowPawn2, gridSize*12, gridSize*14);
        setPawnLocation(redPawn1, gridSize*4, gridSize);
        setPawnLocation(redPawn2, gridSize*5, gridSize);

        addMouseListeners(yellowPawn1);
        addMouseListeners(yellowPawn2);
        addMouseListeners(redPawn1);
        addMouseListeners(redPawn2);

        backgroundLabel.add(yellowPawn1);
        backgroundLabel.add(yellowPawn2);
        backgroundLabel.add(redPawn1);
        backgroundLabel.add(redPawn2);

        backgroundLabel.setComponentZOrder(yellowPawn1, 0);
        backgroundLabel.setComponentZOrder(yellowPawn2, 0);
        backgroundLabel.setComponentZOrder(redPawn1, 0);
        backgroundLabel.setComponentZOrder(redPawn2, 0);
        JButton playCardButton = new JButton("Play Card");
        playCardButton.setBounds(1000, 300, 100, 30);
        playCardButton.addActionListener(_ -> {
            backgroundLabel.remove(playedCardLabel);

            playCard();

            backgroundLabel.add(playedCardLabel);

            backgroundLabel.revalidate();
            backgroundLabel.repaint();
        });
        backgroundLabel.add(playCardButton);
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        JLabel currentPlayerLabel = new JLabel(STR."Current Player: \{currentPlayer}");
        cardPanel.add(currentPlayerLabel, BorderLayout.SOUTH);
        moveInputField = new JTextField(5);
        JButton moveButton = new JButton("Move");
        moveInputField.setBounds(1000, 500, 50, 30);
        moveButton.setBounds(1000, 400, 100, 30);
        JLabel displayedCardLabel = new JLabel();
        displayedCardLabel.setBounds(700, 200, 100, 150);
        moveButton.addActionListener(_ -> {
            String inputText = moveInputField.getText();
            if (!inputText.isEmpty()) {
                try {
                    int moveDistance = Integer.parseInt(inputText);
                    if (moveDistance > -12&&moveDistance<13) {
                        Pawn selectedPawn;
                        switch (currentPlayer) {
                            case 1:
                                selectedPawn = yellowPawn1;

                                break;
                            case 2:
                                selectedPawn = redPawn1;

                                break;
                            case 3:
                                selectedPawn = yellowPawn2;

                                break;
                            case 4:
                                selectedPawn = redPawn2;

                                break;
                            default:
                                return;
                        }
                        movePawn(selectedPawn, moveDistance);
                        currentPlayer = (currentPlayer % 4) + 1;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid input: Distance must be greater than 0");

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input: Please enter a valid integer");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid input: Please enter a value");
            }
        });

        MouseAdapter ma = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                Pawn selectedPawn = (Pawn) e.getSource();
                selectedPawn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Pawn selectedPawn = (Pawn) e.getSource();
                selectedPawn.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Pawn selectedPawn = (Pawn) e.getSource();
                Point currCoords = e.getLocationOnScreen();
                selectedPawn.setLocation(currCoords.x - initialClick.x, currCoords.y - initialClick.y);
            }
        };
        yellowPawn1.addMouseListener(ma);
        yellowPawn1.addMouseMotionListener(ma);
        yellowPawn2.addMouseListener(ma);
        yellowPawn2.addMouseMotionListener(ma);
        redPawn1.addMouseListener(ma);
        redPawn1.addMouseMotionListener(ma);
        redPawn2.addMouseListener(ma);
        redPawn2.addMouseMotionListener(ma);

        backgroundLabel.add(moveInputField);
        backgroundLabel.add(moveButton);

        frame.add(backgroundLabel);
        frame.setVisible(true);
    }
    public static void initializeGrid() {
        JLabel[][] gridLabels = new JLabel[16][16];
        ClassLoader classLoader = SorryGameBoard.class.getClassLoader();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                gridLabels[i][j] = new JLabel();
                gridLabels[i][j].setBounds(i * gridSize, j * gridSize, gridSize, gridSize);
                gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridLabels[i][j].setOpaque(true);
                gridLabels[i][j].setBackground(Color.WHITE);
                backgroundLabel.add(gridLabels[i][j]);
                if ((i == 2 || i == 11) && j == 15) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/yellowSlideEnd.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if ((i == 3 || i == 4 || i == 5 || i == 12 || i == 13) && j == 15) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/yellowSlideMedium.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if ((i == 6 || i == 14) && j == 15) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/yellowSlideStart.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if ((i == 1 || i == 9) && j == 0) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/redSlideStart.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if ((i == 2 || i == 3 || i == 10 || i == 11 || i == 12) && j == 0) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/redSlideMedium.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if ((i == 4 || i == 13) && j == 0) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/redSlideEnd.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i == 0 && (j == 2 || j == 11)) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/greenSlideEnd.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i == 0 && (j == 3 || j == 4 || j == 5 || j == 12 || j == 13)) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/greenSlideMedium.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i == 0 && (j == 6 || j == 14)) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/greenSlideStart.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i == 15 && (j == 1 || j == 9)) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/blueSlideStart.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i == 15 && (j == 2 || j == 3 || j == 11 || j == 12 || j == 10)) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/blueSlideMedium.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (i == 15 && (j == 4 || j == 13)) {
                    ImageIcon customImage = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/blueSlideEnd.png")));
                    int centerX = (40 - Math.min(40, customImage.getIconWidth())) / 2;
                    int centerY = (40 - Math.min(40, customImage.getIconHeight())) / 2;
                    gridLabels[i][j].setBounds(i * 40 + centerX, j * 40 + centerY, Math.min(40, customImage.getIconWidth()), Math.min(40, customImage.getIconHeight()));
                    gridLabels[i][j].setIcon(customImage);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }else if (i == 11 && j == 13) {
                    JPanel startPanel = new JPanel();
                    startPanel.setBounds(i * gridSize, j * gridSize, gridSize * 2, gridSize * 2);
                    startPanel.setLayout(new BorderLayout());
                    startPanel.setOpaque(true);
                    startPanel.setBackground(Color.WHITE);
                    Border border = BorderFactory.createLineBorder(Color.YELLOW, 5);
                    startPanel.setBorder(border);
                    JLabel startLabel = new JLabel("START", SwingConstants.CENTER);
                    startPanel.add(startLabel, BorderLayout.CENTER);
                    JLabel pushLabel = new JLabel();
                    pushLabel.setPreferredSize(new Dimension(gridSize * 2, gridSize));
                    startPanel.add(pushLabel, BorderLayout.SOUTH);

                    backgroundLabel.add(startPanel);
                    backgroundLabel.setComponentZOrder(startPanel, 0);
                }else if (i == 4 && j == 1) {
                    JPanel redPanel = new JPanel();
                    redPanel.setBounds(i * gridSize, j * gridSize, gridSize * 2, gridSize * 2);
                    redPanel.setLayout(new BorderLayout());
                    redPanel.setOpaque(true);
                    redPanel.setBackground(Color.WHITE);
                    Border border = BorderFactory.createLineBorder(Color.RED, 5);
                    redPanel.setBorder(border);

                    // Adding an invisible label above the "START" label to push it down
                    JLabel pushLabel = new JLabel();
                    pushLabel.setPreferredSize(new Dimension(gridSize * 2, gridSize / 4)); // Adjust the height to push the text down
                    redPanel.add(pushLabel, BorderLayout.NORTH);

                    // Set the text for the redLabel to "START"
                    JLabel redLabel = new JLabel("START", SwingConstants.CENTER);
                    redLabel.setOpaque(false);
                    redPanel.add(redLabel, BorderLayout.CENTER);

                    backgroundLabel.add(redPanel);
                    backgroundLabel.setComponentZOrder(redPanel, 0);
                }else  if (i == 2 && j == 6) {
                    JPanel redHomePanel = new JPanel();
                    redHomePanel.setBounds(i * gridSize, j * gridSize, gridSize * 2, gridSize * 2);
                    redHomePanel.setLayout(new BorderLayout());
                    redHomePanel.setOpaque(true);
                    redHomePanel.setBackground(Color.WHITE);
                    Border border = BorderFactory.createLineBorder(Color.RED, 5);
                    redHomePanel.setBorder(border);

                    // Adding an invisible label above the "HOME" label to push it down
                    JLabel pushLabel = new JLabel();
                    // Adjust the height to push the "HOME" text down within the panel
                    pushLabel.setPreferredSize(new Dimension(gridSize * 2, gridSize / 4));
                    redHomePanel.add(pushLabel, BorderLayout.NORTH);

                    // Set the text for the redLabel to "HOME"
                    JLabel homeLabel = new JLabel("HOME", SwingConstants.CENTER);
                    homeLabel.setOpaque(false);
                    redHomePanel.add(homeLabel, BorderLayout.CENTER);

                    backgroundLabel.add(redHomePanel);
                    backgroundLabel.setComponentZOrder(redHomePanel, 0);
                }else if (i == 13 && j == 8) {
                    JPanel yellowHomePanel = new JPanel();
                    yellowHomePanel.setBounds(i * gridSize, j * gridSize, gridSize * 2, gridSize * 2);
                    yellowHomePanel.setLayout(new BorderLayout());
                    yellowHomePanel.setOpaque(true);
                    yellowHomePanel.setBackground(Color.WHITE);
                    Border border = BorderFactory.createLineBorder(Color.YELLOW, 5); // Yellow border
                    yellowHomePanel.setBorder(border);

                    // Adding an invisible label above the "HOME" label to push it down
                    JLabel pushLabel = new JLabel();
                    // Adjust the height to push the "HOME" text down within the panel
                    pushLabel.setPreferredSize(new Dimension(gridSize * 2, gridSize / 4));
                    yellowHomePanel.add(pushLabel, BorderLayout.NORTH);

                    // Set the text for the homeLabel to "HOME"
                    JLabel homeLabel = new JLabel("HOME", SwingConstants.CENTER);
                    homeLabel.setOpaque(false);
                    yellowHomePanel.add(homeLabel, BorderLayout.CENTER);

                    backgroundLabel.add(yellowHomePanel);
                    backgroundLabel.setComponentZOrder(yellowHomePanel, 0);
                }else if (i == 2 && (j == 1 || j == 2 || j == 3 || j == 4 || j == 5)) {
                    gridLabels[i][j].setBackground(Color.RED);
                }else if (i == 13 && (j == 14 || j == 13 || j == 12 || j == 11||j==10))  {
                    gridLabels[i][j].setBackground(Color.YELLOW);
                } else if (i == 0 || i == 15 || j == 0 || j == 15) {
                    gridLabels[i][j].setEnabled(true);
                    gridLabels[i][j].setBackground(Color.WHITE);
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }else {
                    gridLabels[i][j].setBackground(new Color(10, 209, 215));
                    gridLabels[i][j].setOpaque(true);
                    gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    gridLabels[i][j].setBorder(BorderFactory.createEmptyBorder());
                }
            }
        }
        ImageIcon sorryImageIcon = new ImageIcon("src/images/SorryImage.png");

        Image image = sorryImageIcon.getImage();
        Image newimg = image.getScaledInstance(gridSize * 6, gridSize * 3,  java.awt.Image.SCALE_SMOOTH);
        sorryImageIcon = new ImageIcon(newimg);

        JLabel sorryLabel = new JLabel(sorryImageIcon);
        sorryLabel.setBounds(5*gridSize, 7*gridSize, sorryImageIcon.getIconWidth(), sorryImageIcon.getIconHeight());
        backgroundLabel.add(sorryLabel);
        backgroundLabel.setComponentZOrder(sorryLabel, 0);
    }

    private static Pawn initializePawn(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println(STR."Error loading image: \{imagePath}");
            return null;
        }
        return new Pawn(icon);
    }

    private static void setPawnLocation(Pawn pawn, int x, int y) {
        if (pawn != null) {
            pawn.setLocation(x, y);
        }
    }
    public void setTimeToChooseAgain(boolean b) {
        timeToChooseAgain = b;
        System.out.println(STR."setTimeToChooseAgain = \{b}");
    }
    private
    static void addMouseListeners(Pawn pawn) {
        if (pawn == null) return;

        MouseAdapter ma = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                pawn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pawn.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                pawn.setLocation(currCoords.x - initialClick.x, currCoords.y - initialClick.y);
            }
        };
        pawn.addMouseListener(ma);
        pawn.addMouseMotionListener(ma);
    }
    public static void movePawn(Pawn pawn, int moveDistance) {
        if (pawn == null) return;

        int gridSize = 40;
        int maxPosition = 15 * gridSize;
        for (int i = 0; i < moveDistance; i++) {
            int currentX = pawn.getX();
            int currentY = pawn.getY();
            if (pawn.isAtStartPosition()) {
                if (moveDistance==1||moveDistance==2){
                if (pawn==redPawn1||pawn==redPawn2){
                    pawn.setPosX(4);
                    pawn.setPosY(0);
                } else {
                    pawn.setPosX(11);
                    pawn.setPosY(15);
                }
                pawn.updateLocation();
                return;
            }else{
                    continue;
                }
                }
            switch (pawn.getDirection()) {
                case RIGHT:
                    if (currentX < maxPosition) {
                        pawn.setLocation(currentX + gridSize, currentY);
                    } else {
                        pawn.setDirection(Pawn.Direction.DOWN);
                        i--;
                    }
                    break;
                case DOWN:
                    if (currentY < maxPosition) {
                        pawn.setLocation(currentX, currentY + gridSize);
                    } else {
                        pawn.setDirection(Pawn.Direction.LEFT);
                    }
                    break;
                case LEFT:
                    if (currentX > 0) {
                        pawn.setLocation(currentX - gridSize, currentY);
                    } else {
                        pawn.setDirection(Pawn.Direction.UP);
                        i--;
                    }
                    break;
                case UP:
                    if (currentY > 0) {
                        pawn.setLocation(currentX, currentY - gridSize);
                    } else {
                        pawn.setDirection(Pawn.Direction.RIGHT);
                        i--;
                    }
                    break;
            }
        }
    }
    public void playCard() {
        int[] cardNumbers = {1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13};
        Random random = new Random();
        int chosenCardNumber = cardNumbers[random.nextInt(cardNumbers.length)];
        String imagePath;
        if (chosenCardNumber == 13) {
            imagePath = "src/images/cardSorry.png";
        } else {
            imagePath = STR."src/images/card\{chosenCardNumber}.png";
        }        ImageIcon cardIcon = new ImageIcon(imagePath);

        int width =200;
        int height = 200;
        cardIcon.setImage(cardIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        playedCardLabel = new JLabel();

        playedCardLabel.setIcon(cardIcon);
        playedCardLabel.setBounds(700, 150, width, height);

        backgroundLabel.add(playedCardLabel);


        Card pickedCard;
        if (chosenCardNumber == 13) {
            pickedCard = new CardSorry();
        } else {
            pickedCard = new Card();
            pickedCard.setMoveValue(chosenCardNumber);
            pickedCard.setDesc(STR."Move forward by \{chosenCardNumber} steps.");
        }
        System.out.println(STR."Chosen Card: \{pickedCard.getDescription()}");
    }
    public static int getYellow()
    {
        return Yellow;
    }
    public static int getRed()
    {
        return Red;
    }

    public Square[][] gridLabels() {
        return (Model.Square[][]) gridLabels;
    }
}