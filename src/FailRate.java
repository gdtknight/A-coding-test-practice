import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class FailRate {

  public static int[] solution(int N, int[] stages) {
    HashMap<Integer, Integer> passCnt = new HashMap<>();
    HashMap<Integer, Integer> arrivedCnt = new HashMap<>();
    HashMap<Integer, Double> failRate = new HashMap<>();

    for (int i = 1; i <= N; i++) {
      passCnt.put(i, 0);
      arrivedCnt.put(i, 0);
    }

    for (int stage : stages) {
      for (int i = 1; i <= N; i++) {
        if (i < stage) {
          passCnt.put(i, passCnt.getOrDefault(i, 0) + 1);
        } else if (i == stage) {
          arrivedCnt.put(i, arrivedCnt.getOrDefault(i, 0) + 1);
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      double numo = arrivedCnt.getOrDefault(i, 0);
      double deno = passCnt.getOrDefault(i, 0) + arrivedCnt.getOrDefault(i, 0);
      if (deno != 0 && numo != 0) {
        failRate.put(i, numo / deno + (-0.0000001 * (double) i));
      } else {
        failRate.put(i, -1.0 * (double) i);
      }
    }

    PriorityQueue<Map.Entry<Integer, Double>> pq = new PriorityQueue<>(
        (x, y) -> x.getValue() == y.getValue()
            ? Integer.compare(x.getKey(), y.getKey())
            : Double.compare(y.getValue(), x.getValue()));

    for (Map.Entry<Integer, Double> entry : failRate.entrySet()) {
      pq.add(entry);
    }

    int[] answer = new int[N];

    for (int i = 0; i < N; i++) {
      answer[i] = pq.poll().getKey();
    }

    return answer;
  }

  public static int[] solution2(int N, int[] stages) {
    HashMap<Integer, Integer> passCnt = new HashMap<>();
    HashMap<Integer, Integer> arrivedCnt = new HashMap<>();
    // HashMap<Integer, Double> failRate = new HashMap<>();
    Double[][] failRate = new Double[N + 1][2];

    for (int stage : stages) {
      for (int i = 1; i <= N; i++) {
        if (i < stage) {
          passCnt.put(i, passCnt.getOrDefault(i, 0) + 1);
        } else if (i == stage) {
          arrivedCnt.put(i, arrivedCnt.getOrDefault(i, 0) + 1);
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      double numo = arrivedCnt.get(i);
      double deno = passCnt.get(i) + arrivedCnt.get(i);
      if (deno != 0 && numo != 0) {
        failRate[i][0] = numo / deno;
        failRate[i][1] = (double) i;
      } else {
        failRate[i][0] = 0.0;
        failRate[i][1] = (double) i;
      }
    }

    // 2차월 배열 정렬 0번쨰 열 다음 1번째 열 기준(다중 배열 정렬)
    Arrays.sort(failRate, new Comparator<Double[]>() {
      @Override
      public int compare(Double[] o1, Double[] o2) {
        if (o1[0] == o2[0]) {
          return Double.compare(o1[1], o1[1]);
        } else {
          return Double.compare(o1[0], o1[0]);
        }
      }
    });

    int[] answer = new int[N];

    for (int i = 0; i < N; i++) {
      answer[i] = failRate[i + 1][1].intValue();
    }

    return answer;
  }

  public static int[] solution4(int N, int[] stages) {
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

    System.out.println(answer[0]);

    return answer;
  }

  public static void main(String[] args) {
    int[] stages = new int[] { 2, 1, 2, 6, 2, 4, 3, 3 };
    int[] result = solution(5, stages); // result = {3,4,2,1,5}
    System.out.println(Arrays.toString(result));
    // result = solution2(5, stages); // result = {4,1,2,3}
    // System.out.println(Arrays.toString(result));

    stages = new int[] { 4, 4, 4, 4, 4 };
    result = solution(4, stages); // result = {4,1,2,3}
    System.out.println(Arrays.toString(result));
    // result = solution2(4, stages); // result = {4,1,2,3}
    // System.out.println(Arrays.toString(result));
  }
}
