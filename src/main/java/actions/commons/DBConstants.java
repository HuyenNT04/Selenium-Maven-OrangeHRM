package actions.commons;

public class DBConstants {
    //SQL INFOR
    public static final String URL = "jdbc:mysql://localhost:3306/orangehrm";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    //SQL query for Education
    public static final String SEARCH_BY_EDUCATION_NAME = "SELECT * FROM ohrm_education WHERE name = ?";
    public static final String INSERT_EDUCATION = "INSERT INTO ohrm_education (name) VALUES (?)";
    //public static final String insertEducationName = "INSERT INTO ohrm_education (name, description) VALUES (?,?)"; for many params
    public static final String DELETE_BY_EDUCATION_NAME = "DELETE FROM ohrm_education WHERE name = ?";
    public static final String COUNT_EDUCATION_BY_NAME = "SELECT COUNT(*) FROM ohrm_education WHERE name = ?";

    //SQL query for Language
    public static final String DELETE_BY_LANGUAGE_NAME = "DELETE FROM ohrm_language WHERE name = ?";
    public static final String SEARCH_BY_LANGUAGE_NAME = "SELECT * FROM ohrm_language WHERE name = ?";
}
