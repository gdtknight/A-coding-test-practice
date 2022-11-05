
import java.util.HashMap;
import java.util.Map;

public class Prime2 {
  public int solution(int[] nums) {
    int answer = 0;
    int sum = 0;

    Map<Integer, Boolean> primeNumberCheck = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          sum = nums[i] + nums[j] + nums[k];

          // if(sum ==1 ){
          // primeNumberCheck.put(1,false);
          // }
          if (primeNumberCheck.containsKey(sum)) {
            if (primeNumberCheck.get(sum)) {

              answer++;
            }
          } else {
            if (isPrimeNumber(sum)) {
              primeNumberCheck.put(sum, true);
              System.out.println(sum);
              answer++;
            } else {
              primeNumberCheck.put(sum, false);
            }
          }

        }
      }
    }

    return answer;
  }

  public boolean isPrimeNumber(int num) {
    if (num == 1) {
      return false;
    }
    for (int i = 2; i < num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}
