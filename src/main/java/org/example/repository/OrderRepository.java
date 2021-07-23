package org.example.repository;

import org.example.domain.Order;
import org.example.dto.OrderStatusResponseDto;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final JdbcTemplate template;

    private final RowMapper<Long> longRowMapper = (rs, i) -> rs.getLong("id");
    private final RowMapper<OrderStatusResponseDto> osRowMapper = (rs, i) -> new OrderStatusResponseDto(
            rs.getLong("id"),
            rs.getString("order_number"),
            rs.getInt("amount"),
            rs.getInt("currency"),
            rs.getInt("status")
    );

    public OrderRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public Optional<Long> create(Order order) {
        // language=PostgreSQL
        return queryForOptional(
                "INSERT INTO orders(user_id, order_number, amount, currency, returnurl, failurl) VALUES(?, ?, ?, ?, ?, ?) " +
                        "RETURNING id", longRowMapper, order.getUserId(), order.getOrderNumber(), order.getAmount(),
                order.getCurrency(), order.getReturnUrl(), order.getFailUrl()
        );
    }

    public Optional<OrderStatusResponseDto> getStatus(long id) {
        // language=PostgreSQL
        return queryForOptional(
                "SELECT id, order_number, amount, currency, status FROM orders WHERE id = ?",
                osRowMapper, id
        );
    }

    public boolean setStatus(long id, int code) {
        // language=PostgreSQL
        final int update = template.update(
                "UPDATE orders SET status = ? WHERE id = ?",
                code, id
        );
        return update != 0;
    }

    public boolean isUserMatchedWithOrder(long id, String userName, String password) {
        // language=PostgreSQL
        return template.query("SELECT o.id FROM orders AS o JOIN users AS u ON o.user_id = u.id" +
                        " WHERE user_name = ? AND password = ? AND o.id = ?", longRowMapper,
                userName, password, id).size() == 1;
    }

    private <T> Optional<T> queryForOptional(String sql, RowMapper<T> rowMapper, Object... args) {
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(
                sql, rowMapper, args
        )));
    }
}
