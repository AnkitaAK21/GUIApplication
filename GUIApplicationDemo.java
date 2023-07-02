package sdmcet.cse.oop; //the package used is sdmcet.cse.oop

import java.awt.event.ActionEvent; //importing the awt subclasses and swing to use them as interfaces

import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

class CIEException extends Exception {
	public String toString() {
		return "IA Marks should be between 0 and 20 and CTA between 0 and 10";
	}
}

class SEEException extends Exception {
	public String toString() {
		return "SEE Marks should be between 0 and 100";
	}
}

class SEEEligibilityCriteriaException extends Exception {
	public String toString() {
		return "Student is detained from taking SEE";
	}
}

class GUI extends JFrame implements ActionListener { // extends helps in inheriting and implements keyword for using
	// interfaces
	Container contentpane1, contentpane2; // declaring a container
	JButton b; // buttons,labels and TextFields used are from JFrame
	JPanel p, p1, p2, p3, p4, p5, p6, p7;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JTextField t1, t2, t3, t4, t5;
	private int ia1, ia2, ia3, cta, see; // instance variables

	GUI(String title) { // parameterized constructor
		super(title); // inheriting JFrame
		contentpane1 = this.getContentPane(); // initializing the components
		contentpane2 = this.getContentPane();

		b = new JButton("Calculate");
		p = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		p7 = new JPanel();
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		t4 = new JTextField(20);
		t5 = new JTextField(20);
		l1 = new JLabel("Total Marks :                  ");
		l2 = new JLabel("Grade : ");
		l3 = new JLabel("Grade Calculator");
		l4 = new JLabel("Enter IA-1 Marks: ");
		l5 = new JLabel("Enter IA-2 Marks: ");
		l6 = new JLabel("Enter IA-3 Marks: ");
		l7 = new JLabel("Enter CTA Marks: ");
		l8 = new JLabel("Enter SEE MArks: ");

		b.addActionListener(this); // adding the components to particular panels
		p2.add(l3);
		l3.setFont(new Font("Arial", Font.PLAIN, 23));
		p3.add(l4);
		p3.add(t1);
		p4.add(l5);
		p4.add(t2);
		p5.add(l6);
		p5.add(t3);
		p6.add(l7);
		p6.add(t4);
		p7.add(l8);
		p7.add(t5);
		p.add(b);

		p1.add(l1);
		l1.setAlignmentX(LEFT_ALIGNMENT);
		p1.add(l2);
		l2.setAlignmentX(RIGHT_ALIGNMENT);

		this.setLayout(new GridLayout(8, 0));
		contentpane1.add(p2); // adding the panels to containers

		contentpane2.add(p3);
		contentpane2.add(p4);
		contentpane2.add(p5);
		contentpane2.add(p6);
		contentpane2.add(p7);
		contentpane2.add(p);
		contentpane2.add(p1);

	}

	int cie() { // method to calculate cie
		ia1 = Integer.parseInt(t1.getText());
		ia2 = Integer.parseInt(t2.getText());
		ia3 = Integer.parseInt(t3.getText());
		cta = Integer.parseInt(t4.getText());
		int cie = 0;

		if ((ia1 >= 0 && ia1 <= 20) && (ia2 >= 0 && ia2 <= 20) && (ia3 >= 0 && ia3 <= 20) && (cta >= 0 && cta <= 10)) {
			if (ia1 >= ia2 && ia3 >= ia2) {
				cie = ia1 + ia3 + cta;
			} else if (ia1 >= ia3 && ia2 >= ia3) {
				cie = ia1 + ia2 + cta;
			} else {
				cie = ia2 + ia3 + cta;
			}
		} else {
			try { // Exception Handling
				throw new CIEException();
			} catch (CIEException ce) {
				l1.setText("ERROR:" + ce);

			}
		}
		return cie;
	}

	int totalMarks() { // Calculating total marks
		see = Integer.parseInt(t5.getText());
		int total = 0;
		if (see >= 0 && see <= 100) {
			if (see == 38)
				see += 1;

			if (see % 2 != 0)
				total = (see + 1) / 2;
			else
				total = see / 2;

		} else {
			try { // Exception handling
				throw new SEEException();
			} catch (SEEException se) {
				l1.setText("ERROR:" + se);

			}
		}
		return cie() + total;

	}

	char grades() { // Grade calculation
		if (totalMarks() >= 90 && totalMarks() <= 100)
			return 'S';
		else if (totalMarks() >= 80 && totalMarks() <= 89)
			return 'A';
		else if (totalMarks() >= 70 && totalMarks() <= 79)
			return 'B';
		else if (totalMarks() >= 60 && totalMarks() <= 69)
			return 'C';
		else if (totalMarks() >= 50 && totalMarks() <= 59)
			return 'D';
		else if (totalMarks() >= 40 && totalMarks() <= 49)
			return 'E';
		else
			return 'F';

	}

	@Override
	public void actionPerformed(ActionEvent e) { // overriding from ActionListener
		// TODO Auto-generated method stub

		try {
			if (cie() < 20) { // condition for handling Exceptions
				throw new SEEEligibilityCriteriaException();
			} else {
				l1.setText("Total Marks : " + totalMarks() + "             ");
				l2.setText("Grade : " + grades());
			}
		} catch (SEEEligibilityCriteriaException sece) {

			l8.setText("SEE not allowed");
			l2.setText("ERROR:" + sece);
		}
	}

}

public class GUIApplicationDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new GUI("Student Grading System"); // Using Dynamic method Dispatch for referring Jframe

		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // to exit frame when clicked on close(X)
		f.setBounds(400, 250, 700, 500); // defining the size and padding of the frame
		f.setVisible(true); // set the visibility++
	}

}
