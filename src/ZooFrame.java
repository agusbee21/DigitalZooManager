import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZooFrame extends JFrame {
    private ArrayList<Animal> animals = new ArrayList<>();

    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> typeComboBox;
    private JTextField furColorField;
    private JCheckBox canFlyCheckBox;

    private JLabel furColorLabel;
    private JLabel canFlyLabel;

    private JButton addButton;
    private JTextArea logArea;

    public ZooFrame() {
        super("Digital Zoo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form panel (NORTH)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Type:"));
        typeComboBox = new JComboBox<>(new String[]{"Generic Mammal", "Generic Bird"});
        typeComboBox.addActionListener(e -> refreshForm());
        formPanel.add(typeComboBox);

        furColorLabel = new JLabel("Fur Color:");
        formPanel.add(furColorLabel);
        furColorField = new JTextField();
        formPanel.add(furColorField);

        canFlyLabel = new JLabel("Can Fly?");
        formPanel.add(canFlyLabel);
        canFlyCheckBox = new JCheckBox("Can Fly?");
        formPanel.add(canFlyCheckBox);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add Animal");
        addButton.addActionListener(e -> onAddAnimal());
        buttonPanel.add(addButton);

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.add(formPanel, BorderLayout.CENTER);
        topWrapper.add(buttonPanel, BorderLayout.SOUTH);
        add(topWrapper, BorderLayout.NORTH);

        // Log area (CENTER)
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Zoo Log"));
        add(scrollPane, BorderLayout.CENTER);

        refreshForm();
    }

    private void refreshForm() {
        boolean isMammal = typeComboBox.getSelectedIndex() == 0;
        furColorLabel.setVisible(isMammal);
        furColorField.setVisible(isMammal);
        canFlyLabel.setVisible(!isMammal);
        canFlyCheckBox.setVisible(!isMammal);
    }

    private void onAddAnimal() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        boolean isMammal = typeComboBox.getSelectedIndex() == 0;

        // Validasi nama
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi umur
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (age < 0) {
            JOptionPane.showMessageDialog(this, "Age cannot be negative!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Animal animal;
        String typeName;
        if (isMammal) {
            String furColor = furColorField.getText().trim();
            if (furColor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fur Color cannot be empty for Mammal!",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            animal = new Mammal(name, age, furColor);
            typeName = "Mammal";
        } else {
            boolean canFly = canFlyCheckBox.isSelected();
            animal = new Bird(name, age, canFly);
            typeName = "Bird";
        }

        animals.add(animal);
        logArea.append("Added a new " + typeName + "! Info: " + animal.getInfo()
                + ". Sound: " + animal.makeSound() + "\n");

        // Kosongkan form
        nameField.setText("");
        ageField.setText("");
        furColorField.setText("");
        canFlyCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ZooFrame().setVisible(true));
    }
}
