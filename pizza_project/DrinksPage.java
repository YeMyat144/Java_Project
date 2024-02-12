package com.firsttime_pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

public class DrinksPage extends JPanel {
    private JComboBox<String> pizzaSizeComboBox;
    private JCheckBox[] toppingsCheckBoxes;
    private JTextField numberOfPizzasField;
    private JButton calculateButton;
    private JButton resetButton;
    private JTextArea resultTextArea;

    private JPanel mainPanel; 
    private boolean isDarkMode = false;
    private JButton basketButton;
    private JButton orderButton;
    private List<DrinksOrder> selectedItems = new ArrayList<>(); 
    private JRadioButton thai_iced_tea;    
    private JRadioButton taro_milk_tea;
    private JRadioButton matcha_latte;  
    private JRadioButton hong_kong_mile_tea;
    private JRadioButton honey_milk_tea;
    
    public DrinksPage() {
        setLayout(new BorderLayout(10, 10));
        mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel darkModePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        JButton darkModeButton = new JButton("Dark Mode");
        darkModePanel.add(darkModeButton);

        darkModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDarkMode = !isDarkMode;
                updateDarkMode();
            }
        });

        JPanel topPanel = new JPanel();
        JLabel pizzaSizeLabel = new JLabel(OrderApp.pizzaSize);
        pizzaSizeComboBox = new JComboBox<>(new String[]{OrderApp.small, OrderApp.medium, OrderApp.large});
        topPanel.add(pizzaSizeLabel);
        topPanel.add(pizzaSizeComboBox);

        headerPanel.add(darkModePanel, BorderLayout.WEST);
        headerPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Use FlowLayout with vertical alignment and reduced vertical gap (10)
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/resources/bubble.jpeg")); // Adjust the path as needed
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxWidth = 400; 
        int maxHeight = 235; 
        Image scaledImage = image.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
                
        JLabel greetingLabel1 = new JLabel("Welcome to Bubble Boulevard!");
        JLabel greetingLabel2 = new JLabel("Enjoy our delicious bubble teas!");

        Font font = new Font("Helvetica", Font.BOLD | Font.ITALIC, 20); // Adjust font settings as needed
        greetingLabel1.setFont(font);
        greetingLabel2.setFont(font);

        JPanel greetingPanel = new JPanel();
        greetingPanel.setLayout(new GridLayout(2, 1)); // Two rows for the greeting messages
        greetingPanel.add(greetingLabel1);
        greetingPanel.add(greetingLabel2);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(greetingPanel, BorderLayout.NORTH);
        containerPanel.add(imagePanel, BorderLayout.CENTER);
        
        centerPanel.add(containerPanel);
        centerPanel.add(imagePanel);
        
        JPanel toppingsPanel = new JPanel(new GridLayout(0, 1));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder(OrderApp.toppings)); 
        Font checkboxFont = new Font("Arial", Font.BOLD , 20); 
        toppingsCheckBoxes = new JCheckBox[]{
                new JCheckBox(OrderApp.Boba),
                new JCheckBox(OrderApp.Grass_Jelly),
                new JCheckBox(OrderApp.Oreo_Crumbs),
                new JCheckBox(OrderApp.Fruit_Jelly),
                new JCheckBox(OrderApp.Aloe_Jelly)
        };
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            checkBox.setFont(checkboxFont); 
            toppingsPanel.add(checkBox);
            
        }

        JPanel pizzaVarietyPanel = new JPanel(new GridLayout(0, 1));
        pizzaVarietyPanel.setBorder(BorderFactory.createTitledBorder("Drinks Variety")); 
        
        Font radioButtonFont = new Font("Arial", Font.BOLD, 20); 
        thai_iced_tea = new JRadioButton("Thai Iced Tea");
        matcha_latte = new JRadioButton("Matcha Latte");
        taro_milk_tea = new JRadioButton("Taro Milk Tea"); 
        hong_kong_mile_tea = new JRadioButton("Hong Kong Milk Tea");
        honey_milk_tea = new JRadioButton("Honey Milk Tea");

        thai_iced_tea.setFont(radioButtonFont);
        matcha_latte.setFont(radioButtonFont);
        taro_milk_tea.setFont(radioButtonFont);
        hong_kong_mile_tea.setFont(radioButtonFont);
        honey_milk_tea.setFont(radioButtonFont);
        
        ButtonGroup pizzaVarietyGroup = new ButtonGroup();
        pizzaVarietyGroup.add(thai_iced_tea);
        pizzaVarietyGroup.add(matcha_latte);
        pizzaVarietyGroup.add(taro_milk_tea);
        pizzaVarietyGroup.add(hong_kong_mile_tea);
        pizzaVarietyGroup.add(honey_milk_tea);

        pizzaVarietyPanel.add(thai_iced_tea);
        pizzaVarietyPanel.add(matcha_latte);
        pizzaVarietyPanel.add(taro_milk_tea);
        pizzaVarietyPanel.add(hong_kong_mile_tea);
        pizzaVarietyPanel.add(honey_milk_tea);

        centerPanel.add(toppingsPanel);
        centerPanel.add(Box.createHorizontalStrut(30)); 
        centerPanel.add(pizzaVarietyPanel);
        
        JPanel bottomPanel = new JPanel();
        calculateButton = new JButton(OrderApp.calculateButtonLabel);
        resetButton = new JButton(OrderApp.resetButtonLabel);
        basketButton = new JButton("Basket");
        orderButton = new JButton("Order");

        bottomPanel.add(calculateButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(basketButton);
        bottomPanel.add(orderButton);

        JPanel numberOfPizzasPanel = new JPanel();
        JLabel numberOfPizzasLabel = new JLabel("Number of Drinks:");
        numberOfPizzasField = new JTextField(5);
        numberOfPizzasPanel.add(numberOfPizzasLabel);
        numberOfPizzasPanel.add(numberOfPizzasField);

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(numberOfPizzasPanel, BorderLayout.NORTH);
        centerContainer.add(mainPanel, BorderLayout.CENTER);
        centerContainer.add(scrollPane, BorderLayout.EAST);

        add(centerContainer, BorderLayout.CENTER);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        basketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToBasket();
            }
        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

    } 
    

    private void addToBasket() {
        String selectedPizza = getSelectedPizzaVariety();
        List<String> selectedToppings = getSelectedToppings();

        if (!selectedPizza.isEmpty() && !selectedToppings.isEmpty()) {
            int numberOfPizzas = 1; 
            try {
                numberOfPizzas = Integer.parseInt(numberOfPizzasField.getText());
            } catch (NumberFormatException e) {
            }

            double itemPrice = calculateItemPrice(selectedPizza, selectedToppings);
            itemPrice *= numberOfPizzas; 

            DrinksOrder pizzaOrder = new DrinksOrder(selectedPizza, selectedToppings, numberOfPizzas);
            pizzaOrder.setItemPrice(itemPrice);

            selectedItems.add(pizzaOrder); 

            updateBasketDisplay();

            clearSelection();
        }
    }
  
    
    private void updateBasketDisplay() {
        resultTextArea.setText("");

        double totalOrderPrice = 0.0;
        for (DrinksOrder pizzaOrder : selectedItems) {
            resultTextArea.append("Drinks: " + pizzaOrder.getPizzaVariety() + "\n" +
                    "Toppings: " + String.join(", ", pizzaOrder.getToppings()) + "\n" +
                    "Number of Drinks: " + pizzaOrder.getNumberOfPizzas() + "\n" +
                    "Price: $" + pizzaOrder.getItemPrice() + "\n\n");
            totalOrderPrice += pizzaOrder.getItemPrice();
        }

        resultTextArea.append("Total Price for Selected Items: $" + totalOrderPrice + "\n\n");
    }

    
    
    private double calculateItemPrice(String pizzaVariety, List<String> toppings) {

        double basePrice = 0.0; 
        double toppingsPrice = toppings.size() * 1.50; 

        return basePrice + toppingsPrice;
    }
    
    private void placeOrder() {
        double totalOrderPrice = 0.0;
        for (DrinksOrder pizzaOrder : selectedItems) {
            totalOrderPrice += pizzaOrder.getItemPrice();
        }

        resultTextArea.append("\nThank you for your order! Your total price is: $" + totalOrderPrice);
        try{
        Firestore db = new InitializerFirestore().getDB();
        Map<String, Object> user = new HashMap<>();
        user.put("receipt",resultTextArea.getText());
        CollectionReference orderCollectionReference = db.collection("Orders");
        ApiFuture<DocumentReference> future = orderCollectionReference.add(user);
        future.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        selectedItems.clear();
    }

    
    private String getSelectedPizzaVariety() {
        JRadioButton[] pizzaVarietyButtons = new JRadioButton[]{
            thai_iced_tea, taro_milk_tea, matcha_latte, hong_kong_mile_tea, honey_milk_tea};
        for (JRadioButton button : pizzaVarietyButtons) { 	
            if (button != null && button.isSelected()) {
                return button.getText();
            }
        }
        return ""; 
    }

    
    private void clearSelection() {
        pizzaSizeComboBox.setSelectedIndex(0);
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            checkBox.setSelected(false);
        }
        numberOfPizzasField.setText("");
    }

    private void updateDarkMode(Component component) {
        if (isDarkMode) {
            component.setBackground(Color.BLACK);
            component.setForeground(Color.WHITE);
        } else {
            component.setBackground(Color.WHITE);
            component.setForeground(Color.BLACK);
        }

        if (component instanceof Container) {
            Component[] components = ((Container) component).getComponents();
            for (Component child  : components) {
                updateDarkMode(child);
            }
        }
    }

    private void updateDarkMode() {
        updateDarkMode(this); 
    }


    private void calculateTotal() {
        String selectedSize = (String) pizzaSizeComboBox.getSelectedItem();

        int numberOfPizzas = 0;
        try {
            numberOfPizzas = Integer.parseInt(numberOfPizzasField.getText());
        } catch (NumberFormatException e) {
            resultTextArea.setText("Invalid number of Drinks");
            return;
        }

        double basePrice = 0.0;
        if (selectedSize.equals(OrderApp.small)) {
            basePrice = 5.99;
        } else if (selectedSize.equals(OrderApp.medium)) {
            basePrice = 7.99;
        } else if (selectedSize.equals(OrderApp.large)) {
            basePrice = 9.99;
        }

        double toppingsPrice = 0.0;
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                toppingsPrice += 1.50;
            }
        }

        double totalPrice = (basePrice + toppingsPrice) * numberOfPizzas;

        resultTextArea.setText("Drinks Size: " + selectedSize + "\n" +
                "Toppings: " + getSelectedToppings() + "\n" +
                "Number of Drinks: " + numberOfPizzas + "\n" +
                "Total Price: $" + totalPrice);
    }

    private List<String> getSelectedToppings() {
        List<String> selectedToppings = new ArrayList<>();
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedToppings.add(checkBox.getText());
            }
        }
        return selectedToppings;
    }


    private void resetForm() {
        pizzaSizeComboBox.setSelectedIndex(0);
        for (JCheckBox checkBox : toppingsCheckBoxes) {
            checkBox.setSelected(false);
        }
        resultTextArea.setText("");
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Drinks Order App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        DrinksPage pizzaPage = new DrinksPage();

        frame.getContentPane().add(pizzaPage);

        frame.setVisible(true);
    }

}
