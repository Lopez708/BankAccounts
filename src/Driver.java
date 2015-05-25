import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The driver class creates the gui that the user will be interacting with in order to enter bank account information
 * @author Juan Lopez
 *
 */
public class Driver extends JFrame {
	private final int WIDTH = 350;
	private final int HEIGHT = 260;
	private JPanel topPanel = new JPanel();
	private JLabel accNumLabel;
	private JTextField accNumBox;
	private JLabel initBal;
	private JTextField initBalBox;
	private JLabel annualRate;
	private JTextField annualRateBox;
	private JRadioButton saving;
	private JRadioButton checking;
	private JPanel midPanel = new JPanel();
	private JPanel midPanelTop = new JPanel();
	private JPanel midPanelBottom = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private Container contentPane;
	private JButton saveAccount;
	private JButton deposit;
	private JButton withdraw;
	private JButton monthlyProcess;
	private JButton generateReport;
	private JButton exit;	
	private double amount;
	private int accNum;
	private int accType = 1;
	BankAccountManager bAccounts = new BankAccountManager();

	/**
	 * This is the constructor for the class.  It sets the GUI's layout and adds the panels and buttons onto the content pane.
	 * The constructor also calls the Button Listener classes in order to handle user interaction of buttons and radio buttons.
	 * @throws IOException - Exception thrown when the input .jpg logo cannot be found.
	 */
	public Driver() throws IOException {
		setTitle("Bank Account");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		buildTopPanel();
		add(topPanel, BorderLayout.NORTH);
		buildBottomPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		buildMidPanel();
		add(midPanel, BorderLayout.CENTER);
		saveAccount.addActionListener(new SaveAccountListener());
		deposit.addActionListener(new DepositListener());
		withdraw.addActionListener(new WithdrawListener());
		monthlyProcess.addActionListener(new MonthlyProcessListener());
		generateReport.addActionListener(new GenerateReportListener());
		exit.addActionListener(new ExitButtonListener());
		saving.addActionListener(new RadioButtonListener());
		checking.addActionListener(new RadioButtonListener());
	}
	
