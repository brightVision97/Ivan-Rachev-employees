public class Combination {

    private Long employeeId1;

    private Long employeeId2;

    private int days;

    public Combination(Long employeeId1, Long employeeId2) {
        setEmployeeId1(employeeId1);
        setEmployeeId2(employeeId2);
        setDays(0);
    }

    public Long getEmployeeId1() {
        return employeeId1;
    }

    public void setEmployeeId1(Long employeeId1) {
        this.employeeId1 = employeeId1;
    }

    public Long getEmployeeId2() {
        return employeeId2;
    }

    public void setEmployeeId2(Long employeeId2) {
        this.employeeId2 = employeeId2;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void addDays(int days) {
        this.days += days;
    }

    @Override
    public String toString() {
        return employeeId1 + ", " + employeeId2 + " -> " + days;
    }
}
