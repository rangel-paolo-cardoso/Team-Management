package com.rangel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DocumentDaoTest {

    private DocumentDao documentDao;

    public DocumentDaoTest() {
        this.documentDao = new DocumentDaoTest();
    }

    @BeforeEach
    @AfterAll
    public void init() {
        this.clearData();
    }

    @Test
    @DisplayName("1 - Must save a Document.")
    public void mustSave() {
        // SCENERY BUILDING AND ACTION CALL
        this.documentDao.save(this.createObject("544", "544554454", "787"));

        // VERIFICATION OF EXPECTED RESULT
        List<Document> result = this.documentDao.list();

        assertEquals(1, result.size());
        assertEquals("544", result.get(0).getWorkCardNumber());
        assertEquals("544554454", result.get(0).getCpf());
        assertEquals("787", result.get(0).getCbfNumber());
    }

    private void clearData() {
        List<Document> objects = this.documentDao.list();

        for (Document document : objects) {
            this.documentDao.delete(document.getId());
        }
    }
}
