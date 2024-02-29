import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameRule extends JFrame {
	private JTextField 게임방법_게임설명창;
	private ActionListener btnGameRuleBcak;

	public GameRule() {
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		getContentPane().setLayout(null);
		setTitle("게임 방법");

		게임방법_게임설명창 = new JTextField();
		게임방법_게임설명창.setText("게임 설명란");
		게임방법_게임설명창.setBounds(57, 101, 426, 327);
		getContentPane().add(게임방법_게임설명창);
		게임방법_게임설명창.setColumns(10);
		게임방법_게임설명창.setEditable(false);

		JButton btn게임방법_뒤로가기 = new JButton("뒤로가기");
		btn게임방법_뒤로가기.setBounds(475, 528, 97, 23);
		getContentPane().add(btn게임방법_뒤로가기);

		btn게임방법_뒤로가기.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnGameRuleBcak != null) {
					btnGameRuleBcak.actionPerformed(e);
				}
			}
		});

	}

	// 게임룰_뒤로가기 버튼 액션 리스너 설정 메소드
	public void setGameRuleBackListener(ActionListener listener) {
		this.btnGameRuleBcak = listener;

	}
}
