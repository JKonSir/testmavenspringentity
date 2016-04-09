package org.mycompany.entity.dao.impl;

import org.mycompany.entity.dao.StudentDao;
import org.mycompany.entity.mapper.StudentMapper;
import org.mycompany.entity.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class StudentDaoImpl implements StudentDao
{
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    public void create(final String name, final Integer age)
    {
        final String SQL = "insert into Student (name, age) values (?, ?)";

        jdbcTemplateObject.update(new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
            {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);

                return preparedStatement;
            }
        });

        System.out.println("Created Record Name = " + name + " Age = " + age);
    }

    public Student getStudent(Integer id)
    {
        String SQL = "select * from Student where id = ?";
        Student student = (Student) jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StudentMapper());
        return student;
    }

    public List<Student> listStudents()
    {
        String SQL = "select * from Student";
        List<Student> students = jdbcTemplateObject.query(SQL,
                new StudentMapper());
        return students;
    }

    public void delete(final Integer id)
    {
        final String SQL = "delete from Student where id = ?";

        jdbcTemplateObject.update(new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
            {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1, id);

                return preparedStatement;
            }
        });

        System.out.println("Deleted Record with ID = " + id);
    }

    public void update(final Integer id, final Integer age)
    {
        final String SQL = "update Student set age = ? where id = ?";

        jdbcTemplateObject.update(new PreparedStatementCreator()
        {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
            {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, age);

                return preparedStatement;
            }
        });

        System.out.println("Updated Record with ID = " + id);
    }

}
