package controller;

import com.cychang.sprinbootmall.Student;
import com.cychang.sprinbootmall.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {



    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList) {
        String sql = "INSERT INTO student(name) VALUE (:studentName)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for (int i=0; i<studentList.size(); i++){
            Student student = studentList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

        return "執行一批INSERT sql";
    }

    @PostMapping("/students")
    public String insert(@RequestBody Student student){
        String sql = "INSERT INTO student(name) VALUE (:studentName)";

        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql 自動生成的id為: " + id);

        return "執行 INSERT SQL";

    }


    @GetMapping("/students/{studentId}")
    public Student select(@PathVariable Integer studentId){

        //計算資料庫中的數據總數
        String countSql = "SELECT count(*) FROM student";
        Map<String, Object> countMap = new HashMap<>();
        Integer count = namedParameterJdbcTemplate.queryForObject(countSql, countMap, Integer.class);
        System.out.println("student table中的總數: " + count);


        String sql = "SELECT id, name FROM student WHERE id =:studentId";

        Map<String, Object>map = new HashMap<>();
        map.put("studentId", studentId);

        List<Student> list = namedParameterJdbcTemplate.query(sql,map, new StudentRowMapper());

        //把list回傳給前端
        if (list.size() > 0){
            return list.get(0);
        }   else {
            return null;
        }
    }



}

