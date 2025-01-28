package GUI;

public class ResultScreen extends javax.swing.JFrame {

    public ResultScreen(String studentName, int score) {
        initComponents(); // Initialize the GUI components
        setLocationRelativeTo(null);
        setTitle("Score");
        setNameAndScore(studentName, score); // Set custom data

    }
    public void setNameAndScore(String studentName, int score) {
    nameJLabel.setText(studentName + "!"); // Update the name label
    ScoreJLabel.setText(String.valueOf(score)); // Convert the score to a String and set it
}

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ExitjButton1 = new javax.swing.JButton();
        nameJLabel = new javax.swing.JLabel();
        ScoreJLabel = new javax.swing.JLabel();
        PlayAgainJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/score.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ExitjButton1.setBackground(new java.awt.Color(0, 204, 255));
        ExitjButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        ExitjButton1.setText("Exit");
        ExitjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitjButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(ExitjButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 130, 40));

        nameJLabel.setFont(new java.awt.Font("Bodoni MT", 1, 24)); // NOI18N
        nameJLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameJLabel.setText("Name!");
        nameJLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 180, 50));

        ScoreJLabel.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        ScoreJLabel.setForeground(new java.awt.Color(255, 255, 255));
        ScoreJLabel.setText("score");
        ScoreJLabel.setName("score"); // NOI18N
        getContentPane().add(ScoreJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 60, 40));

        PlayAgainJButton.setBackground(new java.awt.Color(0, 204, 0));
        PlayAgainJButton.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        PlayAgainJButton.setText("Play Again!");
        PlayAgainJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayAgainJButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PlayAgainJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 130, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/score.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitjButton1ActionPerformed
        // TODO add your handling code here:
        ExitjButton1.addActionListener(e -> System.exit(0));
    }//GEN-LAST:event_ExitjButton1ActionPerformed

    private void PlayAgainJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayAgainJButtonActionPerformed
        // TODO add your handling code here:
        // Close the current ResultScreen
        this.dispose();

        // Open the LoginForm
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }//GEN-LAST:event_PlayAgainJButtonActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitjButton1;
    private javax.swing.JButton PlayAgainJButton;
    private javax.swing.JLabel ScoreJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nameJLabel;
    // End of variables declaration//GEN-END:variables
}
