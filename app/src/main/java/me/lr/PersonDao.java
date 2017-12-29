package me.lr;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Administrator on 12/27/2017.
 */

@Dao
public interface PersonDao {

    @Query("SELECT * FROM account_detail")
    List<Person> getAllPersons();

    @Insert
    void insertAll(Person... persons);

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM account_detail WHERE email LIKE :emailID LIMIT 1")
    Person findByEmail(String emailID);
}
