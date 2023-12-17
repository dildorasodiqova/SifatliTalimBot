package com.example.bot.repository.manager;

import com.example.bot.dto.UserCountPerDay;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Admin on 17.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.repository.manager
 * @contact @sarvargo
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryCustom {
    private final EntityManager entityManager; // Inject your entity manager here

    public List<UserCountPerDay> getUsersCountLast30Days() {
        LocalDate startDate = LocalDate.now().minus(30, ChronoUnit.DAYS);
        // Create a list of dates for the last 30 days
        List<LocalDate> dateRange = IntStream.range(0, 31)
                .mapToObj(startDate::plusDays)
                .toList();

        String queryString = "SELECT COUNT(u), DATE(u.createdDate) FROM UsersEntity u " +
                "WHERE DATE(u.createdDate) >= :startDate GROUP BY DATE(u.createdDate)";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("startDate", startDate);

        List<Object[]> results = query.getResultList();


        // Create a map of counts by date
        Map<LocalDate, Long> countsByDate = results.stream()
                .collect(Collectors.toMap(
                        result -> ((Date) result[1]).toLocalDate(),
                        result -> (Long) result[0]
                ));

        // Create a list of UserCountPerDay entries, including dates with zero counts
        return dateRange.stream()
                .map(date -> new UserCountPerDay(countsByDate.getOrDefault(date, 0L), date))
                .collect(Collectors.toList());
    }
}
