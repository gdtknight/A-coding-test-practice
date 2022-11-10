
// https://school.programmers.co.kr/learn/courses/30/lessons/42889

// package programmers.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class _42889_FailRate {
  public static int[] mySolution(int N, int[] stages) {
    int userCnt = stages.length; // 해당 스테이지를 통과한 유저수
    int[] arrivedCnt = new int[N + 1]; // 해당 스테이지에 도착한 유저수
    HashMap<Integer, Double> failRate = new HashMap<>(); // 해당 스테이지 실패율 = 해당 스테이지 도착 유저수 / 해당 스테이지 통과 유저수

    for (int stage : stages) {
      if (stage <= N) {
        arrivedCnt[stage]++;
      }
    }

    for (int stage = 1; stage <= N; stage++) {
      if (userCnt != 0) {
        failRate.put(stage, (double) arrivedCnt[stage] / (double) userCnt);
      } else {
        failRate.put(stage, 0.0);
      }

      userCnt -= arrivedCnt[stage];

      // System.out.printf("[%2d Stage] 도착유저수: %2d, 통과한 유저수: %2d, 실패율: %.04f",
      // stage,
      // arrivedCnt[stage], userCnt,
      // failRate.get(stage));
      // System.out.println();
    }

    ArrayList<Map.Entry<Integer, Double>> entryList = new ArrayList<>(failRate.entrySet());
    entryList.sort(new Comparator<Map.Entry<Integer, Double>>() {
      public int compare(Map.Entry<Integer, Double> e1, Map.Entry<Integer, Double> e2) {
        return e2.getValue() == e1.getValue()
            ? e1.getKey().compareTo(e2.getKey())
            : e2.getValue().compareTo(e1.getValue());
        // return e2.getValue().compareTo(e1.getValue());
      }
    });

    int[] answer = new int[N];

    int idx = 0;
    for (Map.Entry<Integer, Double> entry : entryList) {
      answer[idx++] = entry.getKey();
    }

    return answer;
  }

  public static int[] soheenimSolution(int N, int[] stages) {
    Map<Integer, Double> stagesMap = new HashMap<>();

    int[] answer = new int[N];
    int winner = 0;
    int fail = 0;
    double f_w = 0;

    for (int n = 1; n < N + 1; n++) {
      for (int j = 0; j < stages.length; j++) {
        // 1이면 1보다 같거나 크거나
        if (stages[j] >= n) {
          winner += 1;
          if (stages[j] == n) {
            fail += 1;
          }
        }
      }

      f_w = (double) fail / winner;
      if (winner == 0) {
        f_w = 0;
      }
      stagesMap.put(n, f_w);
      winner = 0;
      fail = 0;
    }

    List<Map.Entry<Integer, Double>> list_entries = new ArrayList<Map.Entry<Integer, Double>>(stagesMap.entrySet());

    Collections.sort(list_entries, new Comparator<Map.Entry<Integer, Double>>() {
      // compare로 값을 비교
      public int compare(Map.Entry<Integer, Double> obj1, Map.Entry<Integer, Double> obj2) {
        // 내림 차순으로 정렬
        return obj2.getValue().compareTo(obj1.getValue());
      }
    });

    int indx = 0;
    while (indx < answer.length) {
      for (Map.Entry<Integer, Double> entry : list_entries) {
        answer[indx] = entry.getKey();
        indx++;
      }
    }

    return answer;
  }

  public static int[] dongyoungnimSolution(int N, int[] stages) {
    int[] answer = new int[N]; // 답
    double[] failRate = new double[N]; // 실패율
    int countUser = stages.length; // 각 스테이지별 전체 유저수 계산을 위한 변수
    int user = 0; // 스테이지에 머무르고 있는 유저수
    int allUser = 0; // 전체 유저수

    for (int i = 0; i < N; i++) {
      allUser = countUser;
      for (int j = 0; j < stages.length; j++) {
        int stage = i + 1;
        if (stages[j] == stage) {
          user++;
          countUser--;
        }
      }

      failRate[i] = (double) user / allUser;
      user = 0;
    }

    for (int i = 0; i < N; i++) {
      // 실패율 비교해서 순위 정하기(인덱스 계산)
      int rank = 0;
      for (int j = 0; j < N; j++) {
        if (failRate[i] < failRate[j] && i != j) {
          rank++;
        }
      }

      // 같은 순위일 경우 rank가 동일할 때 다음 자리를 찾아서 저장
      // [3,4,2,1,5] -> 3 = 0.5, 4 = 0.5 실패율 동률
      // ex) answer[0] = 3, 4가 올 수 있는데 먼저 저장된 3이 존재하면 answer[rank] != 0 조건에 해당해서 rank
      // 1증가(여기서 rank는 인덱스)
      // answer[1] = i + 1 = 4
      while (true) {
        if (answer[rank] != 0) {
          rank++;
          continue;
        }
        answer[rank] = i + 1; // 순위 저장 1부터 ... N
        break;
      }
    }
    return answer;
  }

  public static void main(String[] args) {
    Random random = new Random();
    int N = 500; // N은 1이상 500 이하 자연수이다.
    int[] stages = new int[200_000]; // stages의 길이는 1이상 200_000 이하이다.

    for (int i = 0; i < stages.length; i++) {
      stages[i] = random.nextInt(1, N + 2); // stages 에는 1 이상 N+1 이하의 자연수가 담겨있다.
    }

    System.out.println("======== 소희님 방법 ========");
    double beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    int[] answer = soheenimSolution(N, stages);
    double afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    double secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + Arrays.toString(answer));
    System.out.println("시간차이(ms) : " + secDiffTime);

    System.out.println();

    System.out.println("======== 동영님 방법 ========");
    beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    answer = dongyoungnimSolution(N, stages);
    afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + Arrays.toString(answer));
    System.out.println("시간차이(ms) : " + secDiffTime);

    System.out.println();

    System.out.println("======== 개선한 방법 ========");
    beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
    answer = mySolution(N, stages);
    afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

    secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

    System.out.println("Answer :" + Arrays.toString(answer));
    System.out.println("시간차이(ms) : " + secDiffTime);
  }
}
