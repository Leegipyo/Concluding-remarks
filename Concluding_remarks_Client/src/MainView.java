import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class MainView extends JFrame {
	private ActionListener selectMode;
	private ActionListener myPage;
	private ActionListener gameExplanation;
	private ActionListener mainLogout;

	public MainView() {

		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		getContentPane().setLayout(null);
		setTitle("메인화면");

		JButton btn모드선택 = new JButton("모드 선택");
		btn모드선택.setBounds(63, 132, 110, 23);
		getContentPane().add(btn모드선택);

		btn모드선택.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectMode != null) {
					selectMode.actionPerformed(e);
				}
			}
		});

		JButton btn마이페이지 = new JButton("마이페이지");
		btn마이페이지.setBounds(63, 188, 110, 23);
		getContentPane().add(btn마이페이지);

		btn마이페이지.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myPage != null) {
					myPage.actionPerformed(e);
				}
			}

		});

		JButton btn게임방법 = new JButton("게임 방법");
		btn게임방법.setBounds(63, 245, 110, 23);
		getContentPane().add(btn게임방법);

		btn게임방법.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameExplanation != null) {
					gameExplanation.actionPerformed(e);
				}
			}
		});

		JButton btn메인화면_로그아웃 = new JButton("로그아웃");
		btn메인화면_로그아웃.setBounds(475, 10, 97, 23);
		getContentPane().add(btn메인화면_로그아웃);

		btn메인화면_로그아웃.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainLogout != null) {
					mainLogout.actionPerformed(e);
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setForeground(Color.GRAY);
		panel.setBounds(261, 132, 272, 339);
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("여기는 이미지가 들어갑니다");
		panel.add(lblNewLabel);
	}

	public void setSelectMode(ActionListener listener) {
		this.selectMode = listener;
	}

	public void setMypage(ActionListener listener) {
		this.myPage = listener;
	}

	public void setGameExplanation(ActionListener listener) {
		this.gameExplanation = listener;
	}

	public void setMainLogout(ActionListener listener) {
		this.mainLogout = listener;
	}
}
