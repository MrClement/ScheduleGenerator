package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class PersonalScheduleGenerator extends JFrame implements ActionListener {

	private String upperSchoolString = "Upper School";
	private String middleSchoolString = "Middle School";
	private String currentSchoolType = upperSchoolString;

	public PersonalScheduleGenerator() {
		initUI();

	}

	private void initUI() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JRadioButton schoolTypeUpper = new JRadioButton(upperSchoolString);
		schoolTypeUpper.setActionCommand(upperSchoolString);
		schoolTypeUpper.setSelected(true);

		JRadioButton schoolTypeMiddle = new JRadioButton(middleSchoolString);
		schoolTypeUpper.setActionCommand(middleSchoolString);

		ButtonGroup schoolType = new ButtonGroup();
		schoolType.add(schoolTypeUpper);
		schoolType.add(schoolTypeMiddle);

		schoolTypeUpper.addActionListener(this);
		schoolTypeMiddle.addActionListener(this);

		add(schoolTypeUpper);
		add(schoolTypeMiddle);

		JTextArea period1 = new JTextArea("Period 1");
		period1.setLineWrap(true);
		period1.setBounds(20, 20, 100, 10);

		JTextArea period2 = new JTextArea("Period 2");
		period1.setLineWrap(true);
		period1.setBounds(20, 20, 100, 10);
		JTextArea period3 = new JTextArea("Period 3");
		period1.setLineWrap(true);
		period1.setBounds(20, 20, 100, 10);

		JTextArea period4 = new JTextArea("Period 4");
		period1.setLineWrap(true);
		period1.setBounds(20, 20, 100, 10);
		JTextArea period5 = new JTextArea("Period 5");
		period1.setLineWrap(true);
		period1.setBounds(20, 20, 100, 10);

		add(period1);
		add(period2);
		add(period3);
		add(period4);
		add(period5);

		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(50, 60, 80, 30);
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(currentSchoolType);
				System.exit(0);
			}
		});
		add(quitButton);

		setTitle("Simple Example");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				PersonalScheduleGenerator ex = new PersonalScheduleGenerator();
				ex.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentSchoolType = e.getActionCommand();

	}
}
