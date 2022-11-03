import java.time.LocalDate;

public class LocalDateExample {
  public static void main(String[] args) {
    System.out.println(
        LocalDate.of(2016, 5, 24)
            .getDayOfWeek()
            .toString().substring(0, 3));
  }
}
