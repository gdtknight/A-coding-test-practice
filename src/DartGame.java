
import java.util.ArrayList;

public class DartGame {
  public static int solution(String dartResult) {
    int answer = 0;
    int[] answerArr = new int[3];
    ArrayList<String> one = new ArrayList<>();

    int indx = 0;
    int length = dartResult.length();
    while (indx < length) {
      char cur = dartResult.charAt(indx);
      char next = dartResult.charAt(indx + 1);
      if (Character.isDigit(cur) && !Character.isDigit(next)) {
        if (indx + 2 != length) {
          char opt = dartResult.charAt(indx + 2);
          if (opt == '*' || opt == '#') {
            one.add(dartResult.substring(indx, indx + 3));
            indx += 3;
          } else {
            one.add(dartResult.substring(indx, indx + 2));
            indx += 2;
          }
        } else {
          one.add(dartResult.substring(indx, indx + 2));
          indx += 2;
        }

      } else if (Character.isDigit(cur) && Character.isDigit(next)) {
        if (indx + 3 != length) {
          char opt = dartResult.charAt(indx + 3);
          if (opt == '*' || opt == '#') {
            one.add(dartResult.substring(indx, indx + 4));
            indx += 4;
          } else {
            one.add(dartResult.substring(indx, indx + 3));
            indx += 3;
          }
        } else {
          one.add(dartResult.substring(indx, indx + 3));
          indx += 3;
        }
      }
    }

    int answers = 0;
    int calResult = 0;

    for (int i = 0; i < one.size(); i++) {
      String str = one.get(i);
      // String[] str = one.get(i).split("[SDT]");

      if (str.contains("#")) {
        String sub = str.substring(0, str.length() - 1);
        calResult = cal(sub) * (-1);
      } else if (str.contains("*")) {
        String sub = str.substring(0, str.length() - 1);
        if (i != 0) {
          answer += calResult;
        }
        calResult = cal(sub) * 2;
      } else {
        calResult = cal(str);
      }

      answers += calResult;
    }

    return answers;
  }

  public static int cal(String n) {
    int num = Integer.parseInt(n.substring(0, n.length() - 1));

    if (n.contains("D")) {
      return (int) Math.pow(num, 2);
    } else if (n.contains("T")) {
      return (int) Math.pow(num, 3);
    } else {
      return (int) Math.pow(num, 1);
    }
  }

  public static void main(String[] args) {
    String str = "1D2S3T*";
    System.out.println(solution(str));
  }
}
