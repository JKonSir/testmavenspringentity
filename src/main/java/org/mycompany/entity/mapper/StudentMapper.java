package org.mycompany.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mycompany.entity.model.Student;
import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper
{
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));

        return student;
    }

}
