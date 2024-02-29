import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private ActionListener btnJoinMembership;
	private ActionListener btnfindID;
	private ActionListener btnfindPW;

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

		getContentPane().add(pnl);

		JButton btn로그인화면_회원가입 = new JButton("회원가입");
		btn로그인화면_회원가입.setBounds(389, 170, 125, 23);
		pnl.add(btn로그인화면_회원가입);

		btn로그인화면_회원가입.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnJoinMembership != null) {
					btnJoinMembership.actionPerformed(e);
				}
			}
		});

		JButton btn아이디찾기 = new JButton("아아디 찾기");
		btn아이디찾기.setBounds(389, 208, 125, 23);
		pnl.add(btn아이디찾기);

		btn아이디찾기.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnfindID != null) {
					btnfindID.actionPerformed(e);
				}
			}
		});

		JButton btn비밀번호찾기 = new JButton("비밀번호 찾기");
		btn비밀번호찾기.setBounds(389, 246, 125, 23);
		pnl.add(btn비밀번호찾기);

		btn비밀번호찾기.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnfindPW != null) {
					btnfindPW.actionPerformed(e);
//				findPW fpw = new findPW();
//				fpw.setVisible(true);
//				setVisible(false);
				}
			}
		});

		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnlogin.doClick();
				}
			}
		});

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

	// 회원가입 버튼 액션 리스너 설정 메소드
	public void setJoinMembershipListener(ActionListener listener) {
		this.btnJoinMembership = listener;
	}

	// 아아디 찾기 버튼 액션 리스너 설정 메소드
	public void setFindIDListener(ActionListener listener) {
		this.btnfindID = listener;
	}

	// 비밀번호 찾기 액션 리스너 설정 메소드
	public void setFindPassWordListener(ActionListener listener) {
		this.btnfindPW = listener;
	}
}
