package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
//@NoArgsConstructor
@RequiredArgsConstructor
public class CreateDB {
    private final JdbcTemplate jdbcTemplate;

    public void createDB(){
        String query =
                "CREATE TABLE users\n" +
                "(\n" +
                "    id              SERIAL      NOT NULL primary key,\n" +
                "    name                varchar(45) NOT NULL,\n" +
                "    nick_name                varchar(45) NOT NULL,\n" +
                "    email               varchar(45) NOT NULL UNIQUE,\n" +
                "    password            varchar(45) NOT NULL,\n" +
                "    public_count        float DEFAULT NULL,\n" +
                "    subscriptions_count float DEFAULT NULL,\n" +
                "    subscribers_count   float DEFAULT NULL\n" +
                "\n" +
                ");\n" +

                "CREATE TABLE publications\n" +
                "(\n" +
                "    id          SERIAL NOT NULL primary key,\n" +
                "    user_id     int    NOT NULL\n" +
                "                    constraint publication_user_fk REFERENCES users (id)\n" +
                "                    ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    imageLink   varchar(100) DEFAULT NULL,\n" +
                "    description text,\n" +
                "    date        timestamp    DEFAULT NULL\n" +
                ");\n" +

                "CREATE TABLE comments\n" +
                "(\n" +
                "    id     SERIAL NOT NULL primary key ,\n" +
                "    user_id         int    NOT NULL\n" +
                "        constraint comment_user_fk REFERENCES users (id)\n" +
                "            ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    publication_id int    NOT NULL\n" +
                "        constraint comment_publication_fk REFERENCES publications (id)\n" +
                "            ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    text           text,\n" +
                "    date           timestamp DEFAULT NULL\n" +
                ");\n" +

                "CREATE TABLE likes\n" +
                "(\n" +
                "    id        SERIAL NOT NULL primary key ,\n" +
                "    user_id         int    NOT NULL\n" +
                "        constraint like_user_fk REFERENCES users (id)\n" +
                "        ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    publication_id int    NOT NULL\n" +
                "        constraint like_publication_fk REFERENCES publications (id)\n" +
                "            ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    date           timestamp DEFAULT NULL\n" +
                ");\n" +

                "CREATE TABLE subscriptions\n" +
                "(\n" +
                "    id     SERIAL not null primary key ,\n" +
                "    user_id               int       NOT NULL\n" +
                "        constraint subscriptions_user_fk REFERENCES users (id)\n" +
                "            ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    subscript_user_id int       NOT NULL\n" +
                "        constraint subscriptions_users_fk REFERENCES users (id)\n" +
                "            ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "    date                 timestamp NOT NULL\n" +
                ");";
        jdbcTemplate.update(query);
    }

    public void addTestDataDB(){
    String query="\n" +
            "INSERT INTO users\n" +
            "    VALUES(11, 'Argen','Argen', 'argen@gmail.com', 'qwerty', 3, 3,3);"+
            "\n" +
            " INSERT INTO users\n" +
            "    VALUES(12, 'Alex', 'Alex', 'alex@gmail.com', 'qwerty', 3, 3,3);"+
            "\n" +
            " INSERT INTO users\n" +
            "    VALUES(13, 'Brain','Brain', 'b@gmail.com', 'qwerty', 3, 3,3);"+
            "\n" +
            "insert into publications\n" +
            "values ( 11,11,'images/1.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 12,11,'images/2.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 13,11,'images/3.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 14,12,'images/4.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 15,12,'images/5.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 16,12,'images/6.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 17,13,'images/6.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 18,13,'images/6.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into publications\n" +
            "values ( 19,13,'images/6.jpeg', '',date(now()));\n" +
            "\n" +
            "insert into subscriptions\n" +
            "values (11, 11, 12, date(now()));\n" +
            "\n" +
            "insert into subscriptions\n" +
            "values (12, 11, 13, date(now()));\n" +
            "\n" +
            "insert into subscriptions\n" +
            "values (13, 12, 11, date(now()));\n" +
            "\n" +
            "insert into subscriptions\n" +
            "values (14, 12, 13, date(now()));\n" +
            "\n" +
            "insert into subscriptions\n" +
            "values (15, 13, 11, date(now()));\n" +
            "\n" +
            "insert into subscriptions\n" +
            "values (16, 13, 12, date(now()));\n" +
            "\n" +
            "insert into comments\n" +
            "values (11, 11, 11, 'Hello', date(now()));\n" +
            "\n"+
            "insert into comments\n" +
            "values (12, 12, 11, 'Hi', date(now()));\n" +
            "\n" +
            "insert into comments\n" +
            "values (13, 11, 12, 'WTF', date(now()));\n" +
            "\n" +
            "insert into comments\n" +
            "values (14, 13, 11, 'Salam', date(now()));\n" +
            "\n" +
            "insert into likes\n" +
            "values (11, 11, 12, date(now()));\n" +
            "\n" +
            "insert into likes\n" +
            "values (12, 12, 11, date(now()));\n" +
            "\n" +
            "insert into likes\n" +
            "values (13, 13, 11, date(now()));";

        jdbcTemplate.update(query);
    }
}
