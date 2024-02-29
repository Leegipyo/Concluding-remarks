import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyPage extends JFrame {
	private JLabel MyPage_ID;
	private JLabel MyPage_SingleScore;
	private JLabel MyPage_Winner_Count;
	private ActionListener exitMypage;

	public MyPage() {
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		getContentPane().setLayout(null);
		setTitle("마이페이지");

		JButton btn마이페이지_뒤로가기 = new JButton("뒤로가기");
		btn마이페이지_뒤로가기.setBounds(475, 528, 97, 23);
		getContentPane().add(btn마이페이지_뒤로가기);

		btn마이페이지_뒤로가기.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (exitMypage != null) {
					exitMypage.actionPerformed(e);
				}
			}
		});

		JLabel lbl닉네임 = new JLabel("닉네임");
		lbl닉네임.setBounds(47, 61, 57, 15);
		getContentPane().add(lbl닉네임);

		JLabel lbl싱글_최고점수 = new JLabel("싱글 최고점수");
		lbl싱글_최고점수.setBounds(47, 150, 89, 15);
		getContentPane().add(lbl싱글_최고점수);

		JLabel lbl멀티우승횟수 = new JLabel("멀티 우승 횟수");
		lbl멀티우승횟수.setBounds(47, 248, 89, 15);
		getContentPane().add(lbl멀티우승횟수);

		MyPage_ID = new JLabel("");
		MyPage_ID.setBounds(159, 58, 116, 21);
		getContentPane().add(MyPage_ID);

		MyPage_SingleScore = new JLabel("");
		MyPage_SingleScore.setBounds(159, 147, 116, 21);
		getContentPane().add(MyPage_SingleScore);

		MyPage_Winner_Count = new JLabel("");
		MyPage_Winner_Count.setBounds(159, 245, 116, 21);
		getContentPane().add(MyPage_Winner_Count);

	}

	public void setMyPageID(String userID) {
		this.MyPage_ID.setText(userID);
	}

	public void setMyPageSingleScore(String MyPage_SingleScore) {
		this.MyPage_SingleScore.setText(MyPage_SingleScore);
	}

	public void setMultiWinnerCount(String MyPage_Winner_count) {
		this.MyPage_Winner_Count.setText(MyPage_Winner_count);
	}

	public void setExitMypage(ActionListener listener) {
		this.exitMypage = listener;
	}

	public String getMyPageID() {
		return MyPage_ID.getText();
	}

	public String getMyPageSingleScore() {
		return MyPage_SingleScore.getText();
	}

	public String getMyPageMultiWinnerCount() {
		return MyPage_Winner_Count.getText();
	}

}
