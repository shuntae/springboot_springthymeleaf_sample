package jp.co.introduction.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jp.co.introduction.entity.FruitEntity;
import jp.co.introduction.entity.FruitRowMapper;

@Component
public class FruitDao {

  @PersistenceContext private EntityManager em;

  @Autowired private JdbcTemplate jdbcTemplate;

  public List<FruitEntity> getFruitList() {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ");
    sql.append("  ID ");
    sql.append("  , NAME ");
    sql.append("  , PRICE  ");
    sql.append("  , COMMENT  ");
    sql.append("FROM ");
    sql.append("  FRUIT ");
    List<FruitEntity> fruitList = jdbcTemplate.query(sql.toString(), new FruitRowMapper());
    return fruitList;
  }

  public boolean deleteFruit(int id) {
    StringBuilder sql = new StringBuilder();
    sql.append("DELETE ");
    sql.append("FROM ");
    sql.append("  FRUIT ");
    sql.append("WHERE ");
    sql.append("  ID = ? ");
    int resultCount = jdbcTemplate.update(sql.toString(), id);
    return resultCount == 1;
  }
}
