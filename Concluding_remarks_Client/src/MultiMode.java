import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class MultiMode extends JFrame {
	private JLabel 멀티_PC가주는단어;
	private JLabel Multi_timer;
	private JLabel Multi_user1;
	private JLabel Multi_user2;
	private JLabel Multi_user3;
	private TextField Multi_UserInput;
	private ActionListener exitMulti;
	private ActionListener wordSend;
	private JLabel timerLabel;
	private JProgressBar progressBar;
	private Timer timer;

	public MultiMode() {

		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		getContentPane().setLayout(null);
		setTitle("멀티 모드");

		JLabel lbl멀티모드 = new JLabel("멀티 모드");
		lbl멀티모드.setBounds(12, 10, 57, 15);
		getContentPane().add(lbl멀티모드);

		JButton btn멀티모드_게임종료 = new JButton("게임종료");
		btn멀티모드_게임종료.setBounds(475, 10, 97, 23);
		getContentPane().add(btn멀티모드_게임종료);

		btn멀티모드_게임종료.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(MultiMode.this, "게임을 종료하시겠습니까?", "게임 종료",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					stopTimer(); // 타이머 중지
					resetTimer(); // 타이머 초기화

					if (exitMulti != null) {
						exitMulti.actionPerformed(e);
					}
				}
			}
		});

		멀티_PC가주는단어 = new JLabel();
		멀티_PC가주는단어.setBackground(Color.LIGHT_GRAY);
		멀티_PC가주는단어.setText("이전 단어 하나");
		멀티_PC가주는단어.setBounds(121, 88, 316, 100);
		getContentPane().add(멀티_PC가주는단어);

		Multi_timer = new JLabel();
		Multi_timer.setBounds(41, 234, 499, 21);
		getContentPane().add(Multi_timer);

		Multi_user1 = new JLabel("");
		Multi_user1.setBackground(Color.PINK);
		FlowLayout fl_유저1_패널 = (FlowLayout) Multi_user1.getLayout();
		Multi_user1.setBounds(40, 487, 136, 64);
		getContentPane().add(Multi_user1);
		System.out.println(Client.list.toString());

		Multi_user2 = new JLabel();
		Multi_user2.setText("");
		Multi_user2.setBounds(215, 487, 137, 64);
		getContentPane().add(Multi_user2);

		Multi_user3 = new JLabel();
		Multi_user3.setText("");
		Multi_user3.setBounds(388, 487, 137, 64);
		getContentPane().add(Multi_user3);

		Multi_UserInput = new TextField(20);
		Multi_UserInput.setText("유저들이 쓴 단어");
		Multi_UserInput.setBounds(144, 297, 249, 88);
		getContentPane().add(Multi_UserInput);

		JButton btnSendButton = new JButton("단어전송");
		btnSendButton.setBounds(296, 395, 97, 23);
		getContentPane().add(btnSendButton);

		btnSendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopTimer(); // 타이머 중지
				resetTimer(); // 타이머 초기화
				startTimer(); // 타이머 시작

				if (wordSend != null) {
					wordSend.actionPerformed(e);
				}
			}
		});

		// 타이머
		timerLabel = new JLabel("10");
		timerLabel.setOpaque(true);
		timerLabel.setForeground(Color.BLUE);
		timerLabel.setFont(new Font("맑은고딕", Font.PLAIN, 20));
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		timerLabel.setBounds(20, 230, 492, 23);
		getContentPane().add(timerLabel);

		progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(41, 260, 492, 23);
		getContentPane().add(progressBar);

		startTimer(); // 초기에 타이머를 시작

		// 창이 닫힐 때 타이머를 중지하고 초기화
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stopTimer(); // 타이머 중지
				resetTimer(); // 타이머 초기화
			}
		});
	}

	// 타이머를 시작하는 메소드
	private void startTimer() {
		timer = new Timer(100, new ActionListener() {
			int count = 100;

			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				if (count >= 0) {
					timerLabel.setText(Integer.toString(count / 10));
					progressBar.setValue(count);
				} else {
					timer.stop();
				}
			}
		});
		timer.start();
	}

	// 타이머를 중지하는 메소드
	private void stopTimer() {
		if (timer != null) {
			timer.stop();
		}
	}

	// 타이머를 초기화하는 메소드
	private void resetTimer() {
		timerLabel.setText("10");
		progressBar.setValue(100);
	}

	public String getMulti_user1() {
		return Multi_user1.getText();
	}

	public void setMulti_user1(String userID) {
		this.Multi_user1.setText(userID);
	}

	public String getMulti_user2() {
		return Multi_user2.getText();
	}

	public void setMulti_user2(String userID) {
		this.Multi_user2.setText(userID);
	}

	public String getMulti_user3() {
		return Multi_user3.getText();
	}

	public void setMulti_user3(String userID) {
		this.Multi_user2.setText(userID);
	}

	public String getMulti_UserInput() {
		return Multi_UserInput.getText();
	}

	public ActionListener getExitMulti() {
		return exitMulti;
	}

	public void setExitMulti(ActionListener listener) {
		this.exitMulti = listener;
	}

	public void setWordSendListner(ActionListener wordSend) {
		this.wordSend = wordSend;
	}

}
