import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel lUrl, lSettings, lLoginName, lPassName, lLogin, lPlik;
	JTextField tfLoginName, tfPassName, tfLogin, tfUrl;
	JButton fileChooser, bAtak;
	File file;

	public MyFrame() {
		super("Brute Force");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 340);
		setLocation(50, 50);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		lUrl = new JLabel("Adres url do strony:");
		lUrl.setBounds(70, 50, 125, 25);
		add(lUrl);
		lSettings = new JLabel("USTAWIENIA");
		lSettings.setBounds(135, 10, 200, 25);
		lSettings.setFont(new Font(null, Font.BOLD, 20));
		add(lSettings);
		JLabel lLine = new JLabel("---------------------------------------------------");
		lLine.setBounds(70, 142, 280, 25);
		lLine.setFont(new Font(null, Font.PLAIN, 16));
		add(lLine);
		lLoginName = new JLabel("Nazwa dla pola login:");
		lLoginName.setBounds(70, 80, 125, 25);
		add(lLoginName);
		lPassName = new JLabel("Nazwa dla pola haslo:");
		lPassName.setBounds(70, 110, 125, 25);
		add(lPassName);
		lLogin = new JLabel("Podaj swoj login:");
		lLogin.setBounds(70, 180, 125, 25);
		add(lLogin);
		lPlik = new JLabel("Wybierz slownik [txt] ");
		lPlik.setBounds(70, 210, 125, 25);
		add(lPlik);
		tfLoginName = new JTextField();
		tfLoginName.setText("login");
		tfLoginName.setBounds(205, 80, 125, 25);
		add(tfLoginName);
		tfPassName = new JTextField();
		tfPassName.setText("haslo");
		tfPassName.setBounds(205, 110, 125, 25);
		add(tfPassName);
		tfLogin = new JTextField();
		tfLogin.setText("");
		tfLogin.setBounds(205, 180, 125, 25);
		add(tfLogin);
		tfUrl = new JTextField();
		tfUrl.setText("http://localhost/brute_force/index.php");
		tfUrl.setBounds(205, 50, 125, 25);
		add(tfUrl);
		fileChooser = new JButton("Brak pliku");
		fileChooser.setBounds(205, 210, 125, 25);
		add(fileChooser);
		fileChooser.addActionListener(this);
		bAtak = new JButton("Rozpocznij atak");
		bAtak.setBounds(70, 260, 260, 40);
		bAtak.addActionListener(this);
		add(bAtak);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bAtak) {

			bAtak.setForeground(Color.RED);
			bAtak.setText("Atakuje...");

			List<String> passwords = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {

				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					passwords.add(sCurrentLine);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			try {
				String result = Connect.atack(tfUrl.getText(), tfLogin.getText(), passwords);
				bAtak.setText(result);
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}
		if (e.getSource() == fileChooser) {
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				fileChooser.setText(file.getName());

			}
		}

	}

}
