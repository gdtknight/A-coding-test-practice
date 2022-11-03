public class RaidTime {
  public int raidTime(int health, int attackPower) {
    return health % attackPower == 0
        ? health / attackPower
        : health / attackPower + 1;
  }

  public int solution(int[] reward, int[] health, int[] optional) {
    int N = reward.length;

    int attackPower = 1;
    int minTime = 0;

    for (int i = 0; i < N; i++) {
      int curRaidTime = raidTime(health[i], attackPower);
      if (optional[i] == 0) {
        minTime += curRaidTime;
        attackPower += reward[i];
      }
    }
    return minTime;
  }
}
