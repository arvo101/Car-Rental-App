import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;



public class CarRental {

    public static JFrame frame = new JFrame("Car Rental");   
    public static GridBagConstraints constraints = new GridBagConstraints();
    public static int price;
    public static int flag = 0;
    public static int totalPrice = 0;
    
    public static void main(String[] args) {
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2, 0, 2, 0);
        
        JLabel titleLabel = new JLabel("Car Rental System");  
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        frame.add(titleLabel, constraints);  
        
        constraints.gridy = 1;
        JLabel usernameLabel = new JLabel("Username");
        frame.add(usernameLabel, constraints);
        constraints.gridy = 2;
        JTextField textField1 = new JTextField("", 15);
        textField1.setFont(new Font("Arial", Font.PLAIN, 15)); 
        frame.add(textField1, constraints);
        

        constraints.gridy = 3;
        JLabel licenseLabel = new JLabel("License ID");
        frame.add(licenseLabel, constraints);
        constraints.gridy = 4;
        JTextField textField2 = new JTextField("", 15);
        textField2.setFont(new Font("Arial", Font.PLAIN, 15)); 
        frame.add(textField2, constraints);
        
        constraints.gridy = 5;
        JLabel passwordLabel = new JLabel("Password");
        frame.add(passwordLabel, constraints);
        constraints.gridy = 6;
        JTextField textField3 = new JTextField("", 15);
        textField3.setFont(new Font("Arial", Font.PLAIN, 15)); 
        frame.add(textField3, constraints);
        
        constraints.gridy = 7;
        JButton loginButton = new JButton("Login");
        Font newFont = new Font("Arial", Font.BOLD, 15); 
        loginButton.setFont(newFont);
        loginButton.setBackground(Color.LIGHT_GRAY);
        frame.add(loginButton, constraints);
        
        
        
        loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!textField1.getText().isEmpty() &&
                !textField2.getText().isEmpty() &&
                !textField3.getText().isEmpty()) {
                String usernameGiven = "";
                String IdGiven = "";
                String passwordGiven = "";
                
                usernameGiven = textField1.getText();
                IdGiven = textField2.getText();
                passwordGiven = textField3.getText();

                //MySQL connection
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/car","root","root");
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT * FROM users WHERE ID = " + IdGiven);
                while(rs.next()){
                    if (rs.getString(2).equals(usernameGiven) && rs.getString(1).equals(IdGiven) && rs.getString(3).equals(passwordGiven)) {
                        mainScreen();
                        break;
                } else {
                    
                    constraints.gridy = 9;
                    JLabel failLabel = new JLabel("Login failed");
                    frame.add(failLabel, constraints);
                    check();
                    break;
                    }
                }
                con.close();  
           }    catch(ClassNotFoundException | SQLException b){ System.out.println(e);}

                }
            else{
                constraints.gridy = 10;
                JLabel failLabel = new JLabel("Login failed");
                frame.add(failLabel, constraints);
                frame.revalidate();
                
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
                
                }
            }
        });

        frame.setLocation(600, 300);
        frame.pack();
        frame.setSize(800, 450);
        frame.setVisible(true);
        
    }
    
static void mainScreen() {
    frame.getContentPane().removeAll();
    check();

    JLabel carsTitleLabel = new JLabel("Available Cars");
    carsTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    carsTitleLabel.setBackground(Color.LIGHT_GRAY);
    carsTitleLabel.setOpaque(true);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 3;
    frame.add(carsTitleLabel, constraints);
    constraints.gridwidth = 1;

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 1;
    gbc.insets = new Insets(10, 10, 10, 10);

    JPanel panel1 = createLabeledPanel("BMW X1", "Type: SUV      ", "80€/day", "Rating: 4.4", "x1.png");
    JPanel panel2 = createLabeledPanel("Ford Fiesta", "Type: Hatchback", "50€/day", "Rating: 4.5", "fiesta.png");
    JPanel panel3 = createLabeledPanel("Porsche GT3", "Type: Sports", "900€/day", "Rating: 5.0", "gt3.png");

    gbc.gridx = 0;
    mainPanel.add(panel1, gbc);

    gbc.gridx = 1;
    mainPanel.add(panel2, gbc);

    gbc.gridx = 2;
    mainPanel.add(panel3, gbc);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 3;
    frame.add(mainPanel, constraints);
    constraints.gridwidth = 1;

    check();
}

private static JPanel createLabeledPanel(String panelText, String label1Text, String label2Text, String label3Text, String imageName) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    JLabel panelLabel = new JLabel(panelText);
    panelLabel.setFont(new Font("Arial", Font.BOLD, 14));

    JLabel label1 = new JLabel(label1Text);
    JLabel label2 = new JLabel(label2Text);
    JLabel label3 = new JLabel(label3Text);
    JButton button = new JButton("Rent");
    button.setBackground(Color.LIGHT_GRAY);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 0, 5, 0);
    panel.add(panelLabel, gbc);

    ImageIcon carImage = new ImageIcon(imageName); 
    JLabel imageLabel = new JLabel(carImage);
    gbc.gridy = 1; 
    gbc.insets = new Insets(10, 0, 5, 0); 
    panel.add(imageLabel, gbc);

    gbc.gridy = 2;
    gbc.insets = new Insets(0, 0, 5, 0);
    panel.add(label1, gbc);

    gbc.gridy = 3;
    panel.add(label2, gbc);

    gbc.gridy = 4;
    gbc.insets = new Insets(0, 0, 10, 0);
    panel.add(label3, gbc);

    gbc.gridy = 5;
    panel.add(button, gbc);

    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Rent button pressed
            rentCar(panelText);
            check();
        }
    });

    return panel;
}

      static void check() {
        frame.revalidate();
        frame.repaint();
      }
      
