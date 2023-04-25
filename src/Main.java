import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        File file = frame.pickFile();

        Map<Long, List<EmployeeInProject>> projEmps =
                Files.readAllLines(file.toPath()).stream()
                        .map(it -> it.split(", ", 4))
                        .map(it -> {
                            Long employeeId = Long.valueOf(it[0]);
                            Long projectId = Long.valueOf(it[1]);
                            LocalDate dateFrom = convertStringToLocalDate(it[2]);
                            LocalDate dateTo = it[3].equalsIgnoreCase("NULL")
                                    ? LocalDate.now()
                                    : convertStringToLocalDate(it[3]);
                            return new EmployeeInProject(employeeId, projectId, dateFrom, dateTo);
                        })
                        .collect(HashMap::new,
                                (map, emp) -> map.computeIfAbsent(emp.getProjectId(), k -> new ArrayList<>()).add(emp),
                                HashMap::putAll
                        );

        Map<String, Combination> combination = new HashMap<>();
        for (List<EmployeeInProject> emps : projEmps.values()) {
            for (int i = 0; i < emps.size() - 1; i++) {
                for (int j = i + 1; j < emps.size(); j++) {
                    EmployeeInProject emA = emps.get(i);
                    EmployeeInProject emB = emps.get(j);
                    if ((emA.getEndDate().isAfter(emB.getStartDate()) && !emA.getEndDate().isAfter(emB.getEndDate()))
                            || (emB.getEndDate().isAfter(emA.getStartDate()) && !emB.getEndDate().isAfter(emA.getEndDate()))) {
                        LocalDate D1 = emA.getStartDate().isAfter(emB.getStartDate()) ? emA.getStartDate() : emB.getStartDate();
                        LocalDate D2 = emA.getEndDate().isBefore(emB.getEndDate()) ? emA.getEndDate() : emB.getEndDate();
                        int days = (int) Math.ceil((D2.toEpochDay() - D1.toEpochDay()));
                        String key = emA.getEmployeeId() + "-" + emB.getEmployeeId();
                        combination.putIfAbsent(key, new Combination(emA.getEmployeeId(), emB.getEmployeeId()));
                        combination.get(key).addDays(days);
                    }
                }
            }
        }

        List<Combination> result = new ArrayList<>(combination.values());
        result.sort((a, b) -> b.getDays() - a.getDays());
        result.forEach(el -> System.out.println(el.toString()));
    }

    private static LocalDate convertStringToLocalDate(final String date) {
        String[] possibleDateFormats = {
                "yyyy.MM.dd G 'at' HH:mm:ss z",
                "EEE, MMM d, ''yy",
                "h:mm a",
                "hh 'o''clock' a, zzzz",
                "K:mm a, z",
                "yyyyy.MMMMM.dd GGG hh:mm aaa",
                "EEE, d MMM yyyy HH:mm:ss Z",
                "yyMMddHHmmssZ",
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "YYYY-'W'ww-u",
                "EEE, dd MMM yyyy HH:mm:ss z",
                "EEE, dd MMM yyyy HH:mm zzzz",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz",
                "yyyy-MM-dd'T'HH:mm:sszzzz",
                "yyyy-MM-dd'T'HH:mm:ss z",
                "yyyy-MM-dd'T'HH:mm:ssz",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd'T'HHmmss.SSSz",
                "yyyy-MM-dd",
                "yyyyMMdd",
                "dd/MM/yy",
                "dd/MM/yyyy"
        };
        LocalDate localDate = null;
        for (String format : possibleDateFormats) {
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
                localDate = LocalDate.parse(date, dateTimeFormatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        return localDate;
    }

}