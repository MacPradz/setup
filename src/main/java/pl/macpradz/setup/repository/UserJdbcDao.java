package pl.macpradz.setup.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pl.macpradz.setup.entity.User;

@Repository
public class UserJdbcDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    class PersonRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User().setId(rs.getLong("id")).setLogin(rs.getString("login"))
                    .setPassword(rs.getString("password")).setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"));
        }
    }

    public List<User> findAllWithCustomRowMapper() {
        return jdbcTemplate.query("select * from users", new PersonRowMapper());
    }

    public User findById(Long id) {
        return jdbcTemplate
                .queryForObject("select * from users where id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(User.class));
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from users where id=?", id);
    }

    public int insert(User user) {
        return jdbcTemplate.update("insert into users (login, password, first_name, last_name) values(?, ?, ?, ?)" ,
                user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName());
    }

    public int update(User user) {
        return jdbcTemplate.update("update users set login = ?, password = ?, first_name = ?, last_name = ? where id = ?",
                new Object[]{user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getId()});
    }

}
