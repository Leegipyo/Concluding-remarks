import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
	public static void main(String[] args) throws IOException, SQLException {
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;

		server = new ServerSocket(9999); // 9999를 포트번호로 지정하여 서버열음.
		System.out.println("서버를 오픈");
		System.out.println("-----클라이언트 접속 대기-----");

		socket = server.accept(); // 클라이언트에서 서버로 접속하는것을 대기
		System.out.println("클라이언트에서 서버로 접속을 하였습니다.");
		System.out.println("-------------------------------------");

		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("클라이언트에서 id,password를 전송합니다.");
		// 클라이언트로부터 데이터를 읽어옴
		String command = br.readLine();
		if (command.equals("1")) {
			pw.println("기존 회원의 로그인 입니다.");
			String id = br.readLine();
			String password = br.readLine();
			// 받은 데이터 확인을 위한 출력문 작성
			System.out.println("클라이언트에서 전송한 ID: " + id);
			System.out.println("클라이언트에서 전송한 PassWord: " + password);
			String sql = "select * from user where id = ? and password = ?";
			try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, id);
				stmt.setString(2, password);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						id.equals(rs.getString("id"));
						password.equals(rs.getString("password"));

						pw.println("로그인이 성공적으로 완료되었습니다."); // 로그인 성공시 서버에서 클라이언트로 전송
					} else {
						pw.println("해당 ID,비밀번호가 틀렸습니다."); // 로그인 실패시 서버에서 클라이언트로 전송
					}
				}
			}
		} else if (command.equals("2")) { // 단어 입력을 받았을시 처리해야하는 부분
			pw.println("클라이언트에서 서버로 단어를 전송합니다");
			pw.flush(); // 버퍼 비움
			String word = br.readLine();
			System.out.println("클라우드에서 전송한 단어 : " + word);
			WordAPI api = new WordAPI();
			api.getTotalValue(word); // 1이 반환 되면 유효한단어 , -1이면 유효하지 않은 단어
			pw.println("단어 유효성 검사 완료");
			
		}
	}
}
