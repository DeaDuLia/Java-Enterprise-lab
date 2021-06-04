package App.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table1Mapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Table1 table1 = new Table1();
        int a = resultSet.getInt("id");

        return table1;
    }
}
