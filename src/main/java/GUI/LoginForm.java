package GUI;
import Service.StudentService;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();  // Initializes the GUI components
        setLocationRelativeTo(null);
        setTitle("Login");
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    reg_num.requestFocus();  // Move focus to the registration number field
                }
            }
        });
        reg_num.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();  // Trigger login action on Enter key press
                }
            }
        });
        Login.addActionListener(e -> handleLogin());  // Handle login button click
    }
    private void handleLogin() {
        String name = this.name.getText().trim();
        String regNo = this.reg_num.getText().trim();

        // Check if both fields are filled
        if (name.isEmpty() || regNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Instantiate DatabaseHelper (or any service class that validates the login)
        StudentService service = new StudentService();
        boolean isValid = service.validateUser(name, regNo);  // Assuming validateUser is a method in StudentService

        if (isValid) {
            JOptionPane.showMessageDialog(this, "Welcome, " + name + "!");
            new StartQuiz(name, regNo).setVisible(true);  // Open the Quiz Time frame
            dispose();  // Close the current frame
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Login = new javax.swing.JButton();
        name = new javax.swing.JTextField();
        reg_num = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 60, 40));

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registration Number:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 230, 40));

        Login.setBackground(new java.awt.Color(0, 255, 255));
        Login.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        Login.setForeground(new java.awt.Color(0, 0, 0));
        Login.setText("Login");
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 326, 100, -1));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 170, 30));

        reg_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reg_numActionPerformed(evt);
            }
        });
        getContentPane().add(reg_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 170, 30));

        jLabel3.setFont(new java.awt.Font("Stencil", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Enter your Credentials!");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 290, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void validateAndFocus(javax.swing.JTextField currentField, javax.swing.JTextField nextField, String errorMessage) {
        String enteredText = currentField.getText().trim();
        if (enteredText.isEmpty()) {
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            currentField.requestFocus();
        } else {
            nextField.requestFocus();
        }
    }
    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        validateAndFocus(name, reg_num, "Name field cannot be empty!");
    }//GEN-LAST:event_nameActionPerformed

    private void reg_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reg_numActionPerformed
        if (name.getText().trim().isEmpty() || reg_num.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            if (name.getText().trim().isEmpty()) {
                name.requestFocus();
            } else {
                reg_num.requestFocus();
            }
        } else {
            handleLogin();
        }
    }//GEN-LAST:event_reg_numActionPerformed
                             
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField name;
    private javax.swing.JTextField reg_num;
    // End of variables declaration//GEN-END:variables
}