	/**
	 * The method buildTopPanel initializes the top panel with the bank account radio buttons.
	 * @throws IOException - Exception thrown when the input .jpg logo cannot be found.
	 */
	private void buildTopPanel() throws IOException {
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 12));
		topPanel.setBorder(BorderFactory.createTitledBorder("Account Type"));
		topPanel.setPreferredSize(new Dimension(0, 70));
		saving = new JRadioButton ("Saving");
		checking = new JRadioButton ("Checking");
		topPanel.add(saving);
		topPanel.add(checking);
	}
	
	/**
	 * The method buildMidPanel initializes the middle panel labels and text fieds.
	 */
	private void buildMidPanel() {
		midPanel.setLayout(new BorderLayout());
		midPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 6));
		accNumLabel = new JLabel("Account Number ");
		accNumBox = new JTextField(10);
		initBal = new JLabel("             Initial Balance ");
		initBalBox = new JTextField(12);
		annualRate = new JLabel("                Annual Rate ");
		annualRateBox = new JTextField(12);
		midPanel.add(midPanelTop, BorderLayout.CENTER);
		midPanelTop.add(accNumLabel, BorderLayout.CENTER);
		midPanelTop.add(accNumBox, BorderLayout.CENTER);
		midPanelTop.add(initBal, BorderLayout.CENTER);
		midPanelTop.add(initBalBox, BorderLayout.CENTER);
		midPanelTop.add(annualRate, BorderLayout.CENTER);
		midPanelTop.add(annualRateBox, BorderLayout.CENTER);
		midPanel.add(midPanelBottom, BorderLayout.SOUTH);
	}
	
	/**
	 * The method buildBottomPanel initializes the 6 buttons needed for the bottom panel and sets the panel on the southern part of the content pane.
	 */
	private void buildBottomPanel() {
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setPreferredSize(new Dimension(0, 65));
		saveAccount = new JButton("Save Account");
		deposit = new JButton("Deposit");
		withdraw = new JButton("Withdraw");
		monthlyProcess = new JButton("Monthly Process");
		generateReport = new JButton("Generate Report");
		exit = new JButton("Exit");
		bottomPanel.add(saveAccount);
		bottomPanel.add(deposit);
		bottomPanel.add(withdraw);
		bottomPanel.add(monthlyProcess);
		bottomPanel.add(generateReport);
		bottomPanel.add(exit);
		
	}
	
	/**
	 * The purpose of the class SaveAccountListener is to handle the interaction of the user with the save account button.
	 */
	private class SaveAccountListener implements ActionListener {
		
		/**
		 * actionPerformed handles the logic and action of the saveAccount button.  Uses data entered in text boxes and adds the new account.
		 */
		public void actionPerformed(ActionEvent e) {
			
			if (accType == 1) {
				if (accNumBox.getText().equals("") || initBalBox.getText().equals("") || annualRateBox.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Please enter necessary data", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				bAccounts.addAccount(1, Integer.parseInt(accNumBox.getText()), Double.parseDouble(initBalBox.getText()), Double.parseDouble(annualRateBox.getText()));
				accNumBox.setText("");
				initBalBox.setText("");
				annualRateBox.setText("");
			} else if (accType == 2) {
				if (accNumBox.getText().equals("") || initBalBox.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Please enter necessary data", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				bAccounts.addAccount(2, Integer.parseInt(accNumBox.getText()), Double.parseDouble(initBalBox.getText()), 0);
				accNumBox.setText("");
				initBalBox.setText("");
			}
		}
	}
		
		/**
		 * The purpose of the class DepositListener is to handle the interaction of the user with the deposit button.
		 */
		private class DepositListener implements ActionListener {
			
			/**
			 * actionPerformed handles the logic and action of the deposit button.  It adds the given amount to the given account.
			 */
			public void actionPerformed(ActionEvent e) {
				
				amount = Double.parseDouble(JOptionPane.showInputDialog(contentPane, "Enter Amount: ", "Input", JOptionPane.QUESTION_MESSAGE));
				accNum = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Enter Account Number: ", "Input", JOptionPane.QUESTION_MESSAGE));
				bAccounts.credit(accNum, amount);
			}
		}
		
		/**
		 * The purpose of the class WithdrawListener is to handle the interaction of the user with the withdraw button.
		 */
		private class WithdrawListener implements ActionListener {
			
			/**
			 * actionPerformed handles the logic and action of the NewTeam button.  It subtracts the given amount to the given account.
			 */
			public void actionPerformed(ActionEvent e) {
				
				amount = Double.parseDouble(JOptionPane.showInputDialog(contentPane, "Enter Amount: ", "Input", JOptionPane.QUESTION_MESSAGE));
				accNum = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Enter Account Number: ", "Input", JOptionPane.QUESTION_MESSAGE));
				bAccounts.debit(accNum, amount);
			}
		}
		
		/**
		 * The purpose of the class MonthlyProcessListener is to handle the interaction of the user with the monthlyProcess button.
		 */
		private class MonthlyProcessListener implements ActionListener {
			
			/**
			 * actionPerformed handles the logic and action of the monthly process button.  It runs the monthlyProcess method for each bank account, savings or checkings respectively.
			 */
			public void actionPerformed(ActionEvent e) {
				bAccounts.startMonthlyProcess();
			}
		}
		
		/**
		 * The purpose of the class GenerateReportListener is to handle the interaction of the user with the generate report button.
		 */
		private class GenerateReportListener implements ActionListener {
			
			/**
			 * actionPerformed handles the logic and action of the users interaction with the generate report button.  Displays a window with the current bank accounts information.
			 */
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, bAccounts.generateAccountReport(), "Message", JOptionPane.PLAIN_MESSAGE);								
			}
		}
		/**
		 * The purpose of the class ExitButtonListener is to handle the interaction of the user with the exit button.
		 */
		private class ExitButtonListener implements ActionListener {
			
			/**
			 * actionPerformed handles the logic and action of the users interaction with the exit button.  Terminates the application
			 */
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		}
		
		/**
		 * The purpose of the class RadioButtonListener is to handle the interaction of the user with the GUI's Account type radio buttons.
		 */
		private class RadioButtonListener implements ActionListener {
			
			/**
			 * actionPerformed handles the logic and action of the users interaction with the radio buttons.  It allows only one of the radio buttons to be selected at a time 
			 * and assigns account type.
			 */
			public void actionPerformed(ActionEvent e) {
				
				String radioAction = e.getActionCommand();
				
				if (radioAction.equals("Saving")) {
					checking.setSelected(false);
					accType = 1;
					annualRate.setVisible(true);
					annualRateBox.setVisible(true);
				} else if (radioAction.equals("Checking")) {
					saving.setSelected(false);
					annualRate.setVisible(false);
					annualRateBox.setVisible(false);
					accType = 2;
				}
			}
		}

	public static void main(String[] args) throws IOException {
		Driver gui = new Driver();
		gui.setVisible(true);
	}
}
