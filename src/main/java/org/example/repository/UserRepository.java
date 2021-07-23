package org.example.repository;

import org.example.domain.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate template;

    private final RowMapper<Long> longRowMapper = (rs, i) -> rs.getLong("id");
    private final RowMapper<User> rowMapper = (rs, i) -> new User(
            rs.getString("user_name"),
            rs.getString("password")
    );

    public UserRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public Optional<Long> getUserId(String userName, String password) {
        return queryForOptional("SELECT id FROM users WHERE user_name = ? AND password = ?", longRowMapper,
                userName, password);
    }

    public boolean isUserPresent(String userName, String password) {
        return template.query("SELECT user_name, password FROM users WHERE user_name = ? AND password = ?", rowMapper,
                userName, password).size() == 1;
    }

    private <T> Optional<T> queryForOptional(String sql, RowMapper<T> rowMapper, Object... args) {
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(
                sql, rowMapper, args
        )));
    }
}
