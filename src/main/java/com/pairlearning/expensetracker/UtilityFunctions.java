package com.pairlearning.expensetracker;

import com.pairlearning.expensetracker.domain.Category;
import com.pairlearning.expensetracker.domain.Transaction;
import com.pairlearning.expensetracker.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilityFunctions {
    public static final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(rs.getInt("USER_ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"));
    public static final RowMapper<Category> categoryRowMapper = (rs, rowNum) -> new Category(rs.getInt("CATEGORY_ID"), rs.getInt("USER_ID"), rs.getString("TITLE"), rs.getString("DESCRIPTION"), rs.getDouble("TOTAL_EXPENSE"));
    public static RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) -> new Transaction(rs.getInt("TRANSACTION_ID"), rs.getInt("CATEGORY_ID"), rs.getInt("USER_ID"), rs.getDouble("AMOUNT"), rs.getString("NOTE"), rs.getLong("TRANSACTION_DATE")));

    UtilityFunctions() {
    }

    public static Map<String, String> generateJWTToken(User user) {
        /*
         * Generates JWT Token
         * */
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY).setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY)).claim("userId", user.getUserId()).claim("email", user.getEmail()).claim("firstName", user.getFirstName()).claim("lastName", user.getLastName()).compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
