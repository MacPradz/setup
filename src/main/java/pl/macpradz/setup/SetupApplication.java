package pl.macpradz.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.macpradz.setup.entity.User;
import pl.macpradz.setup.repository.UserJdbcDao;

@SpringBootApplication
public class SetupApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserJdbcDao userJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(SetupApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users -> {}", userJdbcDao.findAll());
        logger.info("User with id 1L -> {}", userJdbcDao.findById(1L));
        logger.info("Delete user with id 2L. Rows removed -> {}", userJdbcDao.deleteById(2L));

        User user2 = new User().setFirstName("Jack").setLastName("Strong").setLogin("JS").setPassword("bla");
        logger.info("Inserted new user -> {}", userJdbcDao.insert(user2));

        User adminUpdated =
                new User().setFirstName("szef").setLastName("szefów").setLogin("admin").setPassword("bla").setId(1L);
        logger.info("Updated user -> {}", userJdbcDao.update(adminUpdated));
    }
}
