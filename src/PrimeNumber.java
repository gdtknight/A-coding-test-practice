
public class PrimeNumber {
  public static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    } else {
      for (int i = 2; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
          return false;
        }
      }
      return true;
    }
  }

  public static int solution1(int n) {
    int answer = 0;

    for (int i = 1; i < n + 1; i++) {
      if (isPrime(i)) {
        answer++;
      }
    }

    return answer;
  }

  public static int solution2(int n) {
    int answer = 0;
    boolean[] arr = new boolean[n + 1];

    for (int i = 2; i < n + 1; i++) {
      arr[i] = true;
    }

    for (int i = 2; i < n + 1; i++) {
      if (arr[i]) {
        int cnt = 3;
        for (int j = i * 2; j < n + 1; j = i * cnt++) {
          arr[j] = false;
        }
      } else {
        continue;
      }
    }

    for (int i = 1; i < n + 1; i++) {
      if (arr[i]) {
        answer++;
      }
    }

    return answer;
  }

  public static int solution3(int n) {
    int answer = 0;
    boolean[] arr = new boolean[n + 1];

    for (int i = 2; i < n + 1; i++) {
      arr[i] = true;
    }

    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (arr[i]) {
        int cnt = 3;
        for (int j = i * 2; j < n + 1; j = i * cnt++) {
          arr[j] = false;
        }
      } else {
        continue;
      }
    }

    for (int i = 1; i < n + 1; i++) {
      if (arr[i]) {
        answer++;
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    int K = 10_000_000;
    // int M = Integer.MAX_VALUE / 2;
    // int N = Integer.MAX_VALUE;

    double beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    int answer = solution1(K);
    double afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    double secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + answer);
    System.out.println("시간차이(ms) : " + secDiffTime);

    System.out.println();

    beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    answer = solution2(K);
    afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + answer);
    System.out.println("시간차이(ms) : " + secDiffTime);

    System.out.println();

    beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    answer = solution3(K);
    afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + answer);
    System.out.println("시간차이(ms) : " + secDiffTime);
  }
}
