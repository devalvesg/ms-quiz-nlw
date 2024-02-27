package com.nlwJava.msquiz.seed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateSeed {

    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5434/pg_nlw");
        dataSource.setPassword("admin");
        dataSource.setUsername("admin");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);
    }

    public void run(String...args) throws IOException {
        executeSqlFile("src/main/resources/create.sql");
    }

    private void executeSqlFile(String file) throws IOException {
        try{
            String sqlScript = new String(Files.readAllBytes(Paths.get(file)));
            jdbcTemplate.execute(sqlScript);
            System.out.println("SEED REALIZADO!");
        }
        catch (Exception e){
            System.out.println("Erro no SQL: " + e.getMessage());
        }
    }
}
