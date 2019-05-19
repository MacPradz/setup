package pl.macpradz.setup;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.macpradz.setup.entity.Spending;
import pl.macpradz.setup.entity.User;
import pl.macpradz.setup.model.SpendingCategory;
import pl.macpradz.setup.repository.MonthlyReportRepository;
import pl.macpradz.setup.repository.SpendingJpaRepository;
import pl.macpradz.setup.repository.UserJdbcDao;

@SpringBootApplication
public class SetupApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private UserJdbcDao userJdbcDao;
	private SpendingJpaRepository spendingJpaRepository;
	private MonthlyReportRepository monthlyReportRepository;

	@Autowired
    public SetupApplication(UserJdbcDao userJdbcDao, SpendingJpaRepository spendingJpaRepository,
            MonthlyReportRepository monthlyReportRepository) {
        this.userJdbcDao = userJdbcDao;
        this.spendingJpaRepository = spendingJpaRepository;
        this.monthlyReportRepository = monthlyReportRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SetupApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users -> {}", userJdbcDao.findAll());
        logger.info("All users with custom row mapper -> {}", userJdbcDao.findAllWithCustomRowMapper());
        logger.info("User with id 1L -> {}", userJdbcDao.findById(1L));
        logger.info("Delete user with id 2L. Rows removed -> {}", userJdbcDao.deleteById(2L));

        User user2 = new User().setFirstName("Jack").setLastName("Strong").setLogin("JS").setPassword("bla");
        logger.info("Inserted new user -> {}", userJdbcDao.insert(user2));

        User adminUpdated =
                new User().setFirstName("szef").setLastName("szefów").setLogin("admin").setPassword("bla").setId(1L);
        logger.info("Updated user -> {}", userJdbcDao.update(adminUpdated));

        Spending insert = spendingJpaRepository.insert(new Spending().setValue(BigDecimal.ONE).setCategory(SpendingCategory.CAR).setEventDate(new Date()));
        logger.info("Spending added -> {}", spendingJpaRepository.insert(insert));
        logger.info("all Spendings -> {}", spendingJpaRepository.findAll());
        logger.info("Spending updated -> {}", spendingJpaRepository.update(insert.setValue(BigDecimal.TEN)));
        logger.info("updated Spending -> {}", spendingJpaRepository.findById(insert.getId()));
        logger.info("removed new guy -> {}");
        spendingJpaRepository.deleteById(insert.getId());

        logger.info("all reports -> {}", monthlyReportRepository.findById(1L));
        monthlyReportRepository.deleteById(1L);

        monthlyReportRepository.transactionWorksBehindTheHood();
        monthlyReportRepository.useFlushDetachAndClear();
        monthlyReportRepository.useFlushRefresh();
    }
}
