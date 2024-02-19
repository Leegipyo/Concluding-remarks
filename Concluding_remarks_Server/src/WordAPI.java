import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class WordAPI {
	public int getTotalValue(String word) {
		try {
			String key = "4F568F0B880583B7E82510AAFA533017";
			Scanner scanner = new Scanner(System.in);
			System.out.print("단어를 입력하세요: ");
			scanner.close();

			URL url = new URL("https://stdict.korean.go.kr/api/search.do?key=" + key + "&type_search=search&q=" + word
					+ "&req_type=json");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			// JSON 문자열을 JSONObject로 파싱
			JSONObject jsonObject = new JSONObject(response.toString());

			// "channel" 객체에서 "total" 값을 추출
			int total = jsonObject.getJSONObject("channel").getInt("total");

			if (total > 0) {
				System.out.println("total 값이 1 이상입니다.");
				return 1;
			} else {
				System.out.println("없는 단어입니다.");
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // 오류 발생 시 -1을 반환
		}
	}

}