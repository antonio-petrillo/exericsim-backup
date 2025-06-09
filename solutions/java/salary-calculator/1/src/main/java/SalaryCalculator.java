public class SalaryCalculator {
    public double multiplierPerDaysSkipped(int daysSkipped) {
        return daysSkipped > 5 ? 0.85d : 1d;
    }

    public int multiplierPerProductsSold(int productsSold) {
        return productsSold > 20 ? 13 : 10;
    }

    public double bonusForProductSold(int productsSold) {
        return productsSold * multiplierPerProductsSold(productsSold);
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        double salary = 1000d * multiplierPerDaysSkipped(daysSkipped) + bonusForProductSold(productsSold);
        return salary > 2000d ? 2000d : salary;
    }
}
