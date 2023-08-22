package com.rangel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rangel.dao.DocumentDao;
import com.rangel.model.Document;

public class DocumentDaoTest {

    private DocumentDao documentDao;

    public DocumentDaoTest() {
        this.documentDao = new DocumentDao();
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

    @Test
    @DisplayName("2 - Must perform Document Listing.")
    public void mustList() {

        // SCENERY BUILDING AND ACTION CALL
        for (int i = 0; i <= 5; i++) {
            this.documentDao.save(this.createObject("544", "544554454", "787"));
        }

        // VERIFICATION OF EXPECTED RESULT
        List<Document> result = this.documentDao.list();

        assertEquals(6, result.size());
    }

    @Test
    @DisplayName("3 - Must Edit a Document.")
    public void mustEdit() {
        this.documentDao.save(this.createObject("544", "544554454", "787"));

        Document result = this.documentDao.list().get(0);

        result.setCpf("11111");

        this.documentDao.edit(result);

        // VERIFICATION OF EXPECTED RESULT
        List<Document> resultList = this.documentDao.list();

        assertEquals(1, resultList.size());
        assertEquals("544", resultList.get(0).getWorkCardNumber());
        assertEquals("11111", resultList.get(0).getCpf());
        assertEquals("787", resultList.get(0).getCbfNumber());

    }

    @Test
    @DisplayName("4 - Must Delete a Document.")
    public void mustDelete() {
        this.documentDao.save(this.createObject("544", "544554454", "787"));

        Document result = this.documentDao.list().get(0);

        this.documentDao.delete(result.getId());

        // VERIFICATION OF EXPECTED RESULT
        List<Document> resultList = this.documentDao.list();

        assertEquals(0, resultList.size());

    }

    private void clearData() {
        List<Document> objects = this.documentDao.list();

        for (Document document : objects) {
            this.documentDao.delete(document.getId());
        }
    }

    private Document createObject(String workCardNumber, String cpf, String cbfNumber) {
        Document document = new Document();
        document.setWorkCardNumber(workCardNumber);
        document.setCpf(cpf);
        document.setCbfNumber(cbfNumber);

        return document;
    }
}
