package cordell.DAO;

import cordell.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showPeople() {
        return jdbcTemplate.query("SELECT * FROM People", new PersonMapper());
    }
    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT * FROM People WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO people VALUES(DEFAULT, ?)", person.getName());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE people SET name= ? where id =?", updatedPerson.getName(), updatedPerson.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM people where id = ?", id);
    }
}
