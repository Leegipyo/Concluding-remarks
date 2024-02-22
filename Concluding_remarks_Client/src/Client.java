import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		new Client().start();
	}

	public synchronized void start() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.0.99", 9999);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());

			System.out.println("서버에 연결됨.");
			System.out.println("서버에 성공적으로 연결되었습니다.");

			Login login = new Login();
			login.setLoginListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						pw.println(1);
						pw.flush();
						if (br.readLine().equals("기존 회원의 로그인입니다.")) {
							pw.println(login.getID());
							pw.println(login.getPassword());
							pw.flush();
							System.out.println(br.readLine());
							// 로그인 이 완료 되었을때 그다음 GUI 띄우기
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

			// 이 스래드가 시작 하는 시점에 맞춰서 작성을 해야한다.
			Thread wordTxRx = new Thread(new WordTxRx(pw, br));
			wordTxRx.start();
			wordTxRx.join(); // 스래드가 시작될때 까지 대기 하는 메소드
			// 두 스레드가 모두 종료될 때까지 대기
			wait();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---- EXIT ----");
	}

	private static class WordTxRx implements Runnable {
		private PrintWriter pw;
		private BufferedReader br;

		public WordTxRx(PrintWriter pw, BufferedReader br) {
			this.pw = pw;
			this.br = br;
		}

		@Override
		public void run() {
// 다른 런 메소드로 옮겨야함.
			wordInput wordInput = new wordInput();
			wordInput.setsendWordListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pw.println(2);
					pw.flush();
					try {
						if (br.readLine().equals("클라이언트에서 서버로 단어를 전송합니다")) {
							pw.println(wordInput.getInsertword());
							pw.flush();
							System.out.println(br.readLine());
						} else if (br.readLine().equals("끝말이 맞지 않습니다 다시 단어를 입력하세요.")) {
							System.out.println("단어를 다시 입력하세요.");
						} else if (br.readLine().equals("한글자 사용 불가능.")) {
							System.out.println("한글자 단어는 사용이 불가능합니다.");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}
}
