package com.rangel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rangel.dao.FanDao;
import com.rangel.model.Fan;

public class FanDaoTest {

    private FanDao fanDao;

    public FanDaoTest() {
        this.fanDao = new FanDao();
    }

    @BeforeEach
    @AfterEach
    public void init() {
        this.clearFansData();
    }

    @Test
    @DisplayName("19 - Must Save a Fan.")
    public void mustSave() {
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));

        List<Fan> result = this.fanDao.list();

        assertEquals(5, result.size());
        assertEquals("Fan A", result.get(0).getName());
    }

    @Test
    @DisplayName("20 - Must List a Fan.")
    public void mustList() {
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));
        this.fanDao.save(this.createFan("Fan A"));

        List<Fan> result = this.fanDao.list();

        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("21 - Must Edit a Fan.")
    public void mustEdit() {
        this.fanDao.save(this.createFan("Fan A"));

        Fan fan = this.fanDao.list().get(0);

        fan.setName("Fan B");

        this.fanDao.edit(fan);

        // VERIFICATION OF EXPECTED RESULT
        List<Fan> resultList = this.fanDao.list();

        assertEquals(1, resultList.size());
        assertEquals("Fan B", resultList.get(0).getName());
    }

    @Test
    @DisplayName("22 - Must Delete a Fan.")
    public void mustDelete() {
        this.fanDao.save(this.createFan("Fan A"));

        Fan fan = this.fanDao.list().get(0);

        this.fanDao.delete(fan.getId());

        // VERIFICATION OF EXPECTED RESULT
        List<Fan> resultList = this.fanDao.list();

        assertEquals(0, resultList.size());
    }

    private void clearFansData() {
        List<Fan> fans = this.fanDao.list();

        for (Fan player : fans) {
            this.fanDao.delete(player.getId());
        }
    }

    public Fan createFan(String name) {
        Fan fan = new Fan();
        fan.setName(name);
        return fan;
    }
}
