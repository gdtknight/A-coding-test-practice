import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
// nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.

public class PrimeTest {
  public static void main(String[] args) {
    for (int rount = 0; rount < 5; rount++) {
      System.out.println();
      System.out.println();
      System.out.println("=============== " + (rount + 1) + " =============");
      int[] nums = IntStream.range(1, 1001).toArray();
      // int -> List
      List<Integer> intList = Arrays.stream(nums).boxed().collect(Collectors.toList());

      // System.out.println("nums: " + intList);
      Collections.shuffle(intList);
      nums = intList.subList(0, 50).stream().mapToInt(Integer::intValue).toArray();
      Set<Integer> set = new HashSet<Integer>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
      System.out.println("nums: " + Arrays.toString(nums));
      System.out.println("length: " + nums.length + ", set size: " + set.size());

      System.out.println();
      System.out.println();

      // for (int i = 0; i < 50; i++) {
      // nums[i] = (int) Math.random() * 20;
      // }

      System.out.println("======== 제곱근 판별법 ========");
      double beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
      int answer = solution1(nums);
      double afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

      double secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

      System.out.println("Answer :" + answer);
      System.out.println("시간차이(ms) : " + secDiffTime);
      System.out.println();

      System.out.println("======== 소희님 방법 ========");
      beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
      answer = solution2(nums);
      afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

      secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

      System.out.println("Answer :" + answer);
      System.out.println("시간차이(ms) : " + secDiffTime);
      System.out.println();

      System.out.println("======== 제곱근 판별법 + 해시셋 ========");
      beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
      answer = solution3(nums);
      afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기

      secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

      System.out.println("Answer :" + answer);
      System.out.println("시간차이(ms) : " + secDiffTime);
    }
  }

  public static int solution1(int[] nums) {
    int answer = 0;

    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = 0; j < nums.length - 1; j++) {
        for (int k = 0; k < nums.length; k++) {
          if (isPrime(nums[i] + nums[j] + nums[k])) {
            answer += 1;
          }
        }
      }
    }

    return answer;
  }

  public static int solution2(int[] nums) {
    int answer = 0;
    int sum = 0;

    Map<Integer, Boolean> primeNumberCheck = new HashMap<>();

    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          sum = nums[i] + nums[j] + nums[k];

          if (primeNumberCheck.containsKey(sum)) {
            if (primeNumberCheck.get(sum)) {
              answer += 1;
            }
          } else {
            if (isPrime(sum)) {
              primeNumberCheck.put(sum, true);
              answer += 1;
            } else {
              primeNumberCheck.put(sum, false);
            }
          }
        }
      }
    }
    return answer;
  }

  public static int solution3(int[] nums) {
    int answer = 0;
    Set<Integer> primeSet = new HashSet<>();
    Set<Integer> nonPrimeSet = new HashSet<>();

    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (primeSet.contains(nums[i] + nums[j] + nums[k])) {
            answer += 1;
          } else if (nonPrimeSet.contains(nums[i] + nums[j] + nums[k])) {
          } else {
            if (isPrime(nums[i] + nums[j] + nums[k])) {
              primeSet.add(nums[i] + nums[j] + nums[k]);
              answer += 1;
            } else {
              nonPrimeSet.add(nums[i] + nums[j] + nums[k]);
            }
          }
        }
      }
    }
    return answer;
  }

  public static boolean isPrime(int num) {
    if (num < 2) {
      return false;
    } else {
      for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          return false;
        }
      }
      return true;
    }
  }
}
