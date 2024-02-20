import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("192.168.0.99", 9999); // 접속할 서버의 아이피와 포트 번호 작성.
		System.out.println("서버에 접속이 완료 되었습니다.");
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream());

		Login login = new Login();

		// 로그인 버튼 액션 리스너 설정
		login.setLoginListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					pw.println(1); // 기존회원의 로그인일 경우 서버에 1(int)를 전송.
					pw.flush();
					if (br.readLine().equals("기존 회원의 로그인 입니다.")) {
						pw.println(login.getID()); // 로그인 클래스에서 ID 가져온후 서버에 ID를 전송
						pw.println(login.getPassword()); // 로그인 클래스에서 비밀번호 가져온후 서버에 PassWord 전송
						pw.flush(); // 버퍼 비우기
						System.out.println(br.readLine());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		wordInput wordinput = new wordInput();
		wordinput.setsendWordListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					pw.println(2); // 단어를 서버에 전송할경우 서버에 2를 전송
					pw.flush();
					if (br.readLine().equals("클라이언트에서 서버로 단어를 전송합니다")) {
						pw.println(wordinput.getInsertword());
						pw.flush();
						System.out.println(br.readLine());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}