package pironeer.util;

import java.io.BufferedReader; // 입력 속도 증가
import java.io.IOException; // 버퍼 사용에 따른 예외 처리 시 사용
import java.io.InputStreamReader; // 바이트 스트림(System.in)을 문자 스트림으로 변환
import java.util.StringTokenizer; // 문자열을 특정 구분자로 나누어 단어(토큰) 단위로 처리

public class Reader {
    BufferedReader br;
    StringTokenizer st;

    public Reader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    // 입력을 각각 int, long, double 타입으로 변환하는 메서드
    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        String str = ""; // 빈 문자열
        try {
            str = br.readLine(); // 문자열 한 줄 받기
        } catch (IOException e) {
            e.printStackTrace(); // 예외 발생 시 오류 출력
        }
        return str; // 입력받은 문자열 반환
    }
}