static void rentCar(String panelText) {
    frame.getContentPane().removeAll();
    check();
 
    int bmwprice = 80;
    int fordprice = 50;
    int porscheprice = 900;
    
    if (panelText.equals("BMW X1"))
        price = bmwprice;
    if (panelText.equals("Ford Fiesta"))
        price = fordprice;
    if (panelText.equals("Porsche GT3"))
        price = porscheprice;
    
    JLabel daysRent = new JLabel("How many days do you want to rent the " + panelText + " ?");
    daysRent.setFont(new Font("Arial", Font.BOLD, 18));
    daysRent.setBackground(Color.LIGHT_GRAY);
    daysRent.setOpaque(true);

    JTextField daysTextField = new JTextField(2);
    JButton calculateButton = new JButton("Enter");
    calculateButton.setBackground(Color.LIGHT_GRAY);
    
    JButton payButton = new JButton("Pay");
    payButton.setBackground(Color.LIGHT_GRAY);
    payButton.setPreferredSize(new Dimension(150, 50));
    Font buttonFont = new Font("Arial", Font.BOLD, 16); 
    payButton.setFont(buttonFont); 
    payButton.setPreferredSize(new Dimension(150, 50));

    JPanel rentPanel = new JPanel();
    rentPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(10, 10, 10, 10);
    rentPanel.add(daysRent, gbc);
    
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 35, 5, 5);
    rentPanel.add(daysTextField, gbc);

    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 0, 5, 0);
    JLabel priceLabel = new JLabel("Days: ");
    rentPanel.add(priceLabel, gbc);

    gbc.gridx = 1; 
    gbc.gridy = 1; 
    gbc.gridwidth = 1; 
    gbc.anchor = GridBagConstraints.WEST; 
    gbc.insets = new Insets(5, 70, 5, 35); 
    rentPanel.add(calculateButton, gbc);
    
    gbc.gridx = 1; 
    gbc.gridy = 1;
    gbc.gridwidth = 1; 
    gbc.anchor = GridBagConstraints.WEST; 
    gbc.insets = new Insets(5, 95, 5, 35); 


    
    calculateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Calculate button pressed
        int days = Integer.parseInt(daysTextField.getText());
        totalPrice = days * price;
        gbc.gridx = 1; 
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(5, 167, 5, 35); 
        JLabel totalLabel = new JLabel("Total price: " + totalPrice + " €");
        rentPanel.add(totalLabel, gbc);
        flag = 1;
        check();
        }
    });

    
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(20, 10, 10, 10);
    rentPanel.add(payButton, gbc);
    
     payButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Pay button pressed
            if (flag == 0){
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.insets = new Insets(20, 230, 10, 10);
                JLabel totalLabel = new JLabel("Press \"Enter\" to calculate total amount");
                rentPanel.add(totalLabel, gbc);
            }
            else{
                exitScreen();
                
            check();}
        }
    });
    

    frame.add(rentPanel);
    check();
}


static void exitScreen() {
    frame.getContentPane().removeAll();
    check();

    JLabel titleLabel = new JLabel("Thank you! Come pick up your car tommorow.");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    titleLabel.setBackground(Color.LIGHT_GRAY);
    titleLabel.setOpaque(true);

    JButton rateCarButton = new JButton("Rate Car");
    Font newFont = new Font("Arial", Font.BOLD, 15); 
    rateCarButton.setFont(newFont);
    rateCarButton.setBackground(Color.LIGHT_GRAY);
    
    JButton cancelPaymentButton = new JButton("Cancel Payment");
    cancelPaymentButton.setFont(newFont);
    cancelPaymentButton.setBackground(Color.LIGHT_GRAY);
    JButton exitButton = new JButton("Exit");
    
    exitButton.setFont(newFont);
    exitButton.setBackground(Color.LIGHT_GRAY);
    
    
    JPanel exitPanel = new JPanel();
    exitPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(10, 10, 10, 10);
    exitPanel.add(titleLabel, gbc);

    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 5, 10);
    exitPanel.add(rateCarButton, gbc);

    gbc.gridx = 1;
    gbc.insets = new Insets(5, 10, 5, 10);
    exitPanel.add(cancelPaymentButton, gbc);

    gbc.gridx = 2;
    gbc.insets = new Insets(5, 10, 5, 10);
    exitPanel.add(exitButton, gbc);

    
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Exit button pressed
            System.exit(0); 
        }
    });

cancelPaymentButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        //Cancel Payment button pressed
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel cancelLabel = new JLabel("Payment has been canceled. " + totalPrice + "€ has been returned to your bank account.");
        cancelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(cancelLabel, gbc);

        gbc.gridy = 1;
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Exit button pressed
                System.exit(0); 
            }
        });

        panel.add(exitButton, gbc);
        exitButton.setBackground(Color.LIGHT_GRAY);
        frame.add(panel);
        check();
    }
});

    frame.add(exitPanel);
    check();
}    
}   