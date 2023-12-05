package mapper;

import model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {

//        Integer a = resultSet.getInt("id");
//        String b = resultSet.getString("name"); //取得資料庫的數據
//
//        //將數據轉為Java Object
//        Student student = new Student();
//        student.setId(a);
//        student.setName(b);

        //這種寫法跟上面功能一樣，但較簡潔
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        return student; //完成rowmapper實作

    }
}
