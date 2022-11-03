public class BlindCatch {
  // 18.0 / 20.0
  // 효율성 1케이스 떨어짐 .. 왜 ???!?!?
  public int solution(int x1, int y1, int x2, int y2) {
    int width = Math.abs(x1 - x2) + 1;
    int height = Math.abs(y1 - y2) + 1;

    boolean isEvenWidth = width % 2 == 0 ? true : false;
    boolean isEvenHeight = height % 2 == 0 ? true : false;

    int m = Math.max(width, height);

    if (isEvenWidth && isEvenHeight) {
      return (m / 2) % 2 == 0
          ? m / 2
          : m / 2 + 1;
    } else if (isEvenWidth || isEvenHeight) {
      return m % 2 == 1 && m / 2 % 2 == 0
          ? m / 2 + 1
          : m % 2 == 1 && m / 2 % 2 == 1
              ? m / 2
              : m % 2 == 0 && m / 2 % 2 == 0
                  ? m / 2 + 1
                  : m / 2;
    } else {
      return m / 2 % 2 == 0
          ? m / 2
          : m / 2 + 1;
    }
  }
}
