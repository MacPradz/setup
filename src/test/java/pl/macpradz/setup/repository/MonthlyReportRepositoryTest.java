package pl.macpradz.setup.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import pl.macpradz.setup.entity.MonthlyReport;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonthlyReportRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(MonthlyReportRepositoryTest.class);

    @Autowired
    MonthlyReportRepository repository;

    @Test
    public void findByIdBasic() {
        MonthlyReport report = repository.findById(1L);
        assertThat(report.getDescription()).isEqualTo("dużo wydatków");
    }

    @Test
    @DirtiesContext
    public void deleteByIdBasic() {
        repository.deleteById(2L);
        assertThat(repository.findById(2L)).isNull();
    }
}
