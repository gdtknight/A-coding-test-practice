import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class FailRate {
  public static int[] solution(int N, int[] stages) {
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

  public static void main(String[] args) {
    int[] stages = new int[] { 2, 1, 2, 6, 2, 4, 3, 3 };
    // int[] result = solution(5, stages); // result = {3,4,2,1,5}
    // System.out.println(Arrays.toString(result));

    stages = new int[] { 4, 4, 4, 4, 4 };
    int[] result = solution(4, stages); // result = {4,1,2,3}
    System.out.println(Arrays.toString(result));
  }
}
