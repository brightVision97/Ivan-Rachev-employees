import java.time.LocalDate;

class EmployeeInProject {

    public EmployeeInProject(Long employeeId, Long projectId, LocalDate startDate, LocalDate endDate) {
        setEmployeeId(employeeId);
        setProjectId(projectId);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    private Long employeeId;

    private Long projectId;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
