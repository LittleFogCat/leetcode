public class P595_BigCountries {
    public static void main(String[] args) {
        String select = "select name, population, area from World where area > 3000000 or population > 25000000";

        String select2 = "select name, population, area from World where area > 3000000\n" +
                "union\n" +
                "select name, population, area from World where population > 25000000";
    }

}
