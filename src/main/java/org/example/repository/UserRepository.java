package org.example.repository;

import org.example.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository {
    private final JdbcTemplate template;
    private final RowMapper<User> rowMapper = (rs, i) -> new User(
            rs.getString("user_name"),
            rs.getString("password")
    );

    public UserRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public boolean isUserPresent(String userName, String password) {
        return template.query("SELECT user_name, password FROM users WHERE user_name = ? AND password = ?", rowMapper,
                userName, password).size() == 1;
    }
}
