import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SingleMode extends JFrame {
	private JTextField 싱글_CPU가주는단어;
	private JTextField 싱글_나의단어;
	private JLabel 싱글_나의프로필;
	private ActionListener exitSingleMode;
	private ActionListener sendWord;
	private JLabel timerLabel;
	private JProgressBar progressBar;
	private Timer timer;

	public SingleMode() {
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		getContentPane().setLayout(null);
		setTitle("싱글모드");

		JLabel lbl싱글모드 = new JLabel("싱글모드");
		lbl싱글모드.setBounds(12, 10, 57, 15);
		getContentPane().add(lbl싱글모드);

		JButton btn싱글모드_게임종료 = new JButton("게임종료");
		btn싱글모드_게임종료.setBounds(475, 10, 97, 23);
		getContentPane().add(btn싱글모드_게임종료);

		btn싱글모드_게임종료.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(SingleMode.this, "게임을 종료하시겠습니까?", "게임 종료",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					stopTimer(); // 타이머 중지
					resetTimer(); // 타이머 초기화

					if (exitSingleMode != null) {
						exitSingleMode.actionPerformed(e);
					}
				}
			}
		});

		싱글_CPU가주는단어 = new JTextField();
		싱글_CPU가주는단어.setText("PC가 주는 단어 창");
		싱글_CPU가주는단어.setBounds(74, 76, 419, 127);
		getContentPane().add(싱글_CPU가주는단어);
		싱글_CPU가주는단어.setColumns(10);

		싱글_나의단어 = new JTextField();
		싱글_나의단어.setText("나의 단어");
		싱글_나의단어.setBounds(137, 350, 274, 69);
		getContentPane().add(싱글_나의단어);
		싱글_나의단어.setColumns(10);

		싱글_나의프로필 = new JLabel();
		싱글_나의프로필.setText("나의 프로필");
		싱글_나의프로필.setBounds(202, 489, 116, 21);
		getContentPane().add(싱글_나의프로필);

		JButton btnSendWord = new JButton("단어전송");
		btnSendWord.setBounds(423, 373, 97, 23);
		getContentPane().add(btnSendWord);
		btnSendWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopTimer(); // 타이머 중지
				resetTimer(); // 타이머 초기화
				startTimer(); // 타이머 시작

				if (sendWord != null) {
					sendWord.actionPerformed(e);
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

//		startTimer(); // 초기에 타이머를 시작
//
//		// 창이 닫힐 때 타이머를 중지하고 초기화
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				stopTimer(); // 타이머 중지
//				resetTimer(); // 타이머 초기화
//			}
//		});
	}

	public void setExitSingleMode(ActionListener listener) {
		this.exitSingleMode = listener;
	}

	public void setSingleSendWord(ActionListener listener) {
		this.sendWord = listener;
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

	public String getSendWord() {
		return 싱글_나의단어.getText();
	}
}
