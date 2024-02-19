import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame {
	private JTextField id;
	private JTextField password;
	private ActionListener loginListener;

	public Login() {
		Scanner scan = new Scanner(System.in);
		JPanel pnl = new JPanel();
		JLabel lblId = new JLabel("ID");
		id = new JTextField(10);
		JLabel lblpassowrd = new JLabel("PassWord");
		password = new JTextField(10);
		JButton btnlogin = new JButton("로그인");
		// 로그인 버튼에 액션 리스너 추가
		btnlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginListener != null) {
					loginListener.actionPerformed(e);
				}
			}
		});

		pnl.add(lblId);
		pnl.add(id);
		pnl.add(lblpassowrd);
		pnl.add(password);
		pnl.add(btnlogin);

		add(pnl);

		showGUI();
	}

	private void showGUI() {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	// ID 반환 메소드
	public String getID() {
		return id.getText();
	}

	// 비밀번호 반환 메소드
	public String getPassword() {
		return password.getText();
	}

	// 로그인 버튼 액션 리스너 설정 메소드
	public void setLoginListener(ActionListener listener) {
		this.loginListener = listener;
	}
}
