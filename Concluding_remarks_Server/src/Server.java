import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private static List<ClientHandler> list;

	public static void main(String[] args) throws IOException {
		list = new ArrayList<>();
		try (ServerSocket server = new ServerSocket(9999)) {
			System.out.println("서버를 오픈");
			System.out.println("-----클라이언트 접속 대기-----");
			while (true) {
				Socket socket = server.accept();
				System.out.println("클라이언트번호 " + socket + "접속완료.");

				ClientHandler clientHandler = new ClientHandler(socket);
				Thread clientThread = new Thread(clientHandler);
				list.add(clientHandler);
				clientThread.start();
			}
		}
	}

	static class ClientHandler implements Runnable {
		private Socket socket;
		private BufferedReader br;
		private PrintWriter pw;

		public ClientHandler(Socket socket) {
			this.socket = socket;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				if (br == null || pw == null) {
					System.out.println("BufferedReader 또는 PrintWriter가 null입니다.");
					return;
				}

				while (true) {
					String command = br.readLine();
					System.out.println("확인:" + command);
					if (command.equals("1")) {
						handleLogin();
					} else if (command.equals("2")) {
						handleWordInput();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void handleLogin() throws IOException, SQLException {
			pw.println("기존 회원의 로그인입니다.");
			pw.flush();
			String id = br.readLine();
			String password = br.readLine();

			System.out.println("클라이언트에서 전송한 ID: " + id);
			System.out.println("클라이언트에서 전송한 Password: " + password);

			String sql = "SELECT * FROM user WHERE id = ? AND password = ?";
			try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.setString(2, password);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						pw.println("로그인이 성공적으로 완료되었습니다.");
						pw.flush();
					} else {
						pw.println("해당 ID, 비밀번호가 틀렸습니다.");
						pw.flush();
					}
				}
			}
		}

		Character lastChar = ' '; // 시작단어를 공백으로 설정

		private void handleWordInput() throws IOException, SQLException {
			pw.println("클라이언트에서 서버로 단어를 전송합니다");
			pw.flush();
			String word = br.readLine();
			System.out.println(socket + "클라이언트에서 전송한 단어: " + word);
			if ((lastChar == word.charAt(0) || lastChar == ' ') && word.length() >= 2) { // 마지막 문자와 클라이언트에서 전송한 문자가 동일하면
				System.out.println("끝말 매칭 완료됨.");
				String sql = "SELECT * FROM word WHERE word = ?";
				try (Connection conn = DBConnection.getConnection();
						PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, word);
					try (ResultSet rs = stmt.executeQuery()) {
						if (rs.next()) {
							int doubleCheckValue = rs.getInt("double_check");
							if (doubleCheckValue == 0) {
								pw.println("중복된 단어는 사용할 수 없습니다.");
								pw.flush();
							} else if (doubleCheckValue == 1) {
								String updateSQL = "UPDATE word SET double_check = 0 WHERE word = ?";
								try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
									updateStmt.setString(1, word);
									updateStmt.executeUpdate();
								}
								pw.println(word); // 유효한 단어 입력시 작성한 단어 다시 클라이언트로 전송
								int lastIndex = word.length() - 1;
								lastChar = word.charAt(lastIndex);
								pw.flush();
							}
						} else {
							WordAPI api = new WordAPI();
							int returnValue = api.getTotalValue(word);
							System.out.println("리턴값 확인 " + returnValue);
							if (returnValue == -1) {
								String insertSQL = "INSERT INTO word (word, double_check) VALUES (?, ?)";
								try (PreparedStatement insert = conn.prepareStatement(insertSQL)) {
									insert.setString(1, word);
									insert.setInt(2, 0);
									insert.executeUpdate();

									pw.println(word);// 유효한 단어 입력시 작성한 단어 다시 클라이언트로 전송
									int lastIndex = word.length() - 1;
									lastChar = word.charAt(lastIndex);
									pw.flush();
								}
							} else if (returnValue == 0) {
								pw.println("유효하지 않는 단어입니다. 다시 입력하세요.");
								pw.flush();
							}
						}
					}
				}
			} else if (2 < word.length()) {
				pw.println("한글자 사용 불가능.");
			} else {
				System.out.println("끝말매칭 실패");
				pw.println("끝말이 맞지 않습니다 다시 단어를 입력하세요.");
				pw.flush();
			}
		}
	}
}
