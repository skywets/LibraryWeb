package dao;

import dao.connection.ConnectionBulder;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDao implements DAO<User> {
    private ConnectionBulder connectionBulder;
    private static final String SELECT_ALL = "select id, login, password, registrydate, education_id, name, country, language, birthday from user";
    private static final String SELECT_ID = "select id, login, password, registrydate, education_id, name, country, language, birthday from user where id = ?";
    private static final String INSERT = "insert into user (login, password, registrydate, education_id, name, country, language, birthday) values(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "update education set login = ?, password = ?, registrydate = ?, education_id = ?, " +
            "name = ?, country = ?, language = ?, birthday = ? where id = ?";
    private static final String DELETE = "delete from user where id = ?";

    @Override
    public Optional<User> get(long id) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(SELECT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .educationId(resultSet.getLong("education_id"))
                        .name(resultSet.getString("name"))
                        .country(resultSet.getString("country"))
                        .language(resultSet.getString("language"))
                        .birthday(LocalDate.parse(resultSet.getString("birthday")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        final List<User> result = new ArrayList<>();
        try (Statement statement = connectionBulder.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
                while (resultSet.next()) {
                    result.add(User.builder()
                            .id(resultSet.getLong("id"))
                            .login(resultSet.getString("login"))
                            .password(resultSet.getString("password"))
                            .educationId(resultSet.getLong("education_id"))
                            .name(resultSet.getString("name"))
                            .country(resultSet.getString("country"))
                            .language(resultSet.getString("language"))
                            .birthday(LocalDate.parse(resultSet.getString("birthday")))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long create(User item) {
        long id = -1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(INSERT)) {
            int count = 1;
            preparedStatement.setString(count++, item.getLogin());
            preparedStatement.setString(count++, item.getPassword());
            preparedStatement.setLong(count++, item.getEducationId());
            preparedStatement.setString(count++, item.getName());
            preparedStatement.setString(count++, item.getCountry());
            preparedStatement.setString(count++, item.getLanguage());
            preparedStatement.setString(count++, String.valueOf(item.getBirthday()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(User item) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(UPDATE)) {
            int count = 1;
            preparedStatement.setString(count++, item.getLogin());
            preparedStatement.setString(count++, item.getPassword());
            preparedStatement.setLong(count++, item.getEducationId());
            preparedStatement.setString(count++, item.getName());
            preparedStatement.setString(count++, item.getCountry());
            preparedStatement.setString(count++, item.getLanguage());
            preparedStatement.setString(count++, String.valueOf(item.getBirthday()));
            preparedStatement.setLong(count++, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(User item) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(DELETE)) {
            preparedStatement.setLong(1, item.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + item.getId() + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
